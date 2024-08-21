---
layout: default
title: Code Linter
nav_order: 1
parent: Usecases
permalink: /usecases/code-linter
---

Case example: [ArchGuard Rule Linter](https://archguard.org/governance)

- Code Smell, like [SonarQube](https://www.sonarqube.org/)
- Test Code Smell
- Web API Smell
- SQL Smell, like [SQLFluff](https://sqlfluff.com)

```kotlin
class WebApiRuleVisitor(services: List<ContainerService>) : RuleVisitor(services) {
    private var resources: List<ContainerSupply>

    init {
        resources = services.flatMap {
            it.resources
        }
    }

    override fun visitor(ruleSets: Iterable<RuleSet>): List<Issue> {
        val results: MutableList<Issue> = mutableListOf()
        val context = RuleContext()

        ruleSets.forEach { ruleSet ->
            ruleSet.rules.forEach { rule ->
                val apiRule = rule as WebApiRule
                resources.map {
                    apiRule.visitResource(it, context, fun(rule: Rule, position: IssuePosition) {
                        results += Issue(
                            position,
                            ruleId = rule.key,
                            name = rule.name,
                            detail = rule.description,
                            ruleType = RuleType.HTTP_API_SMELL,
                            fullName = "${it.packageName}:${it.className}:${it.methodName}",
                            source = "${it.sourceHttpMethod} ${it.sourceUrl}"
                        )
                    })
                }
            }
        }

        return results
    }
}
```

Web API Rule Example:

```kotlin
class StartWithoutCrudRule : WebApiRule() {
    init {
        this.id = "start-without-crud"
        this.name = "StartWithoutCrudRule"
        this.key = this.javaClass.name
        this.description = "URL 不应该以 CRUD 开头。错误示例： `/api/getbook`， 正确示例： `GET /api/book`"
        this.severity = Severity.WARN
    }

    override fun visitResource(resource: ContainerSupply, context: RuleContext, callback: IssueEmit) {
        val split = resource.sourceUrl.split("/")
        split.forEach { node ->
            CRUD.forEach {
                if (node != it && node.lowercase().startsWith(it)) {
                    callback(this, IssuePosition())
                }
            }
        }
    }
}
```

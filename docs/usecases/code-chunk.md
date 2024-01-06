---
layout: default
title: Code Chunk
nav_order: 99
parent: Usecases
permalink: /usecases/code-chunk
---

Case example: [Chocolate Factory](https://github.com/unit-mesh/chocolate-factory)

```kotlin
/**
 * `CodeSplitter` 类是将代码拆分为更小块以便更好地进行语义理解和处理的解决方案。
 * 它旨在处理大型代码文件并将其拆分为可管理的部分。
 *
 * 该类提供了根据指定参数（如块大小和最大字符数）拆分代码的方法。
 * 它还允许为每个块添加注释字符串以便更好地理解语义。
 *
 * `CodeSplitter` 类是 SweepAI 代码拆分解决方案的一部分，可用于对代码文件进行块拆分。
 * 有关代码拆分的更多信息，请参阅[SweepAI代码拆分文档](https://docs.sweep.dev/blogs/chunking-2m-files)。
 *
 * 该类可在 [llama_index](https://github.com/jerryjliu/llama_index) 存储库中找到，具体位于 [pull request #7100](https://github.com/jerryjliu/llama_index/pull/7100)。
 *
 * @param comment 用于在每个块前添加的注释字符串，以便更好地理解语义。默认为"//"
 * @param chunkLines 每个块的行数。默认为40行。
 * @param maxChars 每个块的最大字符数。默认为1500个字符。
 * @param chunkLinesOverlap 块之间的重叠行数。默认为15行。
 */
class CodeSplitter(
    private val comment: String = "//",
    private val chunkLines: Int = 40,
    private val maxChars: Int = 1500,
    val chunkLinesOverlap: Int = 15,
) {
    fun split(ds: CodeDataStruct): List<Document> {
        var canonicalName = "$comment canonicalName: " + ds.Package + "." + ds.NodeName
        if (ds.Content.length <= maxChars) {
            return listOf(Document(canonicalName + "\n" + ds.Content))
        }

        return ds.Functions.map {
            if (it.Name == "PrimaryConstructor") {
                return@map null
            }
            canonicalName += "." + it.Name
            split(it, canonicalName)
        }.filterNotNull()
    }

    @TestOnly
    fun split(it: CodeFunction, canonicalName: String): Document {
        var content = it.Content
        val lines = content.split("\n")
        if (lines.size > chunkLines) {
            content = lines.subList(0, chunkLines).joinToString("\n")
        }

        content = canonicalName + "\n" + content
        if (content.length > maxChars) {
            content = content.substring(0, maxChars)
        }

        return Document(content)
    }
}
```  

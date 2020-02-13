all: test

build:


test:
	./gradlew test

coverage:
	./gradlew check jacocoTestReport jacocoRootReports coveralls

changelog:
	conventional-changelog -p angular -i CHANGELOG.md -s -r 0


# Mobiquity Packer
Mobiquity Packer

## Implementation 
* To increase readability and modularity, I implemented class-specific tests.
* Single Responsibility Principle applied to write clean unit tests.

## Usage
* Clone the repository, move to the project directory.
* ```mvn clean install``` command will remove the previously created jar/war/zip files and compiled classes (clean) and execute all the phases necessary to install new archive (install) and run unit tests.

## Structure
```
src
├─ main
│   └── java
│       └── com.mobiquity
│           ├── exception
│           │   ├── APIException.java
│           │   └── Messages.java
│           ├── model
│           │   └── Container.java
│           │   └── Item.java
│           ├── packer
│           │   └── Packer.java
│           ├── parser
│           │   ├── FileParser.java
│           │   ├── ItemParser.java
│           │   └── LineParser.java
│           │   └── Parser.java
│           └── resolver
│               └── Resolver.java
└─ test
    └── java
        └── com.mobiquity
            ├── model
            │   └── ItemTest.java
            ├── packer
            │   └── PackerTest.java
            ├── parser
            │   ├── FileParserTest.java
            │   ├── ItemParserTest.java
            │   └── LineParserTest.java
            │   └── ParserTest.java
            └── resolver
                └── SolverTest.java
```

## Reference
* https://www.vogella.com/tutorials/JUnit/article.html
* https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60
* https://www.baeldung.com
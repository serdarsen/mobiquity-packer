# Mobiquity Packer
Mobiquity Packer is an implementation of my interview assignment given by Mobiquity. The Application calculates to pack the items with the highest cost and the least weight within the capacity, max item size, and cost. You can find detailed information in [Packaging Challenge.pdf](./Packaging Challenge.pdf). 

## Implementation
* To be able to analyze in detail, I broke down the problem into smaller pieces.
    * Reading the lines in the file. 
    * Transforming the info into the models by parsing each line. 
    * Calculating the items to pack using an efficient algorithm.
<br/><br/>
* I handled the file, line, and item parse operations individually to have a separation of concerns.
* I applied the Clean Code Naming Conventions and the Single Responsibility Principle to write clean tests.
* To increase readability and modularity, I implemented class-specific tests.
<br/><br/>
*  We have the Recursion, Memoization, and Tabulation algorithms of the Dynamic Programming when calculating the items to pack.
    *  I avoided the Recursion algorithm because of its inefficiency. Big O(2^N).
    *  I avoided the Memoization algorithm because of its tendency to throw a StackOverflowError. Big O(N) time, O(N) space.
    *  I implemented the Tabulation algorithm because of its efficiency. Big O(N) time, O(1) space.

## Usage
* I built the project with Java 11 and Maven, used JUnit 5 testing framework.
* Clone the repository, move to the project directory.
* ```mvn clean install``` command will remove the previously created jar/war/zip files and compiled classes and execute all the phases necessary to install a new archive and run tests.

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
│           │   ├── Container.java
│           │   └── Item.java
│           ├── packer
│           │   └── Packer.java
│           ├── parser
│           │   ├── FileParser.java
│           │   ├── ItemParser.java
│           │   ├── LineParser.java
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
            │   ├── LineParserTest.java
            │   └── ParserTest.java
            └── resolver
                └── ResolverTest.java
```

## Reference
* https://www.vogella.com/tutorials/JUnit/article.html
* https://jarednielsen.com/big-o-notation/
* https://www.educative.io/courses/grokking-dynamic-programming-patterns-for-coding-interviews/RM1BDv71V60
* https://www.baeldung.com

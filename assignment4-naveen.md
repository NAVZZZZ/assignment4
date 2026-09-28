TASK 1.

1.
mvn archetype:generate \
  -DgroupId=com.meridian.billing \
  -DartifactId=invoice-service \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.5 \
  -DinteractiveMode=false


ls
invoice-service
[ec2-user@mymachine assignment4]$ tree
.
└── invoice-service
    ├── pom.xml
    └── src
        ├── main
        │   └── java
        │       └── com
        │           └── meridian
        │               └── billing
        │                   └── App.java
        └── test
            └── java
                └── com
                    └── meridian
                        └── billing
                            └── AppTest.java

12 directories, 3 files

======

2.
src/main/java = It contains main application java source code and logic app.java
src/main/resources= It contains config files and property files
src/test/java= It contains test source code and junit test cases
pom.xml= It contains all the dependencies, versions, downloaded files and property details


3.mvn compile 

tree
.
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── meridian
│   │               └── billing
│   │                   └── App.java
│   └── test
│       └── java
│           └── com
│               └── meridian
│                   └── billing
│                       └── AppTest.java
└── target
    ├── classes
    │   └── com
    │       └── meridian
    │           └── billing
    │               └── App.class
    ├── generated-sources
    │   └── annotations
    └── maven-status
        └── maven-compiler-plugin
            └── compile
                └── default-compile
                    ├── createdFiles.lst
                    └── inputFiles.lst

22 directories, 6 files

4.
mvn test

 T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.meridian.billing.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.083 s -- in com.meridian.billing.AppTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.908 s
[INFO] Finished at: 2026-09-23T12:51:24Z


Reports:
-------
surefire-reports
    │   ├── TEST-com.meridian.billing.AppTest.xml
    │   └── com.meridian.billing.AppTest.txt


5.
mvn package 

The artifact created was target/invoice-service-1.0-SNAPSHOT.jar

-SNAPSHOT means its a development version

6. mvn clean

.
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── meridian
    │               └── billing
    │                   └── App.java
    └── test
        └── java
            └── com
                └── meridian
                    └── billing
                        └── AppTest.java

 The entire target folder was removed

7. mvn install

 /home/ec2-user/.m2/repository/com/meridian/billing/invoice-service/1.0-SNAPSHOT/invoice-service-1.0-SNAPSHOT.pom

/home/ec2-user/.m2/repository/com/meridian/billing/invoice-service/1.0-SNAPSHOT/invoice-service-1.0-SNAPSHOT.jar

It uses the groupId,artifactId and version converts those coordinates into a directory structure in local repo.


8.

First build:
------------
mvn clean then did time mvn install

real    0m4.875s
user    0m8.607s
sys     0m0.391s
 
Second build:
------------

real    0m6.294s
user    0m7.260s
sys     0m0.428s

A mvn package does compile and test because it follows a linear path so it makes sure it does both before package.

===============================================================

TASK2.

1.
Added to pom.xml

 <dependencies>
    <dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.14.0</version>
    </dependency>
  </dependencies> 


3.mvn dependency:tree

[INFO] com.meridian.billing:invoice-service:jar:1.0-SNAPSHOT
[INFO] +- org.junit.jupiter:junit-jupiter-api:jar:5.11.0:test
[INFO] |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO] |  +- org.junit.platform:junit-platform-commons:jar:1.11.0:test
[INFO] |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO] +- org.junit.jupiter:junit-jupiter-params:jar:5.11.0:test
[INFO] \- org.apache.commons:commons-lang3:jar:3.14.0:compile

we have declared 3.

4.
[INFO] com.meridian.billing:invoice-service:jar:1.0-SNAPSHOT
[INFO] +- org.junit.jupiter:junit-jupiter-api:jar:5.11.0:test
[INFO] |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO] |  +- org.junit.platform:junit-platform-commons:jar:1.11.0:test
[INFO] |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO] +- org.junit.jupiter:junit-jupiter-params:jar:5.11.0:test
[INFO] +- org.apache.commons:commons-lang3:jar:3.14.0:compile
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.17.2:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.17.2:compile
[INFO] \- com.fasterxml.jackson.core:jackson-core:jar:2.15.4:compile

5.
jackson-core 2.15.4 was explicitly in pom.xml and jackson-core 2.17.2 was needed by databind as dependency maven chooses jackson-core 2.15.4 since project's direct declaration takes precedence.

6.

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.2</version>

    <exclusions>
        <exclusion>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.15.4</version>
</dependency>

[INFO] com.meridian.billing:invoice-service:jar:1.0-SNAPSHOT
[INFO] +- org.junit.jupiter:junit-jupiter-api:jar:5.11.0:test
[INFO] |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO] |  +- org.junit.platform:junit-platform-commons:jar:1.11.0:test
[INFO] |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO] +- org.junit.jupiter:junit-jupiter-params:jar:5.11.0:test
[INFO] +- org.apache.commons:commons-lang3:jar:3.14.0:compile
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.17.2:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.17.2:compile
[INFO] \- com.fasterxml.jackson.core:jackson-core:jar:2.15.4:compile


7.

<junit.version>5.11.0</junit.version>
<commons-lang3.version>3.14.0</commons-lang3.version>
<jackson-databind.version>2.17.2</jackson-databind.version>
<jackson-core.version>2.15.4</jackson-core.version>

mvn clean test was successful

 BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.110 s
[INFO] Finished at: 2026-09-23T13:48:40Z


8.
mvn dependency:analyze

[WARNING] The artifact xml-apis:xml-apis:jar:2.0.2 has been relocated to xml-apis:xml-apis:jar:1.0.b2
Unable to process: com.meridian.billing.App
[WARNING] Unused declared dependencies found:
[WARNING]    org.junit.jupiter:junit-jupiter-params:jar:5.11.0:test
[WARNING]    org.apache.commons:commons-lang3:jar:3.14.0:compile
[WARNING]    com.fasterxml.jackson.core:jackson-databind:jar:2.17.2:compile
[WARNING]    com.fasterxml.jackson.core:jackson-core:jar:2.15.4:compile



TASK 3.

2.
 java -jar target/invoice-service-1.0-SNAPSHOT.jar
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/commons/lang3/StringUtils
        at com.meridian.billing.App.main(App.java:13)
Caused by: java.lang.ClassNotFoundException: org.apache.commons.lang3.StringUtils
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:526)
        ... 1 more
 
3. and 4.

Configured the compiler plugin explicitly, pinning its version and setting the Java release level.
Configured the build to produce an executable artifact that includes its dependencies. Record which plugin you used and which goal it binds to

<plugin>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.13.0</version>
  <configuration>
    <release>${maven.compiler.release}</release>
  </configuration>
</plugin>

<maven.compiler.release>17</maven.compiler.release>

java.lang.NoClassDefFoundError: org/apache/commons/lang3/StringUtils

Added
-----

<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-shade-plugin</artifactId>
  <version>3.6.0</version>
  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>shade</goal>
      </goals>
    </execution>
  </executions>
</plugin>


5.
java -jar target/invoice-service-1.0-SNAPSHOT.jar
Customer name: Naveen
Is name empty? false

6.

The plain/original JAR is only about 3.6 KB because it contains mainly your own compiled application
The executable/shaded JAR is 2.8 MB because the Shade Plugin packages your application together with the dependency classes required at runtime.

8.
mvn clean package -DskipTests

It will compile the code and compile the tests but wont run the test while creating package.

 --- maven-compiler-plugin:3.13.0:compile (default-compile) @ invoice-service ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 1 source file with javac [debug release 17] to target/classes
[INFO]
[INFO] --- maven-resources-plugin:3.3.1:testResources (default-testResources) @ invoice-service ---
[INFO] skip non existing resourceDirectory /home/ec2-user/assignment4/invoice-service/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.13.0:testCompile (default-testCompile) @ invoice-service ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 1 source file with javac [debug release 17] to target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:3.3.0:test (default-test) @ invoice-service ---
[INFO] Tests are skipped.


mvn clean package -Dmaven.test.skip=true

It will compile the code but wont compile the tests and wont run the test while creating package.

--- maven-resources-plugin:3.3.1:testResources (default-testResources) @ invoice-service ---
[INFO] Not copying test resources
[INFO]
[INFO] --- maven-compiler-plugin:3.13.0:testCompile (default-testCompile) @ invoice-service ---
[INFO] Not compiling test sources
[INFO]
[INFO] --- maven-surefire-plugin:3.3.0:test (default-test) @ invoice-service ---
[INFO] Tests are skipped.
[INFO]


TASK 4:A

1.Created a parent project named billing-parent with packaging pom, containing two modules: billing-core (a JAR) and billing-web (a WAR).

 tree
.
├── billing-core
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       └── test
│           └── java
├── billing-web
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           └── webapp


2.
They inherited group id com.meridian.billing and version 1.0-SNAPSHOT


5.
 Reactor Summary for billing-parent 1.0-SNAPSHOT:
[INFO]
[INFO] billing-parent ..................................... SUCCESS [  0.192 s]
[INFO] billing-core ....................................... SUCCESS [  1.024 s]
[INFO] billing-web ........................................ SUCCESS [  1.146 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.490 s
[INFO] Finished at: 2026-09-24T05:59:45Z


7.
mvn clean package -pl billing-web -am

Reactor Summary for billing-parent 1.0-SNAPSHOT:
[INFO]
[INFO] billing-parent ..................................... SUCCESS [  0.168 s]
[INFO] billing-core ....................................... SUCCESS [  1.093 s]
[INFO] billing-web ........................................ SUCCESS [  0.756 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.185 s
[INFO] Finished at: 2026-09-24T06:01:42Z



TASK 4:B

2.
mvn clean package -Pdev  

 cat billing-web/target/classes/environment.properties
environment=development


mvn clean package -Pprod

 cat billing-web/target/classes/environment.properties
environment=production


TASK 4:C

1.
mvn clean package -B

2.
Install puts the artifact to the local reposiroty ~/.m2/repository and deploy puts the artifact to remote repository like nexus or any other repos and we use deploy in CI pipeline

3.
I added target to .gitignore file and commited it
git status
On branch master
nothing to commit, working tree clean

4.
Failed Test
----------
mvn clean package -B

 Results:
[INFO]
[ERROR] Failures:
[ERROR]   CoreTest.deliberatelyFail:11 expected: <10> but was: <20>
[INFO]
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0

The JUnit test failed because the expected value was 10, but the actual value was 20

missing dependency version
------------------------

 mvn clean package -B
[INFO] Scanning for projects...
[ERROR] [ERROR] Some problems were encountered while processing the POMs:
[ERROR] 'dependencies.dependency.version' for jakarta.servlet:jakarta.servlet-api:jar is missing. @ line 27, column 21
 @
[ERROR] The build could not read 1 project -> [

[ERROR]   The project com.meridian.billing:billing-web:1.0-SNAPSHOT (/home/ec2-user/assignment4/billing-parent/billing-web/pom.xml) has 1 error
[ERROR]     'dependencies.dependency.version' for jakarta.servlet:jakarta.servlet-api:jar is missing. @ line 27, column 21
[ERROR]

Maven build failed because it was missing the version jakarta.servlet:jakarta.servlet-api:jar

unpinned plugin
---------------

 BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.876 s
[INFO] Finished at: 2026-09-24T07:01:01Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-war-plugin:2.2:war (default-war) on project billing-web: Execution default-war of goal org.apache.maven.plugins:maven-war-plugin:2.2:war failed: Unable to load the mojo 'war' in the plugin 'org.apache.maven.plugins:maven-war-plugin:2.2' due to an API incompatibility: org.codehaus.plexus.component.repository.exception.ComponentLookupException: Cannot access defaults field of Properties
[ERROR] -----------------------------------------------------
[ERROR] realm =    plugin>org.apache.maven.plugins:maven-war-plugin:2.2
[ERROR] strategy = org.codehaus.plexus.classworlds.strategy.SelfFirstStrategy
[ERROR] urls[0] = file:/home/ec2-user/.m2/repository/org/apache/maven/plugins/maven-war-plugin/2.2/maven-war-plugin-2.2.jar
[ERROR] urls[1] = file:/home/ec2-user/.m2/repository/org/apache/maven/reporting/maven-reporting-api/2.0.6/maven-reporting-api-2.0.6.jar
[ERROR] urls[2] = file:/home/ec2-user/.m2/repository/org/apache/maven/doxia/doxia-sink-api/1.0-alpha-7/doxia-sink-api-1.0-alpha-7.jar
[ERROR] urls[3] = file:/home/ec2-user/.m2/repository/commons-cli/commons-cli/1.0/commons-cli-1.0.jar
[ERROR] urls[4] = file:/home/ec2-user/.m2/repository/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-4/plexus-interactivity-api-1.0-alpha-4.jar
[ERROR] urls[5] = file:/home/ec2-user/.m2/repository/org/apache/maven/maven-archiver/2.5/maven-archiver-2.5.jar
[ERROR] urls[6] = file:/home/ec2-user/.m2/repository/org/codehaus/plexus/plexus-io/2.0.2/plexus-io-2.0.2.jar
[ERROR] urls[7] = file:/home/ec2-user/.m2/repository/org/codehaus/plexus/plexus-archiver/2.1/plexus-archiver-2.1.jar
[ERROR] urls[8] = file:/home/ec2-user/.m2/repository/org/codehaus/plexus/plexus-interpolation/1.15/plexus-interpolation-1.15.jar
[ERROR] urls[9] = file:/home/ec2-user/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar
[ERROR] urls[10] = file:/home/ec2-user/.m2/repository/com/thoughtworks/xstream/xstream/1.3.1/xstream-1.3.1.jar
[ERROR] urls[11] = file:/home/ec2-user/.m2/repository/xpp3/xpp3_min/1.1.4c/xpp3_min-1.1.4c.jar
[ERROR] urls[12] = file:/home/ec2-user/.m2/repository/org/codehaus/plexus/plexus-utils/3.0/plexus-utils-3.0.jar
[ERROR] urls[13] = file:/home/ec2-user/.m2/repository/org/apache/maven/shared/maven-filtering/1.0-beta-2/maven-filtering-1.0-beta-2.jar
[ERROR] Number of foreign imports: 1
[ERROR] import: Entry[import  from realm ClassRealm[maven.api, parent: null]]
[ERROR]
[ERROR] -----------------------------------------------------
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginContainerException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <args> -rf :billing-web
[ec2-user@mymachine billing-parent]$



Failed to execute goal
org.apache.maven.plugins:maven-war-plugin:2.2:war

The WAR plugin version was not pinned, so Maven resolved the old maven-war-plugin:2.2, which is incompatible with the current Java environment.




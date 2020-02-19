# Ksoftlin
A Kotlin Ksoft API Wrapper. 

KSoftlin wraps [Ksoft](https://api.ksoft.si) endpoints. You can locate the documents [here](https://kotlin.docs.ksoft.si)

# Gradle

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
	  repositories {
		  maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```groovy
dependencies {
  implementation 'com.github.Ksoft-Si:Ksoftlin:RelaseOrCommitId'
}
```

For the latest build use

```groovy
dependencies {
  implementation 'com.github.Ksoft-Si:Ksoftlin:master-SNAPSHOT'
}
```

# Maven

Step 1. Add the JitPack repository to your build file

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.Ksoft-Si</groupId>
	    <artifactId>Ksoftlin</artifactId>
	    <version>Tag</version>
	</dependency>

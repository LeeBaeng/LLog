# LLog

LeeBaeng Log Module For android is Simple and Comport log module<br>
this module will be also support OSL(onScreenLog) soon.<br>

the OSL is useful in can not check log environment.<br>
and it can use to report some bugs.<br>

### The 1.0.3 Release notes were:
※ not provide debug aar file since 1.0.3

• Provide Ignore Tag (you can block specific tag's log)
• Remove String's Extension Methods. (Change and combine to Any type)
• add parameter printLevel for print intent info and bundle.
• fix issue : print empty additional tag when it null.

<br><br>

### The 1.0.02 Release notes were:

• support Extension Method for Kotlin(Extension for String, Any, Exception, Intent, Bundle Object)
  - just you can use like this.
  - Verbose, Debug & Etc : `"this is log!".logV()` // Just write .log#() for String & Any object.
  - Exception : `e.logEX(TAG)` // tag is optional
  - Intent : `intent.log()`
  - Bundle : `bundle.log()`

<br><br>

### The 0.01.001 beta Release notes were:<br>

• support various print levels : verbose, debug, info, warn, error, except, sys<br>
• Add App's Important log level(sys)<br>
• it can change print level in runtime.<br>
• highlight System and Exception Log by spline<br>
• support print and <intent's extra>(bundle's key and values)<br>
• Tag is not requirement field. and you can set default tag. (if send context by init() function, default tag is Application name(App:Label))<br>
• if you didn't input tag, LLog find caller class name and set to tag automatically
<br>
<br>
<br>
# How to Add
This library published by JitPack.
so you need to Add the JitPack repository to your build file.
<br>
## Step 1
Add it in your root build.gradle(Project Level) at the end of repositories: (AndroidStudio Version **Under the ArcticFox**)
```gradle
// Studio Version Under ArcticFox
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } // Add This Line
  }
}
```

if you're android studio version is **more than Arctic Fox**, add JitPack repository to setting.gradle file
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url "https://jitpack.io"} // Add This Line
    }
}
```

## Step 2
Add the dependency to build.gradle(App or Module Level)
```gradle
dependencies {
    implementation 'com.github.LeeBaeng:LLog:1.0.02'
}
```




finish! enjoy it ;)

<br>
<br>
<br>

# How to Use
> call init function and send `Context`
> this is option(not required). if you call init, App Label(Name) is use to Default Tag
```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      LLog.init(this) // init is optional
    }
```

*v1.0.02 : support kotlin extension method*
```kotlin
  "Hello world".logV()
  "Hello world".logD()
  "Hello world".logI()
  "Hello world".logW()
  "Hello world".logE()
  "Hello world".logS()
```

*you can change additional Tag.(optional)*
```kotlin
  "this is Debug Message".logD("TAG_OnCreate")
```

*Any object can call extension methods*
call obj.toString(). if you want customizing print about object, override toString() method that class!
```kotlin
  intent.logD()
  Fragment().logI() 
  savedInstanceState.logW()
```   

*Exception can call extension methods*
```kotlin
try{
    throw RuntimeException()
}catch (e: Exception){
    e.logEX()
    // you can set additional error message and tag(optional)
    e.logEX("occur error while onCreate", "TAG_OnCreate") 
}
```
and you can call static method. just like this
```kotlin
  LLog.verbose("Hello World verbose")
  LLog.verbose("Hello World verbose set additional tag", "LLog Test") // you can change additional Tag. additional Tag is optional
  LLog.debug("Hello World debug")
  LLog.info("Hello World info")
  LLog.warn("Hello World warn")
  LLog.err("Hello World err")
  LLog.sys("Hello World sys")
  try{
      throw RuntimeException()
  }catch (e: Exception){
      LLog.except(e, "occur error while onCreate")
  }
```

> print log sample
![logcat](https://user-images.githubusercontent.com/100067569/155054161-412e0dd8-0723-4501-81ff-db30ca0b6d38.png)

<br>
<br>
<br>

## Licence
```js
Copyright 2022 LeeBaeng

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

<br>
<br>
<br>



<br>
<br>
<br>

![LeeBaeng's GitHub stats](https://github-readme-stats.vercel.app/api?username=LeeBaeng&show_icons=true&theme=radical)

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fleebaeng%2Fllog&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

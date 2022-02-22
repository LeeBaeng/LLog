# LLog
LeeBaeng Log Module For android is Simple and Comport log module<br>
this module will be also support OSL(onScreenLog) soon.<br>

the OSL is useful in can not check log environment.<br>
and it can use to report some bugs.<br>

The 0.01.001 beta Release notes were:<br>

• support various print levels : verbose, debug, info, warn, error, except, sys<br>
• Add App's Important log level(sys)<br>
• it can change print level in runtime.<br>
• highlight System and Exception Log by spline<br>
• support print and <intent's extra>(bundle's key and values)<br>
• Tag is not requirement field. and you can set default tag. (if send context by init() function, default tag is Application name(App:Label))<br>
• if you didn't input tag, LLog find caller class name and set to tag automatically<br>

This library published by JitPack.
so you need to Add the JitPack repository to your build file.

Add it in your root build.gradle(Project Level) at the end of repositories:
```
// Studio Version Under ArcticFox
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } // Add This Line
  }
}
```

if you're android studio version is over the Arctic Fox, add JitPack repository to setting.gradle file
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url "https://jitpack.io"} // Add This Line
    }
}
```

Add the dependency to build.gradle(App or Module Level)
```
dependencies {
    implementation 'com.github.LeeBaeng:LLog:publish_0.01.001beta_20220221'
}
```

finish! enjoy it ;)

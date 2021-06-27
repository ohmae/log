# Log
[![license](https://img.shields.io/github/license/ohmae/Log.svg)](./LICENSE)
[![GitHub release](https://img.shields.io/github/release/ohmae/Log.svg)](https://github.com/ohmae/Log/releases)
[![GitHub issues](https://img.shields.io/github/issues/ohmae/Log.svg)](https://github.com/ohmae/Log/issues)
[![GitHub closed issues](https://img.shields.io/github/issues-closed/ohmae/Log.svg)](https://github.com/ohmae/Log/issues?q=is%3Aissue+is%3Aclosed)
[![Maven Central](https://img.shields.io/maven-central/v/net.mm2d.log/log)](https://search.maven.org/artifact/net.mm2d.log/log)

This is a simple log print utils, like [android.util.Log](https://developer.android.com/reference/android/util/Log.html).

## log

The "log" module is written in kotlin and can be used anywhere in Java / Kotlin projects.
- API Document [dokka](https://ohmae.github.io/log/log/log/)

## log-android

The "log-android" module is a utility for using the "log" module on Android.
- API Document [dokka](https://ohmae.github.io/log/android/log-android/)

## Requirements

- log
  - Java SE 7 or later

- log-android
  - Android API Level 14 (Android 4.0.0) or later

And I strongly recommend to use with Lambda. (Kotlin, Java8, desugar, retrolambda, etc.)

## Example of use

### Initialize

This library needs to set Log Level. Default is never output log.
And needs to specify a Sender. Default does nothing.

If you use the default implementation, write as follows.

```kotlin
private val DEBUG = true
private val VERBOSE = true
...
if (DEBUG) {
    Logger.setLogLevel(Logger.VERBOSE)
    Logger.setSender(Senders.create())
    Senders.appendCaller(VERBOSE)
    Senders.appendThread(VERBOSE)
}
```

#### for Android

If you write it like this, you can output log to Logcat. (default is System.out)
```kotlin
private val VERBOSE = true
...
if (BuildConfig.DEBUG) {
    Logger.setLogLevel(Logger.VERBOSE)
    Logger.setSender(AndroidSenders.create())
    AndroidSenders.appendCaller(VERBOSE)
    AndroidSenders.appendThread(VERBOSE)
}
```

#### Custom sender

Of course you can implement Sender any way.
e.g. write to a file or send it to the network, etc.

```kotlin
Logger.setSender {level, message, throwable ->
    ...
}
```

If you want to use same format as the default, please try the following:

```kotlin
Logger.setSender(DefaultSender.create { level, tag, message ->
    ...
})
```

#### Log Level

If you set the log level, logs that are equal to or higher than the set value are output.

ASSERT > ERROR > WARN > INFO > DEBUG > VERBOSE

eg. If you set Log.ERROR

```kotlin
Logger.setLogLevel(Log.ERROR)
```

Only ASSERT or ERROR level logs are output.

### logging

```kotlin
Logger.v("verbose")
Logger.d("debug")
Logger.i("info")
Logger.w("warning")
Logger.e("error")
```

#### Output

```kotlin
Logger.setLogLevel(Logger.VERBOSE)
Logger.setSender(Senders.create())
```
```
2018-02-24 05:02:05.510 V [MainWindow] verbose
2018-02-24 05:02:05.510 D [MainWindow] debug
2018-02-24 05:02:05.510 I [MainWindow] info
2018-02-24 05:02:05.510 W [MainWindow] warning
2018-02-24 05:02:05.510 E [MainWindow] error
```

```kotlin
Logger.setLogLevel(Logger.VERBOSE)
Logger.setSender(Senders.create())
Senders.appendCaller(true)
Senders.appendThread(true)
```
```
2018-02-24 05:02:46.541 V [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:57) : verbose
2018-02-24 05:02:46.542 D [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:58) : debug
2018-02-24 05:02:46.542 I [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:59) : info
2018-02-24 05:02:46.542 W [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:60) : warning
2018-02-24 05:02:46.542 E [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:61) : error
```

## How to use

jCenter will close in May. In 0.9.4 moved to mavenCentral from jcenter.  
Please note that the **groupID has changed**

Download from mavenCentral. ![Maven Central](https://img.shields.io/maven-central/v/net.mm2d.log/log)

Add dependencies, as following.
```gradle
dependencies {
    implementation("net.mm2d.log:log:0.9.4")
}
```

If use with Android utils, as following.
```gradle
dependencies {
    implementation("net.mm2d.log:log:0.9.4")
    implementation("net.mm2d.log:log-android:0.9.4")
}
```

Versions below 0.9.4 were distributed with jCenter.
However, jCenter will close and old versions are not migrated to mavenCentral.
If you need an older version, please use the Github Pages repository.

```gradle
repositories {
    maven { url = URI("https://ohmae.github.com/maven") }
}
```

```gradle
dependencies {
    implementation("net.mm2d:log:0.9.3")
    implementation("net.mm2d:log-android:0.9.3")
}
```

## Dependent OSS

- [Kotlin](https://kotlinlang.org/)
  - org.jetbrains.kotlin:kotlin-stdlib

## Author
大前 良介 (OHMAE Ryosuke)
http://www.mm2d.net/

## License
[MIT License](./LICENSE)

# Log
[![license](https://img.shields.io/github/license/ohmae/Log.svg)](./LICENSE)
[![GitHub release](https://img.shields.io/github/release/ohmae/Log.svg)](https://github.com/ohmae/Log/releases)
[![GitHub issues](https://img.shields.io/github/issues/ohmae/Log.svg)](https://github.com/ohmae/Log/issues)
[![GitHub closed issues](https://img.shields.io/github/issues-closed/ohmae/Log.svg)](https://github.com/ohmae/Log/issues?q=is%3Aissue+is%3Aclosed)
[![Maven Repository](https://img.shields.io/badge/maven-jcenter-brightgreen.svg)](https://bintray.com/ohmae/maven/net.mm2d.log)
[![Maven metadata URI](https://img.shields.io/maven-metadata/v/https/jcenter.bintray.com/net/mm2d/log/maven-metadata.xml.svg)](https://bintray.com/ohmae/maven/net.mm2d.log)

This is a simple log print utils, like [android.util.Log](https://developer.android.com/reference/android/util/Log.html).

## log

The "log" module is written in kotlin and can be used anywhere in Java / Kotlin projects.

## log-android

The "log-android" module is a utility for using the "log" module on Android.

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

Download from jCenter.
```gradle
repositories {
    jcenter()
}
```

Add dependencies, as following.
```gradle
dependencies {
    implementation 'net.mm2d:log:0.9.0'
}
```

If use with Android utils, as following.
```gradle
dependencies {
    implementation 'net.mm2d:log:0.9.0'
    implementation 'net.mm2d:log-android:0.9.0'
}
```

## Dependent OSS

- [Kotlin](https://kotlinlang.org/)
  - org.jetbrains.kotlin:kotlin-stdlib-jdk7

## Author
大前 良介 (OHMAE Ryosuke)
http://www.mm2d.net/

## License
[MIT License](./LICENSE)

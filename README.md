# Log
[![license](https://img.shields.io/github/license/ohmae/Log.svg)](./LICENSE)
[![GitHub release](https://img.shields.io/github/release/ohmae/Log.svg)](https://github.com/ohmae/Log/releases)
[![GitHub issues](https://img.shields.io/github/issues/ohmae/Log.svg)](https://github.com/ohmae/Log/issues)
[![GitHub closed issues](https://img.shields.io/github/issues-closed/ohmae/Log.svg)](https://github.com/ohmae/Log/issues?q=is%3Aissue+is%3Aclosed)
[![Maven Repository](https://img.shields.io/badge/maven-jcenter-brightgreen.svg)](https://bintray.com/ohmae/maven/net.mm2d.log)
[![Maven metadata URI](https://img.shields.io/maven-metadata/v/https/jcenter.bintray.com/net/mm2d/log/maven-metadata.xml.svg)](https://bintray.com/ohmae/maven/net.mm2d.log)

This is a simple log print utils, like [android.util.Log](https://developer.android.com/reference/android/util/Log.html).

## log

The "log" module is written in pure java and can be used anywhere in Java projects.

## log-android

The "log-android" module is a utility for using the "log" module on Android.

## Requirements

- log
  - Java SE 7 or later

- log-android
  - Android API Level 14 (Android 4.0.0) or later

## Example of use

### Initialize

```java
private static final boolean DEBUG = true;
private static final boolean VERBOSE = true;
...
Log.initialize(DEBUG, VERBOSE);
```

#### for Android

If you write it like this, you can output log to Logcat. (default is System.out)
```java
Log.setInitializer(AndroidLogInitialiser.getDefault());
Log.initialize(BuildConfig.DEBUG);
```

#### configuration

```java
Log.appendCaller(false);
Log.appendThread(false);
Log.setLogLevel(Log.VERBOSE);
Log.setPrint(new Log.Print(){
    @Override
    public void println(final int level, @Nonnull final String tag, @Nonnull final String message) {
        ...
    }
});
```

#### Log Level

If you set the log level, logs that are equal to or higher than the set value are output.

ASSERT > ERROR > WARN > INFO > DEBUG > VERBOSE

eg. If you set Log.ERROR

```java
Log.setLogLevel(Log.ERROR);
```

Only ASSERT or ERROR level logs are output.

### logging

```java
Log.v("verbose");
Log.d("debug");
Log.i("info");
Log.w("warning");
Log.e("error");
```

#### Output

```java
Log.initialize(true, false);
```
```
2018-02-24 05:02:05.510 V [MainWindow] verbose
2018-02-24 05:02:05.510 D [MainWindow] debug
2018-02-24 05:02:05.510 I [MainWindow] info
2018-02-24 05:02:05.510 W [MainWindow] warning
2018-02-24 05:02:05.510 E [MainWindow] error
```

```java
Log.initialize(true, true);
```
```
2018-02-24 05:02:46.541 V [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:57) : verbose
2018-02-24 05:02:46.542 D [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:58) : debug
2018-02-24 05:02:46.542 I [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:59) : info
2018-02-24 05:02:46.542 W [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:60) : warning
2018-02-24 05:02:46.542 E [MainWindow] [main,5,main] net.mm2d.upnp.sample.MainWindow.main(MainWindow.java:61) : error
```

## How to use

You can download this library from jCenter.
```gradle
repositories {
    jcenter()
}
```

Add dependencies, as following.
```gradle
dependencies {
    ...
    implementation 'net.mm2d:log:0.0.3'
    ...
}
```

If you want to use Android utils, as following.
```gradle
dependencies {
    ...
    implementation 'net.mm2d:log:0.0.3'
    implementation 'net.mm2d:log-android:0.0.3'
    ...
}
```

## Author
大前 良介 (OHMAE Ryosuke)
http://www.mm2d.net/

## License
[MIT License](./LICENSE)

# Ovuview Service API
Android library project for integrating with [OvuView](https://play.google.com/store/apps/details?id=com.sleekbit.ovuview) Android app.

This library makes OvuView accessible as a [bound service](https://developer.android.com/guide/components/bound-services) described by an [AIDL file](https://github.com/pavelkrylsleekbit/ovuview-remote/blob/master/src/main/aidl/com/sleekbit/ovuview/remote/v1/IOvuViewService.aidl).

If you are not interested in the source code and just want to use the released/built version,
just set up the dependency in your `build.gradle`:
```
compile 'com.sleekbit.ovuview.remote:ovuview-remote:0.1'
```

There is also a [sample application](https://github.com/pavelkrylsleekbit/ovuview-client-app) utilizing this library, showing how handy service connection wrappers can be written.

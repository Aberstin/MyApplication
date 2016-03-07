# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Software\Android DEV Tools\AndroidStudio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5                     # 指定代码的压缩级别
-dontusemixedcaseclassnames      # 是否使用大小写混合
-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontpreverify                                  # 混淆时是否做预校验
-verbose                                         # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

-keepattributes SourceFile,LineNumberTable

-dontwarn org.apache.**
-dontwarn com.appsflyer.**

#四大组件不能混淆
-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application {*;}

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference
#自定义控件不要混淆
-keep public class * extends android.view.View {*;}

-keepclasseswithmembernames class * {
    native <methods>;

}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepattributes *Annotation*,EnclosingMethod
-keepclasseswithmembers class * {
	public <init>(android.content.Context, Android.util.attributeSet, int);
}

# Google Play Services library
-keep class * extends java.uti.ListResourceBundle{
	protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable{
	public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
	@com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable{
	public static final ** CREATOR;
}
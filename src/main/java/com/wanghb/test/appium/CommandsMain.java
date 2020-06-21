package com.wanghb.test.appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

/**
 * @author wanghb
 * @since 2020/6/20 10:39 上午
 */
public class CommandsMain {

    public static void main(String[] args) throws Exception {
//        File app  = new File("/Users/wanghb/appium/douyin.apk");
        File app  = new File("/Users/wanghb/AndroidStudioProjects/myapp/app/build/outputs/apk/debug/app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //安卓通过appPackage及appActivity
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.myapp");
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //you are free to set additional capabilities
        AndroidDriver<MobileElement> driver = new AndroidDriver<>(
                new URL("http://0.0.0.0:4723/wd/hub"),
                //if it needs to use locally started server
                //then the target_ip is 127.0.0.1 or 0.0.0.0
                //the default port is 4723
                capabilities);
        //Retrieve the server’s current status
        Map<String, Object> status = driver.getStatus();
        status.forEach((key, value) -> System.out.println(key + ":" + value));
        //Execute Mobile Command
        Object obj = driver.executeScript("mobile: deviceInfo");
        System.out.println(obj);
        Object obj2 = driver.executeScript("mobile: listSms");
        System.out.println(obj2);

        //Check whether the specified app is installed on the device
        boolean appInstalled = driver.isAppInstalled("com.zhihu.android");
        System.out.println(appInstalled);
        if(!appInstalled){
            //Install the given app onto the device
            driver.installApp("/Users/wanghb/AndroidStudioProjects/myapp/app/build/outputs/apk/debug/zhihu.apk");
        }
        System.out.println(driver.isAppInstalled("com.zhihu.android"));

        //Launch the app-under-test on the device
        driver.launchApp();
        //Background App
        driver.runAppInBackground(Duration.ofSeconds(10));
        //Close an app on device
        driver.closeApp();
        //Reset the currently running app for this session
        driver.resetApp();
        //Remove an app from the device
        driver.removeApp("com.example.AppName");
        //Activate the given app onto the device
        driver.activateApp("com.apple.Preferences");
        driver.activateApp("io.appium.android.apis");
        //Terminate the given app on the device
        driver.terminateApp("com.apple.Preferences");
        driver.terminateApp("io.appium.android.apis");
        //Get the given app status on the device
        driver.queryAppState("com.apple.Preferences");
        driver.queryAppState("io.appium.android.apis");
        //Get app strings
        Map<String, String> appStrings = driver.getAppStringMap("en", "/path/to/file");
        appStrings.forEach((key, value) -> System.out.println(key + ":" + value));
        //Set the content of the system clipboard
        // base64Content is Base64-encoded content
        byte[] base64Content = "fidshfodsifds".getBytes(StandardCharsets.UTF_8);
        driver.setClipboard("label", ClipboardContentType.PLAINTEXT, base64Content);
        driver.setClipboardText("happy testing");

        //Get the content of the system clipboard
        // get plaintext
        String clipboard = driver.getClipboard(ClipboardContentType.PLAINTEXT);
        System.out.println(clipboard);
        String clipboardText = driver.getClipboardText();
        System.out.println(clipboardText);


        System.out.println("success");
    }
}

package com.wanghb.test.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

/**
 * @author wanghb
 * @since 2020/6/20 10:39 上午
 */
public class AppiumMain {

    public static void main(String[] args) throws Exception {
//        File app  = new File("/Users/wanghb/appium/douyin.apk");
        File app  = new File("/Users/wanghb/AndroidStudioProjects/myapp/app/build/outputs/apk/debug/app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
//        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //安卓通过appPackage及appActivity
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.myapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //you are free to set additional capabilities
        AppiumDriver<MobileElement> driver = new AppiumDriver<>(
                new URL("http://0.0.0.0:4723/wd/hub"),
                //if it needs to use locally started server
                //then the target_ip is 127.0.0.1 or 0.0.0.0
                //the default port is 4723
                capabilities);
        MobileElement firstText = driver.findElement(By.id("textview_first"));
        System.out.println(firstText.getText());
        System.out.println(firstText.getTagName());
        System.out.println(firstText.getSize());
        MobileElement firstButton = driver.findElement(By.id("button_first"));
        firstButton.click();
        MobileElement secondtext = driver.findElement(By.id("textview_second"));
        System.out.println(secondtext.getText());
        System.out.println(secondtext.getTagName());
        System.out.println(secondtext.getSize());
        MobileElement secondButton = driver.findElement(By.id("button_second"));
        System.out.println(secondButton.getText());
        System.out.println(secondButton.getTagName());
        System.out.println(secondButton.getSize());
        System.out.println("success");
    }
}

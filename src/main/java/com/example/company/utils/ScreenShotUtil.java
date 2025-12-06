package com.example.company.utils;


import io.qameta.allure.Attachment;
import com.microsoft.playwright.Page;

public class ScreenShotUtil {

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(String name, byte[] screenshot) {
        return screenshot;
    }

    public static void takeScreenshot(Page page, String name) {
        byte[] screenshot = page.screenshot(
                new Page.ScreenshotOptions().setFullPage(true)
        );
        attachScreenshot(name, screenshot);
    }
}

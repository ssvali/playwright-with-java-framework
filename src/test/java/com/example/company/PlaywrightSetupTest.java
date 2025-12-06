package com.example.company;

import com.example.company.utils.ScreenShotUtil;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Properties;

class PlaywrightSetupTest {
    private final String env = System.getProperty("env", "dev");
    private static final String headless = System.getProperty("headless", "true");
    protected BrowserContext browserContext;
    protected Page page;

    protected static ThreadLocal<Playwright> playwright
            = ThreadLocal.withInitial(() -> {
                Playwright playwright = Playwright.create();
                playwright.selectors().setTestIdAttribute("data-test");
                return playwright;
            }
    );

    protected static ThreadLocal<Browser> browser = ThreadLocal.withInitial(() ->
            playwright.get().chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless))
                            .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
            )
    );


    String getProperties(String key) {
        Properties prop = new Properties();
        String propFile = String.format("src/test/resources/%s.properties", env);
        try {
            prop.load(new java.io.FileInputStream(propFile));
        } catch (java.io.IOException e) {
            throw new RuntimeException("Could not load properties file for environment: " + env + " (" + propFile + ")", e);
        }
        return prop.getProperty(key);
    }

    @BeforeEach
    void setUpBrowserContext() {
        browserContext = browser.get().newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void closeContext() {
        ScreenShotUtil.takeScreenshot(page, "Final Screenshot - " + this.getClass().getSimpleName());
        browserContext.close();
    }

    @AfterAll
    static void teardown() {
        browser.get().close();
        browser.remove();
        playwright.get().close();
        playwright.remove();
    }

}

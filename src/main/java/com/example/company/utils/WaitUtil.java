package com.example.company.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitUtil {
    private final Page page;
    private static final int DEFAULT_TIMEOUT = 5000; // in milliseconds

    public WaitUtil(Page page) {
        this.page = page;
    }

    public void waitForElementVisible(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(DEFAULT_TIMEOUT));
    }

    public void waitForPageLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(DEFAULT_TIMEOUT));
        waitForJavaScriptToComplete();
    }

    public void waitForJavaScriptToComplete() {
        page.waitForFunction("() => document.readyState === 'complete'", null,
                new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));

        // Wait for jQuery if present
        page.waitForFunction("() => typeof jQuery === 'undefined' || jQuery.active === 0", null,
                new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));
    }
}

package com.example.company.PageObject;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;


public class HomePageObject {
    private final Page page;

    private static final String searchInputSelector = "[placeholder='Search']";
    private static final String searchButtonSelector = "button:has-text('Search')";
    private static final String searchResultCardSelector = ".card";
    private static final String noResultsMessageSelector = "data-test=no-results";

    public HomePageObject(Page page) {
        this.page = page;
    }

    @Step("Searching for item: {itemToSearch}")
    public void search(String itemToSearch) {
        page.waitForResponse(
                response -> response.url().contains("/products/search?q=" + itemToSearch) && response.status() == 200,
                () -> {
                    page.locator(searchInputSelector).fill(itemToSearch);
                    page.locator(searchButtonSelector).click();
                }
        );
    }

    @Step("Getting search results count")
    public int getSearchResultsCount() {
        return page.locator(searchResultCardSelector).count();
    }

    @Step("Getting no results message")
    public String getNoResultsMessage() {
        if(page.locator(noResultsMessageSelector).isVisible()) {
            return page.locator(noResultsMessageSelector).innerText();
        }
        return "";
    }
}

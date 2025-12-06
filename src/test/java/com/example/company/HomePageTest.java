package com.example.company;

import com.example.company.PageObject.HomePageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.company.constants.Constants;

class HomePageTest extends PlaywrightSetupTest {

    private HomePageObject homePageObject;

    @BeforeEach
    void openHomePage() {
        page.navigate(getProperties("url"));
        homePageObject = new HomePageObject(page);
    }

    @Test
    void validateValidSearchItem()
    {
        homePageObject.search("Pliers");
        int searchCount = homePageObject.getSearchResultsCount();
        Assertions.assertTrue(searchCount > 0,"Search results should be greater than 0");
    }

    @Test
    void validateInvalidSearchItem(){
        homePageObject.search("InvalidItemXYZ");
        int noResultsMessage = homePageObject.getSearchResultsCount();
        Assertions.assertEquals(0,noResultsMessage ,"Search results should be greater than 0");
        Assertions.assertEquals(Constants.INVALID_SEARCH_ITEM_MESSAGE, homePageObject.getNoResultsMessage()," No results message should be displayed correctly");
    }
}

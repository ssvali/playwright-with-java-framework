package com.example.company;

import com.example.company.PageObject.NavBarObject;
import com.example.company.utils.WaitUtil;
import com.example.company.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginTest extends PlaywrightSetupTest {

    NavBarObject navBarObject;
    WaitUtil waitUtil;

    @BeforeEach
    void openHomePage() {
        page.navigate(getProperties("url"));
        navBarObject = new NavBarObject(page);
        waitUtil = new WaitUtil(page);
        waitUtil.waitForPageLoad();
    }

    @Test
    @DisplayName("Validate Sign In button is displayed on the navigation bar")
    void validateSignInButtonIsDisplayed() {
        Assertions.assertTrue(navBarObject.isSignInButtonDisplayed());
    }

    @Test
    @DisplayName("Validate successful login with valid credentials")
    void validateSuccessfulLogin() {
        String username = getProperties("username");
        String password = getProperties("password");
        navBarObject.clickSignInButton();
        if(!navBarObject.isUserNameDisplayed(username) || !navBarObject.isPasswordDisplayed(password)) {
            navBarObject.signIn(username, password);
        }
        Assertions.assertEquals(Constants.FIRST_USER_NAME, navBarObject.getLoggedInUserName().trim());
    }

    @Test
    @DisplayName("Validation of mandatory fields during login")
    void validationOfMandatoryFields() {
        navBarObject.clickSignInButton();
        navBarObject.signIn("", "");
        Assertions.assertEquals(Constants.EMAIL_IS_REQUIRED_MESSAGE, navBarObject.getErrorMessageForEmail());
        Assertions.assertEquals(Constants.PASSWORD_IS_REQUIRED_MESSAGE, navBarObject.getErrorMessageForPassword());
    }

    @Test
    @DisplayName("Validate login with invalid credentials")
    void validateLoginWithInvalidCredentials() {
        navBarObject.clickSignInButton();
        String invalidUsername = getProperties("invalidUserName");
        String invalidPassword = getProperties("invalidPassword");
        navBarObject.signIn(invalidUsername, invalidPassword);
        Assertions.assertEquals(Constants.INVALID_EMAIL_OR_PASSWORD, navBarObject.getLoginFailedError() );
    }

    @Test
    @DisplayName("Validate login with empty password")
    void validateLoginWithEmptyPassword() {
        navBarObject.clickSignInButton();
        String validUsername = getProperties("username");
        navBarObject.signIn(validUsername, "");
        Assertions.assertEquals(Constants.PASSWORD_IS_REQUIRED_MESSAGE, navBarObject.getErrorMessageForPassword());
    }
}

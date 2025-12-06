package com.example.company.PageObject;

import com.example.company.utils.WaitUtil;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class NavBarObject {
    private final Page page;
    private WaitUtil waitUtil;
    private String homeMenu = "nav-home";
    private String signInBtn = "data-test=nav-sign-in";
    private String userNameTxt = "email";
    private String passwordTxt = "password";
    private String loginBtn = "login-submit";
    private String userNameAfterLogin = "data-test=nav-menu";
    private String errorMessageForEmail = "email-error";
    private String errorMessageForPassword = "password-error";
    private String loginFailedError = "login-error";

    public NavBarObject(Page page) {
        this.page = page;
        waitUtil = new WaitUtil(page);
    }

    @Step("Navigating to Home Page")
    public boolean isSignInButtonDisplayed() {
        return page.locator(signInBtn).isVisible();
    }

    @Step("Checking if username is displayed")
    public boolean isUserNameDisplayed(String username) {
        return page.getByTestId(userNameTxt).innerText().equals(username);
    }

    @Step("Checking if password is displayed")
    public boolean isPasswordDisplayed(String password) {
        return page.getByTestId(passwordTxt).innerText().equals(password);
    }

    @Step("Clicking on Sign In button")
    public void clickSignInButton() {
        page.locator(signInBtn).click();
    }

    @Step("Signing in with username: {username}")
    public void signIn(String username, String password) {
        page.getByTestId(userNameTxt).fill(username);
        page.getByTestId(passwordTxt).fill(password);
        page.getByTestId(loginBtn).click();
    }

    @Step("Getting logged in user name")
    public String getLoggedInUserName() {
        waitUtil.waitForElementVisible(userNameAfterLogin);
        return page.locator(userNameAfterLogin).innerText();
    }

    @Step("Getting error message for email")
    public String getErrorMessageForEmail() {
        return page.getByTestId(errorMessageForEmail).innerText();
    }

    @Step("Getting error message for password")
    public String getErrorMessageForPassword() {
        return page.getByTestId(errorMessageForPassword).innerText();
    }

    @Step("Getting login failed error message")
    public  String getLoginFailedError() {
        return page.getByTestId(loginFailedError).innerText();
    }
}

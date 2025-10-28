package com.saucedemo;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Waits;
import utilities.uiElements.TextContainer;

public class HomePage {



    private final TextContainer productsTitle =
            new TextContainer(By.cssSelector("#header_container > div.header_secondary_container > span"));



    @Step("Verify that Home Page opened successfully")
    public boolean isLoaded() {
        try {
            Waits.waitForElementVisibility(productsTitle.getLocator(), 10);
            LogsUtils.info(" Verified: Home Page opened successfully — 'Products' title is visible.");
            return true;
        } catch (Exception e) {
            LogsUtils.error(" Home Page did not load properly — 'Products' title not visible.");
            return false;
        }
    }

    public String getTitleText() {
        return productsTitle.getText();
    }



}

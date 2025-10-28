package utilities.common.assertions;

import io.qameta.allure.Story;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import utilities.common.AllureUtils;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;


import java.io.File;
import java.lang.reflect.Method;

import static utilities.selenium.helperClasses.ScreenShotUtils.takeScreenShot;

public class CustomSoftAssert extends SoftAssert {
    public CustomSoftAssert() {
        super();
    }

    public void customAssertAll(ITestResult result) {
        try {
            super.assertAll("Custom Soft Assertion");
        } catch (AssertionError e) {
            LogsUtils.error("Custom Soft Assertion Failed: " + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);}
    }



    @Override
    @Deprecated
    public void assertAll() {
        throw new UnsupportedOperationException(
                "Direct assertAll() is disabled; use customAssertAll() instead."
        );
    }

    @Override
    @Deprecated
    public void assertAll(String message) {
        throw new UnsupportedOperationException(
                "Direct assertAll(String) is disabled; use customAssertAll() instead."
        );
    }

}

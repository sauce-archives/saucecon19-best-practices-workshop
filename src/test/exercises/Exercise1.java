package test.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class Exercise1 {
    private WebDriver driver;

    @Test
    @DisplayName("Exercise1.java - signInLink()")
    public void signInLink(TestInfo testInfo) throws MalformedURLException {
        /** In this section, we will configure our test to run on some specific browser/os combination in Sauce Labs */

        // Input your SauceLabs Credentials
        String sauceUserName = "SAUCE_USERNAME";
        String sauceAccessKey = "SAUCE_ACCESS_KEY";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //sets your user name and access key to run tests in Sauce
        capabilities.setCapability("username", sauceUserName);

        //sets your sauce labs access key
        capabilities.setCapability("accessKey", sauceAccessKey);

        //sets browser to Safari
        capabilities.setCapability("browserName", "Safari");

        //sets operating system to macOS version 10.13
        capabilities.setCapability("platform", "macOS 10.13");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "11.1");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", testInfo.getDisplayName());

        //instantiates a remote WebDriver object with your desired capabilities
        driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com:443/wd/hub"), capabilities);

        //navigate to the url of the Sauce Labs Sample app
        driver.navigate().to("https://www.saucedemo.com");

        //teardown the WebDriver session
        driver.quit();

    }

}

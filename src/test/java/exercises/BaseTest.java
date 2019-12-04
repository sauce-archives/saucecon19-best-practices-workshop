package exercises;

import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SauceSession;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        SauceOptions sauceOptions = new SauceOptions();
        //TODO needs to be implemented in Simple Sauce
//        sauceOptions.setTestName(method.getName());
//        sauceOptions.setBuildName("best-practices");
//        sauceOptions.setTestTags("['best-practices', 'best-practices']");

        driver = new SauceSession(sauceOptions).start();
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }
}


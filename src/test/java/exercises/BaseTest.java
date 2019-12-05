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
    private SauceSession session;

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        SauceOptions sauceOptions = new SauceOptions();
        //TODO needs to be implemented in Simple Sauce
//        sauceOptions.setTestName(method.getName());
        //TODO this will be set by default through our impl. However, if they want, they can set it, but that's the exception,
        //not the rule
//        sauceOptions.setBuildName("best-practices");
//        sauceOptions.setTestTags("['best-practices', 'best-practices']");
        session = new SauceSession(sauceOptions);
        driver = session.start();
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        //String isPassed = result.isSuccess() ? "passed" : "failed";
        //session.stop(result.isSuccess()); //The better approach
    }
}


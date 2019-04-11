package exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CheckoutFeatureTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(Method method) throws MalformedURLException
    {
        // Input your SauceLabs Credentials
        String sauceUsername = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities capabilities = new MutableCapabilities();

        //sets browser to Firefox
        capabilities.setCapability("browserName", "firefox");

        //sets operating system to macOS version 10.13
        capabilities.setCapability("platform", "macOS 10.13");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "58.0");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());
        capabilities.setCapability("username", sauceUsername);
        capabilities.setCapability("accessKey", sauceAccessKey);

        //instantiates a remote WebDriver object with your desired capabilities
        driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);
    }
    @Test
    public void ShouldBeAbleToCheckoutWithItems() {

        // Specify Data
        String firstname = "john";
        String lastname = "doe";
        String postal = "94040";

        String backpack = "div:nth-child(1) > div.pricebar > button";
        String jacket = "div:nth-child(4) > div.pricebar > button";
        String cart = "#shopping_cart_container > a > svg";
        String rmvBtn = "div.cart_list > div.cart_item > div.cart_item_label > div.item_pricebar > button";
        String continueShopping = "div.cart_footer > a.btn_secondary";
        String checkoutLink = "div.cart_footer > a.btn_action.checkout_button";
        String firstNameField = "[data-test='firstName']";
        String lastNameField = "[data-test='lastName']";
        String postalField= "[data-test='postalCode']";
        String cartCheckout = "[value='CONTINUE']";
        String finished = "div.cart_footer > a.btn_action.cart_button";
        String complete = "https://www.saucedemo.com/checkout-complete.html";

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        //navigate to the url of the Sauce Labs Sample app
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.login(username, password);

        // Assert that the url is on the inventory page
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        driver.findElement(By.cssSelector(backpack)).click();
        driver.findElement(By.cssSelector(jacket)).click();

        // proceed to checkout
        driver.findElement(By.cssSelector(cart)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        // remove item from cart
        driver.findElement(By.cssSelector(rmvBtn)).click();

        // continue shopping
        driver.findElement(By.cssSelector(continueShopping)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        // re-add item to cart and proceed to checkout
        driver.findElement(By.cssSelector(jacket)).click();
        driver.findElement(By.cssSelector(cart)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Click Checkout Link
        driver.findElement(By.cssSelector(checkoutLink)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // proceed to shipping info (checkout step 1)
        driver.findElement(By.cssSelector(firstNameField)).sendKeys(firstname);
        driver.findElement(By.cssSelector(lastNameField)).sendKeys(lastname);
        driver.findElement(By.cssSelector(postalField)).sendKeys(postal);

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Click Cart Checkout Link
        driver.findElement(By.cssSelector(cartCheckout)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //  proceed to confirmation page (checkout step 2)
        driver.findElement(By.cssSelector(finished)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // assert that the test is finished by checking the last page's URL
        Assert.assertEquals(driver.getCurrentUrl(), complete);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }
}

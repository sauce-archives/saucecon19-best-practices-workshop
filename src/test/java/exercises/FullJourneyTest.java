package exercises;

import org.openqa.selenium.MutableCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FullJourneyTest {
    private WebDriver driver;

    @Test
    public void fullCustomerJourney(Method method) throws MalformedURLException {

        // Input your SauceLabs Credentials
        String sauceUserName = "SAUCE_USERNAME";
        String sauceAccessKey = "SAUCE_ACCESS_KEY";

        MutableCapabilities capabilities = new MutableCapabilities();

        //sets browser to Safari
        capabilities.setCapability("browserName", "Safari");

        //sets operating system to macOS version 10.13
        capabilities.setCapability("platform", "macOS 10.13");

        //sets the browser version to 11.1
        capabilities.setCapability("version", "11.1");

        //sets your test case name so that it shows up in Sauce Labs
        capabilities.setCapability("name", method.getName());

        capabilities.setCapability("username", sauceUserName);
        capabilities.setCapability("accessKey", sauceAccessKey);

        //instantiates a remote WebDriver object with your desired capabilities
        driver = new RemoteWebDriver(new URL("https://ondemand.saucelabs.com/wd/hub"), capabilities);

        //navigate to the url of the Sauce Labs Sample app
        driver.navigate().to("https://www.saucedemo.com");
        // navigate to desired page
        driver.get("https://www.saucedemo.com");

        // Specify Data
        String firstname = "john";
        String lastname = "doe";
        String postal = "94040";

        // Ignore the following selectors
        String username = "standard_user";
        String password = "secret_sauce";
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";
        String backpack = "div:nth-child(1) > div.pricebar > button";
        String jacket = "div:nth-child(4) > div.pricebar > button";
        String cart = "#shopping_cart_container";
        String rmvBtn = "div:nth-child(4) > div.cart_item_label > div.item_pricebar > button";
        String continueShopping = "a.cart_cancel_link";
        String checkoutLink = "a.cart_checkout_link";
        String firstNameField = "[data-test='firstName']";
        String lastNameField = "[data-test='lastName']";
        String postalField= "[data-test='postalCode']";
        String cartCheckout = "[value='CONTINUE']";
        String finished = "a.cart_checkout_link";
        String inventoryPage = "https://www.saucedemo.com/inventory.html";
        String cartPage = "https://www.saucedemo.com/cart.html";
        String checkout1 = "https://www.saucedemo.com/checkout-step-one.html";
        String checkout2 = "https://www.saucedemo.com/checkout-step-two.html";
        String complete = "https://www.saucedemo.com/checkout-complete.html";

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        // send username keystrokes
        driver.findElement(By.cssSelector(userField)).sendKeys(username);

        // send password keystrokes
        driver.findElement(By.cssSelector(passField)).sendKeys(password);

        // click login button to submit keystrokes
        driver.findElement(By.cssSelector(loginBtn)).click();

        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        // add items to the cart
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

        // Then quit the driver session
        driver.quit();
    }
}
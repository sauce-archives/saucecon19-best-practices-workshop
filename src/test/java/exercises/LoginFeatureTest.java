package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginFeatureTest extends BaseTest  {

    @Test
    public void ShouldBeAbleToLogin() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();

        String username = "standard_user";
        String password = "secret_sauce";
        loginPage.login(username, password);

        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}

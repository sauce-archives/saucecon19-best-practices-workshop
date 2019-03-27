package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginFeatureTest extends BaseTest  {

    @Test
    public void ShouldBeAbleToLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        Assert.assertTrue(loginPage.IsLoaded());

        String username = "standard_user";
        String password = "secret_sauce";
        InventoryPage inventoryPage = loginPage.login(username, password);
        Assert.assertTrue(inventoryPage.IsLoaded());
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}

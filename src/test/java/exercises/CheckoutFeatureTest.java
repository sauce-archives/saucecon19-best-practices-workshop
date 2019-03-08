package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import java.util.concurrent.TimeUnit;

public class CheckoutFeatureTest extends BaseTest {
    @Test
    public void ShouldBeAbleToCheckoutWithItems() {
        // wait 5 seconds
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.visit();
        checkoutPage.setCartState();
        checkoutPage.checkout();
        Assert.assertTrue(checkoutPage.hasItems());
    }
}
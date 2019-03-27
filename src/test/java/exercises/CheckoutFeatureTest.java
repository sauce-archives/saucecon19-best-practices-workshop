package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import pages.ConfirmationPage;

import java.util.concurrent.TimeUnit;


public class CheckoutFeatureTest extends BaseTest{

    @Test
    public void ShouldBeAbleToCheckoutWithItems() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        Assert.assertTrue(confirmationPage.IsLoaded());

        confirmationPage.setPageState();
        Assert.assertTrue(confirmationPage.hasItems());
        CheckoutCompletePage completePage = confirmationPage.FinishCheckout();
        // assert that the test is finished by checking the last page's URL
        Assert.assertTrue(completePage.IsLoaded());
    }

}

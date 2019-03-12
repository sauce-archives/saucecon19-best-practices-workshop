package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import pages.ConfirmationPage;

import java.util.concurrent.TimeUnit;


public class CheckoutFeatureTest extends BaseTest{

    @Test
    public void ShouldBeAbleToCheckoutWithItems() {

        // TODO convert to explicit wait and remove duplication
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.visit();
        confirmationPage.setPageState();
        Assert.assertTrue(confirmationPage.hasItems());

        CheckoutCompletePage completePage = confirmationPage.FinishCheckout();
        // assert that the test is finished by checking the last page's URL
        Assert.assertTrue(completePage.IsLoaded());
    }

}

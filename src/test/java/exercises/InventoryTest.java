package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import pages.InventoryPage;

import java.lang.reflect.Method;


public class InventoryTest extends BaseTest {

    @Tag(name = "oneItemInCart()")
    @Test
    /** Tests placing one specific item in the cart **/
    public void oneItemInCart() {
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        inventoryPage.addOneItem();
        Assert.assertEquals(inventoryPage.itemCount(), "1");
        inventoryPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

    }

    @Tag(name = "twoItemsInCart()")
    @Test
    /** Tests placing two specific items in the cart **/
    public void twoItemsInCart() {
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        inventoryPage.addTwoItems();
        Assert.assertEquals(inventoryPage.itemCount(), "2");
        inventoryPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
    }
}
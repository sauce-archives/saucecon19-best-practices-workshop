package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    WebDriver driver;
    private By backpackBtn = By.cssSelector("#inventory_container > div > div:nth-child(1) > div.pricebar > button");
    private By jacketBtn = By.cssSelector("#inventory_container > div > div:nth-child(4) > div.pricebar > button");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");
    private By checkoutLink = By.className("cart_checkout_link");
    private By itemName = By.className("iventory_item_name");

    private By sauceBot = By.className("peek");

    public static InventoryPage visit(WebDriver driver) {
        InventoryPage page = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com/inventory.html");
        return page;
    }

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addOneItem() {

        driver.findElement(backpackBtn).click();
    }

    public void addTwoItems() {
        driver.findElement(backpackBtn).click();
        driver.findElement(jacketBtn).click();
    }

    public String itemCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void checkout() {
        driver.findElement(cartLink).click();
        driver.findElement(checkoutLink).click();
    }

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public Boolean isSignedIn() {
        return driver.findElements(sauceBot).size() > 0;
    }
}

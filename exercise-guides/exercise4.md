# Exercise 4: Configure Atomic Tests

## Part One: Modify `CheckoutPage`
1. Checkout the branch `04_configure_atomic_tests`.
2. Open `LoginPage` in `src > test > java > pages`.
3. Add the following class methods:
    ```
    public void visit() {
        driver.get("https://www.saucedemo.com/cart.html");
    }
    ```
    ```
    public void checkout() {
        String checkoutLink = "cart_checkout_link";
        driver.findElement(By.className(checkoutLink)).click()
    }
    ```
    ```
    public Boolean hasItems() {
        String cartBadge = "shopping_cart_badge";
        return Integer.parseInt(driver.findElement(By.className(cartBadge)).getText()) > 0;
    }
    ```
4. Open **`CheckoutFeatureTest`** located in `src > test > java > exercises`.
5. You'll notice that the **`ShouldBeAbleToCheckout()`** class method steps through many pages to get to the checkout function. The existing test flow works like this:
    * User logs in
    * Adds some items to the cart
    * Clicks the cart icon to proceed to checkout

This approach is under-optimized because our tests shouldn't rely on the assertions of other tests and it's unecessary to travel through each individual page to reach that feature. Therefore if we're only testing features on a specific page, we can modify the page state using the **`JavaScriptExecutor`**.
    
<br />
    
## Part Two: Implement the `JavascriptExecutor` to Bypass Pages
1. Go back to **`CheckoutPage`** and add the following class method:
    ``` 
    public void setCartState() {
        driver.navigate().refresh();
    }
    ```
2. In **`setCartState()`** add the following **`JavaScriptExecutor`** command to bypass logging in through the **`LoginPage`** object:
    ```
    ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('standard-username', 'standard-user')");
    ```
3. Then add another **`JavaScriptExecutor`** command to bypass adding items to the cart through the **`InventoryPage`** object:
    ```
    ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('cart-contents', '[4,1]')");
    ```
4. In **`CheckoutFeatureTest`**, delete the existing commands and add the following:
    ```
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
    ```
5. Save all and run the following command to ensure the build passes:
    ```
    mvn test
    ```
6. Use `git stash` or `git commit` to discard or save your changes. Checkout the next branch to proceed to the next exercise
    ```
    git checkout 05_test_parallelization
    ```
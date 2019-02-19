# Exercise 5: Configure Atomic Tests
## Part One: Identify Inefficient Tests
1. Checkout branch `05_configure_atomic_tests`.
2. Open **`CartTest`** located in `src > test > java > exercises`.
3. You'll notice that the **`confirmCheckout()`** method instantiates several page objects and depends on several assertions before reaching the actual page under test (**`CartPage`**):
    ```
    @Tag(name = "confirmCheckout()")
    @Test
    //** Tests checkout process **//*
    public void confirmCheckout(Method method) {
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signIn(User.validUser());
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        Assert.assertTrue(inventoryPage.isSignedIn());
        inventoryPage.addAllItems();
        Assert.assertFalse(inventoryPage.emptyCart());
        CartPage cartPage = CartPage.visit(driver);
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }
    ```
    This approach is under-optimized. In general, our tests shouldn't rely on the assertions of other tests and it's unecessary to travel through the entire customer experience unless you're conducting end-to-end functionality testing. Therefore if we're only testing features on a specific page, we should modify the front-end state of the page using the **`JavaScriptExecutor`**.
    
    <br />
    
## Part Two: Leverage the `JavascriptExecutor` to Bypass Pages
1. Open the page object called **`CartPage`** in `src > test > java > pages` and add the following method:
    ``` 
    public void setCartState() {
        driver.navigate().refresh();
    }
    ```
2. In **`setCartState()`** add the following **`JavaScriptExecutor`** command to bypass the **`LogInPage`** status needed to checkout:
    ```
    ((JavascriptExecutor)driver)
        .executeScript("window
            .sessionStorage
            .setItem('standard-username', 'standard-user')");
    ```
3. Then add the **`JavaScriptExecutor`** to bypass adding items to the cart in the **`InventoryPage`**:
    ```
    ((JavascriptExecutor)driver)
        .executeScript("window
            .sessionStorage
            .setItem('cart-contents', '[4,1]')");
    ```
4. In **`CartTest`**, refactor the **`confirmCheckout()`** method to the following:
    ```
    @Tag(name = "checkoutTest()")
    @Test
    public void checkoutTest() {
        CartPage cartPage = CartPage.visit(driver);
        cartPage.setCartState();
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }
    ```
5. Save all and run the following command in your terminal
    ```
    mvn test -Dtest=CartTest
    ```
6. Final Results
    * Before:
    ```
    @Tag(name = "confirmCheckout()")
    @Test
    public void confirmCheckout(Method method) {
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signIn(User.validUser());
        InventoryPage inventoryPage = InventoryPage.visit(driver);
        Assert.assertTrue(inventoryPage.isSignedIn());
        inventoryPage.addAllItems();
        Assert.assertFalse(inventoryPage.emptyCart());
        CartPage cartPage = CartPage.visit(driver);
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }
    ```
    * After:
    ```
    @Tag(name = "checkoutTest()")
    @Test
    public void checkoutTest() {
        CartPage cartPage = CartPage.visit(driver);
        cartPage.setCartState();
        cartPage.checkout();
        Assert.assertTrue(cartPage.hasItems());
    }
    ```
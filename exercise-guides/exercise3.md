# Exercise 3: Abstract and Configure Declarative Test Code
##Part One: Abstract Test Code

1. Checkout branch `03_abstract_test_code`, and open `test > exercises > Exercise3.java`
2. Create a `@BeforeEach` method called `setup()`.
3. Abstract the prerequisite tasks (i.e. `DesiredCapabilities`, `MutableCapabilities`, and `RemoteWebDriver`) into the `setup()`method.
4. Refactor the test code from imperative to declarative, for example:
    
    ####**Before**
    ```
    public void signInSuccessfully(TestInfo testInfo) throws MalformedURLException {
        driver.get("https://www.saucedemo.com");
    
        String username = "standard_user";
        String password = "secret_sauce";
    
        WebDriverWait explicitWait = new WebDriverWait(driver, 10);
    
        WebElement usernameElement = explicitWait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='username']")));
    
        usernameElement.sendKeys(username);
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys(password);
        driver.findElement(By.className("login-button")).submit();
    
        Boolean result = explicitWait.until(ExpectedConditions.urlMatches("https://www.saucedemo.com/inventory.html"));
        Assertions.assertTrue(result);
    }
    ``` 
    ####**After**
    ```
    public void signInSuccessfully() throws AssertionError {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signIn(User.validUser());
    
        HomePage homePage = new HomePage(driver);
        Assertions.assertTrue(homePage.isSignedIn());
    }
    ```

##Part Two: Send Results to Sauce Labs
1. Create an `@AfterEach` method called `teardown()`.
2. Add the following `JavaScriptExecutor` code in order to send the test results to saucelabs.com.
    ```
    @AfterEach
    public void teardown() {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (true ? "passed" : "failed"));
        driver.quit();
    }
    ```
    > Note: Your test is currently incomplete, proceed to the next exercise to create a `SignInPage` object and a `HomePage` object in order for your test to pass.

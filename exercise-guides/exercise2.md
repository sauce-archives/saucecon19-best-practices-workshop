# Exercise 2: Create Page Objects

## Part One: Create a  `LoginPage`
1. Checkout branch `02_create_page_objects`. 
3. In the **`pages`** package, navigate to the class called **`LoginPage`**
4. Create the constructor for the page object:
    ```
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    ```
5. Create a `visit` method in the `LoginPage` object:
    ```
    public LoginPage visit() {
        driver.get("https://www.saucedemo.com");
        return this;
    }
    ```
6. Open **`LoginFeatureTest`** and import the **`LoginPage`** changes to replace **`driver.navigate().to("https://www.saucedemo.com")`** For Example:
    
    * Before
    ```
    driver.navigate().to("https://www.saucedemo.com");
    ```
    * After
    ```
    LoginPage LoginPage = new LoginPage(driver);
    LoginPage.visit();
    ```

7. Run a `mvn test` command to see if the test executes successfully:
    ```
    mvn test -Dtest=LoginFeatureTest
    ```   
    <br />
    
## Part Two: Create `login()` Method
1. Open **`LoginPage`** and create a new class method called `login()`. This method will return a new page object that represents the next page in the journey (i.e. `InventoryPage`)
2. Add the **`LoginPage.visit()`** action in place of **`driver.navigate().to("https://www.saucedemo.com")`** The method will also expect some `String` data for the credentials (`username` and `password`). For Example:
    ```
    public InventoryPage login(String username, String password)
    {
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";
    }
    ```
3. Add the following Selenium commands in order to send the keystrokes:
    ```
    // send username keystrokes
    driver.findElement(By.cssSelector(userField)).sendKeys(username);

    // send password keystrokes
    driver.findElement(By.cssSelector(passField)).sendKeys(password);

    // click login button to submit keystrokes
    driver.findElement(By.cssSelector(loginBtn)).click();
    return new InventoryPage(driver);
    ```
    
4. Open `LoginFeatureTest`, and add the following to the `ShouldBeAbleToLogin` method:
    ```
    import pages.LoginPage;
    ...
        @Test
        public void ShouldBeAbleToLogin() {

            // wait 5 seconds
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
            //navigate to the url of the Sauce Labs Sample app
            LoginPage loginPage = new LoginPage(driver);
            loginPage.visit();

            // Ignore the following selectors
            String username = "standard_user";
            String password = "secret_sauce";
            loginPage.login(username, password);
            Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }
    ```
5. Save and run `mvn test` to ensure the test still executes:
    ```
    mvn test -Dtest=LoginFeatureTest
    ```

<br />


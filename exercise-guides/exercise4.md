# Exercise 4: Implement the Page Object Model
##Part One: Add a `SignInPage` Object
1. Checkout branch `04_page_objects` and open `test > pages > SignInPage.java`
2. Create the following locators.
    
    ```
    private By userField = By.cssSelector("input[data-test='username']");
    private By passwordField = By.cssSelector("input[data-test='password']");
    private By loginButton = By.className("login-button");
    private By error = By.className("fa-times-circle");
    ```
    
3. Create a `SignInPage` constructor class.
    
    ```
    public static SignInPage visit(WebDriver driver) {
        SignInPage page = new SignInPage(driver);
        driver.get("https://www.saucedemo.com");
        return page;
    }
    ```
    
4. Instantiate the `SignInPage` using the current `WebDriver`.

    ```
    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }
    ```

5. Create two methods, `signIn` (used for signing in), and `fillForm` (used for filling form data). The latter method uses the `User` object located in `test > data > User`. The object uses an implementation of the open source [faker library](https://mvnrepository.com/artifact/com.github.javafaker/javafaker/0.2).

    ```
    public void signIn(User data) { 
        fillForm(data); 
    }
    
    private void fillForm(User data) {
        driver.findElement(userField).sendKeys(data.getUsername());
        driver.findElement(passwordField).sendKeys(data.getPassword());
        driver.findElement(loginButton).click();
    }
    ```
##Part Two: Add a `HomePage` Object
1. In `test > pages` add a new class called `HomePage.java`
2. Add the following imports:
    
    ```
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    ```

3. Back in `test > exercises > Example3.java` remove the `private WebDriver driver;` and place it in `HomePage.java`.
4. In `HomePage.java` add a variable for the sauce bot icon.
   
   ```
   private By sauceBot = By.className("peek");
   ``` 

5. Instantiate the `HomePage` using the current `WebDriver` session.

   ```
   public HomePage(WebDriver driver) {
        this.driver = driver; 
   }
   ```
6. Create a public `Boolean` method that returns the sign in status.

    ```
    public Boolean isSignedIn() {
        return driver.findElements(sauceBot).size() > 0; 
    }
    ```
7. Save and run the test `Exercise3.java`. Refer to your [automated test dashboard](https://app.saucelabs.com/dashboard/tests) to see if your test passed on Sauce Labs.

##Bonus Objectives
* Run multiple tests in parallel by adding the following `@Test` methods in `Exercise3.java`:
    ```aidl
    @Tag("signInUnsuccessfully()")
    @DisplayName("signInUnsuccessfully()")
    @Test
    public void signInUnsuccessfully() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signInUnsuccessfully(User.invalidUser());
        Assertions.assertTrue(signInPage.hasErrorMessage());
    }
    
    @Tag("signInBlankPassword()")
    @DisplayName("signInBlankPassword()")
    @Test
    public void signInBlankPassword() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signInUnsuccessfully(User.blankPassword());
    
        Assertions.assertTrue(signInPage.hasErrorMessage());
    }
    ```
 > Disclaimer: the parrallel test feature in JUnit5 is experimental, refer to the documentation for more details:
    https://junit.org/junit5/docs/snapshot/user-guide/#writing-tests-parallel-execution
 
 * Create PageObjects for the remaining pages of [www.saucedemo.com](www.saucedemo.com)
# Exercise 2: Create Page Objects

## Part One: Create Test Methods
1. Checkout branch `02_create_page_objects`. Open `src > test > java > exercises > FullJourneyTest.java`
2. Create a new package in **`src > test > java`** called **`pages`**. 
3. In the **`pages`** package, create a new class called **`LogInPage`**
4. Instantiate the `LogInPage` object:
    ```
    public static LogInPage visit(WebDriver driver) {
        LogInPage page = new LogInPage(driver);
        driver.get("https://www.saucedemo.com");
        return page;
    }
    
    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }
    ```
5. Identify any repeatable test actions and create a method for each action. For example, a method for entering user information into a contact form could look like this:
    ```
    void logIn(By locator, String text) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(locator).sendKeys(text);
    }
    ```
6. Add the locators as private variables, for example:
    ```
    private By userField = By.cssSelector(
    "[placeholder = 'Username']");
    
    private By passwordField = By.cssSelector(
    "[placeholder = 'Password']");
    
    private By loginButton = By.className(
    "login-button");
    ```
    <br />
    
## Part Two: Create a `LogInTest` Class
1. Open **`LogInTest`** and add the following `@BeforeMethod` and `@AfterMethod` classes:
    In **`LogInTest`** add the following methods:
    ```
    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        String methodName = method.getName();

        ChromeOptions chromeOpts = new ChromeOptions();
        chromeOpts.setExperimentalOption("w3c", true);

        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("name", methodName);
        sauceOpts.setCapability("seleniumVersion", "3.11.0");
        sauceOpts.setCapability("user", username);
        sauceOpts.setCapability("accessKey", accessKey);

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
        caps.setCapability("sauce:options", sauceOpts);
        caps.setCapability("browserName", "googlechrome");
        caps.setCapability("browserVersion", "61.0");
        caps.setCapability("platformName", "windows 10");
    
    ...
    
    @AfterMethod
    public void teardown(ITestResult result) {
        ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }
    ```
2. Import the **`LogInPage`** and reference the `@Test` class method. For example:
    ```
    import pages.LogInPage;
    ...
    public class LogInTest {
        
        @Test
        public void logInSuccessfully() {
            LogInPage logInPage = LogInPage.visit(driver);
            logInPage.logIn(userField, username);
            logInPage.logIn(passField, password);
            logInPage.submit(loginBtn);    
            Assertions.assertEquals(driver.getCurrentUrl(),
            "https://www.saucedemo.com/inventory.html");
        }
    }
    ...
    ```
<br />

## Part Three: Test the Results
1. Save all and run **`mvn test`** in your terminal. We now have duplication in not only our tests, but potentially our future page objects. Next we will create a `BasePage` object class and a `BaseTest` object class.
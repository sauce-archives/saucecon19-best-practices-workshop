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
    public void signInSuccessfully() {
        logIn(driver);
    }
    
    private void logIn(WebDriver driver) {
        String username = "standard_user";
        String password = "secret_sauce";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(userField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
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
    public class LogInTest  {
        protected WebDriver driver;
        @BeforeMethod
        public void setup(Method method) throws MalformedURLException {
            String username = System.getenv("SAUCE_USERNAME");
            String accessKey = System.getenv("SAUCE_ACCESS_KEY");
            String methodName = method.getName();
    
            ChromeOptions chromeOpts = new ChromeOptions();
            chromeOpts.setExperimentalOption("w3c", true);
    
            MutableCapabilities sauceOpts = new MutableCapabilities();
            sauceOpts.setCapability("username", username);
            sauceOpts.setCapability("accessKey", accessKey);
            sauceOpts.setCapability("name", methodName);
            sauceOpts.setCapability("seleniumVersion", "3.141.59");
            sauceOpts.setCapability("name", methodName);
            sauceOpts.setCapability("build", "saucecon19-best-practices");
            sauceOpts.setCapability("tags", "['best-practices', 'saucecon19']");
    
            MutableCapabilities caps = new MutableCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
            caps.setCapability("sauce:options", sauceOpts);
            caps.setCapability("browserName", "googlechrome");
            caps.setCapability("browserVersion", "71.0");
            caps.setCapability("platformName", "windows 10");
    
            String sauceUrl = "https://ondemand.saucelabs.com:443/wd/hub";
            URL url = new URL(sauceUrl);
            driver = new RemoteWebDriver(url, caps);
        }
    ...
    
        @AfterMethod
        public void teardown(ITestResult result) {
            ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
            driver.quit();
        }
    }
    ```
2. Import the **`LogInPage`** and reference the `@Test` class method. For example:
    ```
    import pages.LoginPage;
    ...
    public class LogInTest {
        
    @Tag(name = "logInSuccessfully()")
    @Test
    /** Tests for a successful login **/
    public void logInSuccessfully(Method method) {
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signInSuccessfully();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
    ...
    ```
<br />

## Part Three: Test the Results
1. Save all and run **`mvn test`** in your terminal. We now have duplication in not only our tests, but potentially our future page objects. Next we will create a `BasePage` object class and a `BaseTest` object class.
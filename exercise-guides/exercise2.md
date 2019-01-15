# Exercise 2: Manage Test Automation Speeds
##Part One: Configure `sauce:options`
1. Checkout branch `02_set_sauce_options `, and open `test > exercises > Exercise2.java`
2. In the **`MutableCapabilities sauceOpts`** object declaration, set the following options:
    
    ```$xslt
    sauceOpts.setCapability("name", testInfo.getDisplayName());
    sauceOpts.setCapability("seleniumVersion", "3.141.59");
    sauceOpts.setCapability("user", username);
    sauceOpts.setCapability("accessKey", accessKey);
    sauceOpts.setCapability("tags", testInfo.getTags());
    sauceOpts.setCapability("commandTimeout", 600);
    sauceOpts.setCapability("idleTimeout", 1000);
    sauceOpts.setCapability("maxDuration", 3600);
    ```
    
3. In the **`DesiredCapabilities caps`** object declaration, set the following capabilities:

    ```$xslt
    caps.setCapability("sauce:options", sauceOpts);
    caps.setCapability("browserName", "firefox");
    caps.setCapability("browserVersion", "53.0");
    caps.setCapability("platformName", "windows 10");
    ```
<br />

##Part Two: Configure Explicit Waits
1. Create an instance of a Selenium explicit wait so that we can dynamically wait for an element
   
   ```$xslt
   WebDriverWait wait = new WebDriverWait(driver, 5);
   ```
   
2. Wait for the username field to be visible and store that element in a variable
   
   ```$xslt
   By userNameFieldLocator = By.cssSelector("[type='text']");
   wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFieldLocator));
   ```
3. Enter the username and password strings into their respective fields. Then submit the results by "clicking" the submit button user name field
   ```
   driver.findElement(userNameFieldLocator).sendKeys("standard_user");
   driver.findElement(By.cssSelector("[type='password']")).sendKeys("secret_sauce");
   driver.findElement(By.cssSelector("[type='submit']")).click();
   ```
4. Ensure the next page loads by creating another locator and storing it an a variable
    ```$xslt
    By inventoryPageLocator = By.id("inventory_container");
    wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryPageLocator));
    ```
5. Create a test assertion to determine if the test passed or failed
    ```$xslt
    Boolean result = driver.findElements(inventoryPageLocator).size() > 0;
    Assertions.assertTrue(result);
    driver.quit();
    
    ```
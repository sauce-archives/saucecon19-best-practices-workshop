# Exercise 3: Remove Duplication

## Part One: Identify Duplicate Test Code
1. Checkout branch `03_remove_duplication`.
2. Open the new page object called **`InventoryPage`** in `src > test > java > pages`.
3. Note the four class methods:
    * `addOneItem`: adds one item to the shopping cart
    * `addTwoItem`: adds two items to the shopping cart
    * `itemCount`: counts items in the shopping cart
    * `checkout`: proceeds to the checkout page.
4. Open **`InventoryTest`** in `src > test > java > exercises`.
5. There is now duplicate code that also exists in **`LogInTest`**, specifically the `setup()` and `teardown()` methods. Next, we will create a `BaseTest` class that executes these prerequisite and postrequisite test actions.

<br />

## Part Two: Create a `BaseTest` Class
1. Create a class called **`BaseTest`** in `src > test > java > exercises`.
2. Move the **`setup()`** and **`teardown()`** methods that exist in  **`LogInTest`** and **`InventoryTest`** into **`BaseTest`**. 
3. Back in **`LogInTest`** (and **`InventoryTest`**) add the following:
    ```
    public class LogInTest extends BaseTest {
    ...
    }
    ```
4. Remove the **`setup()`** and **`teardown()`** methods in **`LogInTest`** and **`InventoryTest`**.

<br />

## Part Three: Create a `BasePage` Object
1. Identify duplication in any method that uses common Selenium **`driver`** commands such as the following in **`LogInPage`** and **`InventoryPage`**:
    * `.findElement()`
    * `.click()`
    * `.sendKeys()`
2. Create a class called **`BasePage`** in `src > test > java > pages`.   
3. Create new class methods in `BasePage` that represent common Selenium **`driver`** commands, for example:
    
    * Find an element on a page
        ```
        WebElement findBy(By locator) {
            return driver.findElement(locator);
        }
        ```
    * Click on an element
        ```
        void click(By locator) {
            driver.manage().timeouts().implicitlyWait(5, 
            TimeUnit.SECONDS) ;
            findBy(locator).click();
        }
        ```
    * Submit keystrokes
        ```
        void sendKeys(By locator, String text) {
            driver.manage().timeouts().implicitlyWait(5, 
            TimeUnit.SECONDS) ;
            findBy(locator).sendKeys(text);
        }
        ```
4. Use the class methods in **`BasePage`** to refactor duplicate Selenium **`driver`** commands in both **`LogInPage`** and **`InventoryPage`**, for example:
    * Before
        ```
        void logIn(By locator){
            driver.findElement(userField).sendKeys(username);
            driver.findElement(passField).sendKeys(password);
            driver.findElement(logInButton).click();
        }
        ```
    * After
        ```
        void logIn(By locator){
            sendKeys(userField, username);
            sendKeys(passField, password);
            click(logInButton);
        }
        ```
5. Checkout branch `04_explicit_waits` to see the complete examples
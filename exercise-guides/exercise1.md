# Exercise 1: Configure Automated Testing on SauceLabs

##Part One: Set Sauce Labs Account Credentials
1. Checkout branch `01_set_sauce_credentials `. Open `src > test > java > exercises > FullJourneyTest.java`
2. Login to [www.saucelabs.com](https://www.sauceslabs.com), and navigate to the User Settings section of your account profile.
    
    ![User Settings](images/user-settings.png)
    
3. Copy and paste your Username and SauceLabs Access Key to your local clipboard
4. Navigate back to IntelliJ and in the class `FullJourneyTest.java`, update the following variables:
      ```
      String sauceUserName = "SAUCE_USERNAME";
      String sauceAccessKey = "SAUCE_ACCESS_KEY";
      ```
5. Save and run your test by typing in the following in your terminal:
    ```
    mvn test
    ``` 
    you should see the results appear in your Sauce Labs Test Dashboard
    
##Part Two: Set Environment Variables
    
6. Next, modify the `sauceUserName` and `sauceAccessKey` variables to use Environment Variables:

    ```
       String sauceUserName = System.getenv("SAUCE_USERNAME");
       String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
    ```
7. At the bottom of IntelliJ select the Terminal tab:
8. In the Terminal set your Sauce Labs Environment variables:   
   ###### Mac OSX:
   ```
   $ export SAUCE_USERNAME="your saucelabs username"
   $ export SAUCE_ACCESS_KEY="your saucelabs API access Key"
   ```
   ###### Windows:
   ```
   > set SAUCE_USERNAME="your saucelabs username"
   > set SAUCE_ACCESS_KEY="your saucelabs API access Key"
   ```
   > To set an environment variables permanently in Windows, you must append it to the `PATH` variable.
   > Go to "Control Panel > System > Windows version > Advanced System Settings > Environment Variables > System Variables > Edit > New. Then enter the "Name" and "Value"
9. Test the environment variables
    ###### Mac OSX:
    ```
    $ echo $SAUCE_USERNAME
    $ echo $SAUCE_ACCESS_KEY
    ```
    ###### Windows:
    ```
    echo %SAUCE_USERNAME%
    echo %SAUCE_ACCESS_KEY%
    ```
    
    > To refresh a bash shell if you don't see the values run any of the following commands: 
    >  * `$ source ~/.bashrc`
    >  * `$ source ~/.bash_profile`
    >  * `$ source /etc/profile`
    
 8. Run your test using Maven
    ```
    mvn test
    ```
    You should see the following build info appear (after sometime) in your console:
    
    ![Successful Test Build Info](images/ex1-test-build.png)
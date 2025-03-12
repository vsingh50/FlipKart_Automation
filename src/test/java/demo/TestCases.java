package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    
    ChromeDriver driver;
    

    @Test
    public void testCase01() throws InterruptedException{
        String url = "https://www.flipkart.com/";
        //Navigate to flipkart
        Wrappers.navigate(driver, url);
        if(driver.getCurrentUrl().contains("flipkart")){
            System.out.println("Navigated to the flipkart");
        }
        else{
            System.out.println("Not Navigated to the flipkart");
        }
        
        //Make Search
        WebElement searchBoxInput = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        String washingMachineString = "Washing Machine";
        Wrappers.sendKeys(driver, searchBoxInput, washingMachineString);
        WebElement searchIcon = driver.findElement(By.xpath("//button[contains(@title,'Search ')]"));
        Wrappers.click(searchIcon, driver);
        System.out.println("Made Search Successfully");
        //Wait till result loads
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='BUOuZu']/span")));
        Thread.sleep(2000);
        WebElement washingMachineText = driver.findElement(By.xpath("//span[@class='BUOuZu']/span"));
        if(washingMachineText.getText().contains(washingMachineString)){
            System.out.println("Typed Washing Machine into search box");
        }
        else{
            System.out.println("Not able to type Washing Machine into search box");
        }

        WebElement popularity = driver.findElement(By.xpath("//div[@class='sHCOk2']/div[2]"));
        popularity.click();
        Thread.sleep(2000);

        //Counting elements
        List<WebElement> rating = driver.findElements(By.className("XQDdHH"));
        int count = Wrappers.countOfItemsBasedOnRatings(driver, rating, 4);
        System.out.println("Number of Items with less than or equal to given rating 4 are: " + count);
    }

    @Test
    public void testCase02() throws InterruptedException{
        String url = "https://www.flipkart.com/";
        //Navigate to flipkart
        Wrappers.navigate(driver, url);
        if(driver.getCurrentUrl().contains("flipkart")){
            System.out.println("Navigated to the flipkart");
        }
        else{
            System.out.println("Not Navigated to the flipkart");
        }
        Thread.sleep(2000);
        
        //Make Search
        WebElement searchBoxInput = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        String iPhoneString = "iPhone";
        Wrappers.sendKeys(driver, searchBoxInput, iPhoneString);
        WebElement searchIcon = driver.findElement(By.xpath("//button[contains(@title,'Search ')]"));
        Wrappers.click(searchIcon, driver);
        System.out.println("Made Search Successfully");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='BUOuZu']/span")));
        WebElement iPhoneText = driver.findElement(By.xpath("//span[@class='BUOuZu']/span"));
        if(iPhoneText.getText().contains(iPhoneString)){
            System.out.println("Typed iPhone into search box");
        }
        else{
            System.out.println("Not able to type iPhone into search box");
        }

        List<WebElement> items = driver.findElements(By.className("yKfJKb"));
        Wrappers.printingProductTitle(driver, items, 8);

    }

    @Test
    public void testCase03() throws InterruptedException{
        String url = "https://www.flipkart.com/";
        //Navigate to flipkart
        Wrappers.navigate(driver, url);
        System.out.println("Navigated to Flipkart Successfully");
        Thread.sleep(2000);
        
        //Make Search
        WebElement searchBoxInput = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        Wrappers.sendKeys(driver, searchBoxInput, "Coffee Mug");
        WebElement searchIcon = driver.findElement(By.xpath("//button[contains(@title,'Search ')]"));
        Wrappers.click(searchIcon, driver);
        System.out.println("Made Search Successfully");
        Thread.sleep(2000);

        
        WebElement starCheckBox = driver.findElement(By.xpath("(//div[contains(text(),'above')])[1]"));
        starCheckBox.click();
        Thread.sleep(2000);

        List<WebElement> listOfElements = driver.findElements(By.className("slAVV4"));
        Wrappers.printingCoffeeMugTitle(driver, listOfElements);
    }


    @BeforeMethod
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}
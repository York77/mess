
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class Grabber {


    public static void main(String[] args) throws Exception{

        basicTest();

        //selenideTest ();

    }

    public static void selenideTest () {

        Configuration.timeout = 150000;
        Configuration.pageLoadTimeout = 150000;


        open("https://cstnext-dev1.int.kn/login");

       $(By.id("username")).setValue("york.zhang");

       $(By.id("password")).setValue("changeme");


        $(By.id("logIn")).click();

        Selenide.sleep(30000);

    }

    public static void error(Object... arguments) {
        System.out.println(arguments[0]);
        System.out.println(arguments[1]);
        System.out.println(arguments[2]);
        System.out.println(arguments[3]);
    }

    @Test
    public static void webdriver () throws Exception{

        // System Property for Chrome Driver
        System.setProperty("webdriver.chrome.driver", "C:\\ilsource\\stockdatacrawler\\src\\main\\resources\\chromedriver.exe");

        // Instantiate a ChromeDriver class.

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        chromeOptions.setCapability("browserVersion", "1.9");

        chromeOptions.setCapability("platformName", "Windows");



        WebDriver driver=new ChromeDriver(chromeOptions);

        System.out.println(chromeOptions.getBrowserName());
        //System.out.println(chromeOptions.getCapability().toString());
        System.out.println("version: "+chromeOptions.getVersion());
        System.out.println("platform: "+chromeOptions.getPlatform());

        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);


        //WebDriverWait wait = new WebDriverWait(driver, 500);

        //wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));



        // Launch Website
        driver.navigate().to("https://cstnext-dev1.int.kn/login");



        driver.manage().window().maximize();
        driver.findElement(By.id("username")).sendKeys("york.zhang");

        driver.findElement(By.id("password")).sendKeys("changeme");

        driver.findElement(By.id("logIn")).click();

        Set<Cookie> cookieList = driver.manage().getCookies();

       // Set<Cookie> cookieList = driver.manage();

        for(Cookie cookie: cookieList) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
        }



       /*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("descriptionOfData"));
            }
        });*/
        //WebElement foo = driver.findElement(By.id("descriptionOfData"));



        //Maximize the browser
//
        //Scroll down the webpage by 5000 pixels
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("scrollBy(0, 5000)");

        // Click on the Search button


        System.out.println(driver.getTitle());

       // driver.navigate().back();

        //driver.close();
    }

    @Before
    public void init() throws Exception {}

    public static void basicTest() {
        EyesRunner runner;
        Eyes eyes;
        BatchInfo batch;
        WebDriver driver;
        // Set AUT's name, test name and viewport size (width X height)
        // We have set it to 800 x 600 to accommodate various screens. Feel free to
        // change it.
        // System Property for Chrome Driver

        batch = new BatchInfo("Demo batch");
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        chromeOptions.setCapability("browserVersion", "1.9");

        chromeOptions.setCapability("platformName", "Windows");
        System.setProperty("webdriver.chrome.driver", "C:\\ilsource\\stockdatacrawler\\src\\main\\resources\\chromedriver.exe");

        driver=new ChromeDriver(chromeOptions);
        // Initialize the Runner for your test.
        runner = new ClassicRunner();

        // Initialize the eyes SDK
        eyes = new Eyes(runner);


        // Set your personal Applitols API Key from your environment variables.
        eyes.setApiKey("ko999phPGMIiwf9JaLJyDe9M6hVCsm111DaMIZZ7qbYUN0110");

        // set batch name
        eyes.setBatch(batch);
        eyes.open(driver, "Demo App", "Smoke Test", new RectangleSize(800, 800));

        // Navigate the browser to the "ACME" demo app.
        driver.get("https://demo.applitools.com");

        // To see visual bugs after the first run, use the commented line below instead.
        //driver.get("https://demo.applitools.com/index_v2.html");

        // Visual checkpoint #1 - Check the login page.
        eyes.checkWindow("Login Window");

        // This will create a test with two test steps.
        driver.findElement(By.id("log-in")).click();

        // Visual checkpoint #2 - Check the app page.
        eyes.checkWindow("App Window");

        // End the test.
        eyes.closeAsync();
    }

}

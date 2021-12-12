package com.testinium.web.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseTest {

    static WebDriver webDriver=null;
    protected Logger log = Logger.getLogger(this.getClass().getName());

    public String fileName = new SimpleDateFormat("dd.MM.yyyy - HH.mm.ss").format(new Date());
    public File file = new File(fileName+".txt");

    @Before
    public void setup() throws Exception {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\lib\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");

        setWebDriver(new ChromeDriver(options));

        getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().deleteAllCookies();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        BaseTest.webDriver = webDriver;
    }

    @After
    public void teardown(){
        getWebDriver().quit();
    }
}

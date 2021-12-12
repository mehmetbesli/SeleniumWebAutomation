package com.testinium.web.base;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasePage {

    public WebDriver driver=null;
    public WebDriverWait wait=null;
    private static final int DEFAULT_WAIT = 20;

    protected Logger log = Logger.getLogger(this.getClass().getName());

    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver,15);
    }

    protected void goToUrl(String url) {
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    protected void untilElementAppear(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected WebElement findElement(By by, int... index) throws InterruptedException {
        WebElement element;
        untilElementAppear(by);

        if (index.length == 0)
            element = driver.findElement(by);
        else
            element = driver.findElements(by).get(index[0]);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();", element);
        //((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);

        // wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        return element;
    }

    protected List<WebElement> findElements(By by) {
        List<WebElement> webElements;
        untilElementAppear(by);
        webElements = driver.findElements(by);
        return webElements;
    }

    protected void sendKeys(By by, String text) throws InterruptedException {
        WebElement element;
        element = findElement(by);
        if (element.isEnabled()) {
            element.sendKeys(text);
        }
    }

    protected void click(By by, int... index) throws InterruptedException {
        WebElement element;
        element = findElement(by, index);
        element.click();
    }

    protected void control(boolean condition, String onTrue, String onFalse) {
        Assert.assertTrue(onFalse,condition);
        log.info(onTrue);
    }

    protected boolean isElementExist(By by) {
        return isElementExist(by, DEFAULT_WAIT);
    }

    protected boolean isElementExist(By by, int timeSeconds) {
        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
        boolean isExist = driver.findElements(by).size() > 0;
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        return isExist;
    }

    protected String getTextOfElement(By by, int... index) throws InterruptedException {
        WebElement element;
        String text;
        element = findElement(by, index);
        text = element.getText();
        return text;
    }

    protected void scrollMainWindow(int numberX, int numberY) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy('" + numberX + "','" + numberY + "')", "");
    }

    protected void hoverToElement(By by) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(by);
        action.moveToElement(we).build().perform();
    }

    protected void writeToTextFile(String productDetails, File fileName){
        try {
            FileWriter fw = new FileWriter(fileName, true);
            String lineSeparator = System.getProperty("line.separator");
            String[] output = productDetails.split("-");

            for (int i = 0; i <2; i++) {
                fw.write(output[i]);
                fw.write(lineSeparator);
            }

            //instead you could only use:
            //fw.write(info);

            fw.flush();
            fw.close();
        } catch (IOException e) {
            log.log(Level.SEVERE,e.getMessage(), e);
        }
    }

    protected void readTextFilecontrolProductDetails(String productDetails, File file){
        try {
            File myObj = new File(String.valueOf(file));
            Scanner myReader = new Scanner(myObj);
            int i=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(productDetails.contains(data)) {
                    log.info("control succesfull  "+productDetails.split("-")[i]);
                }else{
                    Assert.assertTrue("couldn't control product details ",false);
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE,e.getMessage(), e);
        }
    }
}

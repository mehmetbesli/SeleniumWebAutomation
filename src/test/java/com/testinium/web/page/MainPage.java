package com.testinium.web.page;

import com.testinium.web.base.BasePage;
import com.testinium.web.objectrepo.MainPageObjeRepo;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver){
        super(driver);
    }

    public MainPage gotoUrl(String url){
        goToUrl(url);
        log.info("Url opened");
        return this;
    }

    public MainPage clickSearch() throws InterruptedException {
        click(MainPageObjeRepo.search);
        log.info("clicked search");
        return this;
    }

    public MainPage sendkeySearch(String pc) throws InterruptedException {
        sendKeys(MainPageObjeRepo.search,pc);
        log.info(pc+" word typed");
        return this;
    }

    public MainPage clickFind() throws InterruptedException {
        click(MainPageObjeRepo.findBtn);
        log.info("clicked find button");
        return this;
    }

    public MainPage scrollDownMainWindow(int numberX,int numberY) throws InterruptedException {
        scrollMainWindow(numberX,numberY);
        log.info("clicked find button");
        return this;
    }

    public MainPage gotoSecondPage() throws InterruptedException {
        click(MainPageObjeRepo.nextPage);
        log.info("clicked next page");
        return this;
    }

    public MainPage controlSecondPage(String url) {
        String currentUrl = driver.getCurrentUrl();
        control((currentUrl.contains(url))," you are in second page"," you aren't in second page");
        return this;
    }

    public MainPage clickFirstProduct() throws InterruptedException {

        int attempts = 0;
        while(attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(MainPageObjeRepo.firstProduct));
                click(MainPageObjeRepo.firstProduct);
                log.info("clicked first product");
                break;
            } catch(StaleElementReferenceException e) {}
            attempts++;
        }

        return this;
    }

    public String getProductName() throws InterruptedException {
        return getTextOfElement(MainPageObjeRepo.productName);
    }

    public String getProductPrice() throws InterruptedException {
        return getTextOfElement(MainPageObjeRepo.productPrice);
    }

    public MainPage clickAddBasket() throws InterruptedException {
        if(isElementExist(MainPageObjeRepo.cookiePopupHandle,3)){
            click(MainPageObjeRepo.cookiePopupHandle);
            log.info("closed cookie popup");
        }

        wait.until(ExpectedConditions.elementToBeClickable(MainPageObjeRepo.addBasket));
        click(MainPageObjeRepo.addBasket);
        log.info("clicked add basket");
        return this;
    }

    public MainPage writeDataTextFile(String productDetails, File fileName) {
        writeToTextFile(productDetails,fileName);
        return this;
    }
}

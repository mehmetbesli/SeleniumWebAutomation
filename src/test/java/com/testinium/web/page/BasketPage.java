package com.testinium.web.page;

import com.testinium.web.base.BasePage;
import com.testinium.web.objectrepo.BasketPageObjeRepo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver){
        super(driver);
    }


    public BasketPage gotoBasket() throws InterruptedException {
        hoverToElement(BasketPageObjeRepo.myBasket);
        log.info("hovered my basket");

        wait.until(ExpectedConditions.elementToBeClickable(BasketPageObjeRepo.gotoBasket));
        click(BasketPageObjeRepo.gotoBasket);
        log.info("clicked go to basket");
        return this;
    }

    public BasketPage readAndControlData(String productDetails, File file) throws InterruptedException {
        readTextFilecontrolProductDetails(productDetails,file);
        return this;
    }

    public BasketPage increaseProductNumber(String number) throws InterruptedException {
        Select opt = new Select(findElement(BasketPageObjeRepo.selectValue));
        opt.selectByValue(number);
        log.info("increased product to number "+number);

        String element =findElement(BasketPageObjeRepo.selectValue).getAttribute("value");
        control(number.equalsIgnoreCase(element)," total product count is "+number," Total product count isn't "+number);
        return this;
    }

    public BasketPage deleteProduct() throws InterruptedException {
        click(BasketPageObjeRepo.deleteProduct);
        log.info("deleted product");
        return this;
    }

    public BasketPage controlBasketEmpty(String emptyProduct) throws InterruptedException {
        String emptyProductText=getTextOfElement(BasketPageObjeRepo.emptyProduct);
        control(emptyProductText.contains(emptyProduct)," basket is empty"," basket isn't empty");
        return this;
    }
}

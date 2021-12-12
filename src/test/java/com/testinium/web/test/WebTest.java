package com.testinium.web.test;

import com.testinium.web.base.BaseTest;
import com.testinium.web.ConstantValue;
import com.testinium.web.page.BasketPage;
import com.testinium.web.page.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WebTest extends BaseTest {

    MainPage mainPage;
    BasketPage basketPage;

    @Before
    public void before(){
        mainPage =new MainPage(getWebDriver());
        basketPage=new BasketPage(getWebDriver());
    }

    @Test
    public void test() throws InterruptedException {
        mainPage
                .gotoUrl(ConstantValue.url)
                .sendkeySearch(ConstantValue.pc)
                .clickFind()
                .scrollDownMainWindow(0,5000)
                .gotoSecondPage()
                .controlSecondPage("2")
                .clickFirstProduct();

        String productName=mainPage.getProductName();
        String productPrice=mainPage.getProductPrice();
        mainPage
                .writeDataTextFile(productName+"-"+productPrice,file)
                .clickAddBasket();
        basketPage
                .gotoBasket()
                .readAndControlData(productName+"-"+productPrice,file)
                .increaseProductNumber("2")
                .deleteProduct()
                .controlBasketEmpty(ConstantValue.emptyProduct);
    }

    @After
    public void after(){
        teardown();
        log.info("driver closed");
    }
}

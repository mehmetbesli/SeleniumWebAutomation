package com.testinium.web.objectrepo;

import org.openqa.selenium.By;

public class BasketPageObjeRepo {
    public static final By myBasket = By.cssSelector("span[class='basket-title']");
    public static final By gotoBasket = By.cssSelector("div[class='gg-w-24 gg-d-24 panel-content padding-none'] a[href='https://www.gittigidiyor.com/sepetim']");
    public static final By productName = By.cssSelector("div[class='text-box'] h2");
    public static final By productPrice = By.cssSelector("div[class='total-price'] strong");
    public static final By selectValue = By.cssSelector("select[class='amount']");
    public static final By deleteProduct = By.cssSelector("a[title='Sil']");
    public static final By emptyProduct = By.cssSelector("div[class='clearfix ']");



}

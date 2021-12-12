package com.testinium.web.objectrepo;

import org.openqa.selenium.By;

public class MainPageObjeRepo {
    public static final By search = By.cssSelector("input[placeholder='Keşfetmeye Bak']");
    public static final By findBtn = By.cssSelector("button[data-cy='search-find-button']");
    public static final By nextPage = By.cssSelector("a[title='Sonraki sayfa']");
    public static final By firstProduct = By.xpath("(//li[@class='sc-1nx8ums-0 dyekHG'])[1]//img");
    public static final By productName = By.id("sp-subTitle");
    public static final By productPrice = By.id("sp-price-lowPrice");
    public static final By addBasket = By.id("add-to-basket");
    public static final By cookiePopupHandle = By.cssSelector("div[class='gg-ui-button gg-ui-btn-secondary policy-alert-v2-buttons'] span");


}

package com.skorek.PageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver)
    {
        WebDriverManager.chromedriver().setup();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

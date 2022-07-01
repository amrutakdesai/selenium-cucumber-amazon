package com.skorek.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@id,\"uber-widget-ns\")]/div[1]/span/a")
    private List<WebElement> allMainCategoryLinks;

    public ProductCategoryPage goToBestSellingCameras(){
        allMainCategoryLinks.get(1).click();
        return new ProductCategoryPage(driver);
    }

    public ProductCategoryPage goToProductCategory(String category){
        String category_xpath = String.format("//span[contains(text(), \"%s\")]/parent::h2/following-sibling::span/a", category);
        driver.findElement(By.xpath(category_xpath)).click();
        return new ProductCategoryPage(driver);
    }
}

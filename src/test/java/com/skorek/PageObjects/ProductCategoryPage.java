package com.skorek.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCategoryPage extends PageObject {
    public ProductCategoryPage(WebDriver driver){
        super(driver);
    }

    public ProductPage goToProductOnPosition(int position){
        String xpath_to_position = String.format("//*[@id=\"zg_centerListWrapper\"]/div[%s]/div[2]/div/a/div[1]/img", position+1);
        WebElement element =  driver.findElement(By.xpath(xpath_to_position));
        element.click();
        return new ProductPage(driver);
    }

}

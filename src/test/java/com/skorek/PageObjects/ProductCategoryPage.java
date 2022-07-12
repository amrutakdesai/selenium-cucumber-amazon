package com.skorek.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCategoryPage extends PageObject {
    public ProductCategoryPage(WebDriver driver){
        super(driver);
    }

    public ProductPage goToProductOnPosition(int position){
        String xpath_to_position = String.format("(//a[@class=\"a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal\"])[%s]", position+1);
        WebElement element =  driver.findElement(By.xpath(xpath_to_position));
        element.click();
        return new ProductPage(driver);
    }

}

package com.example.testbing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//https://www.bing.com/
public class MainPage {
    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;


    public void sendText (String text){
        searchField.sendKeys(text);
        System.out.println("Введен текст: " + text);
        searchField.submit();
    }

    public MainPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

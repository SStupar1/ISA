package isa.demo;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class e2eTesting {
    public static final String BASE_URL = "http://localhost:4200";

    private WebDriver driver;

    @Test
    public void login(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/isa/demo/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL + "/login");
        WebElement mail = driver.findElement(By.id("username"));
        mail.sendKeys("pera.peric@gmail.com");
        WebElement pass = driver.findElement(By.id("password"));
        pass.sendKeys("123");
        WebElement btnLog = driver.findElement(By.id("log"));
        btnLog.click();

    }

}
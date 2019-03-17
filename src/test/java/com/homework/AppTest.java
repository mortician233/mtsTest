package com.homework;


import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class AppTest
{
    WebDriver driver = null;
    private ChromeOptions options = null;
    DesiredCapabilities capabilities = new DesiredCapabilities();



    @BeforeClass
    public void initData() {
        System.setProperty("webdriver.chrome.driver",
                "lib/chromedriver.exe");
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        driver = new ChromeDriver();
        driver.manage().window().maximize();


    }

    @Test()
    public void testCreateForm() {
        driver.navigate().to("https://moskva.mts.ru/personal");
        driver.findElement(By.xpath("//button[contains(text(),'Да')]")).isDisplayed();
        driver.findElement(By.xpath("//button[contains(text(),'Да')]")).click();
        driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='number']")).sendKeys("9023930349");
        driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/section[1]/div/div[1]/div/div/div/div[2]/div[1]/form/div[2]/div[3]/button")).click();
        WebElement elementNumber
                = driver.findElement(By.xpath("//input[@type='tel' and @data-type='phone' and @value='(902) 393-03-49']"));
        elementNumber.isEnabled();
        WebElement elementSum = driver.findElement(By.xpath("//input[@type='tel' and @name='Sum' and @value='500,00']"));
        elementSum.isEnabled();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}


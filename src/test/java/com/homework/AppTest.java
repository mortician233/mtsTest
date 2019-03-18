package com.homework;


import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AppTest
{
    private WebDriver driver = null;
    private DesiredCapabilities capabilities = null;
    private ChromeOptions options = null;


    @BeforeClass
    public void initData() {
        System.setProperty("webdriver.chrome.driver",
                "lib/chromedriver.exe");
        options = new ChromeOptions();
        options.merge(capabilities);
        /*
        Свойство не дожидаться загрузки всей страницы, добавлено из за загрузки динамических элементов, слишком долгое ожидание
         */
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @Test()
    public void testCreateForm() {
        driver.navigate().to("https://moskva.mts.ru/personal");
        /*
        Если автотест упал, необходимо увеличить время ожидания
         */
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(text(),'Да')]")).click();
        driver.findElement(By.xpath("//input[@name='number']")).sendKeys("9023930349");
        driver.findElement(By.xpath("//div[@class='tabs__content-item' and not (@style)]//button[text() = 'Пополнить']")).click();
        /*
        Проверка введенных полей на главной странице и сравнение URL
         */
        WebElement elementNumber
                = driver.findElement(By.xpath("//input[@type='tel' and @data-type='phone' and @value='(902) 393-03-49']"));
        elementNumber.isEnabled();
        WebElement elementSum = driver.findElement(By.xpath("//input[@type='tel' and @name='Sum' and @value='500,00']"));
        elementSum.isEnabled();
        System.out.println(driver.getCurrentUrl().equals("https://payment.mts.ru/"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}


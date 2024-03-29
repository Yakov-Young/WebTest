package ru.kemsu;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static ru.kemsu.Util.timeout;

public class WebTests {
    @Test
    public void habrTest() {

        System.setProperty("webdriver.chorme.driver",
                "C:\\Users\\Sibiryakov\\IdeaProjects\\testingWeb\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Переход на сайт
        driver.get("https://habr.com/ru/all/");
        driver.manage().window().setSize(new Dimension(1280, 1000));

        // Нажатие на кнопку перехода в поиск
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//a[@href='/ru/search/']")).click();

        // Проверка акивности элемента
        timeout(driver, 2);
        Assert.assertEquals(driver.findElement((
                By.xpath("//input[@name='q']"))), driver.switchTo().activeElement());

        // Ввод значения для поиска
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//input[@name='q']")).sendKeys("Selenium WebDriver");

        // Выполнение поиска
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//*[@class='tm-svg-img tm-svg-icon']")).click();

        // Переход в статью
        timeout(driver, 2);
        driver.findElement(
                By.linkText("Что такое Selenium WebDriver?")).click();

        // Сравнение даты
        Assert.assertEquals("1 окт 2012 в 16:40",
                driver.findElement(
                        By.xpath("//*[@datetime='2012-10-01T09:40:36.000Z']")).getText());

        // Скролл страницы
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Переход в публикации
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//a[@href='/ru/articles/' and @class='footer-menu__item-link router-link-active']")).click();

        driver.quit();
    }

    @Test
    public void mailTest() {

        System.setProperty("webdriver.chorme.driver",
                "C:\\Users\\Sibiryakov\\IdeaProjects\\testingWeb\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Переход на сайт
        driver.get("https://mail.ru/");
        driver.manage().window().setSize(new Dimension(1280, 1000));

        // Открытие формы входа
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//button[@class='ph-login svelte-1au561b']")
        ).click();

        // Введение логина
        timeout(driver, 10);
        driver.switchTo().frame(
                driver.findElement(By.cssSelector(".ag-popup__frame__layout__iframe"))
        );
        driver.findElement(
                By.xpath("//input[@class='input-0-2-71']")
        ).sendKeys("testich.testovyy");

        // Переход к форме ввода пароля
        timeout(driver,2);
        driver.findElement(
                By.xpath("//*[@class='inner-0-2-81 innerTextWrapper-0-2-82']")
        ).click();

        // Введение пароля
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//input[@class='input-0-2-71 withIcon-0-2-72']")
        ).sendKeys("OU3PAi3erto*");

        // Вход
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//*[@class='inner-0-2-81 innerTextWrapper-0-2-82']")
        ).click();

        // Проверка имени
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//img[@class='ph-avatar-img svelte-dfhuqc']")
        ).click();

        Assert.assertEquals("Тестовый Тестич",
                driver.findElement(By.xpath("//*[@class='ph-name svelte-1popff4']")).getText()
        );

        // Выход из аккаунта
        timeout(driver, 2);
        driver.findElement(
                By.xpath("//*[@class='ph-item ph-item__social svelte-1popff4' and @data-testid='whiteline-account-exit']")
        ).click();

        // Проверка наличия на странице элемента
        timeout(driver, 5);
        Assert.assertTrue(
                driver.findElement(By.xpath("//a[@class='resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big dcd-cfdg-b85yax']"))
                        .isDisplayed()
        );

        driver.quit();
    }
}

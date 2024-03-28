package ru.kemsu;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Util {
    static void timeout(WebDriver driver, int second) {driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(second));}

}

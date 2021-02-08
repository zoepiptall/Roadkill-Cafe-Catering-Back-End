package selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

    public Selenium(){
        File file = new File(
                "src/main/resources/selenium/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://google.com");
        
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
        WebElement searchBar = webDriver.findElement(By.name("q"));
    }

}

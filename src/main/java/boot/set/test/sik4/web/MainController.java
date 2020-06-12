package boot.set.test.sik4.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainController {

	public static void main(String[] args) {
        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", "c:/Users/4depth/Downloads/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com/search?q=롯데리아+역삼");

        List<WebElement> img = driver.findElements(By.className("rISBZc"));
        System.out.println("1111 : " + img.get(0).getAttribute("src"));

//        WebElement img2 = driver.findElement(By.id("dimg_31"));
//        System.out.println("id path : " + img2.getAttribute("src"));

        driver.quit();

    }

}

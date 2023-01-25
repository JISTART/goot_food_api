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
        options.addArguments("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com/?q=테일러커피+연남점&t=ht&iax=images&ia=images");

        List<WebElement> img = driver.findElements(By.className("tile--img__img"));
        System.out.println("1111 : " + img.get(0).getAttribute("src"));

        WebElement img2 = driver.findElement(By.id("dimg_31"));
        System.out.println("id path : " + img2.getAttribute("src"));

        driver.quit();

    }

}
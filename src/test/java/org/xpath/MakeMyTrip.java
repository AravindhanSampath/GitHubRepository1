package org.xpath;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {
	WebDriver driver;
	WebElement element;

	@BeforeClass
	private void lauchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
	}
	
	@Test
	private void search() {
//		driver.navigate().refresh();
		driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		driver.findElement(By.xpath("//a[text()='Search']")).click();
		String journeyPrice = driver.findElement(By.xpath("(//p[contains(@class,'blackText')]//ancestor::div[@class='priceSection'])[1]")).getText();
		System.out.println("Your Journey will be cost of " + journeyPrice);
	}
	
	@AfterClass
	private void closeBrowser() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}
}

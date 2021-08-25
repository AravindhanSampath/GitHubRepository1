package org.xpath;

import java.awt.AWTException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	WebDriver driver;
	WebElement element;
	String firstWindow;

	@BeforeClass
	private void lauchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
	}

	@Test
	private void serachText() throws AWTException {
		driver.findElement(By.xpath("//input[contains(@id,'searchtext')]")).sendKeys("Iphone", Keys.ENTER);
	}

	@Test(priority = 1)
	private void enteringProductDetails() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		firstWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("(//span[contains(@class, 'a-size-')])[6]")).click();
	}

	@Test(priority = 2)
	private void productDetails() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Set<String> secondWindows = driver.getWindowHandles();
		for (String windows : secondWindows) {
			if (windows != firstWindow)
				driver.switchTo().window(windows);
		}
		element = driver
				.findElement(By.xpath("//div[@id='unifiedPrice_feature_div']//child::span[@id='priceblock_ourprice']"));
		String price = element.getText();
		System.out.println("Pricing is " + price);

		element = driver.findElement(By.xpath("//a[text()='FREE delivery:']//following-sibling::b"));
		String deliveryDate = element.getText();
		System.out.println("Your expected Free delivery date is " + deliveryDate);
	}
}

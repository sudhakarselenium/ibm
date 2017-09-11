package com.tng;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ApsrtcSearch_DDT {
	WebDriver driver;

	@BeforeClass
	public void openApplication() {
		System.out.println("First change");
		System.out.println("second change");
		driver = DriverFactory.getDriver("chrome");

		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		// goto apsrtc
		driver.get("http://www.apsrtconline.in/");

	}

	@Test(dataProvider = "getData")
	public void Search(String fromName,String toName ) {
		// String fromName = "HYDERABAD";
		// String toName = "VISAKHAPATNAM";

		fromName = fromName.toUpperCase();
		toName = toName.toUpperCase();
		driver.findElement(By.id("fromPlaceName")).sendKeys(fromName);
		driver.findElement(By.linkText(fromName)).click();

		driver.findElement(By.id("toPlaceName")).sendKeys(toName);
		driver.findElement(By.linkText(toName)).click();

		driver.findElement(By.id("txtJourneyDate")).click();

		WebElement elmCalendar = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']"));

		// click on last date in first month of calendar
		List<WebElement> lstCalDates = elmCalendar
				.findElements(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//a"));

		lstCalDates.get(lstCalDates.size() - 1).click();

		WebDriverWait w = new WebDriverWait(driver, 10);

		w.until(ExpectedConditions.elementToBeClickable(By.id("searchBtn")));

		driver.findElement(By.id("searchBtn")).click();
		List<WebElement> lstSerNos = driver.findElements(By.className("srvceNO"));
		for (WebElement elm : lstSerNos) {
			System.out.println(elm.getText());
		}

		driver.findElement(By.linkText("Home")).click();
	}

	@AfterClass
	public void closeApplication() {
		DriverFactory.closeApplication();
	}

	@DataProvider(name = "gt")
	public Object[][] getData() {

		String[][] tcData = new String[2][2];
		// 1st time
		tcData[0][0] = "HYDERABAD";
		tcData[0][1] = "Visakhapatnam";

		// 2nd time
		tcData[1][0] = "HYDERABAD";
		tcData[1][1] = "tirupathi";

		return tcData;
	}

}













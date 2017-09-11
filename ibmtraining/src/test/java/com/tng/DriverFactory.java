package com.tng;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	public static WebDriver driver;

	public static WebDriver getDriver(String brName) {

		switch (brName) {
		case "chrome":
			// specify browser driver
			System.setProperty("webdriver.chrome.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\chromedriver.exe");

			// open new chrome window
			driver = new ChromeDriver();
			break;
		case "firefox":
			// specify browser driver
			System.setProperty("webdriver.gecko.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\geckodriver.exe");

			// open new firefox window
			driver = new FirefoxDriver();
			break;
		case "edge":
			// specify browser driver
			System.setProperty("webdriver.edge.driver", "F:\\SeleniumSoftware\\BrowserDrivers\\MicrosoftWebDriver.exe");

			// open new firefox window
			driver = new EdgeDriver();
			break;
		default:
			return null;
		}

		return driver;
	}

	public static WebDriver getRemoteDriver(String brName) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		switch (brName) {
		case "chrome":
			cap.setBrowserName("chrome");
			// open new chrome window
			return new RemoteWebDriver(new URL("http://9.234.208.105:4444/wd/hub"), cap);

		case "firefox":
			cap.setBrowserName("firefox");
			// open new ff window
			return new RemoteWebDriver(new URL("http://9.234.208.105:4444/wd/hub"), cap);

		case "edge":
			cap.setBrowserName("edge");
			// open new ff window
			return new RemoteWebDriver(new URL("http://9.234.208.105:4444/wd/hub"), cap);

		case "safari":
			// cap.setBrowserName("safari");
			// open new ff window
			return new RemoteWebDriver(new URL("http://9.234.208.105:4444/wd/hub"), cap.safari());
		default:
			return null;
		}
	}

	public static WebDriver getRemoteDriver(String brName, String nUrl) throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		switch (brName) {
		case "chrome":
			cap.setBrowserName("chrome");
			// open new chrome window
			driver = new RemoteWebDriver(new URL(nUrl), cap);
			 break;
		case "firefox":
			cap.setBrowserName("firefox");
			// open new ff window
			driver = new RemoteWebDriver(new URL(nUrl), cap);
			break;
		case "edge":
			cap.setBrowserName("edge");
			// open new ff window
			driver = new RemoteWebDriver(new URL(nUrl), cap);
			break;
		case "safari":
			// cap.setBrowserName("safari");
			// open new ff window
			driver = new RemoteWebDriver(new URL(nUrl), cap);
			break;
		default:
			driver = null;
			break;
		}
		return driver;

	}

	public static void openApplication(String appUrl) {

		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		// goto apsrtc
		driver.get(appUrl);

	}

	public static void closeApplication() {
		driver.quit();
	}
}

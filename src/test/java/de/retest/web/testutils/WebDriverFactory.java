package de.retest.web.testutils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

	// With several drivers an enum would be useful?!
	public static final String CHROME_DRIVER = "chromedriver";
	public static final String FIREFOX_DRIVER = "firefoxdriver";
	public static final String EDGE_DRIVER = "edgedriver";
	public static final String IE_DRIVER = "iedriver";
	public static final String SAFARI_DRIVER = "safaridriver";

	public static WebDriver getWebDriver( final String driver ) {
		switch ( driver ) {
			case CHROME_DRIVER: {
				return new ChromeDriver(
						new ChromeOptions().addArguments( "--headless", "--no-sandbox", "--window-size=1200,800" ) );
			}
			case FIREFOX_DRIVER: {
				return new FirefoxDriver( new FirefoxOptions().addArguments( "--headless", "--window-size=1200,800" ) );
			}
			default:
				throw new RuntimeException( "There is not a supported web-driver named " + driver );
		}
	}

	public static List<WebDriver> getWebDriverList() {
		return new ArrayList<WebDriver>() {
			{
				add( getWebDriver( CHROME_DRIVER ) );
				add( getWebDriver( FIREFOX_DRIVER ) );
			}
		};
	}
}

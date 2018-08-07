package de.retest.web;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.web.selenium.By;
import de.retest.web.selenium.RecheckDriver;

public class ShowcaseWrapperIT {

	private WebDriver driver;
	private Recheck re;

	@Before
	public void setup() {
		// If ChromeDriver is not in your PATH, uncomment this and point to your installation.
		// e.g. it can be downloaded from http://chromedriver.chromium.org/downloads
		//		System.setProperty( "webdriver.chrome.driver", "path/to/chromedriver" );

		final ChromeOptions opts = new ChromeOptions();
		opts.addArguments(
				// Enable headless mode for faster execution.
				"--headless",
				// Use Chrome in container-based Travis CI environment (see https://docs.travis-ci.com/user/chrome#Sandboxing).
				"--no-sandbox",
				// Fix window size for stable results.
				"--window-size=1200,800" );
		driver = new RecheckDriver( new ChromeDriver( opts ) );

		// Use the default implementation.
		re = new RecheckImpl();
	}

	@Test
	public void index() throws Exception {
		// Set the file name of the Golden Master.
		re.startTest( "showcase" );

		// Open page.
		final Path showcasePath = Paths.get( "src/test/resources/pages/formPage.html" );
		driver.get( showcasePath.toUri().toURL().toString() );

		// Ensure correct page and enable unbreakable recognition.
		re.check( driver, "initial" );

		// Find element by retestId, being practically unbreakable.
		driver.findElement( By.retestId( "rb-peas" ) );

		// Single call instead of multiple assertions (doesn't fail on differences).
		re.check( driver, "index" );

		// Conclude the test case (fails on differences).
		re.capTest();
	}

	@After
	public void tearDown() {
		driver.quit();

		// Produce the result file.
		re.cap();
	}

}

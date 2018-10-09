package de.retest.web;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.web.testutils.FirefoxOptionsFactory;


/*	TODO: Add a list of different web drivers so that each website will be tested with the drivers from the list.
	TODO: e.g. https://gist.github.com/dwelch2344/3907781
*/

// FirefoxTest is not a final class, it is only a prototype for the first test of another Webdriver.
public class FirefoxTest {

	WebDriver driver;
	Recheck re;

	@BeforeEach
	void setUp() {
		driver = new FirefoxDriver( FirefoxOptionsFactory.createNewInstance() );

		re = new RecheckImpl();
	}


	@Test
	void simple_html_page_should_be_checked_with_firefox() throws Exception {
		re.startTest( "simple-page-Firefox" );

		final Path simplePagePath = Paths.get("src/test/resources/pages/simple-page.html" );
		driver.get( simplePagePath.toUri().toURL().toString() );

		re.check( driver, "open" );

		re.capTest();
	}

	@AfterEach
	void tearDown() {
		driver.quit();

		re.cap();
	}

}

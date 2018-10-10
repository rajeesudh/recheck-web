package de.retest.web;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import de.retest.web.testutils.WebDriverFactory;

// WebDriverTest is not a final class, it is only a prototype for the first test of a new web driver.
public class WebDriverTest {

	List<WebDriver> drivers;
	Recheck re;

	/*
	 * @BeforeEach void setUp() {
	 * 
	 * //drivers = WebDriverListProvider.getWebDriverList();
	 * 
	 * //re = new RecheckImpl(); }
	 */

	@Test
	void simple_html_page_should_be_checked_with_all_drivers() throws Exception {

		drivers = WebDriverFactory.getWebDriverList();

		for ( final WebDriver driver : drivers ) {

			re = new RecheckImpl();

			re.startTest( "simple-page-" + driver.getClass().getSimpleName() );

			final Path simplePagePath = Paths.get( "src/test/resources/pages/simple-page.html" );
			driver.get( simplePagePath.toUri().toURL().toString() );

			re.check( driver, "index" );

			// Conclude the test case (fails on differences).
			re.capTest();

			// Produce the result file.
			re.cap();
		}
	}

	/*
	 * If an error occurs in the first web-driver test all further tests with other web-drivers will not be performed.
	 */

	/*
	 * @AfterEach void tearDown() { re.cap(); }
	 */

}

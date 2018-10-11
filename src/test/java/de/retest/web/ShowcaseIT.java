package de.retest.web;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;

class ShowcaseIT {

	Recheck re;

	@BeforeEach
	void setUp() {
		re = new RecheckImpl();
	}

	@ParameterizedTest
	@MethodSource( "de.retest.web.testutils.WebDriverFactory#drivers" )
	void showcase_html_should_be_checked( final WebDriver driver ) throws Exception {
		re.startTest( "showcase-" + driver.getClass().getSimpleName() );

		final Path showcasePath = Paths.get( "src/test/resources/pages/showcase/retest.html" );
		driver.get( showcasePath.toUri().toURL().toString() );

		re.check( driver, "index" );
	}

	@AfterEach
	void tearDown() {
		re.capTest();
		re.cap();
	}

}

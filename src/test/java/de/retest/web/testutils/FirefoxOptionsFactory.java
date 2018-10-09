package de.retest.web.testutils;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxOptionsFactory {


	private FirefoxOptionsFactory() {}

	public static FirefoxOptions createNewInstance( final String... args ) {

		final FirefoxOptions opts = new FirefoxOptions();
		opts.addArguments(
				// Enable headless mode for faster execution.
				"--headless",
				// Fix window size for stable results.
				"--window-size=1200,800");
		opts.addArguments( args );
		return opts;
	}
}

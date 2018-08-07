package de.retest.web.selenium;

public abstract class By extends org.openqa.selenium.By {

	public static ByRetestId retestId( final String retestId ) {
		return new ByRetestId( retestId );
	}

}

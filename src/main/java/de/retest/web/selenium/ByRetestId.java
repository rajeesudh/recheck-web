package de.retest.web.selenium;

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.retest.ui.descriptors.Element;
import de.retest.ui.descriptors.RootElement;

public class ByRetestId extends By implements Serializable {

	private static final long serialVersionUID = -3787115401615364934L;

	private static final Logger logger = LoggerFactory.getLogger( ByRetestId.class );

	private final String retestId;

	public ByRetestId( final String retestId ) {
		this.retestId = retestId;
	}

	@Override
	public List<WebElement> findElements( final SearchContext context ) {
		logger.info( "findElements called with {} of {}.", context, context.getClass() );
		throw new UnsupportedOperationException( "This method makes code transition easier, but cannot be called." );
	}

	public Element findElement( final RootElement lastCheckedState ) {
		if ( lastCheckedState == null ) {
			throw new IllegalArgumentException( "Cannot find element in null state." );
		}
		// TODO Go through all elements and find the one with the given retestId
		throw new RuntimeException( "TODO Implement!" );
	}

}

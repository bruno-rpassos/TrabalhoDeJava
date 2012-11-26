package util;

public class StringUtils {
	public static String camelCaseToUnderscore( final String s ) {
		return s.replaceAll( String.format( "%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])" ), "_" ).toLowerCase();
	}

	public static String underscoreToCamelCase( final String s ) {
		final String[] parts = s.split( "_" );
		final StringBuilder camelCaseString = new StringBuilder();
		for ( final String part : parts )
			camelCaseString.append( StringUtils.toProperCase( part ) );

		final String first = camelCaseString.substring( 0, 1 ).toLowerCase();
		final String rest = camelCaseString.substring( 1 );

		return first + rest;
	}

	private static String toProperCase( final String s ) {
		return s.substring( 0, 1 ).toUpperCase() + s.substring( 1 ).toLowerCase();
	}
}

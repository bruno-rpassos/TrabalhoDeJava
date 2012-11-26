package util;

public class StringUtils {
	public static String underscoreToCamelCase(String s) {
		String[] parts = s.split("_");
		StringBuilder camelCaseString = new StringBuilder();
		for (String part : parts) {
			camelCaseString.append(toProperCase(part));
		}

		String first = camelCaseString.substring(0, 1).toLowerCase();
		String rest = camelCaseString.substring(1);

		return first + rest;
	}

	private static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	public static String camelCaseToUnderscore(String s) {
		return s.replaceAll(
				String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
						"(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"),
				"_").toLowerCase();
	}
}

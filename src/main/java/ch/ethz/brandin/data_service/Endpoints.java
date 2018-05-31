package ch.ethz.brandin.data_service;

public class Endpoints {
	
	private static final String QUERY_ID = "17888483320059182";
	private static final String FIRST = "20";
	private static final String QUERY_PARAMETER = "__a=1";
	
	private static final String ACCOUNT_INFO_URL = "https://www.instagram.com/{user_name}";
	public static final String variables = "{\"id\":\"{userId}\",\"first\":{first},\"after\":\"{endCursor}\"}";
	private static final String ACCOUNT_MEDIA_URL = "https://www.instagram.com/graphql/query/?query_id={query_id}&variables={variables}";

	
	public static String getProfileUrl(String profileName) {
		return ACCOUNT_INFO_URL.
				replace("{user_name}", profileName).
				replace("{query_parameter}", QUERY_PARAMETER);
	}

	public static String getProfileMediaUrl() {
		return ACCOUNT_MEDIA_URL.
				replace("{query_id}", QUERY_ID);
	}
	
	public static String getMediaVariables(long userId, String endCursor) {
		return variables.
				replace("{first}", FIRST).
				replace("{userId}", Long.toString(userId)).
				replace("{endCursor}", endCursor);
	}
}

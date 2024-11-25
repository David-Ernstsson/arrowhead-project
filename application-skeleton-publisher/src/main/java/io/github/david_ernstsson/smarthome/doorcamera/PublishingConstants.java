package io.github.david_ernstsson.smarthome.doorcamera;

public class PublishingConstants {

	//=================================================================================================
	// members

	public static final String BASE_PACKAGE = "ai.aitia";

	public static final String INTERFACE_SECURE = "HTTP-SECURE-JSON";
	public static final String INTERFACE_INSECURE = "HTTP-INSECURE-JSON";
	public static final String HTTP_METHOD = "http-method";

	public static final String SERVICE_LIMIT="service_limit";
	public static final int DEFAULT_SERVICE_LIMIT=200;
	public static final String $SERVICE_LIMIT_WD="${"+SERVICE_LIMIT+":"+DEFAULT_SERVICE_LIMIT+"}";

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private PublishingConstants() {
		throw new UnsupportedOperationException();
	}
}

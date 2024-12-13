package io.github.david_ernstsson.smarthome.radiator;

public class DoorCameraConstants {

	//=================================================================================================
	// members

	public static final String BASE_PACKAGE = "ai.aitia";
	public static final String GET_STATE_SERVICE_DEFINITION = "get-state";
	public static final String GET_STATE_URI = "/state";

	public static final String INTERFACE_SECURE = "HTTP-SECURE-JSON";
	public static final String INTERFACE_INSECURE = "HTTP-INSECURE-JSON";
	public static final String HTTP_METHOD = "http-method";

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private DoorCameraConstants() {
		throw new UnsupportedOperationException();
	}
}

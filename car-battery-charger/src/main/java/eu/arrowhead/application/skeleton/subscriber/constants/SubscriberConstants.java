package eu.arrowhead.application.skeleton.subscriber.constants;

public class SubscriberConstants {
	//=================================================================================================
	// members

	public static final String NOTIFICATION_QUEUE = "notifications";
	public static final String START_INIT_NOTIFICATION_URI = "/" + "startinit";
	public static final String START_RUN_NOTIFICATION_URI = "/" + "startrun";
	public static final String ELECTRICITY_PRICE_LOW = "/" + "electricitypricelow";
	public static final String ELECTRICITY_PRICE_HIGH = "/" + "electricitypricehigh";
	public static final String PRESET_EVENT_TYPES = "preset_events";
	public static final String $PRESET_EVENT_TYPES_WD = "${" + PRESET_EVENT_TYPES + ":" + SubscriberDefaults.DEFAULT_PRESET_EVENT_TYPES + "}";
	
	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private SubscriberConstants() {
		throw new UnsupportedOperationException();
	}
}

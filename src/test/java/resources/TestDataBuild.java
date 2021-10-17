package resources;

import java.util.Arrays;

import pojo_classes.*;

public class TestDataBuild {

	public static AddPlace addPlacePayload(String name, String language, String address) {
		Location lp = new Location();
		lp.setLat(-38.383494);
		lp.setLng(33.427362);
		AddPlace app = new AddPlace();		
		app.setLocation(lp);
		app.setAccuracy(50);
		app.setName(name);
		app.setPhone_number("(+91) 983 893 3937");
		app.setAddress(address);
		app.setTypes(Arrays.asList("shoe park", "shop"));
		app.setWebsite("http://google.com");
		app.setLanguage(language);
		return app;
	}
	
	public static String deletePlacePayload(String placeId) {
		String str = "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}";
		return str;
	}
}
package com.test;



import java.util.HashMap;
import java.util.Map;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.serverside.Event;
import com.facebook.ads.sdk.serverside.EventRequest;
import com.facebook.ads.sdk.serverside.EventResponse;
import com.facebook.ads.sdk.serverside.UserData;
import com.facebook.ads.sdk.serverside.CustomData;

public class ServerSideApiExample {

  public static final String ACCESS_TOKEN = "<ACCESS_TOKEN>";
  public static final String PIXEL_ID = "<ADS_PIXEL_ID>";

  public static void main(String[] args) {
    APIContext context = new APIContext(ACCESS_TOKEN).enableDebug(true);
    context.setLogger(System.out);

    UserData userData = new UserData()
    	.leadId("1234");

    HashMap<String, String> custFBLeadData = new HashMap<String, String>();
    
    custFBLeadData.put("lead_event_source", "FB");
    custFBLeadData.put("facebook_lifecycle_stage_name", "LEAD");
    
    CustomData customData = new CustomData();
    	customData
    				.setCustomProperties(custFBLeadData);
        //.currency("usd")
        //.value(123.45F);

    Event pageViewEvent = new Event();
    pageViewEvent.eventName("Purchase")
        .eventTime(System.currentTimeMillis() / 1000L)
        .userData(userData)
        .customData(customData);

    EventRequest eventRequest = new EventRequest(PIXEL_ID, context);
    eventRequest.addDataItem(pageViewEvent);

    try {
      EventResponse response = eventRequest.execute();
      System.out.println(String.format("Standard API response : %s ", response));
    } catch (APIException e) {
      e.printStackTrace();
    }
  }
}




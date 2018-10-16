package com.linli.consumer.utils;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Event extends Object implements Parcelable{

	/**
	 * event type:
	 * "Region_into": region event;
	 * "Region_leave": region event;
	 * "Beacon_into": beacon event;
	 * "Beacon_leave": beacon event;
	 */
	static public String EVENTTYPE_REGION_INTO = "region_into";
	static public String EVENTTYPE_REGION_LEAVE = "region_leave";
	static public String EVENTTYPE_BEACON_INTO = "beacon_into";
	static public String EVENTTYPE_BEACON_LEAVE = "beacon_leave";
	
	private boolean hasMsg;
	
	private String eventType;
	
	/**
	 * if a event type is regionXX, it should has a region identifier,
	 * otherwise, regionIdentifier is null
	 */
	private String regionIdentifier;
	
	/**
	 * beacon's UUID which triggered the event 
	 */
	private String eventRelateBeaconUUID;
	
	/**
	 * the event message that server delivered
	 */
	private String eventMessage;
	
	/**
	 * each event contains a url to connect
	 */
	private String urlToConnect;
	
	private Bitmap eventImage;
	
	/**
	 * 
	 * @param e_Type
	 * @param e_regionIdentifier
	 * @param e_RelateVeaconUUID
	 * @param e_Message
	 * @param e_url
	 */

	public Event(boolean e_hasMsg, String e_Type, String e_regionIdentifier, String e_RelateVeaconUUID, String e_Message, String e_url, Bitmap e_Image) {
		this.hasMsg = e_hasMsg;
		this.eventType = e_Type;
		this.regionIdentifier = e_regionIdentifier;
		this.eventRelateBeaconUUID = e_RelateVeaconUUID;
		this.eventMessage = e_Message;
		this.urlToConnect = e_url;
		this.eventImage = e_Image;
	}

	public boolean getHasMsg(){
		return hasMsg;
	}
	
	public String getEventType() {
		return eventType == null? "Not define" : eventType;
	}

	public String getRegionIdentifier() {
		return regionIdentifier == null? "Not define" : regionIdentifier;
	}
	
	public String getEventRelateBeaconUUID() {
		return eventRelateBeaconUUID == null? "Not define" : eventRelateBeaconUUID;
	}

	public String getEventMessage() {
		return eventMessage == null? "Not define" : eventMessage;
	}
	
	public String getUrlToConnect(){
		return urlToConnect;
	}
	
	public Bitmap getEventImage(){
		return eventImage;
	}

	@Override
	public int hashCode() {
		int result = (eventRelateBeaconUUID.substring(9, 36)).hashCode();
		result = 31 * result + eventType.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object another) {
		if (this == another)
			return true;
		if (another == null || getClass() != another.getClass())
			return false;

		Event event = (Event) another;
		if ((eventRelateBeaconUUID.substring(9, 36))
				.equals(event.eventRelateBeaconUUID.substring(9, 36)))
			return eventType.equals(event.eventType);
		else
			return false;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		// TODO Auto-generated method stub
		parcel.writeBooleanArray(new boolean[]{hasMsg});
		parcel.writeString(eventType);
		parcel.writeString(regionIdentifier);
		parcel.writeString(eventRelateBeaconUUID);
		parcel.writeString(eventMessage);
		parcel.writeString(urlToConnect);
	}
	
	public static final Creator<Event> CREATOR = new Creator<Event>() {
		public Event createFromParcel(Parcel source) {
			return new Event(source);
		}

		public Event[] newArray(int size) {
			return new Event[size];
		}
	};
	
	private Event(Parcel parcel)
    {
		hasMsg = parcel.readByte() == 1 ? true : false;		
		eventType = parcel.readString();
		regionIdentifier = parcel.readString();
		eventRelateBeaconUUID = parcel.readString();
		eventMessage = parcel.readString();
		urlToConnect = parcel.readString();
		eventImage = null;
    }
}

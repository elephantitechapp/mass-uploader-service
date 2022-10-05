package com.elephantitech.massuploader.entity;

import lombok.Data;

@Data
public class Location {
	private String locationId;
	private String name;
	private String landmark;
	private Double latitude;
	private Double longitude;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String pincode;

}
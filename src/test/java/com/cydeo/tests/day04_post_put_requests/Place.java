package com.cydeo.tests.day04_post_put_requests;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Place {

    @SerializedName("place name")
    @Expose
    public String placeName;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("state abbreviation")
    @Expose
    public String stateAbbreviation;
    @SerializedName("latitude")
    @Expose
    public String latitude;

}
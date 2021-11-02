package com.cydeo.tests.day04_post_put_requests;
import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ZipCodeInfo {

    @SerializedName("post code")
    @Expose
    public String postCode;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("country abbreviation")
    @Expose
    public String countryAbbreviation;
    @SerializedName("places")
    @Expose
    public List<Place> places = null;

}

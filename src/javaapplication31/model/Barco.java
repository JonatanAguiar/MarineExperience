/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31.model;

/**
 *
 * @author jonat
 */
public class Barco {
    private String radioChannelCode;
    private String mmsi;
    private Double latitude;
    private Double longitude;
    private Double trueHeading;

    
    
    public String getRadioChannelCode() {
        return radioChannelCode;
    }

    public String getMmsi() {
        return mmsi;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getTrueHeading() {
        return trueHeading;
    }

    @Override
    public String toString() {
        return "canal: "+this.radioChannelCode+", MMSI: "+this.mmsi+", Lat: "+this.latitude+", Long: "+this.longitude+", dir: "+trueHeading;
    }
}

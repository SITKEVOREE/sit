package org.kevoreetp.common.model;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 09/02/13
 * Time: 16:16
 * To change this template use File | Settings | File Templates.
 */
public class Follower extends  Message {

    public String matricule;
    public int lat;
    public int lon;
    public int altitude;
    public int safety_distance;
    public int accuracy;
    public Boolean isfollowed;

    public float temperature;
    public float heartmonitor;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getSafety_distance() {
        return safety_distance;
    }

    public void setSafety_distance(int safety_distance) {
        this.safety_distance = safety_distance;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public Boolean getIsfollowed() {
        return isfollowed;
    }

    public void setIsfollowed(Boolean isfollowed) {
        this.isfollowed = isfollowed;
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHeartmonitor() {
        return heartmonitor;
    }

    public void setHeartmonitor(float heartmonitor) {
        this.heartmonitor = heartmonitor;
    }
}

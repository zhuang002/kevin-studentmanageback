/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementbackend;

/**
 *
 * @author zhuan
 */
class Address {
    String room;
    String streetNumber;
    String steet;
    String city;
    String province;
    String postcode;

    public void setRoom(String room) {
        this.room = room;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setSteet(String steet) {
        this.steet = steet;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRoom() {
        return room;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getSteet() {
        return steet;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostcode() {
        return postcode;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Jessie
 */
public class Trip implements Serializable{

    private int id;
    private String depTime;
    private String terTime;
    private float price;
    private int totalSeat;
    private int availableSeat;
    private int routeId;
    private String departure;
    private String terminate;
    private String routeName;//thuộc tính phụ

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getTerminate() {
        return terminate;
    }

    public void setTerminate(String terminate) {
        this.terminate = terminate;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public void setTerTime(String terTime) {
        this.terTime = terTime;
    }

    public Trip(int id, String depTime, String terTime, float price, int totalSeat,
            int availableSeat, int routeId, String departure, String terminate) {
        this.id = id;
        this.depTime = depTime;
        this.terTime = terTime;
        this.price = price;
        this.totalSeat = totalSeat;
        this.availableSeat = availableSeat;
        this.routeId = routeId;
        this.departure = departure;
        this.terminate = terminate;
    }

    public Trip(int id, String depTime, String terTime, float price, int totalSeat, int availableSeat) {
        this.id = id;
        this.depTime = depTime;
        this.terTime = terTime;
        this.price = price;
        this.totalSeat = totalSeat;
        this.availableSeat = availableSeat;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getTerTime() {
        return terTime;
    }

    public Trip() {
    }

    public Trip(int routeID, String depTime, String terTime, float price, int totalSeat, int availableSeat, int id) {
        this.id = id;
        this.depTime = depTime;
        this.terTime = terTime;
        this.price = price;
        this.totalSeat = totalSeat;
        this.availableSeat = availableSeat;
        this.routeId = routeID;
    }
}

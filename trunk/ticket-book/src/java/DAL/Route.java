/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;

/**
 *
 * @author Jessie
 */
public class Route implements Serializable {

    private int routID;
    private int departure;
    private int terminate;
    private String name;
    private boolean isShow;

    public int getRoutID() {
        return routID;
    }

    public void setRoutID(int routID) {
        this.routID = routID;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getTerminate() {
        return terminate;
    }

    public void setTerminate(int terminate) {
        this.terminate = terminate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
}

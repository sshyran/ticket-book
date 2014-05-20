/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.Comparator;

/**
 *
 * @author Jessie
 */
public class StationComparator implements Comparator<Route> {

    private String stationType;
    private int stationID1;
    private int stationID2;

    public int getStationID1() {
        return stationID1;
    }

    public void setStationID1(int stationID1) {
        this.stationID1 = stationID1;
    }

    public int getStationID2() {
        return stationID2;
    }

    public void setStationID2(int stationID2) {
        this.stationID2 = stationID2;

    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public int compare(Route r1, Route r2) {
        if (stationType.equals("dep")) {
            stationID1 = r1.getDeparture();
            stationID2 = r2.getDeparture();
        } else {
            stationID1 = r1.getTerminate();
            stationID2 = r2.getTerminate();
        }
        //ascending order
        return stationID1 - stationID2;
    }
}

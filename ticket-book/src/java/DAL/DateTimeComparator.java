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
public class DateTimeComparator implements Comparator<Trip> {

    private String comType;
    private String dateTime1;
    private String dateTime2;

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getDateTime1() {
        return dateTime1;
    }

    public void setDateTime1(String dateTime1) {
        this.dateTime1 = dateTime1;
    }

    public String getDateTime2() {
        return dateTime2;
    }

    public void setDateTime2(String dateTime2) {
        this.dateTime2 = dateTime2;
    }

    public int compare(Trip t1, Trip t2) {
        if (comType.equals("dep")) {
            dateTime1 = t1.getDepTime();
            dateTime2 = t2.getDepTime();
        } else {
            dateTime1 = t1.getTerTime();
            dateTime2 = t2.getTerTime();
        }
        //ascending order
        return dateTime1.compareTo(dateTime2);
    }
}

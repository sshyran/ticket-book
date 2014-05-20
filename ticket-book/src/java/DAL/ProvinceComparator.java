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
public class ProvinceComparator implements Comparator<Station> {

    public int compare(Station s1, Station s2) {
        String province1 = s1.getProvince().toUpperCase();
        String province2 = s2.getProvince().toUpperCase();
        //ascending order
        return province1.compareTo(province2);
        //descending order
        //return fruitName2.compareTo(fruitName1);
    }
}

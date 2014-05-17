/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Jessie
 */
public class Station {
    private int id;
    private String sname;
    private String address;
    private String province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Station(int id, String sname, String address, String province) {
        this.id = id;
        this.sname = sname;
        this.address = address;
        this.province = province;
    }
    
    
    
}

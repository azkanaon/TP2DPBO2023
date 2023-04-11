/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpanelcard;

/**
 *
 * @author Faza
 */
public class Pelukis {
    private String nama;
    private String usia;
    
    public Pelukis(String nama, String usia){
        this.nama = nama;
        this.usia = usia;
    }
    
    // set
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setNilai(String usia) {
        this.usia = usia;
    }
    
    // get
    public String getNama() {
        return this.nama;
    }
    
    public String getUsia() {
        return this.usia;
    }
    
}

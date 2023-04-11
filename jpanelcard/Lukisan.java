/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpanelcard;

/**
 *
 * @author Faza
 */
public class Lukisan {
    private String judul;
    private String deskripsi;
    private int id_pelukis;
    private int id;
    private byte[] picture;
    
    public Lukisan(int id,String judul, String deskripsi, int id_pelukis, byte[] picture){
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.id_pelukis = id_pelukis;
        this.picture = picture;
    }
    
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public void setNilai(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    public void setIdPelukis(int id) {
        this.id_pelukis = id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getJudul() {
        return this.judul;
    }
    
    public String getDeskripsi() {
        return this.deskripsi;
    }
    
    public int getIdPelukis() {
        return this.id_pelukis;
    }
    
    public int getId() {
        return this.id;
    }
    
    public byte[] getImage(){
        return this.picture;
    }
}

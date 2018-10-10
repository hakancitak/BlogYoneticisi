/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogyoneticisi.siniflar.model;

import blogyoneticisi.siniflar.genel.Araclar;
import java.sql.Timestamp;

public class Yorum {
      private int yorumID;
      private int girdiID;
    private int k_ID;
    private String yorumBaslik;
    private String yorumIcerik;
    private Timestamp yorumTarih;
    
    public void hosgeldinYorumuHazirla(Girdi g,Kullanici k){
        this.setGirdiID(g.getGirdiID());
        this.setK_ID(k.getK_ID());
        this.setYorumBaslik("İlk Yorum");
        this.setYorumIcerik("Merhaba "+k.getK_adSoyad()+" bloguma hosgeldiniz.");
        this.setYorumTarih(Araclar.yeniTimeStampOlustur());
     
    }

    /**
     * @return the yorumID
     */
    public int getYorumID() {
        return yorumID;
    }

    /**
     * @param yorumID the yorumID to set
     */
    public void setYorumID(int yorumID) {
        this.yorumID = yorumID;
    }

    /**
     * @return the girdiID
     */
    public int getGirdiID() {
        return girdiID;
    }

    /**
     * @param girdiID the girdiID to set
     */
    public void setGirdiID(int girdiID) {
        this.girdiID = girdiID;
    }

    /**
     * @return the k_ID
     */
    public int getK_ID() {
        return k_ID;
    }

    /**
     * @param k_ID the k_ID to set
     */
    public void setK_ID(int k_ID) {
        this.k_ID = k_ID;
    }

    /**
     * @return the yorumBaslik
     */
    public String getYorumBaslik() {
        return yorumBaslik;
    }

    /**
     * @param yorumBaslik the yorumBaslik to set
     */
    public void setYorumBaslik(String yorumBaslik) {
        this.yorumBaslik = yorumBaslik;
    }

    /**
     * @return the yorumIcerik
     */
    public String getYorumIcerik() {
        return yorumIcerik;
    }

    /**
     * @param yorumIcerik the yorumIcerik to set
     */
    public void setYorumIcerik(String yorumIcerik) {
        this.yorumIcerik = yorumIcerik;
    }

    /**
     * @return the yorumTarih
     */
    public Timestamp getYorumTarih() {
        return yorumTarih;
    }

    /**
     * @param yorumTarih the yorumTarih to set
     */
    public void setYorumTarih(Timestamp yorumTarih) {
        this.yorumTarih = yorumTarih;
    }
}

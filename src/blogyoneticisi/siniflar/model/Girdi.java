package blogyoneticisi.siniflar.model;

import blogyoneticisi.siniflar.genel.Araclar;
import java.sql.Timestamp;

public class Girdi {
 
    private int girdiID;
    private int b_ID;
    private int k_ID;
    private String girdiBaslik;
    private String girdiIcerik;
    private Timestamp girdiTarih;
    
    public void hosgeldinGirdisiHazirla(Kullanici k,Blog b){
        this.setB_ID(b.getB_ID());
        this.setK_ID(k.getK_ID());
        this.setGirdiBaslik("Bloguma Hos Geldiniz.");
        this.setGirdiIcerik("Bu bir deneme girdisidir. Yeni Girdi Eklemek"+
                "yada bu girdiyi degistirmek icin giris linkini kullanınız");
        this.setGirdiTarih(Araclar.yeniTimeStampOlustur());
 
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
     * @return the b_ID
     */
    public int getB_ID() {
        return b_ID;
    }

    /**
     * @param b_ID the b_ID to set
     */
    public void setB_ID(int b_ID) {
        this.b_ID = b_ID;
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
     * @return the girdiBaslik
     */
    public String getGirdiBaslik() {
        return girdiBaslik;
    }

    /**
     * @param girdiBaslik the girdiBaslik to set
     */
    public void setGirdiBaslik(String girdiBaslik) {
        this.girdiBaslik = girdiBaslik;
    }

    /**
     * @return the girdiIcerik
     */
    public String getGirdiIcerik() {
        return girdiIcerik;
    }

    /**
     * @param girdiIcerik the girdiIcerik to set
     */
    public void setGirdiIcerik(String girdiIcerik) {
        this.girdiIcerik = girdiIcerik;
    }

    /**
     * @return the girdiTarih
     */
    public Timestamp getGirdiTarih() {
        return girdiTarih;
    }

    /**
     * @param girdiTarih the girdiTarih to set
     */
    public void setGirdiTarih(Timestamp girdiTarih) {
        this.girdiTarih = girdiTarih;
    }
    
}


package blogyoneticisi.siniflar.model;

import blogyoneticisi.siniflar.genel.Araclar;
import java.sql.Timestamp;

public class Kullanici {
private int k_ID;
private String k_Email;
private String k_Sifre;
private String k_adSoyad;
private String k_Izin;
private Timestamp kayitTarih;

public void bosKullaniciOlustur(){
    this.setK_adSoyad("Yeni Kullanici");
    this.setK_Email("deneme@deneme.com");
    this.setK_Sifre("deneme");
    this.setK_Izin("A");
    this.setKayitTarih(Araclar.yeniTimeStampOlustur());


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
     * @return the k_Email
     */
    public String getK_Email() {
        return k_Email;
    }

    /**
     * @param k_Email the k_Email to set
     */
    public void setK_Email(String k_Email) {
        this.k_Email = k_Email;
    }

    /**
     * @return the k_Sifre
     */
    public String getK_Sifre() {
        return k_Sifre;
    }

    /**
     * @param k_Sifre the k_Sifre to set
     */
    public void setK_Sifre(String k_Sifre) {
        this.k_Sifre = k_Sifre;
    }

    /**
     * @return the k_adSoyad
     */
    public String getK_adSoyad() {
        return k_adSoyad;
    }

    /**
     * @param k_adSoyad the k_adSoyad to set
     */
    public void setK_adSoyad(String k_adSoyad) {
        this.k_adSoyad = k_adSoyad;
    }

    /**
     * @return the k_Izin
     */
    public String getK_Izin() {
        return k_Izin;
    }

    /**
     * @param k_Izin the k_Izin to set
     */
    public void setK_Izin(String k_Izin) {
        this.k_Izin = k_Izin;
    }

    /**
     * @return the kayitTarih
     */
    public Timestamp getKayitTarih() {
        return kayitTarih;
    }

    /**
     * @param kayitTarih the kayitTarih to set
     */
    public void setKayitTarih(Timestamp kayitTarih) {
        this.kayitTarih = kayitTarih;
    }
    
}

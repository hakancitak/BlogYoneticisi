/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogyoneticisi.siniflar.model;

import blogyoneticisi.siniflar.genel.Araclar;
import java.sql.Timestamp;

public class Blog {
    private int b_ID;
    private int k_ID;
    private String blogBaslik;
    private String aciklama;
    private Timestamp olusturmaTarihi;
    public Blog(){
    
    }
    public Blog(Blog b){
        this.b_ID=b.b_ID;
        this.k_ID=b.k_ID;
        this.aciklama=b.aciklama;
        this.blogBaslik=b.blogBaslik;
        this.olusturmaTarihi=b.olusturmaTarihi;
    }
    public void eniKullaniciIcinBlogBilgisiHazirla(Kullanici k){
        this.setK_ID(k.getK_ID());
        this.setBlogBaslik("Merhaba. Ben "+k.getK_adSoyad()+" . bloguma hos geldiniz.");
        this.setAciklama("Bu otomatik olusturulmus bir blogdur. "+
                "Blog icerigini degistirmek istiyorsaniz kullanici "+
                "adi vesifreniz ile sisteme giris yapiniz");
        this.setOlusturmaTarihi(Araclar.yeniTimeStampOlustur());
    
    
    
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
     * @return the blogBaslik
     */
    public String getBlogBaslik() {
        return blogBaslik;
    }

    /**
     * @param blogBaslik the blogBaslik to set
     */
    public void setBlogBaslik(String blogBaslik) {
        this.blogBaslik = blogBaslik;
    }

    /**
     * @return the aciklama
     */
    public String getAciklama() {
        return aciklama;
    }

    /**
     * @param aciklama the aciklama to set
     */
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    /**
     * @return the olusturmaTarihi
     */
    public Timestamp getOlusturmaTarihi() {
        return olusturmaTarihi;
    }

    /**
     * @param olusturmaTarihi the olusturmaTarihi to set
     */
    public void setOlusturmaTarihi(Timestamp olusturmaTarihi) {
        this.olusturmaTarihi = olusturmaTarihi;
    }
}

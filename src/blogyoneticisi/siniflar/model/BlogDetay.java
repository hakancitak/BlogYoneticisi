
package blogyoneticisi.siniflar.model;

public class BlogDetay extends Blog{
    private Kullanici kullanici;
    private int girdiSayisi;
    private int yorumSayisi;
    public BlogDetay(Blog b){
    super(b);
    
    }

    /**
     * @return the kullanici
     */
    public Kullanici getKullanici() {
        return kullanici;
    }

    /**
     * @param kullanici the kullanici to set
     */
    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    /**
     * @return the girdiSayisi
     */
    public int getGirdiSayisi() {
        return girdiSayisi;
    }

    /**
     * @param girdiSayisi the girdiSayisi to set
     */
    public void setGirdiSayisi(int girdiSayisi) {
        this.girdiSayisi = girdiSayisi;
    }

    /**
     * @return the yorumSayisi
     */
    public int getYorumSayisi() {
        return yorumSayisi;
    }

    /**
     * @param yorumSayisi the yorumSayisi to set
     */
    public void setYorumSayisi(int yorumSayisi) {
        this.yorumSayisi = yorumSayisi;
    }
    
}

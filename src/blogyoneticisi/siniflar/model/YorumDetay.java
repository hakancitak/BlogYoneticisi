
package blogyoneticisi.siniflar.model;

public class YorumDetay extends Yorum {
    private Girdi girdi;
    private Kullanici kullanici;

    /**
     * @return the girdi
     */
    public Girdi getGirdi() {
        return girdi;
    }

    /**
     * @param girdi the girdi to set
     */
    public void setGirdi(Girdi girdi) {
        this.girdi = girdi;
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
    
    
}

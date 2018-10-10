
package blogyoneticisi.siniflar.model;

import java.util.List;

public class GirdiDetay extends Girdi {
    private Blog blog;
    private Kullanici kullanici;
    private List<Yorum> yorumlar;

    /**
     * @return the blog
     */
    public Blog getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(Blog blog) {
        this.blog = blog;
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
     * @return the yorumlar
     */
    public List<Yorum> getYorumlar() {
        return yorumlar;
    }

    /**
     * @param yorumlar the yorumlar to set
     */
    public void setYorumlar(List<Yorum> yorumlar) {
        this.yorumlar = yorumlar;
    }
    
}

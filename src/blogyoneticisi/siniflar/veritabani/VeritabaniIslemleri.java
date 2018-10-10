
package blogyoneticisi.siniflar.veritabani;

import blogyoneticisi.siniflar.model.Blog;
import blogyoneticisi.siniflar.model.BlogDetay;
import blogyoneticisi.siniflar.model.Girdi;
import blogyoneticisi.siniflar.model.Kullanici;
import blogyoneticisi.siniflar.model.Yorum;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VeritabaniIslemleri {

private Connection con=null;
String veriTabniURL;
String kullaniciAdi;
String sifre;
String[] tabloIsimleri={"yorum","girdi","kullanicilar","blog"};
public VeritabaniIslemleri(){
    this.veriTabniURL="jdbc:mysql://localhost:3307/vt_blog_yoneticisi";
    this.kullaniciAdi="root";
    this.sifre="123456789";
}
public VeritabaniIslemleri(String url,String kullaniciAdi,String sifre){
this.veriTabniURL=url;
this.kullaniciAdi=kullaniciAdi;
this.sifre=sifre;
}
public void baglan() throws Exception{

//Eger baglanti varsa true gönder
if((con!=null)){
    if(con.isClosed()==false){
      return;
      }
  }
//Baglanti yoksa yeniden olustur
Class.forName("com.mysql.jdbc.Driver");
con=(Connection)DriverManager.getConnection(this.veriTabniURL,
        this.kullaniciAdi,this.sifre);

}

public void baglantiyiKes() throws SQLException{
    if(con!=null){
        if(!con.isClosed()){
            con.close();
        
        
        }
    }
}  
 public void tumTablolariTemizle() throws SQLException {
     for(String s : tabloIsimleri){
     tabloIceriginiSil(s);
     }
     
 }
public void tabloIceriginiSil(String tabloAdi) throws SQLException{
String sorgu ="DELETE FROM "+ tabloAdi;
    Statement st=null;
    st=con.createStatement();
    st.executeUpdate(sorgu);
    st.close();
    
} 
public boolean kullaniciOlustur(Kullanici k ) throws SQLException{
    String sorgu= "INSERT INTO kullanicilar VALUES(DEFAULT,?,?,?,?,?)";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setString(1, k.getK_Email());
    ps.setString(2, k.getK_Sifre());
    ps.setString(3, k.getK_adSoyad());
    ps.setString(4, k.getK_Izin());
    ps.setTimestamp(5, k.getKayitTarih());
    int sonuc=ps.executeUpdate();
    ps.close();
    return sonuc>0;
 }

public void kullanicilariListedenEkle(List<Kullanici> kullaniciListesi) throws SQLException{
    String sorgu="INSERT INTO kullanicilar VALUES (DEFAULT,?,?,?,?,?)";
    PreparedStatement ps=null;
    try {
        con.setAutoCommit(false);
        ps=con.prepareStatement(sorgu);
        for (Kullanici k : kullaniciListesi) {
            ps.setString(1, k.getK_Email());
            ps.setString(2, k.getK_Sifre());
            ps.setString(3, k.getK_adSoyad());
            ps.setString(4, k.getK_Izin());
            ps.setTimestamp(5, k.getKayitTarih());
            ps.executeUpdate();
        }
        con.commit();        
        con.setAutoCommit(true);
        ps.close();   
    } catch (SQLException e) {
        con.rollback();
        throw e;
    }
}
public boolean blogOlustur(Blog b) throws SQLException{
    String sorgu= "INSERT INTO blog VALUES(DEFAULT,?,?,?,?)";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setInt(1, b.getK_ID());
    ps.setString(2, b.getBlogBaslik());
    ps.setString(3, b.getAciklama());
    ps.setTimestamp(4, b.getOlusturmaTarihi());
    int sonuc=ps.executeUpdate();
    ps.close();
    return sonuc>0;
 }
public boolean girdiOlustur(Girdi g) throws SQLException{
    String sorgu= "INSERT INTO girdi VALUES(DEFAULT,?,?,?,?,?)";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setInt(1, g.getB_ID());
    ps.setInt(2, g.getK_ID());
    ps.setString(3, g.getGirdiBaslik());
    ps.setString(4, g.getGirdiIcerik());
    ps.setTimestamp(5, g.getGirdiTarih());
    int sonuc=ps.executeUpdate();
    ps.close();
    return sonuc>0;
 }
public boolean yorumOlustur(Yorum y) throws SQLException{
    String sorgu= "INSERT INTO yorum VALUES(DEFAULT,?,?,?,?,?)";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setInt(1, y.getGirdiID());
    ps.setInt(2, y.getK_ID());
    ps.setString(3, y.getYorumBaslik());
    ps.setString(4, y.getYorumIcerik());
    ps.setTimestamp(5, y.getYorumTarih());
    int sonuc=ps.executeUpdate();
    ps.close();
    return sonuc>0;
 }
public boolean emailKayitliMi(String email) throws SQLException{
    String sorgu= "Select k_Email From kullanicilar where k_Email= ?";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setString(1, email);
    // Gelen ResulSet bos degilse excecute fonksiyonu true dondurur.
    boolean sonuc=ps.execute();
    ps.close();
    return sonuc;
 }
public int kullaniciIDsiBul(String kullaniciEmail) throws SQLException{
    String sorgu= "Select k_ID From kullanicilar where k_Email= ?";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setString(1, kullaniciEmail);
    ResultSet rs=ps.executeQuery();
    int sonuc=0;
    if (rs.next()) 
        sonuc=rs.getInt("k_ID");
    ps.close();
    return sonuc;
 }
public int blogIDsiBul(Timestamp olusturmaTarih) throws SQLException{
    String sorgu= "Select b_ID From blog where olusturma_Tarihi= ?";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setTimestamp(1, olusturmaTarih);
    ResultSet rs=ps.executeQuery();
    int sonuc=0;
    if (rs.next()) 
        sonuc=rs.getInt("b_ID");
    ps.close();
    rs.close();
    return sonuc;
 }
public int girdiIDsiBul(Timestamp girdiTarih) throws SQLException{
    String sorgu= "Select girdiID From girdi where girdiTarih= ?";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setTimestamp(1, girdiTarih);
    ResultSet rs=ps.executeQuery();
    int sonuc=0;
    if (rs.next()) 
        sonuc=rs.getInt("girdiID");
    ps.close();
    rs.close();
    return sonuc;
 }
public Kullanici kullaniciBilgisiniGetir(String k_Email,String k_Sifre) throws SQLException{
    String sorgu= "Select * From kullanicilar where "
            +"k_Email=? and k_Sifre=?";
    PreparedStatement ps=con.prepareStatement(sorgu);
    ps.setString(1, k_Email);
    ps.setString(2, k_Sifre);
    ResultSet rs=ps.executeQuery();
    Kullanici k=null;
    if (rs.next()){
            k=new Kullanici();
            k.setK_ID(rs.getInt("k_ID"));
            k.setK_Email(rs.getString("k_Email"));
            k.setK_Sifre(rs.getString("k_Sifre"));
            k.setK_adSoyad(rs.getString("k_adSoyad"));
            k.setK_Izin(rs.getString("k_Izin"));
            k.setKayitTarih(rs.getTimestamp("kayitTarihi"));
    
    } 
        
    ps.close();
    rs.close();
    return k;
 }
  public Kullanici kullaniciBilgisiGetir(String kullaniciEmail) throws SQLException {
        String sorgu = "SELECT * FROM kullanicilar where k_Email = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setString(1, kullaniciEmail);
        ResultSet rs = pstmt.executeQuery();
        Kullanici k = null;
        if (rs.next()) {
            k = new Kullanici();
            k.setK_ID(rs.getInt("k_ID"));
            k.setK_Email(rs.getString("k_Email"));
            k.setK_Sifre(rs.getString("k_Sifre"));
            k.setK_adSoyad(rs.getString("k_adSoyad"));
            k.setK_Izin(rs.getString("k_Izin"));
            k.setKayitTarih(rs.getTimestamp("kayitTarihi"));
        }
        pstmt.close();
        rs.close();
        return k;
    }

 public Kullanici kullaniciBilgisiGetir(int kullaniciID) throws SQLException {
        String sorgu = "SELECT * FROM kullanicilar where k_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, kullaniciID);
        ResultSet rs = pstmt.executeQuery();
        Kullanici k = null;
        if (rs.next()) {
            k = new Kullanici();
           k.setK_ID(rs.getInt("k_ID"));
           k.setK_Email(rs.getString("k_Email"));
           k.setK_Sifre(rs.getString("k_Sifre"));
           k.setK_adSoyad(rs.getString("k_adSoyad"));
           k.setK_Izin(rs.getString("k_Izin"));
           k.setKayitTarih(rs.getTimestamp("kayitTarihi"));
        }
        pstmt.close();
        rs.close();
        return k;
    }
public List<Kullanici> tumKullanicilariGetir() throws SQLException{ 
    String sorgu="Select * From kullanicilar";
    PreparedStatement pstmt = con.prepareStatement(sorgu);
    ResultSet rs=pstmt.executeQuery();
    List<Kullanici> kList=null;
    if(rs.next()){
        kList=new ArrayList<Kullanici>();
    }
    rs.beforeFirst();
    while(rs.next()){
        Kullanici k=new Kullanici();
         k.setK_ID(rs.getInt("k_ID"));
         k.setK_Email(rs.getString("k_Email"));
         k.setK_Sifre(rs.getString("k_Sifre"));
         k.setK_adSoyad(rs.getString("k_adSoyad"));
         k.setK_Izin(rs.getString("k_Izin"));
         k.setKayitTarih(rs.getTimestamp("kayitTarihi"));
         kList.add(k);
        
    }
    pstmt.close();
    rs.close();
    return kList;
}
 public Blog blogBilgisiGetir(int blogID) throws SQLException {
        String sorgu = "SELECT * FROM blog where b_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, blogID);
        ResultSet rs = pstmt.executeQuery();
        Blog b = null;
        if (rs.next()) {
            b = new Blog();
           b.setB_ID(rs.getInt("b_ID"));
           b.setK_ID(rs.getInt("k_ID"));
           b.setBlogBaslik(rs.getString("b_baslik"));
           b.setAciklama(rs.getString("aciklama"));
           b.setOlusturmaTarihi(rs.getTimestamp("olusturma_Tarihi"));
       
        }
        pstmt.close();
        rs.close();
        return b;
    }
  public int[] blogIstatistikGetir(int blogID) throws SQLException{
        int[] res = new int[2];
        String sorgu = "SELECT count(*) FROM girdi where b_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, blogID);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            res[0] = rs.getInt(1);
        }

        sorgu = "select count(*) from yorum y WHERE " +
                "y.girdiID in" +
                "(Select g.girdiID FROM Girdi g where g.b_ID = ? )";

        pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, blogID);
        rs = pstmt.executeQuery();
        if(rs.next()){
            res[1] = rs.getInt(1);
        }

        pstmt.close();
        rs.close();
        return res;
    }

    public List<Blog> tumBloglariGetir() throws SQLException{

        String sorgu = "SELECT * FROM blog";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        ResultSet rs = pstmt.executeQuery();
        List <Blog> blogList = null;
        if(rs.next()){
            blogList = new ArrayList<Blog>();
        }
        rs.beforeFirst();
        while (rs.next()) {
            Blog b = new Blog();
                 b.setB_ID(rs.getInt("b_ID"));
                 b.setK_ID(rs.getInt("k_ID"));
                 b.setBlogBaslik(rs.getString("b_baslik"));
                 b.setAciklama(rs.getString("aciklama"));
                 b.setOlusturmaTarihi(rs.getTimestamp("olusturma_Tarihi"));
                 blogList.add(b);
        }
        pstmt.close();
        rs.close();
        return blogList;
    }

    public List<Blog> birKullaniciyaAitTumBloglariGetir(Kullanici k)
            throws SQLException
    {

        String sorgu = "SELECT * FROM blog WHERE k_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, k.getK_ID());
        ResultSet rs = pstmt.executeQuery();
        List <Blog> blogList = null;
        if(rs.next()){
            blogList = new ArrayList<Blog>();
        }
        rs.beforeFirst();
        while (rs.next()) {
            Blog b = new Blog();
           b.setB_ID(rs.getInt("b_ID"));
           b.setK_ID(rs.getInt("k_ID"));
           b.setBlogBaslik(rs.getString("b_baslik"));
           b.setAciklama(rs.getString("aciklama"));
           b.setOlusturmaTarihi(rs.getTimestamp("olusturma_Tarihi"));
            blogList.add(b);
        }
        pstmt.close();
        rs.close();
        return blogList;

    }

    public List<Girdi> birBlogaAitTumGirdileriGetir(Blog b)
            throws SQLException
    {
        String sorgu = "SELECT * FROM girdi WHERE b_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, b.getB_ID());
        ResultSet rs = pstmt.executeQuery();
        List <Girdi> girdiList = null;
        if(rs.next()){
            girdiList = new ArrayList<Girdi>();
        }
        rs.beforeFirst();

        while(rs.next()) {
            Girdi g = new Girdi();
            g.setGirdiID(rs.getInt("girdiID"));
            g.setB_ID(rs.getInt("b_ID"));
            g.setK_ID(rs.getInt("k_ID"));
            g.setGirdiBaslik(rs.getString("girdiBaslik"));
            g.setGirdiIcerik(rs.getString("girdiIcerik"));
            g.setGirdiTarih(rs.getTimestamp("girdiTarih"));
            girdiList.add(g);
        }
        pstmt.close();
        rs.close();
        return girdiList;

    }

    public List<Yorum> birGirdiyeAitTumYorumlariGetir(Girdi g)
            throws SQLException
    {
        String sorgu = "SELECT * FROM yorum WHERE girdiID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, g.getGirdiID());
        ResultSet rs = pstmt.executeQuery();
        List <Yorum> yorumList = null;
        if(rs.next()){
            yorumList = new ArrayList<Yorum>();
        }
        rs.beforeFirst();

        while (rs.next()) {
            Yorum y = new Yorum();
            y.setYorumID(rs.getInt("y_ID"));
            y.setGirdiID(rs.getInt("girdiID"));
            y.setK_ID(rs.getInt("k_ID"));
            y.setYorumBaslik(rs.getString("yorumBaslik"));
            y.setYorumIcerik(rs.getString("yorumIcerik"));
            y.setYorumTarih(rs.getTimestamp("yorumTarih"));
            yorumList.add(y);
        }
        pstmt.close();
        rs.close();
        return yorumList;

    }

    public boolean yorumuSil(Yorum  y) throws SQLException{
        String sorgu = "DELETE FROM yorum WHERE y_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, y.getYorumID());
        boolean sonuc = (pstmt.executeUpdate()>0);
        pstmt.close();
        return sonuc;
    }

    public boolean girdiyiSil(Girdi g) throws SQLException{
        String sorgu = "DELETE FROM girdi WHERE girdiID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, g.getGirdiID());
        boolean sonuc = (pstmt.executeUpdate()>0);
        pstmt.close();
        return sonuc;
    }

    public boolean bloguSil(Blog b) throws SQLException{
        String sorgu = "DELETE FROM blog WHERE b_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, b.getB_ID());
        boolean sonuc = (pstmt.executeUpdate()>0);
        pstmt.close();
        return sonuc;
    }
    public boolean kullaniciyiSil(Kullanici k) throws SQLException{
        String sorgu = "DELETE FROM kullanicilar WHERE k_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt = con.prepareStatement(sorgu);
        pstmt.setInt(1, k.getK_ID());
        boolean sonuc = (pstmt.executeUpdate()>0);
        pstmt.close();

        return sonuc;
    }

    public BlogDetay blogDetayGetir(int blogID) throws SQLException{
        Blog b = blogBilgisiGetir(blogID);
        BlogDetay blogDetay = new BlogDetay(b);
        Kullanici k = kullaniciBilgisiGetir(b.getK_ID());
        blogDetay.setKullanici(k);
        int [] istatistik = blogIstatistikGetir(b.getB_ID());
        blogDetay.setGirdiSayisi(istatistik[0]);
        blogDetay.setYorumSayisi(istatistik[1]);
        return blogDetay;

    }

    public boolean blogBilgisiGuncelle(Blog b) throws SQLException{
        String sorgu = "UPDATE blog SET b_baslik = ?, " +
                "aciklama = ?, k_ID = ? WHERE b_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setString(1, b.getBlogBaslik());
        pstmt.setString(2, b.getAciklama());
        pstmt.setInt(3, b.getK_ID());
        pstmt.setInt(4, b.getB_ID());
        int sonuc = pstmt.executeUpdate();
        pstmt.close();

        return (sonuc > 0);

    }

     public boolean kullaniciBilgisiGuncelle(Kullanici k) throws SQLException{
        String sorgu = "UPDATE kullanicilar SET k_Email = ?, " +
                "k_Sifre = ?, k_adSoyad = ?, " +
                "k_Izin = ? WHERE k_ID = ?";
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        pstmt.setString(1, k.getK_Email());
        pstmt.setString(2, k.getK_Sifre());
        pstmt.setString(3, k.getK_adSoyad());
        pstmt.setString(4, k.getK_Izin());
        pstmt.setInt(5, k.getK_ID());
        int sonuc = pstmt.executeUpdate();
        pstmt.close();
        return (sonuc > 0);

    }

    public String [] tabloKolonAdlariniGetir(String tabloAdi) throws SQLException{
        String sorgu = "SELECT * FROM " + tabloAdi;
        PreparedStatement pstmt = con.prepareStatement(sorgu);
        ResultSetMetaData metaData = pstmt.getMetaData();

        int sayi = metaData.getColumnCount();
        String[] sonuc = new String[sayi];
        for(int i = 0; i<sayi; i++)
            sonuc[i] = metaData.getColumnName(i+1);

        return sonuc;
    }


}

        



    


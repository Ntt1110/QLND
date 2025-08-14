package Entity;

import java.util.Date;

public abstract class GiaoDich {
 protected String maGD;
    protected Date ngayGD;
    protected double donGia;
    protected float dienTich;
    protected String loaiGiaoDich;
    

    public GiaoDich(String maGD, Date ngayGD, double donGia, float dienTich,String loaiGiaoDich) {
        this.maGD = maGD;
        this.ngayGD = ngayGD;
        this.donGia = donGia;
        this.dienTich = dienTich;
        this.loaiGiaoDich = loaiGiaoDich;
       
    }

    public abstract double thanhTien();

    public String getMaGD() { return maGD; }
    public Date getNgayGD() { return ngayGD; }
    public double getDonGia() { return donGia; }
    public float getDienTich() { return dienTich; }
    public String getLoaiGiaoDich() { return loaiGiaoDich; }
    
}

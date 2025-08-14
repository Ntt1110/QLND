package Entity;

import java.util.Date;

public class GiaoDichNha extends GiaoDich {
   
    private String diaChi;
    private String loaiNha;

    public GiaoDichNha(String maGD, Date ngayGD, double donGia, float dienTich, String diaChi,String loaiNha) {
        super(maGD, ngayGD, donGia, dienTich, "Nha");
      
        this.diaChi = diaChi;
        this.loaiNha = loaiNha;
    }

    @Override
    public double thanhTien() {
        if ("thuong".equalsIgnoreCase(loaiNha)) {
            return dienTich * donGia * 0.9; 
        } else  {
            return dienTich * donGia ; 
        }
    }
    public String getDiaChi() {
        return diaChi;
    }
    public String getLoaiNha() {
        return loaiNha;
    }
   

}

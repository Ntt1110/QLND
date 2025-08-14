package Entity;

import java.util.Date;

public class GiaoDichDat extends GiaoDich {

private String loaiDat;
    public GiaoDichDat(String maGD, Date ngayGD, double donGia, float dienTich,String phanloai) {
        super(maGD, ngayGD, donGia, dienTich, "Dat");
  
        this.loaiDat = loaiDat;
    }

    @Override
    public double thanhTien() {
        if ("A".equalsIgnoreCase(loaiDat)) {
            return dienTich * donGia * 1.5; 
        } else {
            return dienTich * donGia ; 
        } 
    }

    public String getLoaiDat() {
        return loaiDat;
    }
}



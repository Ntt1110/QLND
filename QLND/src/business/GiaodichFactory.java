package business;

import java.util.Date;

import Entity.*;
import presistence.GiaoDichDTO;



public class GiaodichFactory {
    public static GiaoDich createGiaodich(GiaoDichDTO dto) {
        
       if ("nha".equalsIgnoreCase(dto.loaiGiaoDich)) {
			return new GiaoDichNha(
				dto.maGD,
                dto.ngayGD,
                dto.donGia,
                dto.dienTich,
                dto.diaChi,
			 dto.phanLoai  
             
			);
		} else if ("Dat".equalsIgnoreCase(dto.loaiGiaoDich)) {
			return new GiaoDichDat(
				dto.maGD, dto.ngayGD, dto.donGia, dto.dienTich,dto.phanLoai
				
			);
		}
		
		return null;
	}
}


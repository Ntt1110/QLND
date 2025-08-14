package business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import presistence.*;
import Entity.GiaoDich;
import Entity.GiaoDichDat;
import Entity.GiaoDichNha;

public class XemDanhSachGiaoDichUseCase {
 private final GiaoDichDAOGateway giaoDichGateway;

    public XemDanhSachGiaoDichUseCase(GiaoDichDAOGateway giaoDichGateway) {
        this.giaoDichGateway = giaoDichGateway;
    }

    // Lấy danh sách GiaodichDTOView
    public List<GiaodichDTOView> execute() {
       List<GiaoDich> list = null;
		List<GiaoDichDTO> listDTO = null;
     try {
        listDTO = giaoDichGateway.getAll();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
     list = this.convertToBusinessObjects(listDTO);
     List<GiaoDichDTO> dtoList = this.convertToViewDTO(list);
     
     // Chuyển đổi từ GiaoDichDTO sang GiaodichDTOView
     return dtoList.stream()
             .map(dto -> {
                 GiaodichDTOView view = new GiaodichDTOView();
                 view.maGD = dto.maGD;
                 view.ngayGD = dto.ngayGD;
                 view.dienTich = dto.dienTich;
                 view.loaiGiaoDich = dto.loaiGiaoDich;
                 view.donGia = dto.donGia;
                 view.thanhTien = dto.thanhTien;
                    view.phanLoai = dto.phanLoai; // Thêm trường phân loại
                    view.loaiNha = dto.loaiNha; // Chỉ dùng khi là nhà
                    view.loaiDat = dto.loaiDat; // Chỉ dùng khi là đất
                    view.diaChi = dto.diaChi; // Chỉ dùng khi là nhà
                 return view;
             })
             .collect(Collectors.toList());
    }

    // Lấy danh sách theo loại giao dịch
    public List<GiaoDich> convertToBusinessObjects(List<GiaoDichDTO> dtos) {
       List<GiaoDich> giaodich = new ArrayList<>();
		for (GiaoDichDTO dto : dtos) {
		 GiaoDich giaodichs = GiaodichFactory.createGiaodich(dto);
		 giaodich.add(giaodichs);
		}
		return giaodich;
    }

   

   
    public List<GiaoDichDTO> convertToViewDTO(List<GiaoDich> giaodich) {
        return giaodich.stream()
                .map(gd -> {
                    GiaoDichDTO dto = new GiaoDichDTO();
                    dto.maGD = gd.getMaGD();
                    dto.ngayGD = gd.getNgayGD();
                    dto.donGia = gd.getDonGia();
                    dto.dienTich = gd.getDienTich();
                    dto.thanhTien = gd.thanhTien();
                    dto.loaiGiaoDich = gd.getLoaiGiaoDich();

                    // Map các trường đặc thù theo loại giao dịch
                    if (gd instanceof GiaoDichNha) {
                        GiaoDichNha nha = (GiaoDichNha) gd;
                     dto.loaiNha = nha.getLoaiNha();
                        dto.diaChi = nha.getDiaChi();
                    } else if (gd instanceof GiaoDichDat) {
                        GiaoDichDat dat = (GiaoDichDat) gd;
                        dto.loaiDat = dat.getLoaiDat();
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

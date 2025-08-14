package presentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import business.GiaodichDTOView;
import business.XemDanhSachGiaoDichUseCase;
import presentation.danhSachgiaodichItem;
public class XemdanhSachgiaodichController {

    private XemdanhSachgiaodichModel model;
    private XemDanhSachGiaoDichUseCase useCase;
    
    public XemdanhSachgiaodichController(XemdanhSachgiaodichModel model, XemDanhSachGiaoDichUseCase useCase) {
        this.model = model;
        this.useCase = useCase;
        
    }
    
   public void execute() {
		// model.?????()
		// gửi thông điệp đến StudentListViewUseCase
		List<GiaodichDTOView> newList = useCase.execute();

		// convert DTO sang ViewItem (Present)
		List<danhSachgiaodichItem> listPresent = this.convertToPresent(newList);

		model.listItem = listPresent;// gửi thông điệp cho model
		// vi phạm mvc
		// listViewUI.showList(model);
		// notify đến subscribers
		model.notifySubscribers();

	}
    
   private List<danhSachgiaodichItem> convertToPresent(List<GiaodichDTOView> listDTO) {
		List<danhSachgiaodichItem> list = new ArrayList<danhSachgiaodichItem>();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		int stt = 1;
		for (GiaodichDTOView dto : listDTO) {
			danhSachgiaodichItem item = new danhSachgiaodichItem();
			item.stt = stt++;
			item.maGD = dto.maGD;
			item.ngayGD = dto.ngayGD != null ? fmt.format(dto.ngayGD) : "Chưa xác định";
			item.dienTich = dto.dienTich;
			item.loaiGiaoDich = dto.loaiGiaoDich;
			item.phanloai = dto.phanLoai = dto.loaiNha != null ? dto.loaiNha : dto.loaiDat;
			item.diachi = dto.diaChi;
			item.donGia = dto.donGia;
			item.thanhTien = dto.thanhTien;
			list.add(item);
		}

		return list;

	}
    
   
}


import java.sql.SQLException;

import javax.swing.SwingUtilities;

import business.XemDanhSachGiaoDichUseCase;
import presentation.XemdanhSachgiaodichController;
import presentation.XemdanhSachgiaodichModel;
import presentation.XemdanhSachgiaodichview;

import presistence.MySQLGiaoDichDAO;

public class App {
    public static void main(String[] args) {
      XemdanhSachgiaodichview listViewUI = new XemdanhSachgiaodichview();
		MySQLGiaoDichDAO listViewDAO ;
		XemDanhSachGiaoDichUseCase listViewUseCase = null;
		XemdanhSachgiaodichController listViewController = null;
		XemdanhSachgiaodichModel model = new XemdanhSachgiaodichModel();
		listViewUI.setViewModel(model);
		
			
			listViewDAO = new MySQLGiaoDichDAO();
			
			listViewUseCase = new XemDanhSachGiaoDichUseCase(listViewDAO);
			
			listViewController = new XemdanhSachgiaodichController(model, 
					listViewUseCase);
			listViewController.execute();
			listViewUI.setVisible(true);
			
	
		listViewUI.setVisible(true);
		listViewUI.showList(model);
	}
}


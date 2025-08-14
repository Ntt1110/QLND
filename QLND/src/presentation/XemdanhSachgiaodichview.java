package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class XemdanhSachgiaodichview extends JFrame implements Subscriber {
   private JTextField txtSearch;
    private JButton btnAdd;
    private JTable table;
    private DefaultTableModel model;
    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private XemdanhSachgiaodichModel viewModel;

    public XemdanhSachgiaodichview() {
        super("Danh sách giao dịch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);

        // Panel top
        JPanel top = new JPanel(new BorderLayout(5,5));
        txtSearch = new JTextField();
        btnAdd = new JButton("Thêm");
        top.add(txtSearch, BorderLayout.CENTER);
        top.add(btnAdd, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Table
        String[] cols = {"STT","magd","ngaygiaodich","dongia","diện tích ","loại giao dịch","phân loại","thành tiền","địa chỉ"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
         add(new JScrollPane(table), BorderLayout.CENTER);

      /*  btnAdd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			openAddStudentForm();
		}

		private void openAddStudentForm() {
			OpenAddStudentFormGateway gateway;
			OpenAddStudentFormController controller;
			OpenAddStudentFormUseCase uc;
			OpenAddStudentFormView view;
			view = new OpenAddStudentFormView();
			OpenAddStudentFormModel model = new OpenAddStudentFormModel();
			view.setModel(model);

			gateway = new MockOpenAddStudentFormDAO();
			uc = new OpenAddStudentFormUseCase(gateway);
			
			
			controller = new OpenAddStudentFormController(uc, model);
			controller.execute();
			
		}
	});*/

       
    }

    public void setViewModel(XemdanhSachgiaodichModel viewModel) {
		this.viewModel = viewModel;
		viewModel.registerSubscriber(this);
	}
    
    public void showList(XemdanhSachgiaodichModel viewModel) {
        model.setRowCount(0);
        

        for (danhSachgiaodichItem  item : viewModel.listItem) {
             
            Object[] row = {
                
            		item.stt,
                item.maGD,
                item.ngayGD ,
                item.donGia,
                item.dienTich,
                item.loaiGiaoDich,
                item.phanloai,
                item.thanhTien,
                item.diachi
            };
            model.addRow(row);
        }
        
        
        }
    


	@Override
	public void update() {
		this.showList(viewModel);
		
	}
}

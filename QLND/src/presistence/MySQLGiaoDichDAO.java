package presistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entity.*;



public class MySQLGiaoDichDAO implements GiaoDichDAOGateway {
  String url = "jdbc:mysql://localhost:3306/ql_giaodich?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "ntt11102005";

    public MySQLGiaoDichDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, jdbcUsername, jdbcPassword);
        return connection;
    }

   


   
@Override
public List<GiaoDichDTO> getAll() throws SQLException {
    List<GiaoDichDTO> list = new ArrayList<>();

    String sql = """
        SELECT gd.maGD, gd.ngayGD, gd.donGia, gd.dienTich,
               CASE 
                   WHEN n.maGD IS NOT NULL THEN 'NHA'
                   WHEN d.maGD IS NOT NULL THEN 'DAT'
                   ELSE 'KHAC'
               END AS loaiGiaoDich,
               COALESCE(n.loaiNha, d.loaiDat) AS phanLoai,
               n.diaChi
        FROM giaodich gd
        LEFT JOIN giaodich_nha n ON gd.maGD = n.maGD
        LEFT JOIN giaodich_dat d ON gd.maGD = d.maGD
    """;

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            GiaoDichDTO dto = new GiaoDichDTO();
            dto.maGD = rs.getString("maGD");
            dto.ngayGD = rs.getDate("ngayGD");
            dto.donGia = rs.getDouble("donGia");
            dto.dienTich = rs.getFloat("dienTich");
            dto.loaiGiaoDich = rs.getString("loaiGiaoDich");
            dto.phanLoai = rs.getString("phanLoai");
            dto.diaChi = rs.getString("diaChi");

            list.add(dto);
        }
    }

    return list;
}
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new MySQLGiaoDichDAO();
        System.out.println("Kết nối thành công");
        
	}
}

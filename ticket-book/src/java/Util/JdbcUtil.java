package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 * Lớp này chứa các phương thức tiện ích giúp làm việc với CSDL một cách đơn
 * giản và nhanh chóng. Tất cả các phương thức điều chuyển ngoại lệ sang dạng
 * unchecked nên khi gọi không bắt buộc phải dùng try catch vì vậy rất thuận
 * lợi cho việc viết mã của bạn. Bạn cần cấp các thông số kết nối đến CSDL
 * thông qua các biến tĩnh để các phương thức dựa vào đó mà hoạt động.
 */
public class JdbcUtil
{
    /*
     * Thông số kết nối SQL Server
     */
    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dburl = "jdbc:sqlserver://localhost:1433;DatabaseName=TripBooking;";
    public static String user = "sa";
    public static String password = "123456";

    /*
     *  Tải trình điều khiển của SQL Server (trong sqljdbc4.jar)
     */
    static {
    	try {
            Class.forName(driver);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Mở kết nối đến CSDL
     * @return đối tượng chứa kết nối đến CSDL
     * @exception lỗi kết nối
     */
    public static Connection openConnection() {
        try {
            return DriverManager.getConnection(dburl, user, password);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Đóng kết nối mà ResultSet đang sử dụng
     * @param rs ResultSet cần đóng kết nối
     * @exception lỗi đóng kết nối
     */
    public static void closeConnection(ResultSet rs){
        try{
            rs.getStatement().getConnection().close();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Tạo đối tượng PreparedStatement và cấp giá trị cho các tham số trong câu lệnh sql
     * @param sql câu lệnh sql chứa các tham số (dấu ?)
     * @param args giá trị các tham số trong câu sql
     * @return đối tượng PreparedStatement đã được cấp giá trị cho các tham số
     * @exception lỗi tạo đối tượng PreparedStatement
     */
    public static PreparedStatement createPreparedStatement(String sql, Object...args){
        try{
            Connection connection = JdbcUtil.openConnection();
        	PreparedStatement statement = connection.prepareStatement(sql,
        			ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            for(int i=1;i<=args.length;i++){
                statement.setObject(i, args[i-1]);
            }
            return statement;
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Thực thi câu lệnh thao tác dữ liệu (INSERT, UPDATE, DELETE)
     * @param sql câu lệnh thao tác chứa tham số (?)
     * @param args giá trị các tham số trong câu lệnh sql
     * @return số bản ghi bị ảnh hưởng bởi câu lệnh sql
     * @exception lỗi thao tác dữ liệu
     */
    public static int executeUpdate(String sql, Object...args){
        try{
            PreparedStatement statement = JdbcUtil.createPreparedStatement(sql, args);
            int rows = statement.executeUpdate();
            statement.getConnection().close();
            return rows;
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * Truy vấn dữ liệu (SELECT)
     * @param sql câu lệnh truy vấn chứa tham số (?)
     * @param args giá trị tham số trong câu lệnh sql
     * @return ResultSet chứa kết quả truy vấn
     * @exception lỗi truy vấn dữ liệu
     */
    public static ResultSet executeQuery(String sql, Object...args){
        try{
            PreparedStatement statement = JdbcUtil.createPreparedStatement(sql, args);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    /**
     * Truy vấn và chuyển đổi dữ liệu kết quả truy vấn sang Result
     * để tiến chia sẽ trên web. Vi du trong servlet:
     * Result result = JdbcUtil.toResult("SELECT * FROM Categories");
     * request.setAttribute("result", result);
     * @param sql câu lệnh truy vấn chứa tham số (?)
     * @param args giá trị tham số trong câu lệnh sql
     * @return ResultSet chứa kết quả truy vấn
     * @exception lỗi truy vấn dữ liệu
     */
    public static Result toResult(String sql, Object...args)
    {
    	ResultSet resultSet = executeQuery(sql, args);
        Result result = ResultSupport.toResult(resultSet);
        JdbcUtil.closeConnection(resultSet);
        return result;
    }

    /**
     * Truy vấn một giá trị đơn.
     * Ví dụ: đếm số mặt hàng có giá từ 100 đến 250
     * String sql = "SELECT COUNT(*) FROM Products WHERE UnitPrice BETWEEN ? AND ?";
     * long count = (long)JdbcUtil.executeScalar(sql, 100, 250);
     * @param sql câu lệnh truy vấn chứa tham số (?)
     * @param args giá trị tham số của câu lệnh sql
     * @return giá trị truy vấn được
     * @exception lỗi truy vấn
     */
    public static Object executeScalar(String sql, Object...args){
        try{
            Object value = null;
            ResultSet resultSet = JdbcUtil.executeQuery(sql, args);
            if(resultSet.next()){
                value = resultSet.getObject(1);
            }
            JdbcUtil.closeConnection(resultSet);
            return value;
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}


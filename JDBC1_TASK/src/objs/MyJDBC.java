/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objs;
import objs.SinhVien;
/**
 *
 * @author Moment
 */
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
public class MyJDBC {
    private static String DB_URL="jdbc:mysql://127.0.0.1:3306/sinhvien";
    private static String DB_Username="root";
    private static String DB_Password="itptit2725";
    public static void add(SinhVien sv)
    {
        try
        {
            
            Connection cnt=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
            PreparedStatement ppst =cnt.prepareStatement("INSERT INTO sinhvien VALUES (?,?,?,?) ");
            ppst.setString(1, sv.getId());
            ppst.setString(2, sv.getName());
            ppst.setString(3, sv.getClassId());
            ppst.setDouble(4, sv.getGpa());
            ppst.executeUpdate();
            
            
           
        }
        catch(SQLException e)
        {
            
        }
    }
    public static boolean checkSV(String id)
    {
        try
        {
            Connection cnt=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
            PreparedStatement ppst =cnt.prepareStatement("SELECT * FROM sinhvien WHERE id=? ");
            ppst.setString(1, id);
            ResultSet rs=ppst.executeQuery();
            if(rs.next())
            {
                return false;
            }
            return true;
            
           
        }
        catch(SQLException e)
        {
            
        }
        return true;
    }
    public static ArrayList<SinhVien> getSV()
    {
        ArrayList<SinhVien> arraySv=new ArrayList<>();
        try
        {
            Connection cnt=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
            PreparedStatement ppst =cnt.prepareStatement("SELECT * FROM sinhvien ");
            ResultSet rs=ppst.executeQuery();
            while(rs.next())
            {
                SinhVien sv=new SinhVien(rs.getString("id"),rs.getString("name"),rs.getString("classId"),rs.getDouble("gpa"));
                arraySv.add(sv);
            }
            
           
        }
        catch(SQLException e)
        {
            
        }
        return arraySv;
    }
    public static void deleteAll()
    {
        try
        {
            Connection cnt=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
            PreparedStatement ppst =cnt.prepareStatement("DELETE FROM sinhvien ");
            ppst.executeUpdate();
           
        }
        catch(SQLException e)
        {
            
        }
    }
    public static void update(String id, SinhVien sv)
    {
        try
        {
            Connection cnt=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
            PreparedStatement ppst =cnt.prepareStatement("UPDATE sinhvien SET id=?, name=?, classId=?, gpa=? WHERE id=?");
            ppst.setString(1, sv.getId());
            ppst.setString(2, sv.getName());
            ppst.setString(3, sv.getClassId());
            ppst.setDouble(4, sv.getGpa());
            ppst.setString(5,id);
            ppst.executeUpdate();
           
        }
        catch(SQLException e)
        {
            
        }
    }
    public static void delete(String id)
    {
        try
        {
            Connection cnt=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
            PreparedStatement ppst =cnt.prepareStatement("DELETE FROM sinhvien WHERE id=?");
            ppst.setString(1, id);
            ppst.executeUpdate();
      
           
        }
        catch(SQLException e)
        {
            
        }
    }
    
}

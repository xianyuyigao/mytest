package tmall01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import tmall01.bean.*;
import tmall01.util.*;

public class ProductImageDAO {
	
	/**
	 * ���־�̬���Էֱ��ʾ��չʾ����ͼƬ��������ͼƬ
	 */
	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";
	
	
	/**
	 * ��ȡ����
	 * ��ҳʹ��
	 */
	
	public int getTotal() {
		int total = 0;
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "select count(*) from ProductImage";
			
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return total;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

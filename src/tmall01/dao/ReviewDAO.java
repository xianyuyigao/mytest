package tmall01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tmall01.bean.*;
import tmall01.dao.*;
import tmall01.util.*;

public class ReviewDAO {
	
	/**
	 * ��ȡ����
	 */
	
	public int getTotal() {
		int total = 0;
		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "select count(*) from Review";
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				total = rs.getInt(1);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * ��ȡĳ��ָ��id������
	 */
	public int getTotal(int pid) {
		int total = 0;
		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			
			String sql = "select count(*) from Review where pid = "+pid;
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				total = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	/**
	 * ����һ����¼
	 */
	public void add(Review bean) {
		String sql = "insert into Review values(null,?,?,?,?)";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, bean.getContent());
			ps.setInt(2, bean.getUser().getId());
			ps.setInt(3, bean.getProduct().getId());
			ps.setTimestamp(4, DateUtil.d2t(bean.getCreateDate()));
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				bean.setId(id);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �޸ļ�¼
	 */
	public void update(Review bean) {
		String sql = "update Review set content = ?,uid=?,pid=?,createDate=? where id=?";
		
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1,bean.getContent());
			ps.setInt(2, bean.getUser().getId());
			ps.setInt(3, bean.getProduct().getId());
			ps.setTimestamp(4,DateUtil.d2t(bean.getCreateDate()));
			ps.setInt(5, bean.getId());
			
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ��һ����¼
	 */
	public void delete(int id) {
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "delete from Review where id = "+id;
			s.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����id��ȡһ����¼
	 */
	public Review get(int id) {
		Review bean = new Review();
		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "select * from Review where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				int pid = rs.getInt("pid");
				int uid = rs.getInt("uid");
				
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));
				
				String content = rs.getString("content");
				
				//��δд��
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

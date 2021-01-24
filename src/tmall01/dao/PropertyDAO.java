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

public class PropertyDAO {
	
	/**
	 * 获取总数
	 */
	
	public int getTotal(int cid) {
		int total = 0;
		//
		try(Connection c = DButil.getConnection();Statement s = c.createStatement()){
			
			//
			String sql = "select count(*) from Property where cid =" + cid;
			
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
	 * 增加一个
	 */
	
	public void addProperty(Property bean) {
		//
		String sql = "insert into Property values(null,?,?)";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			//
			ps.setInt(1, bean.getCategory().getId());
			ps.setString(2, bean.getName());
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
	 * 修改
	 */
	public void updateProperty(Property bean) {
		String sql = "update Property set cid = ?,name = ? where id = ?";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			
			ps.setInt(1, bean.getCategory().getId());
			ps.setString(2, bean.getName());
			ps.setInt(3, bean.getId());
			
			ps.execute();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除
	 */
	public void delete(int id) {
		try(Connection c = DButil.getConnection();Statement s = c.createStatement()){
			//
			String sql = "delete from Property where id = " + id;
			s.execute(sql);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取单个Property
	 */
	
	public Property getProperty(int id) {
		Property bean = null;
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			//
			String sql = "select * from Property where id = "+id;
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next()) {
				int cid = rs.getInt("cid");
				
				bean = new Property();
				String name = rs.getString("name");
				Category category = new CategoryDAO().get(cid);
				
				
				bean.setName(name);
				
				bean.setCategory(category);
                bean.setId(id);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return bean;
	}
	
	public List<Property> list(int cid){
		return list(cid,0,Short.MAX_VALUE);
	}
	
	/**
	 * 
	 */
	
	public List<Property> list(int cid,int start,int count){
		List<Property> beans = new ArrayList<Property>();
		
		String sql = "select * from Property where cid = ? order by id desc limit ?,?";
		
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			//
			ps.setInt(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Property bean = new Property();
				int id = rs.getInt(1);
				String name = rs.getString("name");
				bean.setName(name);
				Category category = new CategoryDAO().get(cid);
				
				bean.setCategory(category);
				bean.setId(id);
				
				beans.add(bean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}
	


}

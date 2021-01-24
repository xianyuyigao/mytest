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

public class UserDAO {
	
	/**
	 * 获取总数
	 */
	public int getTotal() {
		int total = 0;
		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement()){
			
			//sql
			String sql = "select count(*) from User";
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
	 * 增加一个用户
	 * @throws SQLException 
	 */
	
	public void add(User bean) {
		String sql = "insert into user values(null,?,?)";
		//
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			//
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getPassword());
			
			//
			ps.execute();
			
			//
			ResultSet rs = ps.getGeneratedKeys();
			//
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
	
	public void update(User bean) {
		String sql = "update user set name=?,password=? where id=?";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			/**
			 * 
			 */
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getPassword());
			ps.setInt(3, bean.getId());
			//
			ps.execute();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 删除用户
	 * 
	 */
	
	public void delete(int id) {

		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement()){
			String sql = "delete from User where id = " + id;
			s.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * 获取单个用户
	 * 
	 */
	
	public User getUser(int id) {
		User bean = null;
		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement()){
			/**
			 * 
			 */
			String sql = "select * from User where id = "+id;
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next()) {
				bean = new User();
				String name = rs.getString("name");
				bean.setName(name);
				String password = rs.getString("password");
				bean.setPassword(password);
				//
				bean.setId(id);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 
	 */
	
	public List<User> list_user(){
		return null;
	}
	
	
	public List<User> list_user(int start,int count){
		//
		List<User> beans = new ArrayList<User>();
		
		String sql = "select * from User order by id desc limit ?,? ";
		
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User bean = new User();
				int id = rs.getInt(1);
				
				String name = rs.getString("name");
				
				bean.setName(name);
				
				String password = rs.getString("password");
				
				bean.setPassword(password);
				
				bean.setId(id);
				
				//
				
				beans.add(bean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return beans;
	}
	
	
	/**
	 * 
	 */
	public boolean isExist(String name) {
		User user = getUser(1);
		
		return true;
	}
	
	
	/**
	 * 根据姓名获取用户
	 * 用于在注册的时候判断用户名是否重复
	 */
	
	public User getUser(String name) {
		User bean = null;
		String sql = "select * from User where name = ?";
		
		//和数据库建立
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bean = new User();
				int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                bean.setId(id);
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	
	/**
	 * 根据用户名和密码来获取用户
	 */
	
	public User getUser(String name, String password) {
        User bean = null;
          
        String sql = "select * from User where name = ? and password=?";
        try (Connection c = DButil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
  
            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

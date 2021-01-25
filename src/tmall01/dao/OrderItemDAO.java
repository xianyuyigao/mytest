package tmall01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tmall01.bean.*;
import tmall01.dao.*;
import tmall01.util.*;


public class OrderItemDAO {
	
	/**
	 * 获取总数
	 * 
	 */
	public int getTotal() {
		int total = 0;
		
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "select count(*) from OrderItem";
			
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
	 * 增加一个订单项
	 */
	public void add(OrderItem bean) {
		String sql = "insert into OrderItem values(null,?,?,?,?)";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, bean.getProduct().getId());
			//订单项在创建的时候，是没有订单信息的
			if(null == bean.getOrder()) {
				ps.setInt(2, -1);
			}else {
				ps.setInt(2, bean.getOrder().getId());
			}
			
			ps.setInt(3, bean.getUser().getId());
			ps.setInt(4, bean.getNumber());
			
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
	 * 更新一条记录
	 */
	
	public void update(OrderItem bean) {
		String sql = "update OrderItem set pid = ?,oid=?,uid=?,number=? where id=?";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setInt(1, bean.getProduct().getId());
            if(null==bean.getOrder())
                ps.setInt(2, -1);
            else
                ps.setInt(2, bean.getOrder().getId());             
            ps.setInt(3, bean.getUser().getId());
            ps.setInt(4, bean.getNumber());
             
            ps.setInt(5, bean.getId());
            ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除一条记录
	 */
	public void delete(int id) {
		  
        try (Connection c = DButil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from OrderItem where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
	/**
	 * 
	 */
	public OrderItem get(int id) {
        OrderItem bean = new OrderItem();
  
        try (Connection c = DButil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from OrderItem where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                int pid = rs.getInt("pid");
                int oid = rs.getInt("oid");
                int uid = rs.getInt("uid");
                int number = rs.getInt("number");
//                Product product = new ProductDAO().get(pid);
//                User user = new UserDAO().get(uid);
//                bean.setProduct(product);
//                bean.setUser(user);
//                bean.setNumber(number);
//                 
//                if(-1!=oid){
//                    Order order= new OrderDAO().get(oid);
//                    bean.setOrder(order);                  
//                }
//                 
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
	/**
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

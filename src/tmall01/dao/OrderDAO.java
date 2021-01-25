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



public class OrderDAO {
	
	/**
	 * 订单的状态
	 */
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
    public static final String waitReview = "waitReview";
    public static final String finish = "finish";
    public static final String delete = "delete";
    
    public int getTotal() {
    	int total = 0;
    	try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
    		String sql = "select count(*) from order_";
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
     * 添加一个订单
     * 
     */
    public void add(Order bean) {
    	
    	String sql = "insert into order_ values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
    	try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
    		ps.setString(1, bean.getOrderCode());
    		ps.setString(2, bean.getAddress());
    		ps.setString(3, bean.getPost());
    		ps.setString(4, bean.getReceiver());
            ps.setString(5, bean.getMobile());
            ps.setString(6, bean.getUserMessage());
             
            ps.setTimestamp(7,  DateUtil.d2t(bean.getCreateDate()));
            ps.setTimestamp(8,  DateUtil.d2t(bean.getPayDate()));
            ps.setTimestamp(9,  DateUtil.d2t(bean.getDeliveryDate()));
            ps.setTimestamp(10,  DateUtil.d2t(bean.getConfirmDate()));
            ps.setInt(11, bean.getUser().getId());
            ps.setString(12, bean.getStatus());
            
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
     * 更新订单
     */
    
    public void update(Order bean) {
    	String sql = "update order_ set address= ?, post=?, receiver=?,mobile=?,userMessage=? ,createDate = ? , payDate =? , deliveryDate =?, confirmDate = ? , orderCode =?, uid=?, status=? where id = ?";
    	
    	try (Connection c = DButil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
    		  
            ps.setString(1, bean.getAddress());
            ps.setString(2, bean.getPost());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getMobile());
            ps.setString(5, bean.getUserMessage());
            ps.setTimestamp(6, DateUtil.d2t(bean.getCreateDate()));;
            ps.setTimestamp(7, DateUtil.d2t(bean.getPayDate()));;
            ps.setTimestamp(8, DateUtil.d2t(bean.getDeliveryDate()));;
            ps.setTimestamp(9, DateUtil.d2t(bean.getConfirmDate()));;
            ps.setString(10, bean.getOrderCode());
            ps.setInt(11, bean.getUser().getId());
            ps.setString(12, bean.getStatus());
            ps.setInt(13, bean.getId());
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    	
    }
    
    /**
     * 删除一个订单
     */
    public void delete(int id) {
    	try (Connection c = DButil.getConnection(); Statement s = c.createStatement();) {
    		  
            String sql = "delete from Order_ where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
    
    /**
     * 获取一个订单
     */
    
    public Order get(int id) {
    	Order bean = new Order();
    	
    	
    	
    	
    	
    	return bean;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

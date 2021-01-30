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

public class ProductDAO {
	
	public int getTotal(int cid) {
        int total = 0;
        try (Connection c = DButil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from Product where cid = " + cid;
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
	
	/**
	 * 添加一条记录
	 */
	public void add(Product bean) {
		String sql = "insert into Product values(null,?,?,?,?,?,?,?)";
		
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			//
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getSubTitle());
			ps.setFloat(3, bean.getOrignalPrice());
            ps.setFloat(4, bean.getPromotePrice());
            ps.setInt(5, bean.getStock());
            ps.setInt(6, bean.getCategory().getId());
            ps.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
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
	
	public void update(Product bean) {
		String sql = "update Product set name= ?, subTitle=?, orignalPrice=?,promotePrice=?,stock=?, cid = ?, createDate=? where id = ?";
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			//
			ps.setString(1, bean.getName());
            ps.setString(2, bean.getSubTitle());
            ps.setFloat(3, bean.getOrignalPrice());
            ps.setFloat(4, bean.getPromotePrice());
            ps.setInt(5, bean.getStock());
            ps.setInt(6, bean.getCategory().getId());
            ps.setTimestamp(7, DateUtil.d2t(bean.getCreateDate()));
            ps.setInt(8, bean.getId());
            ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条记录
	 */
	
	public void delete(int id) {
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "delete from Product where id = "+id;
			s.execute(sql);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取一个产品
	 */
	
	public Product get(int id) {
		Product bean = new Product();
		try(Connection c = DButil.getConnection();Statement s = c.createStatement();){
			String sql = "select * from Product where id = "+id;
			
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next()) {
				String name = rs.getString("name");
				String subTitle = rs.getString("subTitle");
				float orignalPrice = rs.getFloat("orignalPrice");
				float promotePrice = rs.getFloat("promotePrice");
				int stock = rs.getInt("stock");
				int cid = rs.getInt("cid");
				Date createDate = DateUtil.t2d(rs.getTimestamp("createDate"));
				
				bean.setName(name);
				bean.setSubTitle(subTitle);
				bean.setOrignalPrice(orignalPrice);
				bean.setPromotePrice(promotePrice);
				bean.setStock(stock);
				Category category = new CategoryDAO().get(cid);
				bean.setCategory(category);
				bean.setId(cid);
				//
				//bean.setFirstProductImage(firstProductImage);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	public List<Product> list(int cid){
		return list(cid,0,Short.MAX_VALUE);
	}
	
	
	
	public List<Product> list(int cid,int start,int count){
		List<Product> beans = new ArrayList<Product>();
		
		Category category = new CategoryDAO().get(cid);
		
		String sql = "select * from Product where cid = ? order by id desc limit ?,?";
		
		try(Connection c = DButil.getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			//
			ps.setInt(1, cid);
			ps.setInt(2, start);
			ps.setInt(3, count);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product bean =  new Product();
				int id = rs.getInt(1);
				String name = rs.getString("name");
				String subTitle = rs.getString("subTitle");
				float orignalPrice =  rs.getFloat("orignalPrice");
				float promotePrice = rs.getFloat("promotePrice");
				int stock = rs.getInt("stock");
				Date createDate = DateUtil.t2d( rs.getTimestamp("createDate"));
				
				bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOrignalPrice(orignalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);
                bean.setCategory(category);
                //bean.setFirstProductImage(bean);
                beans.add(bean);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

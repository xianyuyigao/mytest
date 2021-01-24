package tmall01.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall01.bean.*;
import tmall01.filter.BackServletFilter;
import tmall01.util.*;
import tmall01.dao.*;


public class PropertyServlet extends BaseBackServlet{
	/**
	 * 属性管理
	 */
	public String add(HttpServletRequest request,HttpServletResponse response,Page page) {
		
		//获取cid
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		//
		Category c = categoryDAO.get(cid);
		//
		String name = request.getParameter("name");
		Property p = new Property();
		p.setCategory(c);
		p.setName(name);
		propertyDAO.addProperty(p);
		
		
		return "@admin_property_list?cid="+cid;
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(String.format("获取到的id:%d", id));
        Property p = propertyDAO.getProperty(id);
        
        System.out.println(String.format("属性: %s", p));
        propertyDAO.delete(id);
		return "@admin_property_list?cid="+p.getCategory().getId();
		
	}
	
	/**
	 * 编辑
	 */
	public String edit(HttpServletRequest request,HttpServletResponse response,Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Property p = propertyDAO.getProperty(id);
		request.setAttribute("p", p);
		
		return "admin/editProperty.jsp";
	}
	
	/**
	 * 更新
	 */
	
	public String update(HttpServletRequest request,HttpServletResponse response,Page page) {
		//
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category c = categoryDAO.get(cid);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Property p = new Property();
		p.setCategory(c);
		p.setId(id);
		p.setName(name);
		
		//
		propertyDAO.updateProperty(p);
		
		
		return "@admin_property_list?cid="+p.getCategory().getId();
	}
	
	/**
	 * 
	 */
	
	public String list(HttpServletRequest request,HttpServletResponse response,Page page) {
		//
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category c = categoryDAO.get(cid);
		
		List<Property> ps = propertyDAO.list(cid, page.getStart(), page.getCount());
		
		int total = propertyDAO.getTotal(cid);
		
		//
		page.setTotal(total);
		page.setParam("&cid="+c.getId());
		
		//
		request.setAttribute("ps", ps);
		request.setAttribute("c", c);
		request.setAttribute("page", page);
		
		return "admin/listProperty.jsp";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

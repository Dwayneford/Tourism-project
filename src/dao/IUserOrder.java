package com.journey.dao;

import java.util.List;

import com.journey.entity.User;
import com.journey.entity.UserOrder;

//订单功能接口
public interface IUserOrder {

	//添加订单
	public boolean addOrder(UserOrder userEnt);
	//删除订单
	public boolean deleteOrder(String userPhone);
	//修改订单
	public boolean updateOrder(String userPhone , UserOrder userEnt);
	
	//查询所有订单
	public List<UserOrder> queryOrders();
	//按订单号查询订单
	public UserOrder queryOrderByOrderNO(String OrderNO);
	//按手机号查询订单
	public UserOrder queryOrderByOrderPhone(String Orderphone);
}

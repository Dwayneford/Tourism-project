package com.journey.dao;

import java.util.List;

import com.journey.entity.User;
//用户功能接口
public interface IUser {

	//添加用户
	public boolean addUser(User userEnt);
	//删除用户
	public boolean deleteUser(String userPhone);
	//修改用户
	public boolean updateUser(String userPhone , User userEnt);
	
	//查询所有用户
	public List<User> queryUsers();
	//按邮箱查询用户
	public User queryUserByMail(String userMail);
	//按手机号查询用户
	public User queryUserByPhone(String userTelphon);
}

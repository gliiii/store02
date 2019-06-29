package cn.tedu.store02.service;

import java.util.Date;

import cn.tedu.store02.entity.User;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.PasswordNotMatchException;
import cn.tedu.store02.service.ex.UpdateException;
import cn.tedu.store02.service.ex.UserConflictException;
import cn.tedu.store02.service.ex.UserNotFoundException;

/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {
	/**
	 * 用户注册业务
	 * @param user 用户数据
	 * @throws UserConflictException 用户冲突异常
	 * @throws InsertException 插入数据异常
	 */
	void reg(User user) throws UserConflictException,InsertException;
	
	/**
	 * 用户登陆业务
	 * @param username 用户名
	 * @param password 密码
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws PasswordNotMatchException 密码错误异常
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @throws PasswordNotMatchException 密码错误异常
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws UpdateException 跟新异常
	 */
	void changePassword(Integer uid,String oldPassword,String newPassword)
			throws PasswordNotMatchException,UserNotFoundException,UpdateException;
	
	/**
	 * 修改用户信息
	 * @param user 用户信息
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws UpdateException 修改用户信息异常
	 */
	void changeUserInfo(User user) throws UserNotFoundException,UpdateException;
	
	/**
	 * 查询用户信息
	 * @param uid 用户id
	 * @return 用户信息
	 * @throws UserNotFoundException 用户不存在
	 */
	User listUserInfo(Integer uid) throws UserNotFoundException;
	
	/**
	 * 修改用户头像
	 * @param uid 用户id
	 * @throws UserNotFoundException 用户不存在异常
	 * @throws UpdateException 更新异常
	 */
	void changeAvater(Integer uid,String avater) throws UserNotFoundException,UpdateException;
}

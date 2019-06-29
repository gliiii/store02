package cn.tedu.store02.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store02.entity.User;
import cn.tedu.store02.mapper.UserMapper;
import cn.tedu.store02.service.IUserService;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.PasswordNotMatchException;
import cn.tedu.store02.service.ex.UpdateException;
import cn.tedu.store02.service.ex.UserConflictException;
import cn.tedu.store02.service.ex.UserNotFoundException;
/**
 * 处理用户数据业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void reg(User user) throws UserConflictException, InsertException {
		//获取用户名
		String username=user.getUsername();
		//通过findByUsername查询是否存在用户民相同的数据
		User data=findByUsername(username);
		//判断用户是否存在
		if(data!=null) {
			//--存在，非null，抛出异常UserConflictException
			throw new UserConflictException("用户注册失败，用户名已存在");
		}
		//根据uuid生成salt
		String salt=UUID.randomUUID().toString().toUpperCase();
		//封装salt
		user.setSalt(salt);
		//password加密并封装
		String password=user.getPassword();
		String md5Password=getMd5Password(password, salt);
		user.setPassword(md5Password);
		//封装isDelete，默认为0
		user.setIsDelete(0);
		//封装四项日志
		Date now=new Date();
		user.setCreatedUser(username);
		user.setCreatedTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);
		//执行添加用户数据
		addNew(user);
	}
	
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		//根据用户名查询用户
		User data=findByUsername(username);
		//判断用户是否存在
		if(data==null) {
			//--不存在，为null，抛出异常UserNotFoundException
			throw new UserNotFoundException("登陆用户失败，用户不存在！");
		}
		//取出isDelete
		Integer isDelete=data.getIsDelete();
		//判断是否已删除
		if(isDelete==1) {
			//--是，为1，抛出异常UserNotFoundException
			throw new UserNotFoundException("登陆用户失败，用户已被注销！");
		}
		//取出salt，取出password
		String salt=data.getSalt();
		String oldPassword=data.getPassword();
		//密码加密
		password=getMd5Password(password, salt);
		//比较密码是否匹配
		if(!password.equals(oldPassword)) {
			//--不匹配，抛出异常PasswordNotMatchException
			throw new PasswordNotMatchException("登陆用户失败，密码错误！");
		}
		//设置密码为null
		data.setPassword(null);
		//设置salt为null
		data.setSalt(null);
		//返回用户数据
		return data;
		
	}

	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword)
			throws PasswordNotMatchException, UserNotFoundException, UpdateException {
		//根据用户id查询用户数据
		User data=findByUid(uid);
		//判断用户是否存在
		if(data==null) {
			//--不存在，为null，抛出异常UserNotFoundException
			throw new UserNotFoundException("修改密码失败，用户不存在！");
		}
		//取出isDelete
		Integer isDelete=data.getIsDelete();
		//判断isDelete是否已删除
		if(isDelete==1) {
			//--已删除，为1，抛出异常UserNotFoundException
			throw new UserNotFoundException("修改密码失败，用户已被删除！");
		}
		//取出salt，password
		String salt=data.getSalt();
		String password=data.getPassword();
		//将oldPassword加密
		 oldPassword=getMd5Password(oldPassword, salt);
		//判断password和oldPassword是否匹配
		 if(!password.equals(oldPassword)) {
			 //不匹配，抛出异常PasswordNotMatchException
			 throw new PasswordNotMatchException("修改密码失败，原密码错误！");
		 }
		 //更新密码
		 Date modifiedTime=new Date();
		 newPassword=getMd5Password(newPassword, salt);
		 updatePassword(uid, newPassword, data.getUsername(), modifiedTime);
	}

	@Override
	public void changeUserInfo(User user) throws UserNotFoundException, UpdateException {
		//获取用户id
		Integer uid=user.getUid();
		//根据用户id查询用户信息
		User data=findByUid(uid);
		//判断用户是否存在
		if(data==null) {
			//--null 用户不存在，抛出异常UserNotFoundException
			throw new UserNotFoundException("修改用户信息失败，用户不存在");
		}
		//获取isDelete
		Integer isDelete=data.getIsDelete();
		//判断用户是否删除
		if(isDelete==1) {
			//--已删除，为1，抛出异常UserNotFoundException
			throw new UserNotFoundException("修改用户信息失败，用户已删除");
		}
		//封装modifiedUser和modifiedTime
		
		user.setUsername(data.getUsername());
		Date modifiedTime=new Date();
		user.setModifiedTime(modifiedTime);
		//更新用户信息
		updateUserInfo(user);
	}

	@Override
	public User listUserInfo(Integer uid) throws UserNotFoundException {
		//根据用户id查询用户信息
		User data=findByUid(uid);
		//判断用户是否存在
		if(data==null) {
			//--不存在，抛出异常UserNotFoundException
			throw new UserNotFoundException("查询用户信息失败，用户不存在！");
		}
		//取出isDelete
		Integer isDelete=data.getIsDelete();
		//判断用户是否删除
		if(isDelete.equals(1)) {
			//--是，已删除，抛出异常UserNotFoundException
			throw new UserNotFoundException("查询用户失败，用户已删除！");
		}
		//清除不希望对外暴露的数据
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		return data;
	}

	@Override
	public void changeAvater(Integer uid,String avater) throws UserNotFoundException, UpdateException {
		//根据uid查询用户信息
		User data=findByUid(uid);
		//判断用户是否存在
		if(data==null) {
			//--不存在，为null，抛出异常UserNotFoundException
			throw new UserNotFoundException("更新头像失败，用户不存在");
		}
		//取出isDelete
		Integer isDelete=data.getIsDelete();
		//判断用户是否已删除
		if(isDelete.equals(1)) {
			//--已删除，为1，抛出异常UserNotFoundException
			throw new UserNotFoundException("更新头像失败，用户已删除");
		}
		//封装两项日志
		String modifiedUser=data.getUsername();
		Date modifiedTime=new Date();
		//执行更新头像
		updateAvater(avater, uid, modifiedUser, modifiedTime);
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	};
	
	/**
	 * 插入用户数据
	 * @param user 用户信息
	 */
	private void addNew(User user) {
		Integer rows=userMapper.addNew(user);
		if(rows!=1) {
			throw new InsertException("注册用户数据异常，请重新注册！");
		}
	};
	
	/**
	 *根据md5摘要算法对密码进行10次加密 
	 * @param password
	 * @param salt
	 * @return
	 */
	private String getMd5Password(String password,String salt) {
		String str=salt+password;
		for(int i=0;i<10;i++) {
			str=DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	
	/**
	 * 根据用户id查询用户数据
	 * @param uid 用户id
	 * @return 用户数据
	 */
	private User findByUid(Integer uid) {
		return userMapper.findByUid(uid); 
	};
	
	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param password 用户密码
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 */
	private void updatePassword(Integer uid,String password,
			String modifiedUser,Date modifiedTime) {
		Integer rows=userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("修改密码失败，更新密码出错");
		}
	};
	
	/**
	 * 修改用户信息
	 * @param user 用户信息
	 */
	private void updateUserInfo(User user) {
		Integer rows=userMapper.updateUserInfo(user);
		if(rows!=1) {
			throw new UpdateException("修改用户信息失败，更新用户信息出错");
		}
	};
	
	/**
	 * 修改用户头像
	 * @param avater 头像路径
	 * @param uid 用户id
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 */
	private void updateAvater(String avater,Integer uid,String modifiedUser,Date modifiedTime) {
		Integer rows=userMapper.updateAvater(avater, uid, modifiedUser, modifiedTime);
		if(rows!=1) {
			throw new UpdateException("修改用户头像失败，更新用户信息出错");
		}
	};
}

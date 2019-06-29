package cn.tedu.store02.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store02.entity.User;

/**
 * 处理用户数据持久层接口
 */
public interface UserMapper {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	User findByUsername(String username);
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer addNew(User user);
	
	/**
	 * 根据用户id查询用户数据
	 * @param uid 用户id
	 * @return 用户信息
	 */
	User findByUid(Integer uid);
	
	/**
	 * 修改密码
	 * @param password 原密码
	 * @return	受影响的行数
	 */
	Integer updatePassword(@Param("uid")Integer uid,
			@Param("password")String password,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return 受影响的行数
	 */
	Integer updateUserInfo(User user);
	
	/**
	 *  修改用户头像
	 * @param avater 头像路径
	 * @param uid 用户id
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改时间
	 * @return
	 */
	Integer updateAvater(@Param("avater")String avater,
			@Param("uid")Integer uid,
			@Param("modifiedUser")String modifiedUser,
			@Param("modifiedTime")Date modifiedTime);
}

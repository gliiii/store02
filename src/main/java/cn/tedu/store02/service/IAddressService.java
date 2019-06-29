package cn.tedu.store02.service;

import java.util.List;

import cn.tedu.store02.entity.Address;
import cn.tedu.store02.service.ex.AccessDeniedException;
import cn.tedu.store02.service.ex.AddressNotFoundException;
import cn.tedu.store02.service.ex.DeleteException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.UpdateException;

/**
 * 处理用户收货地址的业务层接口
 * @author glii0
 *
 */
public interface IAddressService {
	/**
	 * 增加用户地址
	 * @param address 用户地址
	 * @param username 用户名
	 * @throws InsertException 添加数据异常
	 */
	void addNew(Address address,String username)throws InsertException;
	
	/**
	 * 根据uid获取收货地址集合
	 * @param uid 用户id
	 * @return 收货地址集合
	 */
	List<Address> getListByUid(Integer uid);
	
	/**
	 * 设置默认地址
	 * @param aid 地址id
	 * @param uid 用户id
	 * @param username 用户名
	 * @throws UpdateException 更新异常
	 * @throws AddressNotFoundException 地址不存在异常
	 * @throws AccessDeniedException 访问拒绝异常
	 */
	void setDefault(Integer aid,Integer uid,String username)throws UpdateException,AddressNotFoundException,AccessDeniedException;
	
	/**
	 * 根据地址id删除地址
	 * @param aid 地址id
	 * @param uid 用户id
	 * @param username 用户名
	 * @throws DeleteException 删除异常
	 * @throws AddressNotFoundException 地址未找到异常
	 * @throws AccessDeniedException 访问拒绝异常
	 */
	void deleteByAid(Integer aid,Integer uid,String username)throws DeleteException,AddressNotFoundException,AccessDeniedException;
	
	/**
	 * 根据aid查询地址数据
	 * @param aid
	 * @return
	 */
	Address getByAid(Integer aid);
}

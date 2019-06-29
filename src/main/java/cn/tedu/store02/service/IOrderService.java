package cn.tedu.store02.service;

import cn.tedu.store02.entity.Order;
import cn.tedu.store02.service.ex.AddressNotFoundException;
import cn.tedu.store02.service.ex.CartNotFoundException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.UserNotFoundException;

/**
 * 处理订单数据的业务层接口
 * @author glii0
 *
 */
public interface IOrderService {
	/**
	 * 创建订单
	 * @param uid 当前登陆的用户的id
	 * @param aid 当前收货地址的id
	 * @param cids 购物车的数据id
	 * @param username 当前登陆的用户名
	 * @return 订单数据
	 * @throws InsertException 插入异常
	 * @throws AddressNotFoundException 地址未找到异常
	 * @throws UserNotFoundException 用户未找到异常
	 * @throws CartNotFoundException 购物商品信息未找到异常
	 */
	Order createOrder(Integer uid,Integer aid,Integer[] cids,String username)
		throws InsertException,AddressNotFoundException,UserNotFoundException,CartNotFoundException;
}

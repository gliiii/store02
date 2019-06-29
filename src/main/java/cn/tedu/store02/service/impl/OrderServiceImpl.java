package cn.tedu.store02.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store02.entity.Address;
import cn.tedu.store02.entity.Order;
import cn.tedu.store02.entity.OrderItem;
import cn.tedu.store02.mapper.OrderMapper;
import cn.tedu.store02.service.IAddressService;
import cn.tedu.store02.service.ICartService;
import cn.tedu.store02.service.IOrderService;
import cn.tedu.store02.service.ex.AddressNotFoundException;
import cn.tedu.store02.service.ex.CartNotFoundException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.UserNotFoundException;
import cn.tedu.store02.vo.CartVO;
/**
 * 处理订单数据的业务层实现类
 * @author glii0
 *
 */
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	OrderMapper mapper;
	
	@Autowired
	ICartService cartService;
	
	@Autowired
	IAddressService addressService;
	
	
	@Override
	public Order createOrder(Integer uid, Integer aid, Integer[] cids, String username)
			throws InsertException, AddressNotFoundException, UserNotFoundException, CartNotFoundException {
		//根据cids查询对应的购物车数据：cartService.getListByCids(Integer[] cids)
				List<CartVO> cartList=cartService.getListByCids(cids);
				Long totalPrice=0L;
				//遍历并计算总价：total_price
				for (CartVO cartVO : cartList) {
					 totalPrice+=cartVO.getPrice()*cartVO.getNum();
				}
				//创建订单Order对象
				Order order=new Order();
				//向Order对象中封装uid
				order.setUid(uid);
				//根据aid查询地址数据:addressService.getByAid(aid)
				Address data=addressService.getByAid(aid);
				//判断查询结果，如果为null，抛出异常
				if(data==null) {
					throw new AddressNotFoundException("地址不存在");
				}
				//向Order对象封装receiver,phone,address
				String receiver=data.getReceiver();
				String phone=data.getPhone();
				String address=data.getDistrict()+data.getAddress();
				Date now=new Date();
				
				order.setReceiver(receiver);
				order.setPhone(phone);
				order.setAddress(address);
				//向Order对象封装state值为0
				order.setState(0);
				//向Order对象封装orderTime为now
				order.setOrderTime(now);
				//向Order对象封装total_price
				order.setTotalPrice(totalPrice);
				//向Order对象封装4项日志
				order.setCreatedUser(username);
				order.setCreatedTime(now);
				order.setModifiedUser(username);
				order.setModifiedTime(now);
				//执行插入订单数据：insertOrder(order)
				insertOrder(order);
				//根据cids查询对应的购物车数据：cartService.getListByCids(Integer[] cids)
				for (CartVO cartVO : cartList) {
					//遍历查询结果
					Long goodsId=cartVO.getGid();
					String goodsImage=cartVO.getImage();
					Integer goodsNum=cartVO.getNum();
					Long goodsPrice=cartVO.getPrice();
					String goodsTitle=cartVO.getTitle();
					
					//--创建OrderItem对象
					OrderItem orderItem=new OrderItem();
					//--向OrderItem对象中封装商品相关的五个数据
					orderItem.setGoodsId(goodsId);
					orderItem.setGoodsImage(goodsImage);
					orderItem.setGoodsNum(goodsNum);;
					orderItem.setGoodsPrice(goodsPrice);;
					orderItem.setGoodsTitle(goodsTitle);;
					//向Order对象封装4项日志
					orderItem.setCreatedUser(username);
					orderItem.setCreatedTime(now);
					orderItem.setModifiedUser(username);
					orderItem.setModifiedTime(now);
					//--向OrderItem对象中封装oid
					orderItem.setOid(order.getOid());
					//--执行插入订单商品数据：insertOrderItem(orderItem)
					insertOrderItem(orderItem);
				}
				//--TODO 将t_goods表中的库存减少
				//-- TODO 删除t_cart表中对应的数据
				
				//返回
				return order;
	}

	/**
	 * 添加订单
	 * @param order 订单数据
	 */
	private void insertOrder(Order order) {
		Integer rows= mapper.insertOrder(order);
		if(rows!=1) {
			throw new InsertException("添加订单失败。添加数据异常");
		}
	};
	
	/**
	 * 添加订单商品
	 * @param orderItem 订单商品数据
	 */
	private void insertOrderItem(OrderItem orderItem) {;
		Integer rows=mapper.insertOrderItem(orderItem);
		if(rows!=1) {
			throw new InsertException("添加订单商品失败。添加数据异常");
		}
	}

}

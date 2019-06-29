package cn.tedu.store02.mapper;

import cn.tedu.store02.entity.Order;
import cn.tedu.store02.entity.OrderItem;
import cn.tedu.store02.vo.OrderVO;

/**
 * 处理订单数据的持久层接口
 * @author glii0
 *
 */
public interface OrderMapper {
	/**
	 * 添加新订单
	 * @param order 订单
	 * @return 受影响的行数
	 */
	Integer insertOrder(Order order);

	/**
	 * 添加新商品订单
	 * @param orderItem 订单商品
	 * @return 受影响的行数
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	/**
	 * 根据id查询订单详情
	 * @param oid
	 * @return
	 */
	OrderVO findByOid(Integer oid);
}

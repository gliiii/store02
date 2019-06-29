package cn.tedu.store02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store02.entity.Order;
import cn.tedu.store02.service.IOrderService;
import cn.tedu.store02.util.ResponseResult;

/**
 * 处理订单数据的控制器层
 * @author glii0
 *
 */
@RequestMapping("/orders")
@RestController
public class OrderController extends BaseController{
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/create")
	public ResponseResult<Order> handleCreateOrder(Integer aid,Integer[] cids,
			HttpSession session){
		//从session中获取用户id
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		//执行
		Order order=orderService.createOrder(uid, aid, cids, username);
		//返回
		return new ResponseResult<>(SUCCESS,order);
	}
}

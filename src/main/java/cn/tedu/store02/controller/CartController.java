package cn.tedu.store02.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store02.entity.Cart;
import cn.tedu.store02.service.ICartService;
import cn.tedu.store02.util.ResponseResult;
import cn.tedu.store02.vo.CartVO;

/**
 * 处理购物车数据的控制器层
 * @author glii0
 *
 */
@RequestMapping("/carts")
@RestController
public class CartController extends BaseController{
	@Autowired
	private ICartService service;
	
	@PostMapping("/add")
	public ResponseResult<Void> handleAddToCart(@RequestParam("gid")Long gid,
			@RequestParam("num")Integer num,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		Cart cart=new Cart();
		cart.setUid(uid);
		cart.setGid(gid);
		cart.setNum(num);
		service.addToCart(cart, username);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/")
	public ResponseResult<List<CartVO>> handleGetListByUid(HttpSession session,String by,
			Integer[] cids){
		//从session中获取uid
		Integer uid=getUidFromSession(session);
		//调用业务层getListByUid(uid)方法
		List<CartVO> data;
		if("cids".equals(by)) {
			data=service.getListByCids(cids);
		}else {
			data=service.getListByUid(uid);
		}
		//返回
		return new ResponseResult<>(SUCCESS,data);
	}
	
	@PostMapping("{cid}/add_num")
	public ResponseResult<Integer> handleAddNum(@PathVariable("cid")Integer cid,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		Integer data= service.addNum(cid, uid, username);
		return new ResponseResult<Integer>(SUCCESS,data);
	}
	
	@PostMapping("{cid}/reduce_num")
	public ResponseResult<Integer> handleReduceNum(@PathVariable("cid")Integer cid,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		Integer data= service.reduceNum(cid, uid, username);
		return new ResponseResult<Integer>(SUCCESS,data);
	}
	
	@PostMapping("/{cid}/delete")
	public ResponseResult<Void> handleDelteByCid(@PathVariable("cid")Integer cid,HttpSession session){
		Integer uid=getUidFromSession(session);
		service.deleteByCid(cid, uid);
		return new ResponseResult<Void>(SUCCESS);
	}
	 
}

package cn.tedu.store02.controller;
/**
 * 处理用户收货地址的控制器层
 * @author glii0
 *
 */

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store02.entity.Address;
import cn.tedu.store02.service.IAddressService;
import cn.tedu.store02.util.ResponseResult;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{
	@Autowired
	IAddressService service;
	
	@PostMapping("/addnew")
	public ResponseResult<Void> handleAddNew(Address address,HttpSession session){
		//从session中获取username
		String username=session.getAttribute("username").toString();
		//从session中获取uid，并绑定到address当中
		Integer uid=getUidFromSession(session);
		address.setUid(uid);
		//执行添加收货地址
		service.addNew(address, username);
		return new ResponseResult<>(SUCCESS);
	}
	
	@GetMapping("/")
	public ResponseResult<List<Address>> handleGetListByUid(HttpSession session){
		Integer uid=getUidFromSession(session);
		List<Address> data=service.getListByUid(uid);
		return new ResponseResult<List<Address>>(SUCCESS,data);
	}
	
	@PostMapping("/{aid}/set_default")
	public ResponseResult<Void> handleSetDefault(@PathVariable("aid")Integer aid,HttpSession session){
		//从session中获取uid
		Integer uid=getUidFromSession(session);
		//从session中获取username
		String username=session.getAttribute("username").toString();
		//执行
		service.setDefault(aid, uid, username);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/{aid}/delete")
	public ResponseResult<Void> handleDelete(@PathVariable("aid")Integer aid,HttpSession session){
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		service.deleteByAid(aid, uid, username);
		return new ResponseResult<Void>(SUCCESS);
	}

}

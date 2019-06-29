package cn.tedu.store02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store02.entity.Goods;
import cn.tedu.store02.service.IGoodsService;
import cn.tedu.store02.util.ResponseResult;

/**
 * 处理商品数据的控制器层
 * @author glii0
 *
 */
@RequestMapping("/goods")
@RestController
public class GoodsController extends BaseController{
	@Autowired
	IGoodsService service;
	
	@GetMapping("/hot")
	public ResponseResult<List<Goods>> handleGetHotGoods(){
		Integer count=4;
		List<Goods> data=service.getHotGoods(count);
		return new ResponseResult<List<Goods>>(SUCCESS,data);
	}
	
	@GetMapping("/{id}/details")
	public ResponseResult<Goods> handleGetById(@PathVariable("id")Long id){
		Goods data=service.getById(id);
		return new ResponseResult<Goods>(SUCCESS,data);
	}
}

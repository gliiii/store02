package cn.tedu.store02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store02.entity.District;
import cn.tedu.store02.service.IDistrictService;
import cn.tedu.store02.util.ResponseResult;

/**
 * 处理省市区数据的控制器类
 * @author glii0
 *
 */
@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController{
	@Autowired
	IDistrictService service;
	
	@GetMapping("/")
	public ResponseResult<List<District>> handleGetListByParent(@RequestParam("parent")String parent){
		List<District> data=service.getListByParent(parent);
		return new ResponseResult<List<District>>(SUCCESS,data); 
	}
	
	@GetMapping("/{code}")
	public ResponseResult<District> handleGetByCode(@PathVariable("code")String code){
		District data=service.getByCode(code);
		return new ResponseResult<District>(SUCCESS,data);
	}
	
}

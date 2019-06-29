package cn.tedu.store02.controller;
/**
 * 控制器层的基类
 */

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store02.service.ex.FileContentTypeException;
import cn.tedu.store02.service.ex.FileEmptyException;
import cn.tedu.store02.service.ex.FileSizeException;
import cn.tedu.store02.service.ex.FileUploadException;
import cn.tedu.store02.service.ex.InsertException;
import cn.tedu.store02.service.ex.PasswordNotMatchException;
import cn.tedu.store02.service.ex.ServiceException;
import cn.tedu.store02.service.ex.UpdateException;
import cn.tedu.store02.service.ex.UserConflictException;
import cn.tedu.store02.service.ex.UserNotFoundException;
import cn.tedu.store02.util.ResponseResult;

public class BaseController {
	
	public static final int SUCCESS=200;
	
	/**
	 * 根据session获取uid
	 * @param session
	 * @return 用户id
	 */
	public Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public ResponseResult<Void> handleException(Exception e){
		ResponseResult<Void> rr=new ResponseResult<Void>();
		rr.setMessage(e.getMessage());
		if(e instanceof UserConflictException) {
			//400-用户名冲突异常
			rr.setState(400);
		}else if(e instanceof InsertException){
			//500-数据插入异常
			rr.setState(500);
		}else if(e instanceof UserNotFoundException) {
			//401-用户不存在异常
			rr.setState(401);
		}else if(e instanceof PasswordNotMatchException) {
			//402-密码错误异常
			rr.setState(402);
		}else if(e instanceof UpdateException) {
			//501-更新数据异常
			rr.setState(501);
		}else if(e instanceof FileEmptyException) {
			//601-上传文件为空异常
			rr.setState(601);
		}else if(e instanceof FileSizeException) {
			//602-上传文件大小超出限制异常
			rr.setState(602);
		}else if(e instanceof FileContentTypeException) {
			//603-上传文件类型错误异常
			rr.setState(603);
		}else if(e instanceof FileUploadException) {
			//603-上传文件时读写异常
			rr.setState(604);
		}
		return rr;
	}
}

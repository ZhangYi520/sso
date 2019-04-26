package com.zy.sso.file.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zy.sso.base.result.PageRequest;
import com.zy.sso.base.result.Result;
import com.zy.sso.file.entity.FileEntity;
import com.zy.sso.file.service.serviceI.FileServiceI;

@Controller
@RequestMapping("/file")
@CrossOrigin
@Api(tags = { "文件管理-csx-1" })
public class FileController {
	private static Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	FileServiceI fileServiceImp;

	@ResponseBody
	@PostMapping("/insertFile")
	@ApiOperation(value = "上传文件接口", notes = "上传文件到本地文件夹并返回入库的文件ID")
	@Transactional
	public Result<Object> insertFile(
			HttpServletRequest request,
			@RequestParam(name = "picture_file", required = true) MultipartFile picture_file) {
		Integer id = fileServiceImp.uploadFile(request, picture_file);
		FileEntity  file = fileServiceImp.getFileById(id);
		log.info("FileController.insertFile.返回图片上传信息："+file.toString());
		return Result.success(file);
	}
	
	// 查询所有文件上传信息
	@ResponseBody
	@PostMapping("/getFile")
	@ApiOperation(value = "查询文件上传信息", notes = "查询文件上传信息")
	public Result<List<FileEntity>> getFile(HttpServletResponse response,PageRequest param) {
		log.info("FileController.getFile.请求参数：" + param.toString());
		Result<List<FileEntity>> result = fileServiceImp.getFile(param);
		log.info("FileController.getFile.返回参数：" + result.toString());
		return result;
	}
}

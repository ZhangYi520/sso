package com.zy.sso.file.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zy.sso.base.result.PageRequest;
import com.zy.sso.file.entity.FileEntity;
import com.zy.sso.file.vo.PageParam;



@Repository
public interface FileDao {

	//插入文件
	public Integer insertFile(FileEntity entity);
	//删除文件
	public void deleteFile(Integer id);
	//根据ID查询文件
	public FileEntity getFileById(Integer id);
	//总条数
	public Integer getFileCount(PageParam param);
	
	//分页查询文件
	public List<FileEntity> getFile(PageRequest vo);
}

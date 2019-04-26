package com.zy.sso.file.service.serviceImp;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageHelper;
import com.zy.sso.base.exception.GlobalException;
import com.zy.sso.base.result.CodeMsg;
import com.zy.sso.base.result.PageRequest;
import com.zy.sso.base.result.Result;
import com.zy.sso.base.util.StringUtil;
import com.zy.sso.file.config.PropertiesConfig;
import com.zy.sso.file.entity.FileEntity;
import com.zy.sso.file.mybatis.dao.FileDao;
import com.zy.sso.file.service.serviceI.FileServiceI;
import com.zy.sso.file.util.FileUploadTool;


@Service
public class FileServiceImp  implements FileServiceI {
	
	@Autowired
	FileDao fileDao;
	
	@Override
	public Integer uploadFile(HttpServletRequest request, MultipartFile multipartFile){
		if(null == multipartFile){
			throw new GlobalException(CodeMsg.BASE_BIND_ERROR);
		}
    	FileUploadTool fileUploadTool = new FileUploadTool();
        FileEntity entity = fileUploadTool.createFile(multipartFile, request);
        fileDao.insertFile(entity);
        return entity.getId();
	}
	
	@Override
	public Integer modifyFile(HttpServletRequest request, MultipartFile multipartFile,Integer oldFileId){
		//删除原来的文件DB
		fileDao.deleteFile(oldFileId);
		//上传新的文件到本地
		FileUploadTool fileUploadTool = new FileUploadTool();
		FileEntity file = fileUploadTool.createFile(multipartFile, request);
		//保存文件DB
		fileDao.insertFile(file);
		return file.getId();
	}
	
	@Override
	public void deleteFile(Integer fileId){
		FileEntity file = fileDao.getFileById(fileId);
		if(null!=file && null!=file.getId()){
			fileDao.deleteFile(file.getId());
		}
	}
	
	@Override
	public FileEntity getFileById(Integer fileId){
		
		FileEntity  fileVo = fileDao.getFileById(fileId);
		fileVo.setPathAll(PropertiesConfig.getProperty("spring.http.url")+fileVo.getPathAll());
		return  fileVo;
	}
	
	@Override
	public Result<List<FileEntity>> getFile(PageRequest vo) {

		PageHelper.startPage(vo.getPage(), vo.getPageSize(),vo.getSort()+" "+vo.getSortOrder());
		List<FileEntity> fileVo = fileDao.getFile(vo);
		if(null != fileVo ){
			for (FileEntity fileEntity : fileVo) {
				if(StringUtil.isNotNull(fileEntity.getPathAll())) {
					fileEntity.setPathAll(PropertiesConfig.getProperty("spring.http.url")+fileEntity.getPathAll());
				}
			
			}
		}
		return Result.success(fileVo);
	}
}

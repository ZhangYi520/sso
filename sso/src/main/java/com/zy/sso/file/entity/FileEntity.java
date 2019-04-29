package com.zy.sso.file.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@ApiModel
/**
 * 
 **************************************************
 * @Description:文件实体类
 * @author:ZY
 * @date:2019年01月14日
 **************************************************
 */
public class FileEntity implements Serializable {


	private static final long serialVersionUID = 5438588206722475810L;

    @ApiModelProperty(name = "id", value = "文件ID", dataType = "Integer")
	private Integer id;

    @ApiModelProperty(name = "type", value = "文件类型", dataType = "String")
	private String type;
    
    @ApiModelProperty(name = "size", value = "文件大小", dataType = "String")
	private String size;
    
    @ApiModelProperty(name = "pathAll", value = "文件完整路径[用于长传完后图片展示]", dataType = "String")
	private String pathAll;
    
    @ApiModelProperty(name = "nameOld", value = "文件名称旧", dataType = "String")
	private String nameOld;

    @ApiModelProperty(name = "nameNew", value = "文件名称新", dataType = "String")
	private String nameNew;

    @ApiModelProperty(name = "ct", value = "创建时间", dataType = "String")
	private String ct;	

    @ApiModelProperty(name = "path", value = "文件路径[保存时传此值]", dataType = "String")
	private String path;
			
}

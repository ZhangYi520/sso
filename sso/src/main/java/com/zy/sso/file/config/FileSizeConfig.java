package com.zy.sso.file.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileSizeConfig {

	/**  
     * 文件上传配置  
     * @return  
     */  
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件最大  
        factory.setMaxFileSize("1024000KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("10240000KB");  
        return factory.createMultipartConfig();  
    } 
}

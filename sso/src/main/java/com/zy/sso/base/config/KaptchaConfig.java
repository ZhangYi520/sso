package com.zy.sso.base.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

//验证码配置类
@Component
public class KaptchaConfig {
	@Bean  
    public DefaultKaptcha getDefaultKaptcha(){  
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();  
        Properties properties = new Properties();  
        properties.setProperty("kaptcha.border", "yes");  //是否有边框
        properties.setProperty("kaptcha.border.color", "105,179,90"); //边框颜色 
        properties.setProperty("kaptcha.textproducer.font.color", "green");//字体颜色
        properties.setProperty("kaptcha.noise.color", "0,255,255");  //干扰颜色
        properties.setProperty("kaptcha.image.width", "110");  //宽度
        properties.setProperty("kaptcha.image.height", "40");  //高度
        properties.setProperty("kaptcha.textproducer.font.size", "30"); //字体大小
        properties.setProperty("kaptcha.textproducer.char.space", "3"); //文字间距
        properties.setProperty("kaptcha.session.key", "code");  
        properties.setProperty("kaptcha.textproducer.char.length", "4");  //验证码个数
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");  //字体样式
        Config config = new Config(properties);  
        defaultKaptcha.setConfig(config);  

        return defaultKaptcha;  
    }  
}

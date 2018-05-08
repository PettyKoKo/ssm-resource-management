package com.kzl.rm.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MyBatisGeneratorTest {

	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;//如果已经生成过了是否进行覆盖
        File configFile = new File("mbg.xml");
        ConfigurationParser cfgParser = new ConfigurationParser(warnings);//配置文件解析器
        Configuration config = cfgParser.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
        System.out.println("mybatis 代码生成成功。。。");

	}

}

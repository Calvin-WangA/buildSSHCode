package com.wch.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 加载对应的配置文件
 * Created by calvinwang on 16-7-10.
 */
public class JdbcTools {

    private static Properties prop;

    private static String projectUrl;

    private JdbcTools() {
    }

    /**
     * 该代码块加载对应的属性文件到当前上下文中
     */
    static{

        projectUrl = System.getProperty("user.dir");
        try{
            prop = System.getProperties();
            prop.load(new FileInputStream(projectUrl +"/jdbc.properties"));
            prop.load(new FileInputStream(projectUrl + "/config.properties"));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Properties getProp(){

        return prop;
    }
}

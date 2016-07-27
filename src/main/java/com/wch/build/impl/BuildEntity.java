package com.wch.build.impl;

import com.wch.build.iface.BuildJavaCode;
import com.wch.util.CommonTools;

import java.util.List;

/**
 * 构建需要的文件
 * Created by calvinwang on 16-7-10.
 */
public class BuildEntity extends BuildJavaCode {

    public BuildEntity(String tableName) {
        super(tableName);
    }

    /**
     * 往实体类中追加属性
     * @param propName 属性名
     */
    private  String appendProperty( String propName) {

        try {

            StringBuilder builder = new StringBuilder();
            //写入属性声明
            String prop = "private String " + propName + ";\n";
            builder.append(prop);
            //首字母转换为大写以匹配方法
            String medName = propName.replaceFirst(propName.substring(0,1)
                    ,propName.substring(0,1).toUpperCase());
            //写入get方法
            prop = "public String get" + medName + "(){"
                    + " return this." + propName + "=" + propName + ";"
                    + "}\n";
            builder.append(prop);
            //写入set方法
            prop = "public void set" + medName + "(){"
                    + "this." + propName + "=" + propName + ";"
                    + "}\n";
            builder.append(prop);

            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String buildFileBody(List<String[]> cols) {
        //遍历属性追加到文件
        String[] infos;
        String content = "";
        for (int i = 0; i < cols.size(); i++) {
            infos = cols.get(i);
            String propName = CommonTools.buildPropertyName(infos[0]);
            content += appendProperty(propName);
        }

        return content;
    }

}

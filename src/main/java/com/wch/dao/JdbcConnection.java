package com.wch.dao;

import java.sql.PreparedStatement;

import com.wch.build.iface.BuildFile;
import com.wch.build.impl.*;
import com.wch.util.FormatJavaCode;
import com.wch.util.JdbcTools;
import com.wch.util.CommonTools;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 实现数据库中表的列信息的查询
 * Created by calvinwang on 16-7-9.
 */
public class JdbcConnection {

    private static Properties prop;

    private static Connection conn;

    private static PreparedStatement prepStat;

    private static ResultSet resultSet;

    /**
     * 产生对应数据库的连接
     */
    static {
        try {
            prop = JdbcTools.getProp();
            Class.forName(prop.getProperty("driverClass"));
            conn = DriverManager.getConnection(prop.getProperty("url")
                    + "?user="+ prop.getProperty("user")
                    + "&password=" + prop.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过执行自定义的sql语句查询出对应表的列信息
     * @param sql 要执行的查询sql的语句
     * @return
     */
    public static List<String[]> executeMySql(String sql) {

        List<String[]> colInfos;
        try {
            prepStat = conn.prepareStatement(sql);
            prepStat.setString(1, prop.getProperty("schemaName"));
            prepStat.setString(2, prop.getProperty("tableName"));
            resultSet = prepStat.executeQuery();

            String[] columns = prop.getProperty("columns").split(":");
            int cursorLen = columns.length;
            String[] columnInfo;
            colInfos = new ArrayList<String[]>();

            while(resultSet.next()) {
                columnInfo = new String[cursorLen];

                for(int i = 0;  i < cursorLen; i++)
                {
                    columnInfo[i] = resultSet.getString(columns[i]);
                }

                colInfos.add(columnInfo);
            }

            return colInfos;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        String sql = prop.getProperty("querySql");

        List<String[]> cols = executeMySql(sql);

        String tableName = prop.getProperty("tableName");

        String pack = prop.getProperty("package");

        String fileName = pack + ".entity."
                + CommonTools.buildEntityName(tableName);
        BuildFile build = new BuildEntity(tableName);

        build.buildFile(fileName,"java", cols);

        BuildFile dao = new BuildJavaInterface(tableName);
        fileName = pack + ".dao."
                + CommonTools.buildEntityName(tableName);
        dao.buildFile(fileName + "Mapper", "java", cols);

        //构建service文件
        BuildFile service = new BuildJavaInterface(tableName);
        fileName = pack + ".service."
                + CommonTools.buildEntityName(tableName);
        service.buildFile(fileName + "Service", "java", cols);

        BuildFile serviceImpl = new BuildJavaServiceImpl(tableName);
        fileName = pack + ".service.impl."
                + CommonTools.buildEntityName(tableName);
        serviceImpl.buildFile(fileName + "ServiceImpl", "java", cols);

        //测试Controller的构建
        BuildFile controller = new BuildJavaController(tableName);
        fileName = pack + ".controller." + CommonTools.buildEntityName(tableName);
        controller.buildFile(fileName + "Controller", "java", cols);

        //构建mybatis的Mapper文件
        BuildFile mapper = new BuildMybatisMapper();
        fileName = pack + ".dao."
                + CommonTools.buildEntityName(tableName);
        mapper.buildFile(fileName + "Mapper", "xml", cols);


        /*String[] infos;
        for(int i = 0; i < cols.size(); i++){
            infos = cols.get(i);
            for(int j = 0; j < infos.length; j++){
                System.out.print(infos[j] + " ");
            }
            System.out.println();
        }*/
    }


}

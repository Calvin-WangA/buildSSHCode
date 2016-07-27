package com.wch;

import com.wch.build.iface.BuildFile;
import com.wch.build.impl.BuildEntity;
import com.wch.build.impl.BuildMybatisMapper;
import com.wch.dao.JdbcConnection;
import com.wch.util.BuildMybatisSQL;
import com.wch.util.CommonTools;
import com.wch.util.JdbcTools;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static com.wch.dao.JdbcConnection.executeMySql;

/**
 * Created by calvinwang on 16-7-17.
 */
public class selectTest {

    private static List<String[]> cols;

    private static String tableName;

    private static Properties prop;

    static{
        prop = JdbcTools.getProp();

        String sql = prop.getProperty("querySql");

        cols = executeMySql(sql);
        tableName = prop.getProperty("tableName");
    }

    @Test
    public void testBuildQueryForList(){

        BuildMybatisSQL mSql = new BuildMybatisSQL( tableName,cols);
        System.out.println(mSql.buildQueryForList());

        return ;
    }

    @Test
    public void testBuildQueryForObject(){

        BuildMybatisSQL mSql = new BuildMybatisSQL(tableName, cols);
        System.out.println(mSql.buildQueryForObject());

        return ;
    }

    @Test
    public void testBuildUpdateSql(){

        BuildMybatisSQL mSql = new BuildMybatisSQL( tableName,cols);
        System.out.println(mSql.buildUpdateSql());

        return ;
    }

    @Test
    public void testBuilderInsertSql(){

        BuildMybatisSQL mSql = new BuildMybatisSQL( tableName,cols);
        System.out.println(mSql.buildInsertSql());

        return ;
    }

    @Test
    public void testBuilderDeleteSql(){

        BuildMybatisSQL mSql = new BuildMybatisSQL( tableName,cols);
        System.out.println(mSql.builderDeleteSql());

        return ;
    }

    @Test
    public void testBuildDml(){

        BuildMybatisSQL mSql = new BuildMybatisSQL(tableName, cols);
        System.out.println(mSql.buildDml());

        return ;
    }

}

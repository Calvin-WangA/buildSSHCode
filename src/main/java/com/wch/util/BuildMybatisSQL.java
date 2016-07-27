package com.wch.util;

import java.util.List;
import java.util.Properties;

/**
 * Created by calvinwang on 16-7-17.
 */
public class BuildMybatisSQL {

    private String className;

    private String proName;

    private String tableName;

    private List<String[]> cols;

    private String[] infos;

    private Properties prop;

    public List<String[]> getCols() {
        return cols;
    }

    public void setCols(List<String[]> cols) {
        this.cols = cols;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BuildMybatisSQL(String className, List<String[]> cols){
        this.className = CommonTools.buildEntityName(className);
        this.tableName = className;
        this.proName = CommonTools.buildPropertyName(className);
        this.cols = cols;
        this.prop = JdbcTools.getProp();
    }

    /**
     *
     * @return
     */
    public String buildDml(){

        StringBuilder builder = new StringBuilder();

        builder.append(buildResultMap());
        builder.append(buildQueryForList());
        builder.append(buildQueryForObject());
        builder.append(buildInsertSql());
        builder.append(buildUpdateSql());
        builder.append(builderDeleteSql());

        return builder.toString();
    }

    /**
     *
     * @return
     */
    public String buildQueryForList(){

        String[] colNames = CommonTools.getColumnNames(cols);
        StringBuilder builder = new StringBuilder("<select id=\"get");
        builder.append(className);
        builder.append("forList\" parameterType=\"");
        builder.append(className);
        builder.append("\" resultMap=\"");
        builder.append(proName);
        builder.append("Map\">\n");

        builder.append("    select ");
        builder.append(CommonTools.getStringForColumns(cols));
        builder.append(" from ");
        builder.append(tableName);
        builder.append("\n");
        builder.append(buildQueryForConditionMybatis());

        builder.append("</select>\n");

        return builder.toString();
    }

    public String buildQueryForObject(){

        StringBuilder builder = new StringBuilder("<select id=\"getSingle");
        String[] pKeyCol = JdbcTools.getProp().getProperty("primaryKey").split(",");
        builder.append(className);
        builder.append("\" resultMap=\"");
        builder.append(proName);
        builder.append("Map\">\n");

        builder.append("    select ");
        builder.append(CommonTools.getStringForColumns(cols));
        builder.append(" from ");
        builder.append(tableName);
        builder.append("\n    where ");
        int len = pKeyCol.length;
        for(int i = 0; i < len; i++){
            builder.append(pKeyCol[i]);
            builder.append("=#");
            builder.append(CommonTools.buildPropertyName(pKeyCol[i]));
            builder.append("#\n        and ");
        }
        builder = new StringBuilder(builder.substring(0, builder.length() - 12));

        builder.append("  </select>\n");
        return builder.toString();
    }

    /**
     *
     * @return
     */
    public String buildUpdateSql(){

        StringBuilder builder = new StringBuilder("<update id=\"update");
        builder.append(className);
        builder.append("\" parameterType=\"");
        builder.append(className);
        builder.append("\">\n");

        builder.append("    update ");
        builder.append(tableName);
        builder.append(" set ");
        String[] colNames = CommonTools.getColumnNames(cols);
        String[] proNames = CommonTools.columnsToProperties(cols);
        for(int i = 0; i < colNames.length; i++) {
            builder.append(colNames[i]);
            builder.append("=#");
            builder.append(proNames[i]);
            builder.append("#\n    ");
        }
        builder.append(buildQueryForConditionMybatis());

        builder.append("</update>\n");
        return builder.toString();
    }

    /**
     *
     * @return
     */
    public String buildInsertSql(){

        StringBuilder builder = new StringBuilder("<insert id=\"insert");
        builder.append(className);
        builder.append("\" parameterType=\"");
        builder.append(className);
        builder.append("\">\n");

        builder.append("    insert into ");
        builder.append(tableName);
        builder.append("(");
        builder.append(CommonTools.getStringForColumns(cols));
        builder.append(") \n     values(");

        String[] proNames = CommonTools.columnsToProperties(cols);
        for(int i = 0; i < proNames.length; i++){
            builder.append("#");
            builder.append(proNames[i]);
            builder.append("#,");
        }
        builder = new StringBuilder(builder.substring(0,builder.length() - 1));
        builder.append(")\n");

        builder.append("  </insert>\n");
        return builder.toString();
    }

    /**
     *
     * @return
     */
    public String builderDeleteSql(){

        StringBuilder builder = new StringBuilder("<delete id=\"delete");
        builder.append(className);
        builder.append("\" parameterType=\"");
        builder.append(className);
        builder.append("\">\n");

        builder.append("    delete from ");
        builder.append(tableName);
        builder.append("\n    ");
        builder.append(buildQueryForConditionMybatis());

        builder.append("</delete>\n");
        return builder.toString();
    }


    /**
     *
     * @return
     */
    public String buildQueryForConditionIbatis(){

        String[] names = CommonTools.columnsToProperties(cols);
        StringBuilder builder = new StringBuilder("<dynamic prepend=\"where\">\n");

        for(int i = 0; i < cols.size(); i++){
            infos = cols.get(i);
            builder.append("<IsNotEmpty property=\"");
            builder.append(names[i]);
            builder.append("\" prepend=\"and\">\n");
            builder.append("            ");
            builder.append(infos[0]);
            builder.append("=#");
            builder.append(names[i]);
            builder.append("#\n");
            builder.append("      </IsNotEmpty>\n");
        }

        builder.append("</dynamic>\n");
        return builder.toString();
    }

    public String buildQueryForConditionMybatis(){

        String[] names = CommonTools.columnsToProperties(cols);
        StringBuilder builder = new StringBuilder("<where>\n");

        builder.append("<if test=\"\">\n        1=1\n      </if>\n");
        for(int i = 0; i < cols.size(); i++){
            infos = cols.get(i);
            builder.append("<if test=\"");
            builder.append(names[i]);
            builder.append("!=null and ");
            builder.append(names[i]);
            builder.append("!=''\">\n");
            builder.append("        and ");
            builder.append(infos[0]);
            builder.append("=#{");
            builder.append(names[i]);
            builder.append("}\n");
            builder.append("      </if>\n");
        }

        builder.append("</where>\n");
        return builder.toString();
    }

    public String buildResultMap(){


        StringBuilder builder = new StringBuilder();
        String pack = prop.getProperty("package");
        String[] proNames = CommonTools.columnsToProperties(cols);

        // to do追加当前类的别名
        builder.append("<resultMap id=\"");
        builder.append(proName);
        builder.append("Map\" type=\"");
        builder.append(pack);
        builder.append(".entity.");
        builder.append(className);
        builder.append("\">\n");

        int len  = cols.size();
        /*String pkey = prop.getProperty("primaryKey");
        builder.append("<id column=\"");
        builder.append(pkey);
        builder.append("\" property=\"");
        builder.append(CommonTools.buildPropertyName(pkey));
        builder.append("\"/>\n");*/
        for(int i = 0; i < len; i++){
            builder.append("<result column=\"");
            builder.append(cols.get(i)[0]);
            builder.append("\" property=\"");
            builder.append(proNames[i]);
            builder.append("\"/>\n");
        }
        builder.append("</resultMap>\n");

        return builder.toString();
    }
}

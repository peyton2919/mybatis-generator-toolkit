package cn.peyton.children.mgts.mybatis.template;

import cn.peyton.children.mgts.mybatis.db.DbHelper;
import cn.peyton.children.mgts.mybatis.entity.Column;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.utils.ConvertUtil;

import java.util.List;

/**
 * <h3></h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @email <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date: 2018年8月10日 9:28
 * @version: 1.0.0
 * </pre>
 */
public final class CompatTemplate extends BaseTemplate{

    /**
     * <h4>对象转换传参对象</h4>
     * @param tableName        表名
     * @param resultObjectName 返回对象名
     * @param paramObjectName 参数对象名
     * @return
     */
    public static String createCompat(String tableName, String resultObjectName,String paramObjectName) {
        return createCompat(tableName,resultObjectName,null,paramObjectName,null);
    }

    /**
     * <h4>对象转换传参对象</h4>
     * @param tableName 表名
     * @param resultObjectName 返回对象名
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param paramObjectName 参数对象名
     * @param prefixParam 参数对象 要去除前缀的属性
     * @return
     */
    public static String createCompat(String tableName, String resultObjectName,String  prefixResult,
                                      String paramObjectName,String prefixParam) {

        return createCompat(DbHelper.getTables(tableName,null).get(0),
                    resultObjectName,prefixResult,paramObjectName,prefixParam);
    }

    /**
     * <h4>对象转换传参对象</h4>
     * @param table 表对象
     * @param resultObjectName 返回对象名
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param paramObjectName 参数对象名
     * @param prefixParam 参数对象 要去除前缀的属性
     * @return
     */
    public static String createCompat(Table table, String resultObjectName, String  prefixResult,
                                      String paramObjectName, String prefixParam) {
        if (null == table) {
            return "";
        }
        List<Column> columnList = table.getColumns();
        StringBuffer sb = new StringBuffer();
        int size = columnList.size();
        String _ron = ConvertUtil.toFirstLowerCase(paramObjectName);
        sb.append("\t/**\r\n");
        sb.append("\t * <h4>" + paramObjectName + "对象转成" + resultObjectName + "对象<h4> \r\n");
        //标题注解
        createAnnotationTitle(columnList, sb, size);
        sb.append("\tpublic " + resultObjectName + " compat(" + paramObjectName + " " + _ron + "){ \r\n");
        sb.append("\t\tif(null == "+ _ron +"){\r\n");
        sb.append("\t\t\treturn new " + resultObjectName + "();\r\n");
        sb.append("\t\t}\r\n");

        createContent(prefixResult, prefixParam, columnList, sb, size, _ron,false);
        sb.append("\t\treturn this;\r\n");
        sb.append("\t} \r\n");
        return sb.toString();
    }

}

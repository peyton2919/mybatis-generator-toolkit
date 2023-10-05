package cn.peyton.children.mgts.mybatis.template;

import cn.peyton.children.mgts.mybatis.db.DbHelper;
import cn.peyton.children.mgts.mybatis.entity.Column;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.utils.ConvertUtil;

import java.util.List;

/**
 * <h3>传参对象转换对象</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public final class ConvertTemplate extends BaseTemplate{

    /**
     * <h4>传参对象转换对象</h4>
     * @param tableName        表名
     * @param resultObjectName 转换后对象名
     * @return
     */
    public static String createConvert(String tableName, String resultObjectName) {

        return createConvert(tableName, resultObjectName, null, null);
    }

    /**
     * <h4>传参对象转换对象</h4>
     * @param tableName        表名
     * @param resultObjectName 转换后对象名
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param prefixParam 参数对象 要去除前缀的属性
     * @return
     */
    public static String createConvert(String tableName, String resultObjectName,
                                       String  prefixResult,String prefixParam) {
        return createConvert(DbHelper.getTables(tableName,null).get(0),
                resultObjectName,prefixResult,prefixParam);
    }

    /**
     *  <h4>传参对象转换对象</h4>
     * @param table 表对象
     * @param resultObjectName  转换后对象名
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param prefixParam 参数对象 要去除前缀的属性
     * @return
     */
    public static String createConvert(Table table , String resultObjectName,
                                       String  prefixResult, String prefixParam) {
        if (null == table) {
            return "";
        }
        List<Column> columnList = table.getColumns();
        StringBuffer sb = new StringBuffer();
        int size = columnList.size();
        String _ron = ConvertUtil.toFirstLowerCase(resultObjectName);

        sb.append("\t/**\r\n");
        sb.append("\t * <h4>对象转成" + resultObjectName + "对象<h4> \r\n");
        //标题注解
        createAnnotationTitle(columnList, sb, size);
        sb.append("\tpublic " + resultObjectName + " convert(){ \r\n");

        sb.append("\t\t" + resultObjectName + " " + _ron + " = new " + resultObjectName + "(); \r\n");
        //方法调用
        createContent(prefixResult, prefixParam, columnList, sb, size, _ron,true);
        sb.append("\t\treturn " + _ron + ";\r\n");
        sb.append("\t} \r\n");
        return sb.toString();
    }
}

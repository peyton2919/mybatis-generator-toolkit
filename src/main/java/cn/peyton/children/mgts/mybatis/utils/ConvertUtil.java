package cn.peyton.children.mgts.mybatis.utils;

/**
 * <h3>数据类型转换工具类 .</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public final class ConvertUtil {

    /**
     * <h3>一个字符转换大写字符</h3>
     * <pre>
     *     格式: 属性名 aaaBbbCcc 改成 AaaBbbCcc
     * </pre>
     * @param filed 属性名
     * @return
     */
    public static String convertFirst(String filed){
        if (null == filed){return  null;}

        StringBuffer sb = new StringBuffer(filed);
        String first = sb.substring(0, 1);
        sb.delete(0, 1);
        sb.insert(0, first.toUpperCase());
        first.toLowerCase();
        return sb.toString();
    }

    /**
     * <h3>一个字符转换小写字符</h3>
     * <pre>
     *     格式: 属性名 AaaBbbCcc 改成 aaaBbbCcc
     * </pre>
     * @param name 属性名
     * @return
     */
    public static String toFirstLowerCase(String name) {
        if (null == name){return  null;}

        StringBuffer sb = new StringBuffer(name);
        String first = sb.substring(0, 1);
        sb.delete(0, 1);
        sb.insert(0, first.toLowerCase());
        return sb.toString();
    }

    /**
     * <h3>表名转换成对象名,并把'_'去掉,并把拼接单词第一个字母改成大写</h3>
     * <pre>
     *     格式: 表名 aaa_bbb_ccc 改成 对象 AaaBbbCcc
     * </pre>
     * @param tableName 表名
     * @return
     */
    public static String convertTableName2ObjName(String tableName) {
        if (null == tableName){return  null;}

        StringBuffer sb = new StringBuffer(tableName);
        String first = sb.substring(0, 1);
        sb.delete(0, 1);
        sb.insert(0, first.toUpperCase());

        return getStringBuffer(sb).toString();//方法调用
    }

    /**
     * <h3>表的字段名转换成属性名</h3>
     * <pre>
     *     格式: 字段名 aaa_bbb_ccc 改成 属性名 aaaBbbCcc
     * </pre>
     * @param columnName
     * @return
     */
    public static String convertColumnName2FieldName(String columnName) {
        if (null == columnName){return  null;}
        StringBuffer sb = new StringBuffer(columnName);
        return getStringBuffer(sb).toString();
    }

    /**
     * <h3>转换对象属性类型</h3>
     *  <pre>
     *      数据库字段类型 转成 对象属性类型
     *  </pre>
     * @param columnType 字段类型
     * @param sbpackage 需要导入的包
     * @return
     */
    public static String convertFieldType(String columnType, StringBuffer sbpackage) {
        columnType = columnType.toLowerCase();
        if (columnType.contains("unsigned")){
            String _tmp = columnType.replace(" unsigned","");
            columnType = _tmp;
        }
        if ("varchar".equals(columnType) ||
                "nvarchar".equals(columnType) ||
                "longvarchar".equals(columnType) ||
                "text".equals(columnType) ||
                "char".equals(columnType)){
            return "String";
        }else if ("datetime".equals(columnType) ||
                "date".equals(columnType) ||
                "timestamp".equals(columnType) ||
                "time".equals(columnType) ||
                "year".equals(columnType)) {
            if (null != sbpackage) {
                sbpackage.append("import java.util.Date;\r\n");
            }
            return "Date";
        } else if ("bit".equals(columnType)) {
            return "Boolean";
        } else if ("int".equals(columnType) || "integer".equals(columnType) || "tinyint".equals(columnType)) {
            return "Integer";
        }else if ("smallint".equals(columnType)) {
            return "Short";
        } else if ("float".equals(columnType) || "real".equals(columnType)) {
            return "Float";
        }else if ("bigint".equals(columnType)) {
            return "Long";
        }else if ("double".equals(columnType)) {
            return "Double";
        }else if ("decimal".equals(columnType) || "numeric".equals(columnType) || "money".equals(columnType)) {
            if (null != sbpackage) {
                sbpackage.append("import java.math.BigDecimal;\r\n");
            }
            return "BigDecimal";
        } else if ("binary".equals(columnType) || "varbinary".equals(columnType) || "longvarbinary".equals(columnType)) {
            return "Byte[]";
        }
        return "ErrorType";
    }

    /**
     * <h4>数据库字段类型 转换 对象属性类型</h4>
     * <pre>
     *     用在Mapper.xml 文件中 参数类型[parameterType]
     * </pre>
     * @param columnType 数据库 字段 类型
     * @return 对象属性类型
     */
    public static String convertFieldTypePath(String columnType) {
        if ("INTEGER".equals(columnType)) {
            return "java.lang.Integer";
        } else if ("BIGINT".equals(columnType)) {
            return  "java.lang.Long";
        } else if ("String".equals(columnType)) {
            return "java.lang.String";
        }else {
            return "string";
        }
    }

    /**
     * <h4>移除前缀，并将表名转成对象名</h4>
     * <pre>
     *  1) . 如 removePrefix 为空时, 直接把首字母转换成大写，并把有下划线的去掉，下划线后的字母改成大写
     *      转换 样式: sys_user_info ==> SysUserInfo
     *  如 removePrefix 不为空时{比对多个可以用 ','分开如 'pu_,sys_...'},前部有相同则去除,否则 如（1）转换
     *      removePrefix = "pu_,sys_";
     *      转换 样式 sys_user_info ==> UserInfo [找到相同]
     *          样式 tb_user_info ==> TbUserInfo [找到相同]
     * </pre>
     * @param tableName
     * @param removePrefix
     * @return
     */
    public static String removePrefixTableName2ObjName(String tableName, String removePrefix) {
        if (null != removePrefix && removePrefix.length() > 0) {
            StringBuffer tSb = new StringBuffer(tableName);
            String[] splits = removePrefix.split(",");
            int length = splits.length;
            for (int i = 0; i < length; i++) {
                int tLength = splits[i].length();
                String tTemp = splits[i].toLowerCase();
                if (tSb.substring(0, tLength).toLowerCase().equals(tTemp)) {
                    return ConvertUtil.convertTableName2ObjName(tSb.substring(tLength));
                }
            }
        }
        return ConvertUtil.convertTableName2ObjName(tableName);
    }

    /**
     * <h3>移除尾部字段转成首字母大写驼峰写法</h3>
     * <h4>方法调用：removeTailField("owner_id","_id")</h4>
     * @param columnName 行名 如：owner_id 或 table_info_ld
     * @param removeSuffix 移除的后缀 如：_id 或 _ld
     * @return 返回名称 如：Owner 或 TableInfo
     */
    public static String removeTailField(String columnName, String removeSuffix) {

        if (null != removeSuffix && removeSuffix.length() > 0) {
            int _position = columnName.lastIndexOf(removeSuffix);
            columnName = columnName.substring(0, _position);
        }
        return ConvertUtil.convertTableName2ObjName(columnName);
    }


    /**
     * <h3>获取字符</h3>
     * @param sb
     * @return
     */
    private static StringBuffer getStringBuffer(StringBuffer sb) {
        int size = sb.length() -1 ;
        int lastIndex = sb.lastIndexOf("_");
        if (size == lastIndex) {
            sb = sb.deleteCharAt(lastIndex);
        }
        size = sb.lastIndexOf("_");

        for (int i = 0; i < size; i++) {
            int location = sb.indexOf("_");
            if (location > 0) {
                String temp = sb.substring(location + 1, location + 2);
                sb.delete(location, location + 2);
                sb.insert(location, temp.toUpperCase());
                i = location;
            }
        }
        return sb;
    }
}

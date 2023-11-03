package cn.peyton.children.mgts.mybatis.template;


import cn.peyton.children.mgts.mybatis.entity.Column;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.file.FileUtil;
import cn.peyton.children.mgts.mybatis.utils.ConvertUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <h3>基础 模板</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @email <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date: 2018年8月10日 9:28
 * @version: 1.0.0
 * </pre>
 */
public abstract class BaseTemplate {
    /** 前缀 get */
    protected static final String PREFIX_GET = "get";
    /** 前缀 set */
    protected static final String PREFIX_SET = "set";
    /** 路径 */
    protected static String _path;
    /** 申明 可变换的字符串 */
    protected static StringBuffer sb ;

    protected static Table _table = new Table();


    /**
     * <h3>创建类部注释</h>
     * @param name 名称
     */
    protected static void createAnnotation(String name) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        sb.append("/**\r\n");
        sb.append(" * <h3> " + name + "</h3>\r\n");
        sb.append(" * <pre>\r\n");
        sb.append(" * @author <a href=\"http://www.peyton.cn\">peyton</a>\r\n");
        sb.append(" * @mail <a href=\"mailto:fz2919@tom.com\">fz2919@tom.com</a>\r\n");
        sb.append(" * @date " + dateFormat.format(new Date()) + "\r\n");
        sb.append(" * @version 1.0.0\r\n");
        sb.append(" * </pre>\r\n");
        sb.append("*/\r\n");
    }

    /**
     * <h4>创建文件</h4>
     * @param packageName 包名
     * @param fileName 文件名
     * @param ext 扩展名
     */
    protected static void createFileContent(String packageName, String fileName, String ext) {
        String tPath = packageName.replace(".", "/");
        tPath = FileUtil.changePath(_path) + tPath;
        tPath = FileUtil.changePath(tPath);
        FileUtil.createDirectory(tPath);
        FileUtil.createFile(tPath, fileName, ext, sb.toString());
    }

    /**
     * <h4>判断当前路径</h4>
     * @param path 路径名称
     * @return 绝对路径
     */
    protected static String existPath(String path) {
        if(null == path || "".equals(path) || ".".equals(path)) {
            String  tPath = new File(BaseTemplate.class.getResource("/").getPath()).getAbsolutePath();
            String eq = "target\\";
            int location = tPath.lastIndexOf(eq);
            tPath = tPath.substring(0,location);
            String fixedPath = "src\\main\\java\\";
            return tPath + fixedPath;
        }
        return  path;
    }



    /**
     * <h4>标题注解</h4>
     * @param columnList 字段行的集合
     * @param sb 储存的字符
     * @param size 集合大小
     */
    protected static void createAnnotationTitle(List<Column> columnList, StringBuffer sb, int size) {
        sb.append("\t * <pre>\r\n");
        sb.append("\t * \t 转换字段如下:\r\n");
        sb.append("\t * \t [");
        for (int i = 0; i < size; i++) {
            sb.append(columnList.get(i).getFieldName());
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]\r\n");
        sb.append("\t * </pre>\r\n");
        sb.append("\t */\r\n");
    }

    /**
     *
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param prefixParam 参数对象 要去除前缀的属性
     * @param columnList 对象集合
     * @param sb 拼装字符串
     * @param size 集合大小
     * @param _ron 对象名
     * @param exist true 为 convent ; false 为compat
     */
    protected static void createContent(String prefixResult, String prefixParam, List<Column> columnList,
                                      StringBuffer sb, int size, String _ron,boolean exist) {
        for (int i = 0; i < size; i++) {
            Column column = columnList.get(i);
            String fieldName = "";
            String fieldResult = "";
            String fieldParam = "";
            if (column.getFk()) {
                fieldName = column.getFkExclusiveObjectName();
            } else {
                fieldName = column.getFieldName();
            }

            if (null != prefixResult){
                int _rlen = fieldName.indexOf(prefixResult);
                if (_rlen > -1) {
                    fieldResult = fieldName.substring(_rlen + prefixResult.length());
                    fieldResult = ConvertUtil.toFirstLowerCase(fieldResult);
                }else {
                    fieldResult = fieldName;
                }
                int _plen = (null != prefixParam) ? fieldName.indexOf(prefixParam) : -2;
                if (_plen > -1) {
                    fieldParam = fieldName.substring(_plen + prefixParam.length());
                    fieldParam = ConvertUtil.toFirstLowerCase(fieldParam);
                }else {
                    fieldParam = fieldName;
                }
            }else {
                fieldResult = fieldName;
                fieldParam = fieldName;
            }

            sb.append("\t\t");
            String upperCaseField = ConvertUtil.convertFirst(fieldResult);
            if (exist) {
                sb.append(_ron + ".set" + upperCaseField + "(" + fieldParam + ");\r\n");
            }else {
                sb.append("this.set" + upperCaseField + "(");
                sb.append(_ron + ".get" + ConvertUtil.convertFirst(fieldParam) + "()");
                sb.append(");\r\n");
            }
        }
    }
}

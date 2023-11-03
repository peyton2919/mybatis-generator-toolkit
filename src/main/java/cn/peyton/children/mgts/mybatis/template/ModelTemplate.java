package cn.peyton.children.mgts.mybatis.template;

import cn.peyton.children.mgts.mybatis.entity.Column;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.utils.ConvertUtil;

import java.util.List;

/**
 * <h3>Model 模板</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public class ModelTemplate extends BaseTemplate {

    /**
     *  <h3>创建实体类</h3>
     * @param table 表对象
     * @param objName 对象名称
     * @param suffixName 后缀名称  为空时，默认添加 Param
     * @param prefix 去除字段前缀
     * @param path  绝对 路径
     * @param packageName 包的地址
     * @return
     */
    public static String create(Table table , String objName,String suffixName, String prefix, String path, String packageName) {

        return createMulti(table, objName, suffixName , prefix, null, path, packageName,true);
    }

    /**
     * <h3>创建实体类</h3>
     * @param table 表对象
     * @param objName 对象名称
     * @param suffixName 后缀名称 为空时，默认添加 Param
     * @param prefix 去除字段前缀
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param path 绝对 路径
     * @param packageName 包的地址
     * @param isSingle 是否单个，这个设置false
     * @return
     */
    public static String createMulti(Table table , String objName,String suffixName, String prefix,
                                     String  prefixResult, String path, String packageName,Boolean isSingle) {
        _table = table;
        _path = existPath(path);
        sb = new StringBuffer();
        if (null != packageName) {
            sb.append("package " + packageName + ";\r\n\r\n");
        }
        //
        sb.append(table.getImportPackage());
        sb.append("\r\nimport java.io.Serializable;\r\n");
        String explain = (suffixName == null) ? " 实体类": " 参数 传递类[用来展示数据]类";
        //调用父类方法创建头部
        createAnnotation(table.getComment() + explain);
        //创建类
        if (isSingle) {
            createModelClass(objName, prefix);
        } else {
            createModelClass(objName,prefix,prefixResult);
        }

        //调用父类方法保存文件
        createFileContent(packageName, ((objName == null) ? table.getObjectName():objName), "java");
        return sb.toString();
    }


    // ================================================ create Model method begin ================================================ //


    /**
     * <h3>创建对象</h3>
     */
    private static void createModelClass(String objName,String prefix) {
        sb.append("public class " + ((objName == null) ? _table.getObjectName():objName) + " implements Serializable {\r\n");
        createModelContent(prefix);

        sb.append("}\r\n");
    }

    /**
     * <h3>创建对象</h3>
     */
    private static void createModelClass(String objName,String prefix, String  prefixResult) {
        sb.append("public class " + ((objName == null) ? _table.getObjectName():objName) + " implements Serializable {\r\n");
        createModelContent(prefix);
        sb.append(ConvertTemplate.createConvert(_table, _table.getObjectName(), prefixResult, prefix));
        sb.append(CompatTemplate.createCompat(_table, objName, prefixResult, _table.getObjectName(), prefix));
        sb.append("}\r\n");
    }

    /**
     * <h3>创建内容</h3>
     * @return
     */
    private static void createModelContent(String prefix) {

        int size = _table.getColumns().size();
        List<Column> columnList = _table.getColumns();
        for (int i = 0; i < size; i++) {
            sb.append("\t/** " + columnList.get(i).getRemarks() + "  */\r\n");

            String _type = "";
            String _name = "";
            if (columnList.get(i).getFk()) {
                _name = columnList.get(i).getFkExclusiveObjectName();
                _type = columnList.get(i).getFkExclusiveObjectType();
            }else {
                _name = columnList.get(i).getFieldName();
                _type = columnList.get(i).getFieldType();
            }

            if (null != prefix) {
                _name = _name.replace(prefix, "");
                _name = ConvertUtil.toFirstLowerCase(_name);
            }

            //添加私有字段
            sb.append("\tprivate " + _type + " " + _name + ";\r\n");

        }
        sb.append("\r\n");
        sb.append("\t//================================== Constructor =======================================//\r\n");
        sb.append("\r\n");
        sb.append("\t//================================== Method =======================================//\r\n");
        sb.append("\r\n");
        sb.append("\r\n");
        sb.append("\t//================================== PREFIX_GET AND PREFIX_SET =======================================//\r\n");
        sb.append("\r\n");
        for (int i = 0; i < size; i++) {

            String _type = "";
            String _name = "";
            if (columnList.get(i).getFk()) {
                _name = columnList.get(i).getFkExclusiveObjectName();
                _type = columnList.get(i).getFkExclusiveObjectType();
            }else {
                _name = columnList.get(i).getFieldName();
                _type = columnList.get(i).getFieldType();
            }

            if (null != prefix) {
                _name = _name.replace(prefix, "");
                _name = ConvertUtil.toFirstLowerCase(_name);
            }

            String _remarks =columnList.get(i).getRemarks();

            sb.append("\t/** \r\n");
            sb.append("\t * @param " + _name + " " + _remarks + " \r\n");
            sb.append("\t */ \r\n");
            sb.append("\tpublic void " + PREFIX_SET + ConvertUtil.convertFirst(_name) + "(" +
                    _type + " " + _name + "){\r\n\t\tthis."+  _name + " = " + _name + ";\r\n\t}\r\n\r\n");

            sb.append("\t/** \r\n");
            sb.append("\t * @return " + _remarks + " \r\n");
            sb.append("\t */ \r\n");
            sb.append("\tpublic " + _type + " " + PREFIX_GET + ConvertUtil.convertFirst(_name) + "(){\r\n" +
                    "\t\treturn "+  _name + ";\r\n\t}\r\n\r\n");
        }
    }
    // ================================================ create Model method end ================================================ //
}

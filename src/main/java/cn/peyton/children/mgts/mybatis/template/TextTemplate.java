package cn.peyton.children.mgts.mybatis.template;

import cn.peyton.children.mgts.mybatis.db.DbHelper;
import cn.peyton.children.mgts.mybatis.entity.Column;
import cn.peyton.children.mgts.mybatis.entity.Table;

import java.util.List;

/**
 * <h3>文本模板 类</h3>
 * <pre>
 *     创建各种文本
 * </pre>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
@SuppressWarnings("ALL")
public class TextTemplate extends BaseTemplate{

    /**
     * <h4>创建单个</h4>
     * @param tableName         表名
     * @param removePrefix      去除前缀
     * @param path              绝对路径
     * @param mapperPackageName mapper包路径
     * @param entityPackageName entity包路径
     */
    public static void create(String tableName, String removePrefix, String path,
                              String mapperPackageName, String entityPackageName, boolean isAll) {

        Table table = DbHelper.getTables(tableName,removePrefix).get(0);

        single(table , path, mapperPackageName, entityPackageName, isAll);
    }



    /**
     * <h4>创建多个[当前 库下所有表]</h4>
     * @param removePrefix      移除对象名前缀(sys_,tb_)
     * @param path              绝对路径
     * @param mapperPackageName mapper包路径
     * @param entityPackageName entity包路径
     */
    public static void create( String removePrefix, String path,
                              String mapperPackageName, String entityPackageName, boolean isAll) {
        List<Table> _tables = DbHelper.getTables(null ,removePrefix);
        //设置对象名
        int _size = _tables.size();
        for (int i = 0; i < _size; i++) {
            Table _table = _tables.get(i);
            single(_table , path, mapperPackageName, entityPackageName, isAll);
        }
    }

    /**
     * <h4>创建单个 参数 传递类</h4>
     * @param tableName 表名
     * @param objName 对象名称
     * @param prefix 去除字段前缀
     * @param path  绝对 路径
     * @param packageName 包的地址
     */
    public static void createParam(String tableName ,String objName,String prefix,
                                   String path,String packageName) {
        Table table = DbHelper.getTables(tableName, null).get(0);
        table.setObjectName(objName);
        ModelTemplate.create(table,objName,null, prefix, path, packageName);

    }

    /**
     * <h3>创建多个 参数 传递类</h3>
     * @param suffixName 后缀名称  为空时，默认添加 Param
     * @param removeTablePrefix 去除表前缀
     * @param prefix  去除字段前缀
     * @param path  绝对 路径
     * @param packageName 包的地址
     */
    public static void createParams(String suffixName,String removeTabelPrefix,String prefix,
                                   String path,String packageName) {
        if (null == suffixName || "".equals(suffixName)) {
            suffixName = "Param";
        }
        List<Table> tables = DbHelper.getTables(null,removeTabelPrefix);
        int size = tables.size();
        for (int i=0; i < size; i++){
            Table table = tables.get(i);
            //设置对象名
            ModelTemplate.create(table,(table.getObjectName() + suffixName) ,suffixName, prefix , path , packageName);
        }
    }

    /**
     *  <h4>创建多个 参数 传递类</h4>
     * @param suffixName 后缀名称 为空时，默认添加 Param
     * @param removeTablePrefix 去除表前缀
     * @param prefix 去除字段前缀
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param path 绝对 路径
     * @param packageName 包的地址
     */
    public static void createParamCompatConvert(String suffixName,String removeTablePrefix,String prefix,
                                                String  prefixResult,String path, String packageName){
        if (null == suffixName || "".equals(suffixName)) {
            suffixName = "Param";
        }
        List<Table> _tables = DbHelper.getTables(null,removeTablePrefix);
        int _size = _tables.size();
        for (int i=0; i < _size; i++){
            Table _table = _tables.get(i);
            //设置对象名
            // _table.setObjectName(removePrefixTableName2ObjName(table.getTableName(), prefixResult));

            ModelTemplate.createMulti(_table, (_table.getObjectName() + suffixName),suffixName,
                        prefix,prefixResult , path, packageName, false);
        }
    }


    /**
     *
     * @param table 表名
     * @param path 输出路径
     * @param mapperPackageName mapper包名路径
     * @param entityPackageName entity包名路径
     * @param isAll true 输出service 与 controller
     */
    private static void single(Table table,String path, String mapperPackageName, String entityPackageName, boolean isAll) {
        //创建 实体类
        ModelTemplate.create(table,null,null,null, path, entityPackageName);
        //创建 Mapper类
        MapperTemplate.create(table,path,mapperPackageName,entityPackageName);

        //isAll 为true 时创建 Service 与 Controller
        if (isAll) {
            int tLocation = mapperPackageName.lastIndexOf(".");
            StringBuffer tSb = new StringBuffer(mapperPackageName);
            tSb.delete(tLocation + 1, mapperPackageName.length());
            String importPackagePrefix = tSb.toString();
            //创建 service 层
            ServiceTemplate.create(table.getObjectName(),table.getComment(),path,mapperPackageName,
                    importPackagePrefix + "service");
            //创建 Controller 层
            ControllerTemplate.create(table.getObjectName(),table.getComment(),path,importPackagePrefix,
                    importPackagePrefix + "controller");
        }
        StringBuffer stringBuffer = new StringBuffer(table.getObjectName() + " 生成相应 [modle,mapper,mapperxml");
        if (isAll) {
            stringBuffer.append("service,contrlloer");
        }
        stringBuffer.append("] 生成成功!");
        System.out.println(stringBuffer);
    }


    // ##################################>>>>>>>>>>  外部直接调用方法 <<<<<<<<<<################################## //

    /**
     * <h4>获取表</h4>
     * @param tableName 表名
     * @param removePrefix 去除前缀
     * @return
     */
    public static Table getTable(String tableName,String removePrefix) {
        return DbHelper.getTables(tableName,removePrefix).get(0);
    }

    /**
     * <h3>获取数据库下的所有表名</h3>
     * @return
     */
    public static List<String> getTableNames() {
        return DbHelper.getTableNames();
    }

    /**
     * <h4>创建构造函数</h4>
     * @return
     */
    public static String createConstructor(String tableName, String objName) {
        Table table = DbHelper.getTables(tableName,null).get(0);
        List<Column> columnList = table.getColumns();
        StringBuffer sb = new StringBuffer();
        int size = columnList.size();
        sb = new StringBuffer();

        sb.append("\t/**\r\n");
        sb.append("\t * <h4>构造函数</h4>\r\n");

        sb.append("\t */\r\n");
        sb.append("\tpublic " + objName + "(){}\r\n\r\n");
        sb.append("\t/**\r\n");
        sb.append("\t * <h4>构造函数</h4>\r\n");
        for (int i = 0; i < size; i++) {
            Column column = columnList.get(i);
            sb.append("\t * @param " + column.getFieldName() + " " + column.getRemarks() + "\r\n");
        }
        sb.append("\t */\r\n");
        sb.append("\tpublic " + objName + "(");
        for (int i = 0; i < size; i++) {
            Column column = columnList.get(i);
            sb.append(column.getFieldType() + " " + column.getFieldName());
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("){\r\n");
        for (int i = 0; i < size; i++) {
            Column column = columnList.get(i);
            sb.append("\t\tthis." + column.getFieldName() + " = " + column.getFieldName() + ";\r\n");
        }
        sb.append("\t}\r\n");
        return sb.toString();
    }



    // ##################################>>>>>>>>>>  外部直接调用方法 <<<<<<<<<<################################## //




}

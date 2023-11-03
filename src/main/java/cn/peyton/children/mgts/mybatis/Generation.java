package cn.peyton.children.mgts.mybatis;

import cn.peyton.children.mgts.mybatis.db.DbHelper;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.template.CompatTemplate;
import cn.peyton.children.mgts.mybatis.template.ConvertTemplate;
import cn.peyton.children.mgts.mybatis.template.TextTemplate;
import cn.peyton.children.mgts.mybatis.utils.DatabaseUtil;

import java.util.List;

/**
 * <h3>生成工具类 .</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public final class Generation {

    /**
     * <h4> 构造函数</h4>
     * <pre>
     *     必需初始化DbHelper
     * </pre>
     * @param driver 驱动名称
     * @param url 地址
     * @param username 用户名
     * @param password 密码
     */
    public Generation(String driver, String url, String username, String password) {
        DbHelper.setDRIVER(driver);
        DbHelper.setURL(url);
        DbHelper.setUSRENAME(username);
        DbHelper.setPASSWORD(password);
    }

    /**
     * <h4> 构造函数</h4>
     * @param util 操作数据库对象
     */
    public Generation(DatabaseUtil util){
        DbHelper.setDRIVER(util.getDriver());
        DbHelper.setURL(util.getUrl());
        DbHelper.setUSRENAME(util.getUsername());
        DbHelper.setPASSWORD(util.getPassword());
    }

    /**
     * <h4>创建单个 对象</h4>
     *
     * @param tableName         表名
     * @param removePrefix      去除前缀
     * @param path              绝对 路径
     * @param mapperPackageName 数据层类包的地址
     * @param entityPackageName 实体类包的地址
     * @param isAll             是否创建全部[包service与controller包,包的前缀与Mapper包的前缀相同]
     */
    public void create(String tableName, String removePrefix, String path,
                       String mapperPackageName, String entityPackageName, boolean isAll) {

        TextTemplate.create(tableName, removePrefix, path, mapperPackageName, entityPackageName,isAll);

    }

    /**
     * <h4>创建单个 参数 传递类</h4>
     * @param tableName 表名
     * @param objName 对象名称
     * @param prefix 去除字段前缀
     * @param path  绝对 路径
     * @param packageName 包的地址
     */
    public void createParam(String tableName,String objName,
                            String prefix, String path, String packageName) {

        TextTemplate.createParam(tableName,objName,prefix, path,packageName);
    }

    /**
     *  <h4>创建多个 参数 传递类</h4>
     * @param suffixName 后缀名称 为空时，默认添加 Param
     * @param removeTablePrefix 去除表前缀
     * @param prefix 去除字段前缀
     * @param path 绝对 路径
     * @param packageName 包的地址
     */
    public void createParams(String suffixName,String removeTablePrefix,String prefix, String path, String packageName){
        TextTemplate.createParams(suffixName,removeTablePrefix,prefix,path,packageName);
    }

    /**
     * <h4><h4>创建多个 对象</h4></h4>
     * @param removePrefix      移除对象名前缀(sys_,tb_)
     * @param path              绝对 路径
     * @param mapperPackageName 数据层类包的地址
     * @param entityPackageName 实体类包的地址
     * @param isAll             是否创建全部[包service与controller包,包的前缀与Mapper包的前缀相同]
     */
    public void create(String removePrefix, String path,
                       String mapperPackageName, String entityPackageName, boolean isAll) {
        TextTemplate.create(removePrefix, path, mapperPackageName, entityPackageName,isAll);
    }

    /**
     * <h4>获取当前 库下所有表名</h4>
     *
     * @return
     */
    public List<String> getTableNames() {
        return DbHelper.getTableNames();
    }

    /**
     * <h4>获取表</h4>
     * @param tableName 表名
     * @param removePrefix 去除前缀
     * @return
     */
    public Table getTable(String tableName, String removePrefix) {
        return TextTemplate.getTable(tableName,removePrefix);
    }

    /**
     * <h4>创建一个无参构造函数 和 全参构造函数</h4>
     * @param tableName 表名
     * @param objName   对象名
     * @return
     */
    public String createConstructor(String tableName, String objName) {
        return TextTemplate.createConstructor(tableName, objName);
    }

    /**
     * <h4>传参对象转换对象</h4>
     * @param tableName        表名
     * @param resultObjectName 转换后对象名
     * @return
     */
    public String createConvert(String tableName, String resultObjectName){
        return ConvertTemplate.createConvert(tableName, resultObjectName);
    }

    /**
     * <h4>传参对象转换对象</h4>
     * @param tableName        表名
     * @param resultObjectName 转换后对象名
     * @param prefixResult 返回对象 要去除前缀的属性
     * @param prefixParam 参数对象 要去除前缀的属性
     * @return
     */
    public String createConvert(String tableName, String resultObjectName,
                                       String  prefixResult,String prefixParam){
        return ConvertTemplate.createConvert(tableName, resultObjectName, prefixResult, prefixParam);
    }

    /**
     * <h4>对象转换传参对象</h4>
     * @param tableName        表名
     * @param resultObjectName 返回对象名
     * @param paramObjectName 参数对象名
     * @return
     */
    public String createCompat(String tableName, String resultObjectName,String paramObjectName) {
        return CompatTemplate.createCompat(tableName, resultObjectName, paramObjectName);
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
    public String createCompat(String tableName, String resultObjectName,String  prefixResult,
                                      String paramObjectName,String prefixParam) {
        return CompatTemplate.createCompat(tableName, resultObjectName, prefixResult, paramObjectName, prefixParam);
    }

    /**
     *  <h4>创建多个 参数 传递类</h4>
     * @param suffixName 后缀名称 为空时，默认添加 Param
     * @param removeTablePrefix 去除表前缀
     * @param prefix 去除字段前缀
     * @param path 绝对 路径
     * @param packageName 包的地址
     */
    public void createParamCompatConvert(String suffixName,String removeTablePrefix,String prefix,
                                          String path, String packageName){

        TextTemplate.createParamCompatConvert(suffixName,removeTablePrefix,prefix,removeTablePrefix,path,packageName);
    }

    // ================================== 接口 =========================================== //
    /**
     * <h3>连接数据字段</h3>
     * <pre>
     * Author: <a href="http://www.peyton.cn">peyton</a>
     * MailListener: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * CreateDate: 2018/8/9 13:27
     * Version: 1.0.0
     * </pre>
     */
    public interface Join{
        String DRIVER = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/db_lemon" +
                "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
        String USRENAME = "root";
    }

}

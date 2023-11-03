package cn.peyton.children.mgts;


import cn.peyton.children.mgts.mybatis.Generation;
import cn.peyton.children.mgts.mybatis.utils.DatabaseUtil;

/**
 * <h3></h3>
 * <pre>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2021/11/8 23:49
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @version 1.0.0
 * </pre>
 */
public final class ExecutePlumMall {

    public static String POJO = "cn.peyton.plum.mall.pojo";
    public static String MAPPER = "cn.peyton.plum.mall.mapper";
    public static String PARAM = "cn.peyton.plum.mall.param";
    public static String PATH = "f:/mall/";

   public static void main(String[] args){
       DatabaseUtil databaseUtil =new DatabaseUtil("db_mall");
       Generation generation = new Generation(databaseUtil);
       //generation.create("adsense",null,PATH,MAPPER,POJO,false);

       generation.create("sys_,tb_",PATH,MAPPER,POJO,true);
       generation.createParamCompatConvert(null,"sys_,tb_",null,PATH,PARAM);

       //generation.createParams(null,null,null,PATH,PARAM);

   }
}

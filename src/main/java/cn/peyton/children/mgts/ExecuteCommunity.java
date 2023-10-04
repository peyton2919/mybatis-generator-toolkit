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
public final class ExecuteCommunity {

    public static String POJO = "cn.peyton.children.chatter.pojo";
    public static String MAPPER = "cn.peyton.children.chatter.mapper";
    public static String PARAM = "cn.peyton.children.chatter.param";
    public static String PATH = "f:/community/";

   public static void main(String[] args){
       DatabaseUtil databaseUtil =new DatabaseUtil("db_chatter");
       Generation generation = new Generation(databaseUtil);
       //generation.create("adsense",null,PATH,MAPPER,POJO,false);

       generation.create("",PATH,MAPPER,POJO,true);
       generation.createParamCompatConvert(null,null,null,PATH,PARAM);

       //generation.createParams(null,null,null,PATH,PARAM);

   }
}

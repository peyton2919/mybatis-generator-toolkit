package cn.peyton.children.mgts;

import cn.peyton.children.mgts.mybatis.Generation;
import cn.peyton.children.mgts.mybatis.utils.DatabaseUtil;

/**
 * <h3></h3>
 * <pre>
 * @mail: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2022-04-27 21:56
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @version 1.0.0
 * </pre>
 */
public class ExecuteMall1 {

    public static String POJO = "cn.peyton.plum.mall.pojo";
    public static String MAPPER = "cn.peyton.plum.mall.mapper";
    public static String VO = "cn.peyton.plum.mall.vo";
    public static String PATH = "f:/temp/mall3/";

    public static void main(String[] args) {
        DatabaseUtil util = new DatabaseUtil("db_t1");
        Generation generation = new Generation(util);

        generation.create("tbma_",PATH,MAPPER,POJO,true);
        generation.createParamCompatConvert("Vo","tbma_",null,PATH, VO);

    }
}

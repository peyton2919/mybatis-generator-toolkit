package cn.peyton.children.mgts;

import cn.peyton.children.mgts.mybatis.Generation;
import cn.peyton.children.mgts.mybatis.utils.DatabaseUtil;

/**
 * <h3></h3>
 * <pre>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2022-04-10 9:21
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @version 1.0.0
 * </pre>
 */
public class ExecuteMall {

    public static String POJO = "cn.peyton.plum.mall.pojo";
    public static String MAPPER = "cn.peyton.plum.mall.mapper";
    public static String PARAM = "cn.peyton.plum.mall.param";
    public static String PATH = "f:/temp/mall2/";

    public static void main(String[] args) {
        DatabaseUtil util = new DatabaseUtil("db_mall-1");
        Generation generation = new Generation(util);
        // 全部数据
        // generation.create(null,PATH,MAPPER,POJO,true);

        // 单个数据

        generation.create("goods_comment",null,PATH,MAPPER,POJO,true);
        // generation.create("order_item",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_card_value",null,PATH,MAPPER,POJO,true);
        // generation.create("sys_log",null,PATH,MAPPER,POJO,true);
        // generation.create("sys_supplier_log",null,PATH,MAPPER,POJO,true);
        // generation.create("member_address",null,PATH,MAPPER,POJO,true);
        // generation.create("order_item",null,PATH,MAPPER,POJO,true);
        // generation.create("member",null,PATH,MAPPER,POJO,true);
        // generation.create("member_info",null,PATH,MAPPER,POJO,true);
        // generation.create("order_item",null,PATH,MAPPER,POJO,true);
        // generation.create("cart",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_comment",null,PATH,MAPPER,POJO,true);
        // generation.create("comment_image",null,PATH,MAPPER,POJO,true);
        // generation.create("sku_goods_type",null,PATH,MAPPER,POJO,true);

        // generation.create("collect",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_attr",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_sku",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_sku_card",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_sku_card_value",null,PATH,MAPPER,POJO,true);
        // generation.create("member_address",null,PATH,MAPPER,POJO,true);
        // generation.create("goods_banner",null,PATH, MAPPER,POJO,false);

        // Table goods_banner = generation.getTable("goods_banner", null);
        // System.out.println(goods_banner);

    }
}

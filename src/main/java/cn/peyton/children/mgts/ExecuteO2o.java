package cn.peyton.children.mgts;

import cn.peyton.children.mgts.mybatis.Generation;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.utils.DatabaseUtil;

/**
 * <h3></h3>
 * <pre>
 * @email <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2021/11/8 23:49
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @version 1.0.0
 * </pre>
 */
public final class ExecuteO2o {

    public static String POJO = "cn.peyton.plum.o2o.pojo";
    public static String MAPPER = "cn.peyton.plum.o2o.mapper";
    public static String PARAM = "cn.peyton.plum.o2o.param";
    public static String PATH = "f:/o2o/";

   public static void main(String[] args){
       DatabaseUtil databaseUtil = new DatabaseUtil("db_o2o");
       Generation generation = new Generation(databaseUtil);
       //
       // generation.create("tb_",PATH,MAPPER,POJO,true);
       // generation.createParamCompatConvert(null,"tb_","","tb_,sys_",PATH,PARAM);
       //
       //
       // generation.create("tb_shop","tb_",PATH,MAPPER,POJO,true);

       Table cart = generation.getTable("cart", null);
       System.out.println(cart);

       // List<Table> tables = DbHelper.getTables(null,"tb_");

       // Person person = new Person();
       // Class<?> clazz = person.getClass();
       // Field[] fields = clazz.getDeclaredFields();
       // Method[] methods = clazz.getDeclaredMethods();
//       clazz.getAnnotations(); //获取所有的注解，包括自己声明的以及继承的
//       clazz.getAnnotation(Class annotationClass); //获取指定的注解，该注解可以是自己声明的，也可以是继承的
//       clazz.getDeclaredAnnotations(); //获取自己声明的注解
//        Annotation[] _as = clazz.getDeclaredAnnotations();
//        for (Annotation a : _as) {
//            //获取注解名
//            String _name = a.annotationType().getSimpleName();
//            System.out.println(_name);
//        }
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String name = field.getName();
//            String type = field.getGenericType().toString();
//
//
//            Annotation[] annotations = field.getDeclaredAnnotations();
//
//            //判断 注解类型
//            for (Annotation annotation : annotations) {
//                //获取注解名
//                String _name = annotation.annotationType().getSimpleName();
//                System.out.println(_name);
//            }
//        }

   }

}

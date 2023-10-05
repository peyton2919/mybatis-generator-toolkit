package cn.peyton.children.mgts.sql;

import java.util.List;

/**
 * <h3>创建简单数据格式</h3>
 *
 *
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public class GenerateDataUtil {

	/**
	 * <h4>创建Mapper层的简单十个方法</h4>
	 * <pre>
	 * 1、 	void save(T t);
	 * 2、	void update(T t);
	 * 3、 	void delete(K k);
	 * 4、 	T findById(K k);
	 * 5、 	List<T> findByLikeName(String name);
	 * 6、	List<Color> findBySelect();
	 * 7、	List<T> findByAll();
	 * 8、	int isRename(String name);
	 * 9、	int count();
	 * 10、	int countByLikeName(String name);
	 * </pre>
	 * @param objName 对象名(首字母小写)
	 * @param tbName 表名
	 * @param fields 属性名{xxx,xxx,...}
	 * @param columns 表字段名{xx_xxx,xx_xxx,...}
	 * @param keyField 属性主键名
	 * @param keyColumn 表主键名
	 * @param lnField 模糊查找属性名称{这个属性与主键合成下拉框查找}
	 * @param lnColumn 模糊查找表字段名称{这个字段与主键合成下拉框查找}
	 */
	public static void generateMapper(String objName,String tbName,String fields,String columns,
			String keyField,String keyColumn,String lnField,String lnColumn,String statusName,int statusValue) {
		
		//1
		String saveSql = CreateSQLUtil.insertSQL(tbName, columns, fields);
		System.out.println("@Insert(\""+ saveSql +"\")");
		System.out.print("@Options(useGeneratedKeys=true,keyProperty=\""+keyField+"\",keyColumn=\""+keyColumn+"\",");
		System.out.println("flushCache=FlushCachePolicy.TRUE,timeout = TIMEOUT)");
		System.out.println("void save("+changeIndex(objName)+" " +objName + ");\r\n\r\n");

		//2
		String updateSql = CreateSQLUtil.updateSQL(tbName, columns, fields, keyColumn, keyField,statusName,statusValue);
		System.out.println("@Update(\"" + updateSql + "\")");
		System.out.println("@Options(flushCache=FlushCachePolicy.TRUE,timeout = TIMEOUT)");
		System.out.println("void update(" + changeIndex(objName) + " " + objName + ");\r\n\r\n");

		//3
		String deleteSql = CreateSQLUtil.deleteSQL(tbName, keyColumn, keyField);
		System.out.println("@Delete(\"" + deleteSql + "\")");
		System.out.println("@Options(flushCache=FlushCachePolicy.TRUE,timeout = TIMEOUT)");
		System.out.println("void delete(@Param(\"" + keyField + "\")Long " + keyField + ");\r\n\r\n");

		//4
		String fbiSql = CreateSQLUtil.selectSQL(tbName,  keyColumn + "," +columns, keyColumn, keyField,statusName,statusValue);
		System.out.println("@Select(\"" + fbiSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		//
		System.out.println("@Results({");
		System.out.println("@ResultAdapter(column=\"" + keyColumn + "\",property=\"" + keyField + "\"),");
		String[] fbiCols = columns.split(",");
		String[] fbiFies = fields.split(",");
		for (int i = 0; i < fbiFies.length; i++) {
			System.out.print("@ResultAdapter(column=\"" + fbiCols[i] + "\",property=\"" + fbiFies[i] + "\")");
			if (i != fbiFies.length -1) {
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.println("})");
		//
		System.out.println(changeIndex(objName) + " findById(@Param(\"" + keyField + "\")Long " + keyField + ");\r\n\r\n");

		//5
		String likeNameSql = CreateSQLUtil.selectSQLLikeName(tbName, keyColumn + "," + columns, lnColumn, lnField,statusName,statusValue);
		System.out.println("@Select(\"" + likeNameSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		System.out.println("@Results({");
		System.out.println("@ResultAdapter(column=\"" + keyColumn + "\",property=\"" + keyField + "\"),");
		String[] likeNameCols = columns.split(",");
		String[] likeNameFies = fields.split(",");
		for (int i = 0; i < fbiFies.length; i++) {
			System.out.print("@ResultAdapter(column=\"" + likeNameCols[i] + "\",property=\"" + likeNameFies[i] + "\")");
			if (i != fbiFies.length -1) {
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.println("})");
		System.out.println("List<"+changeIndex(objName)+"> findByLikeName(@Param(\""+lnField+"\")String "+lnField+");\r\n\r\n");

		//6
		String fbsSql = CreateSQLUtil.selectSQLLikeName(tbName, keyColumn+","+lnColumn, null, null,statusName,statusValue);
		System.out.println("@Select(\"" + fbsSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		System.out.println("@Results({");
		System.out.println("@ResultAdapter(column=\"" + keyColumn + "\",property=\"" + keyField + "\"),");
		System.out.println("@ResultAdapter(column=\"" + lnColumn + "\",property=\"" + lnField + "\")");
		System.out.println("})");
		System.out.println("List<"+changeIndex(objName)+"> findBySelect();\r\n\r\n");

		//7
		String fbaSql = CreateSQLUtil.selectSQL(tbName,  keyColumn + "," +columns, null, null,statusName,statusValue);
		System.out.println("@Select(\"" + fbaSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		//
		System.out.println("@Results({");
		System.out.println("@ResultAdapter(column=\"" + keyColumn + "\",property=\"" + keyField + "\"),");
		String[] fbaCols = columns.split(",");
		String[] fbaFies = fields.split(",");
		for (int i = 0; i < fbiFies.length; i++) {
			System.out.print("@ResultAdapter(column=\"" + fbaCols[i] + "\",property=\"" + fbaFies[i] + "\")");
			if (i != fbiFies.length -1) {
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.println("})");
		//
		System.out.println("List<"+changeIndex(objName)+"> findByAll();\r\n\r\n");

		//8
		String isRenameSql = CreateSQLUtil.selectSQLIsRename(tbName, keyColumn, lnColumn, lnField,statusName,statusValue);
		System.out.println("@Select(\"" + isRenameSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		System.out.println("int isRename(@Param(\"" + lnField + "\") String " + lnField + ");\r\n\r\n");

		//9
		String countSql = CreateSQLUtil.selectSQLIsRename(tbName, keyColumn, null, null,statusName,statusValue);
		System.out.println("@Select(\"" + countSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		System.out.println("int count();\r\n\r\n");
		
		//10
		String countLNSql = CreateSQLUtil.selectSQLLikeName(tbName, "count("+keyColumn + ") ", lnColumn, lnField,statusName,statusValue);
		System.out.println("@Select(\"" + countLNSql + "\")");
		System.out.println("@Options(useCache=true , flushCache=FlushCachePolicy.FALSE,timeout = TIMEOUT)");
		System.out.println("int countByLikeName(@Param(\"" + lnField + "\")String " + lnField + ");\r\n\r\n");
	}
	
	
	/**
	 * <h4>创建简单Service层的方法</h4>
	 * <pre>
	 * 1、 	void save(T t);
	 * 2、	void update(T t);
	 * 3、 	void delete(K k);
	 * 4、 	T findById(K k);
	 * 5、 	List<T> findByLikeName(String name);
	 * 6、	List<Color> findBySelect();
	 * 7、	List<T> findByAll();
	 * 8、	int isRename(String name);
	 * 9、	int count();
	 * 10、	int countByLikeName(String name);
	 * </pre>
	 * @param objName 对象名{第一个字母要小写}
	 * @param objMapper 对象Mapper名{第一个字母要小写}
	 */
	public static void generateService(String objName,String objMapper) {
		String tcacheName = toUpperCase(objName)+ "CACHENAME";
		
		System.out.println("@Resource");
		System.out.println("private " + changeIndex(objMapper) + " " + objMapper + ";");
		
		System.out.println("private LocalCache<String, " + changeIndex(objName) + "> localCache;");
		System.out.println("private final static String "+ tcacheName + " = \"" + objName + "_\";\r\n");
		
		System.out.println("@SuppressWarnings(\"unchecked\")");
		System.out.println("public " + changeIndex(objName) + "ServiceImpl() {");
		System.out.println("if (null == localCache) {");
		System.out.println("localCache = LocalCache.getInstance();");
		System.out.println("}");
		System.out.println("}\r\n");
		
		//1
		String tObjName = changeIndex(objName);
		System.out.println("public " + tObjName + " save(" + tObjName + " " + objName + ") {");
		System.out.println(objMapper + ".save(" + objName + ");");
		System.out.println("localCache.put(" + tcacheName + " + " + objName + ".getId(), " + objName + ");");
		System.out.println("return " + objName + ";");
		System.out.println("}\r\n\r\n");
		
		//2
		System.out.println("public " + tObjName + " update(" + tObjName + " " + objName + ") {");
		System.out.println(objMapper + ".update(" + objName + ");");
		System.out.println("localCache.put(" + tcacheName + " + " + objName + ".getId(), " + objName + ");");
		System.out.println("return " + objName + ";");
		System.out.println("}\r\n\r\n");
		
		//3
		System.out.println("public void delete(Long id) {");
		System.out.println(objMapper + ".delete(id);");
		System.out.println("localCache.remove(" + tcacheName + " + id);");
		System.out.println("}\r\n\r\n");

		//4	
		System.out.println("public " + tObjName + " findById(Long id) {");
		System.out.println(changeIndex(objName) + " " + objName + " = localCache.get(" + tcacheName +" + id);");
		System.out.println("if (null == " + objName + ") {");
		System.out.println(objName + " = " + objMapper + ".findById(id);");
		System.out.println("if (null != " + objName + ")localCache.put(" + tcacheName + " + id, " + objName + ");");
		System.out.println("}");
		
		System.out.println("return " + objMapper + ".findById(id);	");
		System.out.println("}\r\n\r\n");
		
		//5
		System.out.println("public List<" + tObjName + "> findByLikeName(String name){");
		System.out.println("return " + objMapper + ".findByLikeName(name);");
		System.out.println("}\r\n\r\n");

		//6
		System.out.println("public List<" + tObjName + "> findBySelect(){");
		System.out.println("return " + objMapper + ".findBySelect();");
		System.out.println("}\r\n\r\n");

		//7
		System.out.println("public List<" + tObjName + "> findByAll(){");
		System.out.println("return " + objMapper + ".findByAll();");
		System.out.println("}");

		//8
		System.out.println("public boolean isRename(String name){");
		System.out.println("return " + objMapper + ".isRename(name) > 0 ;");
		System.out.println("}\r\n\r\n");
		
		//9
		System.out.println("public int count() {");
		System.out.println("return " + objMapper + ".count();");
		System.out.println("}\r\n\r\n");
		
		//10
		System.out.println("public int countByLikeName(String name) {");
		System.out.println("return " + objMapper + ".countByLikeName(name);");
		System.out.println("}\r\n\r\n");
	}
	/**
	 * <h4>创建实体类</h4>
	 * @param parameters 参数集合
	 * @param objName 类名{第一个字母小写}
	 */
	public static void generateEntities(List<Parameter> parameters,String objName) {
		
		for (int i = 0; i < parameters.size(); i++) {
			System.out.println("/**  " + parameters.get(i).getExplain() + "  */");
			System.out.println("private " + parameters.get(i).getType() + " " + parameters.get(i).getName() + ";");
			
		}
		System.out.println();
		
		System.out.println("/** 构造方法 */");
		System.out.println("public " + changeIndex(objName) + "() { }\r\n");
		
		System.out.println("/**");
		System.out.println("* 构造方法 ");
		for (int i = 0; i < parameters.size(); i++) {
			System.out.println("* @param " + parameters.get(i).getName() + " " + parameters.get(i).getExplain());
		}
		System.out.println("*/");
		
		System.out.print("public " + changeIndex(objName) + "(");
		for (int i = 0; i < parameters.size(); i++) {
			System.out.print(parameters.get(i).getType() + " " + parameters.get(i).getName());
			if (i != parameters.size()-1) {
				System.out.print(" , ");
			}
		
		}
		System.out.println("){");
		for (int i = 0; i < parameters.size(); i++) {
			System.out.println("this." + parameters.get(i).getName() + " = " + parameters.get(i).getName() + ";");
		}
		System.out.println("}");
		
		System.out.println();
		for (int i = 0; i < parameters.size(); i++) {
			System.out.println("/**");
			System.out.println(" * @return " + parameters.get(i).getExplain());
			System.out.println(" */");
			System.out.println("public " + parameters.get(i).getType() + " get" + changeIndex(parameters.get(i).getName()) + "() {");
			System.out.println("return this." + parameters.get(i).getName() + ";");
			System.out.println("}");
			System.out.println("/**");
			System.out.println("* @param " + parameters.get(i).getName() + " " + parameters.get(i).getExplain());
			System.out.println("*/");
			System.out.println("public void set" + changeIndex(parameters.get(i).getName()) + "(" + parameters.get(i).getType() + " " + parameters.get(i).getName() + ") {");
			System.out.println("this." + parameters.get(i).getName() + " = " + parameters.get(i).getName() + ";");
			System.out.println("}");
		}
		
		
	}
	
	public static String changeIndex(String field) {
		return field.substring(0, 1).toUpperCase() + field.substring(1);
	}
	
	
	public static String toUpperCase(String name) {
		if (null == name) {
			return null;
		}
		if (name.length() > 6 ) {
			name = name.substring(0, 6).toUpperCase();
			return name;
		}
		return name.toUpperCase();
	}
	
}

package cn.peyton.children.mgts.sql;

import java.lang.reflect.Field;

/**
 * 
 * <h3>创建SQL语句</h3>
 *
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public class CreateSQLUtil {

	//strategy策略   identical相同 character字符
	/** 策略用类反射时用到这个属性为CREATE_SQL_STRATEGY_IDENTICL时表示 数据库字段与类属性相同;  */
	public final static int CREATE_SQL_STRATEGY_IDENTICL = 0;
	/** 策略用类反射时用到这个属性为CREATE_SQL_STRATEGY_CHARACTER_CHANGE时表示 数据库字段与类属性不相同;,<br>
	 * 如果数据库字段从第二个起,遇到有大写的字母则在大写字母前加'_';  */
	public final static int CREATE_SQL_STRATEGY_CHARACTER_CHANGE = 1;
	/**  "SELECT " */
	public final static String  CREATE_SQL_SELECT = "SELECT ";
	/** "INSERT INTO " */
	public final static String  CREATE_SQL_INSERT_INTO = "INSERT INTO ";
	/** "DELETE " */
	public final static String  CREATE_SQL_DELETE = "DELETE ";
	/** "UPDATE " */
	public final static String  CREATE_SQL_UPDATE = "UPDATE ";
	/** "FROM " */
	public final static String  CREATE_SQL_FROM = "FROM ";
	/** "WHERE " */
	public final static String  CREATE_SQL_WHERE = "WHERE ";
	/** "SET " */
	public final static String  CREATE_SQL_SET = "SET ";
	/** "* " */
	public final static String  CREATE_SQL_ALL = "* ";
	
	
	public CreateSQLUtil() { }
	
	/**
	 * <h5>生成插入SQL语句</h5>
	 *
	 * @Title: insertSQL  生成插入SQL语句,这个方法适用于数据库字段与类对象属性相同
	 * @param object 要生成对象的类
	 * @return 返回类型 SQL语句
	 */
	public static String insertSQL(Object object){
		return _insertSQL(object, CREATE_SQL_STRATEGY_IDENTICL);
	}
	/**
	 * <h5>生成插入SQL语句</h5>
	 *
	 * @Title: insertSQL 生成插入SQL语句,<br>
	 * @param  object 类对象 
	 * @param strategy 生成策略  0: 生成数据库字段与类对象属性相同;<br>
	 * 				1: 生成数据库字段从第二个开始,遇到大写字母时,就在大写字母前加"_"并把大写字母转成小写字母;<br>
	 * @return 返回类型 SQL语句
	 */
	public static String insertSQL(Object object , int strategy){
		return _insertSQL(object, CREATE_SQL_STRATEGY_CHARACTER_CHANGE);
	}

	/**
	 * <h5>生成插入SQL语句</h5>
	 *
	 * @Title: insertSQL
	 * @param tableName 表名
	 * @param columns 数据库字段集合("xxx,xxx,...")
	 * @param fields 类对象属性集合("xxx,xxx,...")
	 * @return 返回类型 SQL语句
	 */
	public static String insertSQL(String tableName,String columns,String fields) {
		String[] _columns = columns.split(",");
		String[] _fields = fields.split(",");
		return _insertSQL(tableName, _columns, _fields);
	}
	/**
	 * <h5>生成插入SQL语句,适用于数据库字段与类属性相同</h5>
	 *
	 * @Title: insertSQL
	 * @param tableName 表名
	 * @param fields 类对象属性集合("xxx,xxx,...")
	 * @return 返回类型 SQL语句
	 */
	public static String insertSQL(String tableName,String fields) {
		
		String[] _fields = fields.split(",");
		return _insertSQL(tableName, _fields, _fields);
	}
	
	/**
	 * <h5>生成插入SQL语句</h5>
	 *
	 * @Title: insertSQL
	 * @param tableName 表名
	 * @param columnNames  数据库字段集合
	 * @param fieldNames 类对象属性集合
	 * @return 返回类型 SQL语句
	 */
	public static String insertSQL(String tableName,String[] columnNames, String[] fieldNames) {
		return _insertSQL(tableName, columnNames, fieldNames);
	}
	
	/**
	 * <h5>生成更新SQL语句</h5>
	 *
	 * @Title: upateSQL生成更新SQL语句
	 * @param tableName 表名
	 * @param columnNames 数据库字段集合
	 * @param fieldNames 类对象属性集合
	 * @param columnWhereName 数据库字段条件名
	 * @param fieldWhereName 类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	public static String upateSQL(String tableName,String[] columnNames, String[] fieldNames,
			String columnWhereName,String fieldWhereName,String statusName,int statusValue) {
		if (null != columnNames && null != fieldNames && columnNames.length == fieldNames.length) {
			return _updateSQL(tableName, columnNames, fieldNames, columnWhereName, fieldWhereName,statusName,statusValue);
		}
		return null;
	}

	/**
	 * <h5>生成更新SQL语句</h5>
	 *
	 * @Title: updateSQL 生成更新SQL语句
	 * @param tableName 表名 
	 * @param columns 数据库字段集合("xxx,xxx,xxx,...")
	 * @param fields 类对象属性集合("xxx,xxx,xxx,...")
	 * @param columnWhereName 数据库字段条件名
	 * @param fieldWhereName 类对象属性名
	 * @return 返回类型 SQL语句
	 */
	public static String updateSQL(String tableName,String columns,String fields,
			String columnWhereName,String fieldWhereName,String statusName,int statusValue) {
		String[] columnNames = columns.split(",");
		String[] fieldNames = fields.split(",");
		if (null != columnNames && null != fieldNames && columnNames.length == fieldNames.length) {
			return _updateSQL(tableName, columnNames, fieldNames, columnWhereName, fieldWhereName,statusName,statusValue);
		}
		return null;
	}
	/**
	 * <h4>生成更新SQL语句,适用于数据库字段与类属性相同</h4>
	 * @param tableName 表名
	 * @param fields 类对象属性集合("xxx,xxx,xxx,...")
	 * @param fieldWhereName 类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	public static String updateSQL(String tableName,String fields,String fieldWhereName,String statusName,int statusValue) {
		
		String[] fieldNames = fields.split(",");
		if (null != fieldNames) {
			return _updateSQL(tableName, fieldNames, fieldNames, fieldWhereName, fieldWhereName,statusName,statusValue);
		}
		return null;
	}
	
	
	/**
	 * <h5>生成删除SQL语句</h5>
	 *
	 * @Title: deleteSQL 生成删除SQL语句
	 * @param tableName 表名
	 * @param columnWhereName 数据库字段名
	 * @param fieldWhereName 类对象属性名
	 * @return 返回类型 SQL语句
	 */
	public static String deleteSQL(String tableName, String columnWhereName,String fieldWhereName) {
		StringBuilder sb = new StringBuilder(CREATE_SQL_DELETE + CREATE_SQL_FROM + tableName + " ");
		sb.append(CREATE_SQL_WHERE + columnWhereName + "=#{" + fieldWhereName +"}");
		return sb.toString();
	}
	/**
	 * <h5>生成删除SQL语句,适用于数据库字段与类属性相同</h5>
	 *
	 * @Title: deleteSQL 生成删除SQL语句
	 * @param tableName 表名
	 * @param fieldWhereName 类对象属性名
	 * @return 返回类型 SQL语句
	 */
	public static String deleteSQL(String tableName, String fieldWhereName) {
		StringBuilder sb = new StringBuilder(CREATE_SQL_DELETE + CREATE_SQL_FROM + tableName + " ");
		sb.append(CREATE_SQL_WHERE + fieldWhereName + "=#{" + fieldWhereName +"}");
		return sb.toString();
	}
	
	/**
	 * <h4>生成查询SQL语句</h4>
	 * @param tableName 表名
	 * @param displayDataFields 显示数据字段,null时为查询全部;格式("name,age..."或者 "u_name as name,u_age as age...")
	 * @param columnWhereName 条件数据库字段名 null时为空条件
	 * @param fieldWhereName 条件 类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	public static String selectSQL(String tableName,String displayDataFields , 
			String columnWhereName,String fieldWhereName,String statusName,int statusValue) {
		boolean tbool = true;
		StringBuilder sb = new StringBuilder(CREATE_SQL_SELECT);
		if (null == displayDataFields) { sb.append(CREATE_SQL_ALL);
		}else { sb.append(displayDataFields);}
		sb.append(" " + CREATE_SQL_FROM + tableName);
		if (null != columnWhereName) {
			sb.append(" " + CREATE_SQL_WHERE + columnWhereName);
			sb.append("=#{" + fieldWhereName + "}");
			tbool = false;
		}
		if (null != statusName) {
			sb.append(tbool ? " where " : " and ");	
			sb.append(statusName + "=" + statusValue);
		}
		return sb.toString();
	}
	
	/**
	 * <h4>生成查询SQL语句</h4>
	 * @param tableName 表名
	 * @param displayDataFields 显示数据字段,null时为查询全部;格式("name,age..."或者 "u_name as name,u_age as age...")
	 * @param columnWhereName 条件数据库字段名 null时为空条件
	 * @param fieldWhereName 条件 类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	public static String selectSQLLikeName(String tableName, String displayDataFields , 
			String columnWhereName,String fieldWhereName,String statusName,int statusValue) {
		boolean tbool = true;
		StringBuilder sb = new StringBuilder(CREATE_SQL_SELECT);
		if (null == displayDataFields) { sb.append(CREATE_SQL_ALL);
		}else { sb.append(displayDataFields);}
		sb.append(" " + CREATE_SQL_FROM + tableName + " ");
		if (null != columnWhereName) {
			sb.append(" " + CREATE_SQL_WHERE + columnWhereName);
			sb.append(" like concat('%',#{" + fieldWhereName + "},'%')");
			tbool = false;
		}
		if (null != statusName) {
			sb.append(tbool ? " where " : " and ");	
			sb.append(statusName + "=" + statusValue);
		}
		return sb.toString();
	}
	
	/**
	 * <h4>重名查询</h4>
	 * @param tableName 表名 
	 * @param keyColumn 表的主键
	 * @param columnWhereName 条件表名 
 	 * @param fieldWhereName 条件属性名
 	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return
	 */
	public static String selectSQLIsRename(String tableName,String keyColumn,String columnWhereName,
			String fieldWhereName,String statusName,int statusValue) {
		boolean tbool = true;
		StringBuilder sb = new StringBuilder(CREATE_SQL_SELECT);
		sb.append("count(" + keyColumn +") " );
		sb.append(CREATE_SQL_FROM + tableName);
		if (null != columnWhereName && null != fieldWhereName) {
			sb.append(" " + CREATE_SQL_WHERE + columnWhereName);
			sb.append("=#{" + fieldWhereName + "}");
			tbool = false;
		}
		if (null != statusName) {
			sb.append(tbool ? " where " : " and ");	
			sb.append(statusName + "=" + statusValue);
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	/**
	 * <h4>生成查询SQL语句</h4>
	 * @param tableName 表名
	 * @param fieldWhereName 条件 类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	public static String selectSQL(String tableName,String fieldWhereName,String statusName,int statusValue) {
		boolean tbool = true;
		StringBuilder sb = new StringBuilder(CREATE_SQL_SELECT);
		sb.append(CREATE_SQL_ALL);
		sb.append(CREATE_SQL_FROM + tableName);
		if (null != fieldWhereName) {
			sb.append(" " + CREATE_SQL_WHERE + fieldWhereName);
			sb.append("=#{" + fieldWhereName + "}");
			tbool = false;
		}
		if (null != statusName) {
			sb.append(tbool ? " where " : " and ");	
			sb.append(statusName + "=" + statusValue);
		}
		return sb.toString();
	}
	/**
	 * <h4>生成查询SQL语句</h4>
	 * @param tableName 表名
	 * @param columnWhereName 条件 表字段名
	 * @param fieldWhereName 条件 类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	public static String selectSQL(String tableName,String columnWhereName , String fieldWhereName,String statusName,int statusValue) {
		boolean tbool = true;
		StringBuilder sb = new StringBuilder(CREATE_SQL_SELECT);
		sb.append(CREATE_SQL_ALL);
		sb.append(CREATE_SQL_FROM + tableName);
		if (columnWhereName != null && fieldWhereName != null) {
			sb.append(" " + CREATE_SQL_WHERE + columnWhereName);
			sb.append("=#{" + fieldWhereName + "}");
			tbool = false;
		}
		if (null != statusName) {
			sb.append(tbool ? " where " : " and ");	
			sb.append(statusName + "=" + statusValue);
		}
		return sb.toString();
	}
	
	
	
	
	//================================================== private mehtod ==============================================//
	/**
	 * <h5>生成更新SQL语句</h5>
	 *
	 * @Title: _updateSQL 生成更新SQL语句
	 * @param tableName 表名 
	 * @param columnNames 数据库字段名集合
	 * @param fieldNames 类对象属性集合
	 * @param columnWhereName  条件数据库字段名
	 * @param fieldWhereName 条件类对象属性名
	 * @param statusName 状态名称
	 * @param statusValue 状态值
	 * @return 返回类型 SQL语句
	 */
	private static String _updateSQL(String tableName, String[] columnNames, String[] fieldNames,
			String columnWhereName, String fieldWhereName,String statusName,int statusValue) {
		StringBuilder sb = new StringBuilder(CREATE_SQL_UPDATE + tableName + " " + CREATE_SQL_SET);
		for (int i = 0; i < fieldNames.length; i++) {
			sb.append(columnNames[i] + "=#{" + fieldNames[i] +"},");
		}
		//String temp =sb.toString().substring(0, sb.length()-1);
		String temp = sb.toString().substring(0, sb.length()-1);
		sb = new StringBuilder();
		sb.append(temp + " ");
		sb.append(CREATE_SQL_WHERE + columnWhereName + "=#{" + fieldWhereName + "}");
		if (null != statusName) {
			sb.append(" and " + statusName + "=" + statusValue);
		}
		return sb.toString();
	}
	
	/**
	 * <h5>生成转换字符</h5>
	 * 生成数据库字段从第二个开始,遇到大写字母时,就在大写字母前加"_"并把大写字母转成小写字母;
	 * @Title: createCharacterChange 生成转换字符
	 * @param column 数据库字段名
	 * @return 转换后的字符
	 */
	private static StringBuilder createCharacterChange(String column) {
		char[] chars= column.toCharArray();
		StringBuilder sbChars = new StringBuilder();
		if (chars.length>0) {
			sbChars.append(chars[0]+"");
			for (int j = 1; j < chars.length; j++) {
				if (Character.isUpperCase(chars[j])) {
					String temp = chars[j]+"";
					sbChars.append("_" + temp.toLowerCase());
				}else {
					sbChars.append(chars[j]);
				}
			}
		}
		return sbChars;
	}
	
	/**
	 * <h5>生成插入SQL语句</h5>
	 *
	 * @Title: _insertSQL 生成插入SQL语句
	 * @param object 类对象
	 * @param strategy 策略0与1; 0: 生成数据库字段与类对象属性相同;<br>
	 * 				1: 生成数据库字段从第二个开始,遇到大写字母时,就在大写字母前加"_"并把大写字母转成小写字母;<br>
	 * @return 返回类型 SQL语句
	 */
	private static String _insertSQL(Object object , int strategy){
		try {
			Class<?> clazz = object.getClass();
			String tableName = clazz.getName();
			tableName = tableName.substring(tableName.lastIndexOf('.')+1);
			Field[] fields = clazz.getDeclaredFields();
			StringBuilder sb_columns = new StringBuilder(); 
			StringBuilder sb_fields = new StringBuilder(); 
			
			StringBuilder sb = new StringBuilder(CREATE_SQL_INSERT_INTO + tableName + " (");
			for (int i = 0; i < fields.length; i++) {
				if (CREATE_SQL_STRATEGY_CHARACTER_CHANGE == strategy) {
					sb_columns.append(createCharacterChange(fields[i].getName())  + ",");
				}else {
					sb_columns.append(fields[i].getName() +",");
				}
				
				sb_fields.append("#{" + fields[i].getName() + "},");
			}
			sb.append(sb_columns.substring(0, sb_columns.length()-1));
			sb.append(")values(");
			sb.append(sb_fields.substring(0, sb_fields.length()-1));
			sb.append(")");
			return sb.toString();
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * <h5>生成插入SQL语句</h5>
	 *
	 * @Title: _insertSQL 生成插入SQL语句
	 * @param tableName 表名 
	 * @param columnNames  数据库字段集合
	 * @param fieldNames 类对象属性集合
	 * @return 返回类型 SQL语句
	 */
	private static String _insertSQL(String tableName, String[] columnNames, String[] fieldNames) {
		if (null != columnNames && null != fieldNames && columnNames.length == fieldNames.length) {
			StringBuilder sb = new StringBuilder(CREATE_SQL_INSERT_INTO + tableName + " (");
			StringBuilder sb_columns = new StringBuilder(); 
			StringBuilder sb_fields = new StringBuilder(); 
			for (int i = 0; i < fieldNames.length; i++) {
				sb_columns.append(columnNames[i] +",");
				sb_fields.append("#{" + fieldNames[i] + "},");
			}
			sb.append(sb_columns.substring(0, sb_columns.length()-1));
			sb.append(")values(");
			sb.append(sb_fields.substring(0, sb_fields.length()-1));
			sb.append(")");
			return sb.toString();
		}
		return null;
	}
	
}

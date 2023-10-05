package cn.peyton.children.mgts.mybatis.db;

import cn.peyton.children.mgts.mybatis.entity.Column;
import cn.peyton.children.mgts.mybatis.entity.Table;
import cn.peyton.children.mgts.mybatis.utils.ConvertUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <h3>操作数据库工具类 .</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018/8/6 8:55
 * @version 1.0.0
 * </pre>
 */
public final class DbHelper {
    /** 数据库驱动  */
    private static String DRIVER;
    /** 数据库链接地址 */
    private static String URL;
    /** 数据库用户名 */
    private static String USRENAME;
    /** 数据库密码 */
    private static String PASSWORD;

    /**
     * <h4>构造函数</h4>
     */
    public DbHelper(){
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * <h3>获取数据库下的所有表名</h3>
     * @return
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[]{"TABLE"});

            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getTableNames]方法!");
        }finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return tableNames;
    }

    /**
     * <h3>根据表名获取表对象信息</h3>
     * @param tableName 表名
     * @return 表对象
     */
    public static Table getTable(String tableName,String removeTablePrefix){
        String _sql = "show table status like '"  + tableName + "'";
        Connection connection = getConnection();
        DatabaseMetaData metaData = null;
        Statement statement = null;
        ResultSet rs= null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(_sql);
            //读取 Table 信息
            List<Table> tables = _readTableExpands(rs);
            //
            if (null != tables && tables.size() > 0){
                metaData = connection.getMetaData();
                return  _readTableToObject(rs, metaData, tables.get(0), removeTablePrefix);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getTable]方法!");
        }finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
        return null;
    }

    /**
     * <h3>获取表转成对象</h>
     * @param tableName 表名 {为空[null]时查全部，有值查单表}
     * @param removeTablePrefix 去除表前缀
     * @return
     */
    public static List<Table> getTables(String tableName, String removeTablePrefix) {
        List<Table> tables = new ArrayList<Table>();
        DatabaseMetaData metaData = null;
        Connection connection = getConnection();
        ResultSet rs = null;
        try {
            metaData = connection.getMetaData();
            if (null != tableName) {
                tables.clear();
                tables.add(getTable(tableName,removeTablePrefix));
            } else {
                tables.clear();
                //调用方法
                 tables = _readTableExpands();
                int size = tables.size();
                for (int i = 0; i < size; i++) {
                    //调用方法
                    _readTableToObject(rs, metaData, tables.get(i), removeTablePrefix);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getTables]方法!");
        }finally {
            closeResultSet(rs);
            closeConnection(connection);
        }

        return tables;
    }


    /**
     * <h4>获取主键集合</h4>
     * @param tableName 表名
     * @return
     */
    public static List<String> getPrimaryKeyName(String tableName) {
        List<String> pks = new ArrayList<String>();
        Connection conn = getConnection();
        ResultSet rs = null;
        DatabaseMetaData dbmd = null;
        try {
            dbmd = conn.getMetaData();
            rs = dbmd.getPrimaryKeys(null, null, tableName);
            while (rs.next()) {
                pks.add(rs.getString(Column.Constants.COLUMN_NAME));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getPrimaryKeyName]方法!");
        }finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return pks;
    }

    /**
     * <h3>获取表的字段名</h3>
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<String>();
        //连接数据库
        Connection conn = getConnection();
        DatabaseMetaData dbmd = null;
        ResultSet rs = null;
        try {
            dbmd = conn.getMetaData();
            rs = dbmd.getColumns(null, "%", tableName, "%");
            while (rs.next()) {
                columnNames.add(rs.getString(Column.Constants.COLUMN_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getColumnNames]方法!");
        }finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return columnNames;
    }

    /**
     * <h3>获取表的类型</h3>
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<String>();
        //连接数据库
        Connection conn = getConnection();
        DatabaseMetaData dbmd = null;
        ResultSet rs = null;
        try {
            dbmd = conn.getMetaData();
            rs = dbmd.getColumns(null, "%", tableName, "%");
            while (rs.next()) {
                columnTypes.add(rs.getString(Column.Constants.TYPE_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getColumnTypes]方法!");
        }finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return columnTypes;
    }

    /**
     * <h3>获取表的注释</h3>
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnComments = new ArrayList<String>();
        //连接数据库
        Connection conn = getConnection();
        DatabaseMetaData dbmd = null;
        ResultSet rs = null;
        try {
            dbmd = conn.getMetaData();
            rs = dbmd.getColumns(null, "%", tableName, "%");
            while (rs.next()) {
                columnComments.add(rs.getString(Column.Constants.REMARKS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getColumnComments]方法!");
        }finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return columnComments;
    }

    /**
     * <h3>获取主键 的列 数据</h3>
     * @param columnList 列集合
     * @param pkColumnName 列主键名
     * @return
     */
    public static Column getPrimaryKeyColumn(List<Column> columnList,String pkColumnName) {
        if (null != pkColumnName && null != columnList && columnList.size() > 0) {
            int size = columnList.size();
            for (int i = 0; i < size; i++) {
                if (pkColumnName.equals(columnList.get(i).getColumnName())) {
                    return columnList.get(i);
                }
            }
        }
        return new Column();
    }


    //************************************ 私有方法 ************************************//

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * <h3>获取表对象集合</h3>
     * @return
     */
    private static List<Table> _readTableExpands() {
        List<Table> tables = new ArrayList<Table>();
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet rs= null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("show table status");
            //调用方法
            tables = _readTableExpands(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取表数据异常[DbHelper]--[getTableName]方法!");
        }finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
        return tables;
    }


    /**
     * <h3>读取表扩展信息集合</h3>
     * @param rs
     * @return
     * @throws SQLException
     */
    private static List<Table> _readTableExpands(ResultSet rs) throws SQLException {
        List<Table> tables = new ArrayList<Table>();
        Table table;
        while (rs.next()) {
            table = new Table();
            // 设置 表名
            table.setTableName(rs.getString(Table.Constants.NAME));
            // 设置 表存储引擎
            table.setEngine(rs.getString(Table.Constants.ENGINE));
            // 设置 版本
            table.setVersion(rs.getString(Table.Constants.VERSION));
            // 设置 行格式。对于MyISAM引擎，这可能是Dynamic,Fixed或Compressed。
            //      动态行的行长度可变，例如：Varchar 或 Blob 类型字段；
            //      固定行是指行长长度不变，例如：Char 和 Integer 类型字段；
            table.setRowFormat(rs.getString(Table.Constants.ROW_FORMAT));
            // 设置 表中的行数；对于非事务性表，这个值是精确的，对于事务性引擎，这个值通常是估算的
            table.setRows(rs.getString(Table.Constants.ROWS));
            // 设置 平均每行包括的字节数
            table.setAvgRowLength(rs.getString(Table.Constants.AVG_ROW_LENGTH));
            // 设置 整个表的数据量（单位：字节）
            table.setDataLength(rs.getString(Table.Constants.DATA_LENGTH));
            // 设置 表可以容纳的最大数据量
            table.setMaxDataLength(rs.getString(Table.Constants.MAX_DATA_LENGTH));
            // 设置 索引占用磁盘的空间大小
            table.setIndexLength(rs.getString(Table.Constants.INDEX_LENGTH));
            // 设置 对于MyISAM引擎,标识已分配，但现在未使用的空间，并且包含了已被删除行的空间
            table.setDataFree(rs.getString(Table.Constants.DATA_FREE));
            // 设置 下一个自动增长值
            table.setAutoIncrement(rs.getString(Table.Constants.AUTO_INCREMENT));
            try {
                String _ct = rs.getString(Table.Constants.CREATE_TIME);
                String _ut = rs.getString(Table.Constants.UPDATE_TIME);
                String _cht = rs.getString(Table.Constants.CHECK_TIME);
                if (null != _ct && !"".equals(_ct) ){
                    // 设置 表创建时间
                    table.setCreateTime(format.parse(_ct));
                }
                if (null != _ut && !"".equals(_ut) ){
                    // 设置 表最近更新的时间
                    table.setUpdateTime(format.parse(_ut));
                }
                if (null != _cht && !"".equals(_cht) ){
                    // 设置 使用check table 或 myisamchk 工具检查表的最近时间
                    table.setCheckTime(format.parse(_cht));
                }
            }catch (ParseException e){
                e.printStackTrace();
                System.out.println("获取表数据异常[DbHelper]--[getTables]方法!");
            }
            // 设置 表的默认字符集和字符排序规则
            table.setCollation(rs.getString(Table.Constants.COLLATION));
            // 设置 如果启用，则对整个表的内容计算时的校验和
            table.setCheckSum(rs.getString(Table.Constants.CHECK_SUM));
            // 设置 指表创建时的其他所有选项
            table.setCreateOptions(rs.getString(Table.Constants.CREATE_OPTIONS));
            // 设置 包含了其他额外信息，对于MyISAM引擎，包含了注释；
            //       如果表使用的是innodb引擎，将现实表的剩余空间；
            //       如果是一个视图，注释里面包含了VIEW字样；
            table.setComment(rs.getString(Table.Constants.COMMENT));
            tables.add(table);
        }
        return tables;
    }


    /**
     * <h3>获取表转换成对象</h3>
     * @param rs
     * @param metaData
     * @param table 表对象
     * @param removeTablePrefix 去除表前缀
     * @return
     * @throws SQLException
     */
    private static Table _readTableToObject(ResultSet rs, DatabaseMetaData metaData,
                                            Table table, String removeTablePrefix) throws SQLException {
        String _tableName = table.getTableName();

        // 1.获取表 主键
        rs = metaData.getPrimaryKeys(null, null, _tableName);
        while (rs.next()) {
            table.setPrimaryKeyName(rs.getString(Column.Constants.COLUMN_NAME));
            break;
        }

        // 2. 设置 表的对象名
        table.setObjectName(ConvertUtil.removePrefixTableName2ObjName(_tableName,removeTablePrefix));

        // 3.设置表 行的集合
        List<Column> _columns = new ArrayList<Column>();

        rs = metaData.getColumns(null, null, _tableName, "%");
        // 读取表的每行信息
        _readColumn(_columns,rs);
        //
        table.setColumns(_columns);

        // 4.获取表 外键 添加到 行中
        rs = metaData.getImportedKeys(null, null, _tableName);
        int _size = _columns.size();
        while (rs.next()) {
            // 获取 外键列名(数据库）
            String fkColumnName = rs.getString(Column.ImportedKeys.FK_COLUMN_NAME);
            //循环 行 集合
            for (int i = 0; i < _size; i++) {
                // 判断 外键列名与 行集合的 列名 相同 则 赋值其他属性
                if (fkColumnName.equals(_columns.get(i).getColumnName())) {
                    // 设置 外键表的列名(数据库）
                    _columns.get(i).getExpandForeignKey().setPkColumnName(
                            rs.getString(Column.ImportedKeys.PK_COLUMN_NAME));
                    // 设置 外键表名(数据库）
                    String _tabName = rs.getString(Column.ImportedKeys.PK_TABLE_NAME);
                    // 设置 根据表名 设置成 对象类型
                    String _objType = ConvertUtil.removePrefixTableName2ObjName(_tabName, removeTablePrefix);
                    // 设置 根据表名 设置成 对象名称
                    String _objName = ConvertUtil.toFirstLowerCase(_objType);
                    // 设置 是否外键 默认false不是
                    _columns.get(i).setFk(true);
                    // 设置 外键表名(数据库）
                    _columns.get(i).getExpandForeignKey().setPkTableName(_tabName);
                    // 设置 外键列名(数据库）
                    _columns.get(i).getExpandForeignKey().setFkColumnName(fkColumnName);
                    // 设置 对象类型
                    _columns.get(i).getExpandForeignKey().setPkFieldType(_objType);
                    // 设置 对象名称
                    _columns.get(i).getExpandForeignKey().setFkFieldName(_objName);
                    // 设置 主索引键资料表的目录名称
                    _columns.get(i).getExpandForeignKey().setPkTableCat(
                            rs.getString(Column.ImportedKeys.PK_TABLE_CAT));
                    // 设置 主索引键资料表的结构描述名称
                    _columns.get(i).getExpandForeignKey().setPkTableSchem(
                            rs.getString(Column.ImportedKeys.PK_TABLE_SCHEM));
                    // 设置 包含外部索引键资料表的目录名称
                    _columns.get(i).getExpandForeignKey().setFkTableCat(
                            rs.getString(Column.ImportedKeys.FK_TABLE_CAT));
                    // 设置 外部索引键资料表的名称
                    _columns.get(i).getExpandForeignKey().setFkTableName(
                            rs.getString(Column.ImportedKeys.FK_TABLE_NAME));
                    // 设置 外键列名(数据库）
                    _columns.get(i).getExpandForeignKey().setFkColumnName(fkColumnName);
                    // 设置 资料行在多重资料行主索引键中的序号
                    _columns.get(i).getExpandForeignKey().setKeySeq(
                            rs.getShort(Column.ImportedKeys.KEY_SEQ));
                    // 设置 外部索引键的名称
                    _columns.get(i).getExpandForeignKey().setFkName(
                            rs.getString(Column.ImportedKeys.FK_NAME));
                    // 设置 主索引键的名称
                    _columns.get(i).getExpandForeignKey().setPkName(
                            rs.getString(Column.ImportedKeys.PK_NAME));
                    // 设置 外部索引键资料表的结构描述名称
                    _columns.get(i).getExpandForeignKey().setFkTableSchem(
                            rs.getString(Column.ImportedKeys.FK_TABLE_SCHEM));
                }
            }
        }

        // 5. 判断是否有外键 并赋值
        for (int i = 0; i < _size; i++) {
            //获取 字段名
            String _fieldName = _columns.get(i).getFieldName();
            // 设置 属性 首字母 小写
            _columns.get(i).getInteriorNameConversion().setFirstConvertLowerCase(_fieldName);
            // 设置 原始数据名称
            _columns.get(i).getInteriorNameConversion().setOriginal(_columns.get(i).getColumnName());
            // 设置 属性 首字母 大写
            _columns.get(i).getInteriorNameConversion().setFirstConvertUpperCase(
                    ConvertUtil.convertFirst(_fieldName));
            if (_columns.get(i).getFk()) {
                //设置名称
                String _name = ConvertUtil.removeTailField(_columns.get(i).getColumnName(),"_id");
                _fieldName =  ConvertUtil.toFirstLowerCase(_name);
                // 设置 专属 对象名称{关联外键表的表名转换}
                _columns.get(i).setFkExclusiveObjectName(_fieldName);
                // 设置 专属 外键对象主键属性名称
                _columns.get(i).setFkExclusiveChildFieldName(
                        ConvertUtil.convertColumnName2FieldName(
                                _columns.get(i).getExpandForeignKey().getPkColumnName()));
                // 设置 专属 对象类型{关联外键表的表名转换}
                _columns.get(i).setFkExclusiveObjectType(_columns.get(i).getExpandForeignKey().getPkFieldType());
                // 设置 属性 首字母 小写
                _columns.get(i).getInteriorNameConversion().setFirstConvertLowerCase(_fieldName);
                // 设置 属性 首字母 大写
                _columns.get(i).getInteriorNameConversion().setFirstConvertUpperCase(
                        ConvertUtil.convertFirst(_name));
            }

        }

        //
        return table;
    }

    /**
     * <h4>读取列信息</h4>
     * @param columnList 列集合
     * @param rs
     * @throws SQLException
     */
    private static void _readColumn(List<Column> columnList, ResultSet rs) throws SQLException {
        Column column;
        while (rs.next()) {
            column = new Column();
            //设置字段名称
            column.setColumnName(rs.getString(Column.Constants.COLUMN_NAME));
            //设置字段类型
            column.setColumnType(rs.getString(Column.Constants.TYPE_NAME));
            //设置字段注释
            column.setRemarks(rs.getString(Column.Constants.REMARKS));
            //设置属性类型和所需引用的包
            column.setFieldType(ConvertUtil.convertFieldType(column.getColumnType(), column.getImportPackage()));
            //设置属性名称
            column.setFieldName(ConvertUtil.convertColumnName2FieldName(column.getColumnName()));
            if (!column.getFieldType().contains("Boolean")) {
                //设置字段长度
                column.setColumnSize(Integer.valueOf(rs.getString(Column.Constants.COLUMN_SIZE)));
            }

            //设置字段小数位
            column.setDecimalDigits(rs.getInt(Column.Constants.DECIMAL_DIGITS));
            //--------------------------------- 表信息 扩展类 ----------------------------------------//

            //表类别（可能为空）
            column.getExpandTableInfo().setTableCat(rs.getString(Column.Constants.TABLE_CAT));
            //表模式（可能为空）
            column.getExpandTableInfo().setTableSchem(rs.getString(Column.Constants.TABLE_SCHEM));
            //表名
            column.getExpandTableInfo().setTableName(rs.getString(Column.Constants.TABLE_NAME));
            //对应的java.sql.Types的SQL类型(列类型ID)
            column.getExpandTableInfo().setDataType(rs.getString(Column.Constants.DATA_TYPE));
            //缓冲长度（有待验证）
            column.getExpandTableInfo().setBufferLength(rs.getString(Column.Constants.BUFFER_LENGTH));
            //基数（通常是10或2，有待验证）
            column.getExpandTableInfo().setNumPrecRadix(rs.getString(Column.Constants.NUM_PREC_RADIX));
            //是否允许为null
            column.getExpandTableInfo().setNullable(rs.getString(Column.Constants.NULLABLE));
            //默认值
            column.getExpandTableInfo().setColumnDef(rs.getString(Column.Constants.COLUMN_DEF));
            //SQL数据类型（有待验证）
            column.getExpandTableInfo().setSqlDataType(rs.getString(Column.Constants.SQL_DATA_TYPE));
            //SQL时间间隔（有待验证）
            column.getExpandTableInfo().setSqlDatetimeSub(rs.getString(Column.Constants.SQL_DATETIME_SUB));
            //对于 char 类型，该长度是列中的最大字节数
            column.getExpandTableInfo().setCharOctetLength(rs.getString(Column.Constants.CHAR_OCTET_LENGTH));
            //表中列的索引（从1开始）
            column.getExpandTableInfo().setOrdinalPostion(rs.getString(Column.Constants.ORDINAL_POSITION));
            //是否允许为null，0为不允许为空，1为允许为空，2为不确定
            column.getExpandTableInfo().setIsNullable(rs.getString(Column.Constants.IS_NULLABLE));
            //--------------------------------- 表信息 扩展类 ----------------------------------------//
            columnList.add(column);
        }
    }

    //************************************ 数据库打开关闭方法 ********************************//

    /**
     * <h3>获取连接</h3>
     * @return
     */
    public static Connection getConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USRENAME, PASSWORD);
            System.out.println("开始连接数据库!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库异常!");
        }
        return conn;
    }

    /**
     * <h3>关闭连接</h3>
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (null != conn) {
            try {
                conn.close();
                System.out.println("关闭连接数据库!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭数据库异常!");
            }
        }
    }

    /**
     * <h3>关闭 ResultSet</h3>
     * @param resultSet
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (null != resultSet) {
            try {
                resultSet.close();
                System.out.println("正常关闭ResultSet!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭ResultSet异常!");
            }
        }
    }

    /**
     * <h3>关闭 Statement</h3>
     * @param statement
     */
    public static void closeStatement(Statement statement) {
        if (null != statement) {
            try {
                statement.close();
                System.out.println("正常关闭Statement!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("关闭Statement异常!");
            }
        }
    }

    //************************************ 数据库打开关闭方法 ********************************//

    // ========================================== set method ==========================================//
    /**
     * @param DRIVER 数据库驱动
     */
    public static void setDRIVER(String DRIVER) {
        DbHelper.DRIVER = DRIVER;
    }

    /**
     * @param URL 数据库链接地址
     */
    public static void setURL(String URL) {
        DbHelper.URL = URL;
    }

    /**
     * @param USRENAME 数据库用户名
     */
    public static void setUSRENAME(String USRENAME) {
        DbHelper.USRENAME = USRENAME;
    }

    /**
     * @param PASSWORD 数据库密码
     */
    public static void setPASSWORD(String PASSWORD) {
        DbHelper.PASSWORD = PASSWORD;
    }
    // ========================================== set method ==========================================//

    public static void get() {
        try {
            Connection connection = getConnection();
            ResultSet rs = null;
            DatabaseMetaData dbmd = connection.getMetaData();
            rs = dbmd.getColumns(null, null, "tb_shop", "%");
            // ResultSetMetaData rsmd = rs.getMetaData();
            // int size = rsmd.getColumnCount();
            while (rs.next()) {
                String tc = rs.getString("TABLE_CAT");
                System.out.println(tc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeGetImportedKeys() {
        try {
            Connection con = getConnection();
            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet rs = dbmd.getImportedKeys(null, null, "tb_shop");
            ResultSetMetaData rsmd = rs.getMetaData();

            // Display the result set data.
            int cols = rsmd.getColumnCount();
            while(rs.next()) {
                System.out.println(rs.getString(Column.ImportedKeys.PK_COLUMN_NAME));
                System.out.println(rs.getString("FK_NAME"));
            }
            rs.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

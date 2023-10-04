package cn.peyton.children.mgts.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h3>数据表对象</h3>
 * <pre>
 * Author: <a href="http://www.peyton.cn">peyton</a>
 * MailListener: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * ProjectName: lemon
 * PackageName: cn.peyton.spring.tool.mybatis.entity.Table.java
 * CreateDate: 2018/8/6 10:23
 * Version: 1.0.0
 * </pre>
 */
public class Table {
    /**  表名 */
    private String tableName;
    /**  对象名 */
    private String objectName;
    /**  列的集合 */
    private List<Column> columns;
    /** 主键名 */
    private String primaryKeyName;
    /** 需要引入的包  */
    private String importPackage;
    //************************************** expand **************************************//
    /** 表存储引擎   */
    private String engine;
    /** 版本   */
    private String version;
    /** 行格式。对于MyISAM引擎，这可能是Dynamic,Fixed或Compressed。
     * 动态行的行长度可变，例如：Varchar 或 Blob 类型字段；
     * 固定行是指行长长度不变，例如：Char 和 Integer 类型字段；*/
    private String rowFormat;
    /**  表中的行数；对于非事务性表，这个值是精确的，对于事务性引擎，这个值通常是估算的；  */
    private String rows;
    /**  平均每行包括的字节数  */
    private String avgRowLength;
    /**  整个表的数据量（单位：字节）  */
    private String dataLength;
    /** 表可以容纳的最大数据量   */
    private String maxDataLength;
    /**  索引占用磁盘的空间大小  */
    private String indexLength;
    /**  对于MyISAM引擎,标识已分配，但现在未使用的空间，并且包含了已被删除行的空间  */
    private String dataFree;
    /** 下一个自动增长值   */
    private String autoIncrement;
    /**  表创建时间  */
    private Date createTime;
    /** 表最近更新的时间   */
    private Date updateTime;
    /**  使用check table 或 myisamchk 工具检查表的最近时间 */
    private Date checkTime;
    /**  表的默认字符集和字符排序规则  */
    private String collation;
    /**  如果启用，则对整个表的内容计算时的校验和  */
    private String checkSum;
    /**  指表创建时的其他所有选项  */
    private String createOptions;
    /**  包含了其他额外信息，对于MyISAM引擎，包含了注释；
     * 如果表使用的是innodb引擎，将现实表的剩余空间；
     * 如果是一个视图，注释里面包含了VIEW字样；*/
    private String comment;
    //************************************** expand **************************************//
    /**
     * @return 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return 对象名
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * @param objectName 对象名
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * @return 列的集合
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @param columns 列的集合
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * @return 主键名
     */
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    /**
     * @param primaryKeyName 主键名
     */
    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    /**
     * @return 需要引入的包
     */
    public String getImportPackage() {
        if (null != columns && columns.size() > 0) {
            int size = columns.size();
            List<String> temps = new ArrayList<String>();
            for (int i = 0; i < size; i++) {
                temps.add(columns.get(i).getImportPackage().toString());
            }
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size ; j++) {
                    if (temps.get(i).equals(temps.get(j))) {
                        temps.remove(j);
                        size = size - 1;
                    }
                }
            }
            size = temps.size();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < size; i++) {
                sb.append(temps.get(i));
            }
            return sb.toString();
        }
        return importPackage;
    }

    /**
     * @param importPackage 需要引入的包
     */
    public void setImportPackage(String importPackage) {
        this.importPackage = importPackage;
    }

    /**
     * @return 表存储引擎
     */
    public String getEngine() {
        return engine;
    }

    /**
     * @param engine 表存储引擎
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * @return 版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version 版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return 行格式。对于MyISAM引擎，这可能是Dynamic,Fixed或Compressed。
     *       动态行的行长度可变，例如：Varchar 或 Blob 类型字段；
     *       固定行是指行长长度不变，例如：Char 和 Integer 类型字段；
     */
    public String getRowFormat() {
        return rowFormat;
    }

    /**
     * @param rowFormat  行格式。对于MyISAM引擎，这可能是Dynamic,Fixed或Compressed。
     *       动态行的行长度可变，例如：Varchar 或 Blob 类型字段；
     *       固定行是指行长长度不变，例如：Char 和 Integer 类型字段；
     */
    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    /**
     * @return 表中的行数；对于非事务性表，这个值是精确的，对于事务性引擎，这个值通常是估算的；
     */
    public String getRows() {
        return rows;
    }

    /**
     * @param rows 表中的行数；对于非事务性表，这个值是精确的，对于事务性引擎，这个值通常是估算的；
     */
    public void setRows(String rows) {
        this.rows = rows;
    }

    /**
     * @return 平均每行包括的字节数
     */
    public String getAvgRowLength() {
        return avgRowLength;
    }

    /**
     * @param avgRowLength 平均每行包括的字节数
     */
    public void setAvgRowLength(String avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    /**
     * @return 整个表的数据量（单位：字节）
     */
    public String getDataLength() {
        return dataLength;
    }

    /**
     * @param dataLength 整个表的数据量（单位：字节）
     */
    public void setDataLength(String dataLength) {
        this.dataLength = dataLength;
    }

    /**
     * @return 表可以容纳的最大数据量
     */
    public String getMaxDataLength() {
        return maxDataLength;
    }

    /**
     * @param maxDataLength 表可以容纳的最大数据量
     */
    public void setMaxDataLength(String maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    /**
     * @return 索引占用磁盘的空间大小
     */
    public String getIndexLength() {
        return indexLength;
    }

    /**
     * @param indexLength 索引占用磁盘的空间大小
     */
    public void setIndexLength(String indexLength) {
        this.indexLength = indexLength;
    }

    /**
     * @return 对于MyISAM引擎,标识已分配，但现在未使用的空间，并且包含了已被删除行的空间
     */
    public String getDataFree() {
        return dataFree;
    }

    /**
     * @param dataFree 对于MyISAM引擎,标识已分配，但现在未使用的空间，并且包含了已被删除行的空间
     */
    public void setDataFree(String dataFree) {
        this.dataFree = dataFree;
    }

    /**
     * @return 下一个自动增长值
     */
    public String getAutoIncrement() {
        return autoIncrement;
    }

    /**
     * @param autoIncrement 下一个自动增长值
     */
    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    /**
     * @return 表创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 表创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 表最近更新的时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 表最近更新的时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 使用check table 或 myisamchk 工具检查表的最近时间
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
     * @param checkTime 使用check table 或 myisamchk 工具检查表的最近时间
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * @return 表的默认字符集和字符排序规则
     */
    public String getCollation() {
        return collation;
    }

    /**
     * @param collation 表的默认字符集和字符排序规则
     */
    public void setCollation(String collation) {
        this.collation = collation;
    }

    /**
     * @return 如果启用，则对整个表的内容计算时的校验和
     */
    public String getCheckSum() {
        return checkSum;
    }

    /**
     * @param checkSum 如果启用，则对整个表的内容计算时的校验和
     */
    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    /**
     * @return 指表创建时的其他所有选项
     */
    public String getCreateOptions() {
        return createOptions;
    }

    /**
     * @param createOptions 指表创建时的其他所有选项
     */
    public void setCreateOptions(String createOptions) {
        this.createOptions = createOptions;
    }

    /**
     * @return 包含了其他额外信息，对于MyISAM引擎，包含了注释；
     *       如果表使用的是innodb引擎，将现实表的剩余空间；
     *       如果是一个视图，注释里面包含了VIEW字样；
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment 包含了其他额外信息，对于MyISAM引擎，包含了注释；
     *       如果表使用的是innodb引擎，将现实表的剩余空间；
     *       如果是一个视图，注释里面包含了VIEW字样；
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    //**************************************************************************************************************//
    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", objectName='" + objectName + '\'' +
                ", columns=" + columns +
                ", primaryKeyName='" + primaryKeyName + '\'' +
                ", importPackage='" + importPackage + '\'' +
                '}';
    }

    /**
     * <h3>表获取字段</h3>
     * <pre>
     * Author: <a href="http://www.peyton.cn">peyton</a>
     * MailListener: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * CreateDate: 2018/8/8 14:22
     * Version: 1.0.0
     * </pre>
     */
    public interface Constants{

        //****************************** show table status 属性 开始 ***********************************//
        /** 表名称  */
        String NAME = "Name";
        /**
         * 表的存储引擎
         */
        String ENGINE = "Engine";
        /**
         * 版本
         */
        String VERSION = "Version";
        /**
         * 行格式。对于MyISAM引擎，这可能是Dynamic，Fixed或Compressed。
         * 动态行的行长度可变，例如Varchar或Blob类型字段。
         * 固定行是指行长度不变，例如Char和Integer类型字段。
         */
        String ROW_FORMAT = "Row_format";
        /**
         * 表中的行数。对于非事务性表，这个值是精确的，对于事务性引擎，这个值通常是估算的。
         */
        String ROWS = "Rows";
        /**
         * 平均每行包括的字节数
         */
        String AVG_ROW_LENGTH = "Avg_row_length";
        /**
         * 整个表的数据量(单位：字节)
         */
        String DATA_LENGTH = "Data_length";
        /**
         * 表可以容纳的最大数据量
         */
        String MAX_DATA_LENGTH = "Max_data_length";
        /**
         *  索引占用磁盘的空间大小
         */
        String INDEX_LENGTH = "Index_length";
        /**
         *  对于MyISAM引擎，标识已分配，但现在未使用的空间，并且包含了已被删除行的空间。
         */
        String DATA_FREE = "Data_free";
        /**
         *  下一个Auto_increment的值
         */
        String AUTO_INCREMENT = "Auto_increment";
        /**
         * 表的创建时间
         */
        String CREATE_TIME = "Create_time";
        /**
         *  表的最近更新时间
         */
        String UPDATE_TIME = "Update_time";
        /**
         *  使用 check table 或myisamchk工具检查表的最近时间
         */
        String CHECK_TIME = "Check_time";
        /**
         * 表的默认字符集和字符排序规则
         */
        String COLLATION = "Collation";
        /**
         * 如果启用，则对整个表的内容计算时的校验和
         */
        String CHECK_SUM = "Checksum";
        /**
         * 指表创建时的其他所有选项
         */
        String CREATE_OPTIONS = "Create_options";
        /**
         * 包含了其他额外信息，对于MyISAM引擎，
         * 包含了注释徐标新，如果表使用的是innodb引擎 ，
         * 将现实表的剩余空间。如果是一个视图，注释里面包含了VIEW字样。
         */
        String COMMENT = "Comment";
        //****************************** show table status 属性 结束 ***********************************//
    }
//**************************************************************************************************************//
}

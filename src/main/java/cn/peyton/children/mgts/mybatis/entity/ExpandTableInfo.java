package cn.peyton.children.mgts.mybatis.entity;

/**
 * <h3>表信息 扩展类</h3>
 * <pre>
 * @email: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @project name: plum
 * @class name: cn.peyton.plum.mgts.mybatis.entity.ExpandTableInfo.java
 * @create date: 2021-11-24 13:55
 * @author: <a href="http://www.peyton.cn">peyton</a>
 * @version: 1.0.0
 * </pre>
 */
public class ExpandTableInfo {

    /** 表类别（可能为空） */
    private String tableCat;
    /** 表模式（可能为空） */
    private String tableSchem;
    /** 表名 */
    private String tableName;
    /** 对应的java.sql.Types的SQL类型(列类型ID) */
    private String dataType;
    /** 缓冲长度（有待验证） */
    private String bufferLength;
    /** 基数（通常是10或2，有待验证） */
    private String numPrecRadix;
    /** 是否允许为null */
    private String nullable;
    /** 默认值 */
    private String columnDef;
    /** SQL数据类型（有待验证） */
    private String sqlDataType;
    /** SQL时间间隔（有待验证） */
    private String sqlDatetimeSub;
    /** 对于 char 类型，该长度是列中的最大字节数 */
    private String charOctetLength;
    /** 表中列的索引（从1开始） */
    private String ordinalPostion;
    /** 是否允许为null，0为不允许为空，1为允许为空，2为不确定 */
    private String isNullable;


    /**
     * @return 表类别（可能为空）
     */
    public String getTableCat() {
        return tableCat;
    }

    /**
     * @param tableCat 表类别（可能为空）
     */
    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    /**
     * @return 表模式（可能为空）
     */
    public String getTableSchem() {
        return tableSchem;
    }

    /**
     * @param tableSchem 表模式（可能为空）
     */
    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

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
     * @return 对应的java.sql.Types的SQL类型(列类型ID)
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType 对应的java.sql.Types的SQL类型(列类型ID)
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @return 缓冲长度（有待验证）
     */
    public String getBufferLength() {
        return bufferLength;
    }

    /**
     * @param bufferLength 缓冲长度（有待验证）
     */
    public void setBufferLength(String bufferLength) {
        this.bufferLength = bufferLength;
    }

    /**
     * @return 基数（通常是10或2，有待验证）
     */
    public String getNumPrecRadix() {
        return numPrecRadix;
    }

    /**
     * @param numPrecRadix 基数（通常是10或2，有待验证）
     */
    public void setNumPrecRadix(String numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
    }

    /**
     * @return 是否允许为null
     */
    public String getNullable() {
        return nullable;
    }

    /**
     * @param nullable 是否允许为null
     */
    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    /**
     * @return 默认值
     */
    public String getColumnDef() {
        return columnDef;
    }

    /**
     * @param columnDef 默认值
     */
    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    /**
     * @return SQL数据类型（有待验证）
     */
    public String getSqlDataType() {
        return sqlDataType;
    }

    /**
     * @param sqlDataType SQL数据类型（有待验证）
     */
    public void setSqlDataType(String sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    /**
     * @return SQL时间间隔（有待验证）
     */
    public String getSqlDatetimeSub() {
        return sqlDatetimeSub;
    }

    /**
     * @param sqlDatetimeSub SQL时间间隔（有待验证）
     */
    public void setSqlDatetimeSub(String sqlDatetimeSub) {
        this.sqlDatetimeSub = sqlDatetimeSub;
    }

    /**
     * @return 对于 char 类型，该长度是列中的最大字节数
     */
    public String getCharOctetLength() {
        return charOctetLength;
    }

    /**
     * @param charOctetLength 对于 char 类型，该长度是列中的最大字节数
     */
    public void setCharOctetLength(String charOctetLength) {
        this.charOctetLength = charOctetLength;
    }

    /**
     * @return 表中列的索引（从1开始）
     */
    public String getOrdinalPostion() {
        return ordinalPostion;
    }

    /**
     * @param ordinalPostion 表中列的索引（从1开始）
     */
    public void setOrdinalPostion(String ordinalPostion) {
        this.ordinalPostion = ordinalPostion;
    }

    /**
     * @return 是否允许为null，0为不允许为空，1为允许为空，2为不确定
     */
    public String getIsNullable() {
        return isNullable;
    }

    /**
     * @param isNullable 是否允许为null，0为不允许为空，1为允许为空，2为不确定
     */
    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }
}

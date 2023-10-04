package cn.peyton.children.mgts.mybatis.entity;

/**
 * <h3>列 对象 [数据库 --> 表 --> 列]</h3>
 * <pre>
 * Author: <a href="http://www.peyton.cn">peyton</a>
 * MailListener: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * ProjectName: lemon
 * PackageName: cn.peyton.spring.tool.mybatis.entity.Column.java
 * CreateDate: 2018/8/6 12:47
 * Version: 1.0.0
 * </pre>
 */
public class Column {
    /** 列名(数据库） */
    private String columnName;
    /** 列类型(数据库） */
    private String columnType;
    /** 列注释 */
    private String remarks;
    /** 列长度(数据库） */
    private int columnSize;
    /** 小数 */
    private int decimalDigits;
    /** 属性名称 */
    private String fieldName;
    /** 属性类型 */
    private String fieldType;
    /** 需要引入的包  */
    private StringBuffer importPackage = new StringBuffer();
    /** 名称转换类（转换后） */
    private InteriorNameConversion interiorNameConversion = new InteriorNameConversion();
    /** 数据行扩展（外键） */
    private ExpandForeignKey expandForeignKey = new ExpandForeignKey();
    /** 表信息 扩展类 */
    private ExpandTableInfo expandTableInfo = new ExpandTableInfo();
    /** 是否外键 默认false不是 */
    private Boolean isFk = false;
    /** 专属 对象类型{关联外键表的表名转换} */
    private String fkExclusiveObjectType;
    /** 专属 对象名称{关联外键表的表名转换} */
    private String fkExclusiveObjectName;
    /** 专属 外键对象主键属性名称 */
    private String fkExclusiveChildFieldName;
    //****************************************** ***********************************************//

    /** */
    /** */
    /** */

    /**
     * @return 列名(数据库）
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName 列名(数据库）
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return 列类型(数据库）
     */
    public String getColumnType() {
        if (null != columnType) {
            if (columnType.toUpperCase().equals("INT")) {
                return "INTEGER";
            } else if (columnType.toUpperCase().equals("INT UNSIGNED")) {
                return "INTEGER";
            } else if (columnType.toUpperCase().equals("BIGINT")) {
                return "BIGINT";
            }else if (columnType.toUpperCase().equals("BIGINT UNSIGNED")) {
                return "BIGINT";
            }else if (columnType.toUpperCase().equals("TEXT")) {
                return "LONGVARCHAR";
            } else if (columnType.toUpperCase().equals("DATETIME")) {
                return "TIMESTAMP";
            }
        }
        return columnType;
    }

    /**
     * @param columnType 列类型(数据库）
     */
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    /**
     * @return 列注释
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks 列注释
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return 列长度(数据库）
     */
    public int getColumnSize() {
        return columnSize;
    }

    /**
     * @param columnSize 列长度(数据库）
     */
    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    /**
     * @return 小数
     */
    public int getDecimalDigits() {
        return decimalDigits;
    }

    /**
     * @param decimalDigits 小数
     */
    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * @return 属性名称
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     *
     * @param fieldName 属性名称
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return 属性类型
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * @param fieldType 属性类型
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * @return 需要引入的包
     */
    public StringBuffer getImportPackage() {
        return importPackage;
    }

    /**
     * @param importPackage 需要引入的包
     */
    public void setImportPackage(StringBuffer importPackage) {
        this.importPackage = importPackage;
    }

    /**
     * @return 名称转换类（转换后）
     */
    public InteriorNameConversion getInteriorNameConversion() {
        return interiorNameConversion;
    }

    /**
     * @param interiorNameConversion 名称转换类（转换后）
     */
    public void setInteriorNameConversion(InteriorNameConversion interiorNameConversion) {
        this.interiorNameConversion = interiorNameConversion;
    }

    /**
     * @return 数据行扩展（外键）
     */
    public ExpandForeignKey getExpandForeignKey() {
        return expandForeignKey;
    }

    /**
     * @param expandForeignKey 数据行扩展（外键）
     */
    public void setExpandForeignKey(ExpandForeignKey expandForeignKey) {
        this.expandForeignKey = expandForeignKey;
    }

    /**
     *
     * @return 表信息 扩展类
     */
    public ExpandTableInfo getExpandTableInfo() {
        return expandTableInfo;
    }

    /**
     * @param expandTableInfo 表信息 扩展类
     */
    public void setExpandTableInfo(ExpandTableInfo expandTableInfo) {
        this.expandTableInfo = expandTableInfo;
    }
    /**
     * @return 是否外键 默认false
     */
    public Boolean getFk() {
        return isFk;
    }

    /**
     * @param fk 是否外键 默认false
     */
    public void setFk(Boolean fk) {
        isFk = fk;
    }

    /**
     * @return 专属 对象类型{关联外键表的表名转换}
     */
    public String getFkExclusiveObjectType() {
        return fkExclusiveObjectType;
    }

    /**
     * @param fkExclusiveObjectType 专属 对象类型{关联外键表的表名转换}
     */
    public void setFkExclusiveObjectType(String fkExclusiveObjectType) {
        this.fkExclusiveObjectType = fkExclusiveObjectType;
    }

    /**
     * @return 专属 对象名称{关联外键表的表名转换}
     */
    public String getFkExclusiveObjectName() {
        return fkExclusiveObjectName;
    }

    /**
     * @param fkExclusiveObjectName 专属 对象名称{关联外键表的表名转换}
     */
    public void setFkExclusiveObjectName(String fkExclusiveObjectName) {
        this.fkExclusiveObjectName = fkExclusiveObjectName;
    }

    /**
     * @return 专属 外键对象主键属性名称
     */
    public String getFkExclusiveChildFieldName() {
        return fkExclusiveChildFieldName;
    }

    /**
     * @param fkExclusiveChildFieldName 专属 外键对象主键属性名称
     */
    public void setFkExclusiveChildFieldName(String fkExclusiveChildFieldName) {
        this.fkExclusiveChildFieldName = fkExclusiveChildFieldName;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", nameType='" + columnType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", columnSize=" + columnSize +
                ", decimalDigits=" + decimalDigits +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", improtPackage=" + importPackage +
                '}';
    }

    /**
     * <h3>列获取字段</h3>
     * <pre>
     * Author: <a href="http://www.peyton.cn">peyton</a>
     * MailListener: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
     * CreateDate: 2018/8/8 14:22
     * Version: 1.0.0
     * </pre>
     */
    public interface Constants{
        /** 表类别（可能为空）  */
        String TABLE_CAT = "TABLE_CAT";
        /** 表模式（可能为空） */
        String TABLE_SCHEM = "TABLE_SCHEM";
        /** 表名  */
        String TABLE_NAME = "TABLE_NAME";
        /** 列名  */
        String COLUMN_NAME = "COLUMN_NAME";
        /** 对应的java.sql.Types的SQL类型(列类型ID)  */
        String DATA_TYPE = "DATA_TYPE";
        /** java.sql.Types类型名称(列类型名称)  */
        String TYPE_NAME = "TYPE_NAME";
        /** 缓冲长度  */
        String BUFFER_LENGTH = "BUFFER_LENGTH";
        /** 列描述  */
        String REMARKS = "REMARKS";
        /** 列大小 */
        String COLUMN_SIZE = "COLUMN_SIZE";
        /** 小数位数  */
        String DECIMAL_DIGITS = "DECIMAL_DIGITS";
        /**
         * 基数（通常是10或2，有待验证）
         */
        String NUM_PREC_RADIX = "NUM_PREC_RADIX";
        /** 是否允许为null */
        String NULLABLE = "NULLABLE";
        /** 默认值 */
        String COLUMN_DEF = "COLUMN_DEF";
        /**
         * SQL数据类型（有待验证）
         */
        String SQL_DATA_TYPE = "SQL_DATA_TYPE";
        /**
         * SQL时间间隔（有待验证）
         */
        String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
        /**
         * 对于 char 类型，该长度是列中的最大字节数
         */
        String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
        /**
         * 表中列的索引（从1开始）
         */
        String ORDINAL_POSITION = "ORDINAL_POSITION";
        /**
         * 是否允许为null，0为不允许为空，1为允许为空，2为不确定
         */
        String IS_NULLABLE = "IS_NULLABLE";

    }

    public interface ImportedKeys {
        /** 包含主索引鍵資料表的目錄名稱 */
        String PK_TABLE_CAT = "PKTABLE_CAT";
        /** 主索引鍵資料表的結構描述名稱 */
        String PK_TABLE_SCHEM = "PKTABLE_SCHEM";
        /**主索引鍵資料表的名稱 */
        String PK_TABLE_NAME = "PKTABLE_NAME";
        /** 主索引鍵的資料行名稱 */
        String PK_COLUMN_NAME = "PKCOLUMN_NAME";
        /** 包含外部索引鍵資料表的目錄名稱 */
        String FK_TABLE_CAT = "FKTABLE_CAT";
        /** 外部索引鍵資料表的結構描述名稱  */
        String FK_TABLE_SCHEM = "FKTABLE_SCHEM";
        /** 外部索引鍵資料表的名稱 */
        String FK_TABLE_NAME = "FKTABLE_NAME";
        /** 外部索引鍵的資料行名稱 */
        String FK_COLUMN_NAME = "FKCOLUMN_NAME";
        /** 資料行在多重資料行主索引鍵中的序號 */
        String KEY_SEQ = "KEY_SEQ";
        /** 外部索引鍵的名稱 */
        String FK_NAME = "FK_NAME";
        /** 主索引鍵的名稱 */
        String PK_NAME = "PK_NAME";

    }

}

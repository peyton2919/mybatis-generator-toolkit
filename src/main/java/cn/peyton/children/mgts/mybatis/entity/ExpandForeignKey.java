package cn.peyton.children.mgts.mybatis.entity;

/**
 * <h3>数据行扩展（外键）</h3>
 * <pre>
 * @email: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @project name: plum
 * @class name: cn.peyton.plum.mgts.mybatis.entity.Expand.java
 * @create date: 2021-11-24 13:37
 * @author: <a href="http://www.peyton.cn">peyton</a>
 * @version: 1.0.0
 * </pre>
 */
public final class ExpandForeignKey {
    //****************************************** importedKey begin *************************************//

    /** 外键列名(数据库） */
    private String fkColumnName;
    /** 外键属性名称 */
    private String fkFieldName;
    /** 外键表名(数据库） */
    private String pkTableName;
    /** 外键对象名称(对象做为类型) */
    private String pkFieldType;
    /** 外键表的列名(数据库） */
    private String pkColumnName;
    /** 外表对象的属性名称  */
    private String pkFieldName;
    /** 主索引键资料表的目录名称 */
    private String pkTableCat;
    /** 主索引键资料表的结构描述名称 */
    private String pkTableSchem;
    /** 包含外部索引键资料表的目录名称 */
    private String fkTableCat;
    /** 外部索引键资料表的结构描述名称 */
    private String fkTableSchem;
    /** 外部索引键资料表的名称 */
    private String fkTableName;
    /** 资料行在多重资料行主索引键中的序号 */
    private Short keySeq;
    /** 外部索引键的名称 */
    private String fkName;
    /** 主索引键的名称 */
    private String pkName;

    //****************************************** importedKey end *************************************//

    //****************************************** importedKey begin *************************************//

    /**
     * @return 外键列名(数据库）
     */
    public String getFkColumnName() {
        return fkColumnName;
    }

    /**
     * @param fkColumnName 外键列名
     */
    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    /**
     * @return 外键属性名称
     */
    public String getFkFieldName() {
        return fkFieldName;
    }

    /**
     * @param fkFieldName 外键属性名称
     */
    public void setFkFieldName(String fkFieldName) {
        this.fkFieldName = fkFieldName;
    }

    /**
     * @return 外键表名(数据库）
     */
    public String getPkTableName() {
        return pkTableName;
    }

    /**
     * @param pkTableName 外键表名(数据库）
     */
    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    /**
     * @return 外键对象名称（对象作为属性）
     */
    public String getPkFieldType() {
        return pkFieldType;
    }

    /**
     * @param pkFieldType 外键对象名称（对象作为属性）
     */
    public void setPkFieldType(String pkFieldType) {
        this.pkFieldType = pkFieldType;
    }

    /**
     * @return 外键表的列名(数据库）
     */
    public String getPkColumnName() {
        return pkColumnName;
    }

    /**
     * @param pkColumnName 外键表的列名(数据库）
     */
    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    /**
     * @return 外表对象的属性名称
     */
    public String getPkFieldName() {
        return pkFieldName;
    }

    /**
     * @param pkFieldName 外表对象的属性名称
     */
    public void setPkFieldName(String pkFieldName) {
        this.pkFieldName = pkFieldName;
    }

    /**
     *
     * @return 包含主索引键资料表的目录名称
     */
    public String getPkTableCat() {
        return pkTableCat;
    }

    /**
     *
     * @param pkTableCat 包含主索引键资料表的目录名称
     */
    public void setPkTableCat(String pkTableCat) {
        this.pkTableCat = pkTableCat;
    }

    /**
     * @return 主索引键资料表的结构描述名称
     */
    public String getPkTableSchem() {
        return pkTableSchem;
    }

    /**
     * @param pkTableSchem 主索引键资料表的结构描述名称
     */
    public void setPkTableSchem(String pkTableSchem) {
        this.pkTableSchem = pkTableSchem;
    }

    /**
     * @return 包含外部索引键资料表的目录名称
     */
    public String getFkTableCat() {
        return fkTableCat;
    }

    /**
     * @param fkTableCat 包含外部索引键资料表的目录名称
     */
    public void setFkTableCat(String fkTableCat) {
        this.fkTableCat = fkTableCat;
    }

    /**
     * @return 外部索引键资料表的结构描述名称
     */
    public String getFkTableSchem() {
        return fkTableSchem;
    }

    /**
     * @param fkTableSchem 外部索引键资料表的结构描述名称
     */
    public void setFkTableSchem(String fkTableSchem) {
        this.fkTableSchem = fkTableSchem;
    }

    /**
     * @return 外部索引键资料表的名称
     */
    public String getFkTableName() {
        return fkTableName;
    }

    /**
     * @param fkTableName 外部索引键资料表的名称
     */
    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    /**
     * @return 资料行在多重资料行主索引键中的序号
     */
    public Short getKeySeq() {
        return keySeq;
    }

    /**
     * @param keySeq 资料行在多重资料行主索引键中的序号
     */
    public void setKeySeq(Short keySeq) {
        this.keySeq = keySeq;
    }

    /**
     * @return 外部索引键的名称
     */
    public String getFkName() {
        return fkName;
    }

    /**
     * @param fkName 外部索引键的名称
     */
    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    /**
     * @return 主索引键的名称
     */
    public String getPkName() {
        return pkName;
    }

    /**
     * @param pkName 主索引键的名称
     */
    public void setPkName(String pkName) {
        this.pkName = pkName;
    }


    //****************************************** importedKey end *************************************//
}

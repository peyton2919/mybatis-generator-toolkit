package cn.peyton.children.mgts.mybatis.entity;

/**
 * <h3>名称转换类（转换后）</h3>
 * <pre>
 * @email: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @project name: plum
 * @class name: cn.peyton.plum.mgts.mybatis.entity.Field.java
 * @create date: 2021-11-24 13:38
 * @author: <a href="http://www.peyton.cn">peyton</a>
 * @version: 1.0.0
 * </pre>
 */
public final class InteriorNameConversion {
    /**  原始字段名称  */
    private String original;
    /** 首字母转换成大写   */
    private String firstConvertUpperCase;
    /** 首字母转换成小写   */
    private String firstConvertLowerCase;
    /**    */
    /**    */
    /**
     *
     * @return 原始字段名称
     */
    public String getOriginal() {
        return original;
    }

    /**
     *
     * @param original 原始字段名称
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     *
     * @return 首字母转换成大写
     */
    public String getFirstConvertUpperCase() {
        return firstConvertUpperCase;
    }

    /**
     *
     * @param firstConvertUpperCase 首字母转换成大写
     */
    public void setFirstConvertUpperCase(String firstConvertUpperCase) {
        this.firstConvertUpperCase = firstConvertUpperCase;
    }

    /**
     *
     * @return 首字母转换成小写
     */
    public String getFirstConvertLowerCase() {
        return firstConvertLowerCase;
    }

    /**
     *
     * @param firstConvertLowerCase 首字母转换成小写
     */
    public void setFirstConvertLowerCase(String firstConvertLowerCase) {
        this.firstConvertLowerCase = firstConvertLowerCase;
    }
}

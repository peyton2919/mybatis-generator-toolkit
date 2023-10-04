package cn.peyton.children.mgts.mybatis.utils;

/**
 * <h3>操作数据库 工具类</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public final class DatabaseUtil {
    private String URL_SUFFIX = "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private String URL_CHATTER = "jdbc:mysql://localhost:3306/";

    private String driver;
    private String url;
    private String username;
    private String password;
    private String databaseName;

    /**
     * 构造函数
     * @param databaseName 数据库名称
     */
    public DatabaseUtil(String databaseName) {
        this.databaseName = databaseName;
        this.driver = getDriver();
        this.url = getUrl();
        this.username = getUsername();
        this.password = getPassword();
    }

    public DatabaseUtil(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public String getDriver() {
        if (null == driver) {
            return "com.mysql.jdbc.Driver";
        }
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        if (null == url) {
            return URL_CHATTER + getDatabaseName() + URL_SUFFIX;
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        if (null == username){
            return "root";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if (null == password){
            return "hc2919";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}

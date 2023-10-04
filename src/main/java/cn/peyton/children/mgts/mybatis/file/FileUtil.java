package cn.peyton.children.mgts.mybatis.file;

import java.io.File;
import java.io.FileOutputStream;

/**
 * <h3>文件工具类 .</h3>
 * <pre>
 * Author: <a href="http://www.peyton.cn">peyton</a>
 * MailListener: <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * ProjectName: lemon
 * PackageName: cn.peyton.spring.tool.mybatis.file.FileUtil.java
 * CreateDate: 2018/8/7 13:27
 * Version: 1.0.0
 * </pre>
 */
public class FileUtil {
    /** 文件路径+名称 */
    private static String absolutePath;

    /**
     * 创建文件
     * @param path  文件路径
     * @param fileName  文件名称
     * @param ext  文件扩展名
     * @param fileContent   文件内容
     * @return  是否创建成功，成功则返回true
     */
    public static boolean createFile(String path ,String fileName,String ext,String fileContent){
        boolean bool = false;
        //文件路径+名称+文件类型
        absolutePath = path + fileName + "." + ext;
        File file = new File(absolutePath);

        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
            }
            System.out.println("success create file,the file is "+ absolutePath);
            //创建文件成功后，写入内容到文件里
            writeFileContent(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * <h3>向文件中写入内容</h3>
     * @param content 内容
     * @return 判断是否写入成功true 否则false
     * @throws Exception 抛出异常
     */
    private static boolean writeFileContent(String content) throws Exception {
        boolean flag = false;
        FileOutputStream fileOutputStream = null;
        File file = new File(absolutePath);
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes("UTF-8"));
            flag = true;
        } catch (Exception e) {
            System.out.println("文件写入失败！" + e);
        }finally {
            //不要忘记关闭
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return flag;
    }

    /**
     * 创建文件目录 ,当存在相同的文件目录名时不做处理
     * @param pathDirectoryName 要给出绝对路径和目录名称
     * @return 返回true表示有创建文件目录,并且文件创建成功,否则就表示文件目录已经存在不做处理
     */
    public static boolean createDirectory(String pathDirectoryName){
        //如果文件夹不存在则创建
        File file =new File(pathDirectoryName);
        boolean result = false;
        if (!file.exists() && !file.isDirectory()) {
            result = file.mkdirs();
        }
        return result;
    }

    /**
     * <h3>改变路径</h3>
     * <pre>
     *     路径 如: c:/tools 变换后 c:/tools/
     *          如: c:/tools/ 就不作改变直接返回 c:/tools/
     * </pre>
     * @param path 路径
     * @return 返回最反正带/
     */
    public static String changePath(String path) {
        String t = path.substring(path.length() - 1);
        int l = path.lastIndexOf("/");
        return ("/".equals(t) || "\\".equals(t)) ? (path) : (path + (l > -1 ? "/":"\\"));
    }

    /**
     * 删除文件
     * @param fileName 文件名称
     * @return 判断是否写入成功true 否则false
     */
    public static boolean delFile(String fileName){
        Boolean bool = false;
        absolutePath =  absolutePath + fileName+".txt";
        File file  = new File(absolutePath);
        try {
            if(file.exists()){
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }
}

package cn.peyton.children.mgts.mybatis.template;

import cn.peyton.children.mgts.mybatis.utils.ConvertUtil;

/**
 * <h3>controller 模板</h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018年8月10日 9:42
 * @version 1.0.0
 * </pre>
 */
public class ControllerTemplate extends BaseTemplate {

    /**
     * <h4>创建Controller类</h4>
     * @param packageName mapper包路径
     * @return
     */
    public static String create(String objName,String commentName, String path, String importPackagePrefix, String packageName) {
        sb = new StringBuffer();
        _path = existPath(path);
        if (null != packageName) {
            sb.append("package " + packageName + ";\r\n");
        }
        sb.append("\r\n");
        sb.append("import " + importPackagePrefix + "service." + objName + "Service;\r\n");
        sb.append("import org.springframework.stereotype.Controller;\r\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
        sb.append("import jakarta.annotation.Resource;\r\n\r\n");

        createAnnotation(commentName + " Controller 类");
        createControllerClass(objName);
        createFileContent(packageName, objName + "Controller", "java");
        return sb.toString();
    }


    // ============================================== create Controller method begin ============================================== //
    /**
     * <h4>创建Controller 类</h4>
     */
    private static void createControllerClass(String objName) {
        String tempObjName = ConvertUtil.toFirstLowerCase(objName);
        sb.append("@RestController\r\n");
        //sb.append("@RequestMapping(\"/\")\r\n");
        sb.append("public class " + objName + "Controller {\r\n\r\n");
        sb.append("\t@Resource\r\n");
        sb.append("\tprivate " + objName + "Service " + tempObjName + "Service;\r\n\r\n");
        sb.append("}\r\n");
    }
    // ============================================== create Controller method end ============================================== //
}

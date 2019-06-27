package cn.edu.sjtu.sip_server.constant;


public class Const {
    public final static String FROM_EMAIL = "c2lwc2VydmVyQDE2My5jb20=";
    public final static String IMPORT_DATA = "bHVvamluaG9uZzk3";

    public final static String EMAIL_KEY = "email";
    /**
     * session key user
     */
    public final static String USER_KEY = "user";
    /**
     * session key admin
     */
    public final static String ADMIN_KEY = "admin";

    public static String LOCAL_IP = "localhost";
    /**
     * sip server port
     */
    public static int PORT = 8080;


    public static boolean RUNTIME = true;
    /**
     * image_path for save uploaded images
     */
    public final static String IMAGE_PATH = "picture/";

    /**
     * 列表大小
     */
    public final static int PAGE_SIZE = 20;

    /**
     * email正则表达式
     */
    public final static String EMAIL_PATTERN = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
}

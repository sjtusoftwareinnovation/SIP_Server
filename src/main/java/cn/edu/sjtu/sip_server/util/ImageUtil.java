package cn.edu.sjtu.sip_server.util;

import cn.edu.sjtu.sip_server.constant.Const;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
public class ImageUtil {


    /**
     * 为上传的图片生成缩略图,默认给图片路径添加前缀thumbnail.
     */
    public static void generateThumbnail(String originalPath, int width, int height) {
        try {
            Thumbnails.of(new File(originalPath))
                    .size(width, height)
                    .outputFormat("jpg")
                    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }

    /**
     * 用毫秒时间戳来存储图片
     *
     * @param imageName:带后缀名
     * @return
     */
    public static String getImageName(String imageName) {
        if ((imageName != null) && (imageName.length() > 0)) {
            int dot = imageName.lastIndexOf('.');
            if ((dot > -1) && (dot < (imageName.length() - 1))) {
                return Long.toString(System.currentTimeMillis()) + "." + imageName.substring(dot + 1);
            }
        }
        log.error("imageName don't have suffix name");
        return Long.toString(System.currentTimeMillis()) + ".png";
    }

    /**
     * 将页面的multipartfile保存在picture目录下
     *
     * @param file
     * @return
     */
    public static String saveImage(MultipartFile file) {
        log.debug("photo.length:" + file.getSize());
        String saveImagePath = null;
        if (file == null) {
            log.error("input file is null!");
            return saveImagePath;
        } else if (file.getSize() <= 0) {
            log.error("input file size error!");
            return saveImagePath;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                log.error("file getInputStream error");
                e.printStackTrace();
            }

            String resourcePath = new ImageUtil().getResourcePath();
            File file1 = new File(resourcePath + Const.IMAGE_PATH);
            if (file1.isDirectory()) {
                log.debug(resourcePath + Const.IMAGE_PATH + " exists");
            } else {
                file1.mkdirs();
            }
            String imageFileName = getImageName(file.getOriginalFilename());
            saveImagePath = Const.IMAGE_PATH + imageFileName;
            boolean result = inputStreamToFile(ins, resourcePath + saveImagePath);
            if (result) {
            } else {
                return null;
            }
        }
        log.info("saveImagePath:" + saveImagePath);
        return saveImagePath;
    }

    /**
     * get resource path
     *
     * @return
     */
    public static String getResourcePath() {
        String classPath = ImageUtil.class.getResource("/").toString();
        log.info("class .getResource:" + classPath);
        log.info("is runtime:" + Const.RUNTIME);
//        deploy this path will be wrong
        String relativePath = ImageUtil.class.getResource("/").getPath();
        String resourcePath = null;
        int beginIndex = 1;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            beginIndex = 1;
        } else {
            beginIndex = 0;
        }
        if (Const.RUNTIME) {
//            if window substring(1),if mac substring(0)
//            resourcePath = relativePath.substring(beginIndex);
            resourcePath = "/opt/";
        } else {
//            resourcePath = "src/main/resources/";
            resourcePath = "E:/";
        }
        log.info("resourcePath:" + resourcePath);
        return resourcePath;
    }

    /**
     * 将输入流保存到target路径中
     *
     * @param ins
     * @param target
     */
    public static boolean inputStreamToFile(InputStream ins, String target) {
        boolean result = false;
        try {

            OutputStream os = new FileOutputStream(new File(target));
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将文件转为byte[]
     *
     * @param filePath 文件路径
     * @return
     */
    public static byte[] getBytes(String filePath) {
        File file = new File(filePath);
        ByteArrayOutputStream out = null;
        byte[] s = null;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) != -1) {
                out.write(b, 0, b.length);
            }
            s = out.toByteArray();
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.info(filePath + " file not found!");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info(filePath + " file io operation error!");
            e.printStackTrace();
        }
        return s;
    }
}

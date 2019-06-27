package cn.edu.sjtu.sip_server.util;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Enycryption {
    /**
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) {
//        try {
//            byte[] result = (new BASE64Decoder()).decodeBuffer(key);
//            return new String(result);
//        } catch (IOException e) {
//            log.error("decrypt error,data:" + key);
//            e.printStackTrace();
//        }
        return null;
    }


    /**
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return null;
        //return (new BASE64Encoder()).encodeBuffer(key);
    }
}

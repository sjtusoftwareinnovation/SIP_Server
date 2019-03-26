package cn.edu.sjtu.sip_server;

import cn.edu.sjtu.sip_server.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(value = 1)
public class SIPServerRunner implements ApplicationRunner {
    @Value("${server.port}")
    private int port;
    @Value("${server.local.ip}")
    private String localIp;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.debug("runner init the arguments");
        Const.PORT = port;
        Const.LOCAL_IP = localIp;
    }
}

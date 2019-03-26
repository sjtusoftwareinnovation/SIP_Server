package cn.edu.sjtu.sip_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HttpPutFormContentFilter;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@Slf4j
public class SIPServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SIPServerApplication.class, args);
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

}

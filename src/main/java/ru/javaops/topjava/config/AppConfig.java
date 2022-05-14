package ru.javaops.topjava.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableCaching
// TODO: cache only most requested data!
public class AppConfig {

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    Server h2Server() throws SQLException {
//        log.info("Start H2 TCP server");
//        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//    }

    //    https://stackoverflow.com/a/46947975/548473
//    @Bean
//    Module module() {
//        return new Hibernate5Module();
//    }
}

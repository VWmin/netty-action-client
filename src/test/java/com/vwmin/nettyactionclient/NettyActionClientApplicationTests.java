package com.vwmin.nettyactionclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NettyActionClientApplicationTests {

    @Resource(name = "heartBeat")
    CustomProtocol heartBeat;

    @Test
    void contextLoads() {
        System.out.println(heartBeat);
    }

}

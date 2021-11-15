package com.vwmin.nettyactionclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Configuration
public class HeartBeatConfig {

    public static CustomProtocol heartBeat;

    @Resource(name = "heartBeat")
    private CustomProtocol autowiredHeartBeat;

    @Bean("heartBeat")
    public CustomProtocol heartBeat(){
        return CustomProtocol.heartBeat() ;
    }

    @PostConstruct
    public void init(){
        heartBeat = autowiredHeartBeat;
    }

}
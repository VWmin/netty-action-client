package com.vwmin.nettyactionclient;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

import static java.util.UUID.randomUUID;

@Data
public class CustomProtocol implements Serializable {
    private static final long serialVersionUID = 4671171056588401542L;

    private static final String UUID = randomUUID().toString();

    @SerializedName("Id")
    private String id;

    @SerializedName("To")
    private String to;

    /**heartbeat=1, chat=2*/
    @SerializedName("Type")
    private int type;

    @SerializedName("Content")
    private String content ;

    private CustomProtocol(MessageType type, String content){
        this.id = UUID;
        this.type = type.value();
        this.content = content;
    }

    private CustomProtocol(MessageType type, String to, String content){
        this.id = UUID;
        this.type = type.value();
        this.to = to;
        this.content = content;
    }

    public static CustomProtocol msg(String to, String content){
        return new CustomProtocol(MessageType.Chat, to, content);
    }

    public static CustomProtocol heartBeat(String content){
        return new CustomProtocol(MessageType.Heartbeat, content);
    }

    public static CustomProtocol heartBeat(){
        return heartBeat("ping");
    }
}

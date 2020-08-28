package com.xe.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class WebSocketUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    // 存储 websocket session
    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    /**
     * @param session 用户 session(是否存在用户)
     * @param message 发送内容
     */
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message);
            System.out.println("666");
        } catch (IOException e) {
            logger.error("sendMessage IOException ",e);
        }
    }

    public static void sendMessageAll(String message) {//发送所有集合信息到各个聊天室中
        System.out.println(333);
        //如果是同一个地址共用Session，添加和发消息会出现重复效果
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session, message));//lambda表达式

//        Session session=null;
//        for (String key : ONLINE_USER_SESSIONS.keySet()) {
//            session=ONLINE_USER_SESSIONS.get(key);
//            sendMessage(session, message);
//        }
    }

}
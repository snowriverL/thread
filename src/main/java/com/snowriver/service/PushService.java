package com.snowriver.service;

import com.snowriver.push.MessagePushModel;
import com.snowriver.push.SmsValidateResult;

/**
 * 消息推送Service,根据不同的ActionType推送到不同的端
 */
public interface PushService {

    /**
     * 推消息给Android设备
     * @param pushContext 统一推送入口Model
     */
    void pushMessageToAndroid(MessagePushModel pushContext);

    /**
     * 推消息给iOS设备
     * @param pushContext 统一推送入口Model
     */
    void pushMessageToiOS(MessagePushModel pushContext);

    /**
     * 推通知给Android设备
     * @param pushContext 统一推送入口Model
     */
    void pushNoticeToAndroid(MessagePushModel pushContext);

    /**
     * 推通知给iOS设备
     * @param pushContext 统一推送入口Model
     */
    void pushNoticeToiOS(MessagePushModel pushContext);

    /**
     * 推送给Web服务端
     * @param pushContext 统一推送入口Model
     */
    void pushToWeb(MessagePushModel pushContext);

    /**
     * 推送短信
     * @param pushContext 统一推送入口Model
     */
    void pushSMSMessage(MessagePushModel pushContext);

    /**
     * 推送到微信
     * @param pushContext 统一推送入口Model
     */
    void pushToWeChat(MessagePushModel pushContext);

    /**
     * 短信验证码
     * @param pushContext 统一推送入口Model
     */
    SmsValidateResult pushVerificationCode(MessagePushModel pushContext);

    /**
     * 验证校验码
     * @param msgId 短信msgId
     * @param code 验证码
     * @return 校验结果
     */
    SmsValidateResult validVerificationCode(Long msgId, String code);

}

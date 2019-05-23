package com.snowriver.push;

/**
 * 操作接口类型
 */
public enum ActionType {
    PushMessageToAndroid,
    PushMessageToiOS,
    PushNoticeToAndroid,
    PushNoticeToiOS,
    PushToWeb,
    PushSMSMessage,
    PushToWeChat,
    PushVerificationCode,
    ALL,
    CancelPush
}

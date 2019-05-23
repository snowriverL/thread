package com.snowriver.service;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.snowriver.cache.MessageCache;
import com.snowriver.push.MessagePushModel;
import com.snowriver.push.SmsValidateResult;

import java.util.ArrayList;
import java.util.List;

public class PushServiceImpl implements PushService{

    private MessageCache cache = MessageCache.instance();

    public void pushMessageToAndroid(MessagePushModel pushContext) {

    }

    public void pushMessageToiOS(MessagePushModel pushContext) {

    }

    public void pushNoticeToAndroid(MessagePushModel pushContext) {
        try {
            /*androidSend.sendMessage(pushContext);*/
        } catch (Exception e) {
            // 自己的代码部分导致的异常，根据策略进行重推
            Integer pushCount = pushContext.getPushCount();
            pushContext.setPushCount(pushCount + 1);
            // 加入缓存，定时任务重推消息
            cache.messageKeySet.add(pushContext.getId());
            cache.getCache().put(pushContext.getId(), pushContext);
        }
    }

    public void pushNoticeToiOS(MessagePushModel pushContext) {

    }

    public void pushToWeb(MessagePushModel pushContext) {
        //存储消息到DB
        System.out.println("推送到Web端成功");
    }

    public void pushSMSMessage(MessagePushModel pushContext) {
        //调用阿里云SDK推送短信
        //SendService smsSend = Singleton.get(SmsSend.class);
        try {
            //smsSend.sendMessage(pushContext);
        } catch (Exception e) {
            // 自己的代码部分导致的异常，根据策略进行重推
            Integer pushCount = pushContext.getPushCount();
            pushContext.setPushCount(pushCount + 1);
            // 加入缓存，定时任务重推消息
            cache.messageKeySet.add(pushContext.getId());
            cache.getCache().put(pushContext.getId(), pushContext);
        }
    }

    public void pushToWeChat(MessagePushModel pushContext) {

    }

    public SmsValidateResult pushVerificationCode(MessagePushModel pushContext) {
        //SmsSend smsSend = Singleton.get(SmsSend.class);
        SmsValidateResult result = new SmsValidateResult();

        List<String> templateList = new ArrayList();
        //生成6位随机验证码
        Integer verificationCode = NumberUtil.generateRandomNumber(100000, 999999, 1)[0];
        //配置短信验证码和验证码失效时间,以分钟为单位
        String body = pushContext.getBody();
        //默认超时时间
        Integer expireTime = 2;
        if (StrUtil.isNotBlank(body)) {
            boolean flag = NumberUtil.isInteger(body);
            expireTime = flag ? Integer.parseInt(body) : expireTime;
        }
        templateList.add(verificationCode.toString());
        templateList.add(expireTime.toString());
        //pushContext.setBody(String.join(",", templateList));
        try {
            //result = smsSend.sendVerificationCode(pushContext);
        } catch (Exception e) {
            //LibLog.info("验证码发送失败， mobile：{}, {}, {}", pushContext.getReceiver(), e.getMessage(), e);
            result.setStatus(false);
            result.setValidateMsg(e.getMessage());
            return result;
        }
        return result;
    }

    public SmsValidateResult validVerificationCode(Long msgId, String code) {
        //SmsSend smsSend = Singleton.get(SmsSend.class);
        //return smsSend.validateVerificationCode(msgId, code);
        return null;
    }
}

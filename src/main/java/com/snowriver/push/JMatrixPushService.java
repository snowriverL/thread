package com.snowriver.push;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Singleton;
import com.snowriver.cache.MessageCache;
import com.snowriver.service.PushService;
import com.snowriver.service.PushServiceImpl;

import java.util.concurrent.*;

/**
 * JMatrix平台消息推送服务
 * 推送消息到Web,Android,ios,微信,短信.
 * <p>
 *      通知：会直接在通知栏弹出展示。
 *      消息：发送后不会在系统通知栏展现，消息由应用控制，自定义样式和打开方式。
 * </p>
 */
public class JMatrixPushService {

    private static final int MAX_COUNT = 3;

    public static JMatrixPushService instance() {
        return Singleton.get(JMatrixPushService.class);
    }

    private MessageCache cache = MessageCache.instance();

    /**
     * 使用线程池来处理消息，应对高并发的情况下线程较多的情况
     * 消息队列，存储传送进来的消息
     */
    private final ThreadPoolExecutor executorService = new ThreadPoolExecutor(4, 4,
            3, TimeUnit.MINUTES, new ArrayBlockingQueue(500), new TaskThreadFactory(), new TaskRejectedExecutionHandler());

    private PushService pushService = Singleton.get(PushServiceImpl.class);


    /**
     * 验证码推送接口
     * @param pushContext 统一推送入口
     */
    public SmsValidateResult pushVerificationCode(PushContext pushContext) {

        MessagePushModel messagePushModel = new MessagePushModel();
        BeanUtil.copyProperties(pushContext, messagePushModel);

        return pushService.pushVerificationCode(messagePushModel);
    }

    /**
     * 验证码验证接口
     * @param msgId 验证码返回唯一标识
     * @param code 验证码
     * @return 验证结果
     */
    public SmsValidateResult validVerificationCode(Long msgId, String code) {
        return pushService.validVerificationCode(msgId, code);
    }

    /**
     * 消息推送接口
     * @param pushContext 统一推送Model
     */
    public void pushMessage(PushContext pushContext) {

        MessagePushModel messagePushModel = new MessagePushModel();
        BeanUtil.copyProperties(pushContext, messagePushModel);

        //添加定时任务
        /*if (!QuartzManager.isExecuting()) {
            QuartzManager.addJob("rePush", "jMatrix", "push", "pushSide", MessageJob.class, "0/10 * * * * ?");
        }*/

        // 校验重推次数,次数超过3，从缓存中移除该消息，不进行重推
        Integer count = messagePushModel.getPushCount();
        if (count > MAX_COUNT) {
            // 从缓存中移除消息，不进行重推
            cache.getCache().remove(messagePushModel.getId());
            return;
        }

        //接收的消息存储到DB
        /*if (count == 0) {
            messagePushModel.setId(LongIdGen.get().nextId());
            sqlManager.insertTemplate(messagePushModel);
        }*/
        //所有接收到的消息统一放入队列中处理,根据Action调取不同的service来处理
        ActionType action = messagePushModel.getAction();
        executorService.execute(() -> {
            //根据不同Action调用不同的service推送消息
            try {
                switch (action) {
                    case PushToWeb:
                        pushService.pushToWeb(messagePushModel);
                        return;
                    case PushToWeChat:
                        pushService.pushToWeChat(messagePushModel);
                        return;
                    case PushSMSMessage:
                        pushService.pushSMSMessage(messagePushModel);
                        return;
                    case PushNoticeToiOS:
                        pushService.pushNoticeToiOS(messagePushModel);
                        return;
                    case PushMessageToiOS:
                        pushService.pushMessageToiOS(messagePushModel);
                        return;
                    case PushNoticeToAndroid:
                        pushService.pushNoticeToAndroid(messagePushModel);
                        return;
                    case PushMessageToAndroid:
                        pushService.pushMessageToAndroid(messagePushModel);
                        return;
                    case CancelPush:
                    default:
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                //LibLog.error(e);
            }
        });
    }

    /**
     * 线程工厂
     */
    static class TaskThreadFactory implements ThreadFactory {
        int i = 0;
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("JMatrix-push-executor-thread" + i++);
            return thread;
        }
    }

    /**
     * 抛弃策略
     */
    static class TaskRejectedExecutionHandler implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 队列满了以后重新入队
            if (!executor.isShutdown()) {
                executor.execute(r);
            }
        }
    }

}

package com.snowriver.push;

import java.sql.Timestamp;

/**
 * 统一推送入口Model
 */

public class PushContext {

    /**
     * 主键
     */
    private Long id;
    /**
     * 操作接口名称
     */
    private ActionType action;
    /**
     * appKey信息
     */
    private Long appKey;
    /**
     * 推送目标
     */
    private TargetType target;
    /**
     * 接收者
     */
    private String receiver;
    /**
     * 发送的消息的标题
     */
    private String title;
    /**
     * 发送的消息内容
     */
    private String body;
    /**
     * 消息创建时间
     */
    private Timestamp createTime;
    /**
     * 执行时间
     */
    private Timestamp executeTime;
    /**
     * 消息发送人
     */
    private Long userId;
    /**
     * 推送状态,0|失败，1|成功
     */
    private Integer status = 0;
    /**
     * 推送统计
     */
    private Integer pushCount = 0;
    /**
     * 推送失败描述
     */
    private String errorDesc;

    private PushContext(ActionType action, TargetType target, String receiver, String title, String body, Timestamp executeTime, Long userId) {
        this.action = action;
        this.target = target;
        this.receiver = receiver;
        this.title = title;
        this.body = body;
        this.executeTime = executeTime;
        this.userId = userId;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private ActionType action;
        private TargetType target;
        private String receiver;
        private String title;
        private String body;
        private Timestamp executeTime;
        private Long userId;

        public Builder setActionType(ActionType action) {
            this.action = action;
            return this;
        }

        public Builder setTargetType(TargetType target) {
            this.target = target;
            return this;
        }

        public Builder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setExecuteTime(Timestamp executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public PushContext build() {

            if (executeTime == null) {
                executeTime = new Timestamp(System.currentTimeMillis());
            }
            return new PushContext(action, target, receiver, title, body, executeTime, userId);
        }

    }

    protected Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    protected ActionType getAction() {
        return action;
    }

    protected void setAction(ActionType action) {
        this.action = action;
    }

    protected Long getAppKey() {
        return appKey;
    }

    protected void setAppKey(Long appKey) {
        this.appKey = appKey;
    }

    protected TargetType getTarget() {
        return target;
    }

    protected void setTarget(TargetType target) {
        this.target = target;
    }

    protected String getReceiver() {
        return receiver;
    }

    protected void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    protected String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected String getBody() {
        return body;
    }

    protected void setBody(String body) {
        this.body = body;
    }

    protected Timestamp getCreateTime() {
        return createTime;
    }

    protected void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    protected Timestamp getExecuteTime() {
        return executeTime;
    }

    protected void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }

    protected Long getUserId() {
        return userId;
    }

    protected void setUserId(Long userId) {
        this.userId = userId;
    }

    protected Integer getStatus() {
        return status;
    }

    protected void setStatus(Integer status) {
        this.status = status;
    }

    protected Integer getPushCount() {
        return pushCount;
    }

    protected void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }

    protected String getErrorDesc() {
        return errorDesc;
    }

    protected void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}

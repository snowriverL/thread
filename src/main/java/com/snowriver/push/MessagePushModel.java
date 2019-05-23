package com.snowriver.push;

import java.sql.Timestamp;

public class MessagePushModel {

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

    public MessagePushModel() {
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public Long getAppKey() {
        return appKey;
    }

    public void setAppKey(Long appKey) {
        this.appKey = appKey;
    }

    public TargetType getTarget() {
        return target;
    }

    public void setTarget(TargetType target) {
        this.target = target;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPushCount() {
        return pushCount;
    }

    public void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString() {
        return "MessagePushModel{" +
                "id=" + id +
                ", action=" + action +
                ", appKey=" + appKey +
                ", target=" + target +
                ", receiver='" + receiver + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createTime=" + createTime +
                ", executeTime=" + executeTime +
                ", userId=" + userId +
                ", status=" + status +
                ", pushCount=" + pushCount +
                ", errorDesc='" + errorDesc + '\'' +
                '}';
    }
}

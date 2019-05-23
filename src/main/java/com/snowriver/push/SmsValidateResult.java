package com.snowriver.push;

/**
 * 短信校验结果
 */
public class SmsValidateResult {

    private Long msgId;

    private boolean status;

    private String validateMsg;

    private Long mobile;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getValidateMsg() {
        return validateMsg;
    }

    public void setValidateMsg(String validateMsg) {
        this.validateMsg = validateMsg;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SmsValidateResult{" +
                "msgId=" + msgId +
                ", status=" + status +
                ", validateMsg='" + validateMsg + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}

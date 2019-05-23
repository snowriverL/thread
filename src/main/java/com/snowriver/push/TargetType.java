package com.snowriver.push;

/**
 * 推送目标
 * <p>
 *      DEVICE:根据设备推送
 *      ACCOUNT:根据账号推送
 *      ALIAS:根据别名推送
 *      TAG:根据标签推送
 *      ALL:推送给全部设备
 * </p>
 */
public enum TargetType {
    DEVICE,
    ACCOUNT,
    ALIAS,
    TAG,
    ALL
}

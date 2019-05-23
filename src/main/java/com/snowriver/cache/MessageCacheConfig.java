package com.snowriver.cache;

import java.util.concurrent.TimeUnit;

/**
 * Todo:通过读取配置获得模型的redis server信息
 */
public class MessageCacheConfig {

    private Integer oneCacheLimit = 100;
    private Integer oneExpireAfterAccess = 5;
    private TimeUnit oneExpireTimeUnit = TimeUnit.MINUTES;

    private boolean useTwoLevel = false;
    private Integer twoExpireAfterAccess = 1;
    private TimeUnit twoExpireTimeUnit = TimeUnit.DAYS;
    private String redisTopic = "modelcache";
    private String redisHost = "localhost";
    private Integer redisPort = 6379;

    public MessageCacheConfig() {
    }

    public static MessageCacheConfig getDefault() {
        //TODO: 从配置中加载，目前赞时用代码写死
        MessageCacheConfig config = new MessageCacheConfig();
        return config;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(Integer redisPort) {
        this.redisPort = redisPort;
    }

    public Integer getOneCacheLimit() {
        return oneCacheLimit;
    }

    public void setOneCacheLimit(Integer oneCacheLimit) {
        this.oneCacheLimit = oneCacheLimit;
    }

    public Integer getOneExpireAfterAccess() {
        return oneExpireAfterAccess;
    }

    public void setOneExpireAfterAccess(Integer oneExpireAfterAccess) {
        this.oneExpireAfterAccess = oneExpireAfterAccess;
    }

    public TimeUnit getOneExpireTimeUnit() {
        return oneExpireTimeUnit;
    }

    public void setOneExpireTimeUnit(TimeUnit oneExpireTimeUnit) {
        this.oneExpireTimeUnit = oneExpireTimeUnit;
    }

    public boolean isUseTwoLevel() {
        return useTwoLevel;
    }

    public void setUseTwoLevel(boolean useTwoLevel) {
        this.useTwoLevel = useTwoLevel;
    }

    public Integer getTwoExpireAfterAccess() {
        return twoExpireAfterAccess;
    }

    public void setTwoExpireAfterAccess(Integer twoExpireAfterAccess) {
        this.twoExpireAfterAccess = twoExpireAfterAccess;
    }

    public TimeUnit getTwoExpireTimeUnit() {
        return twoExpireTimeUnit;
    }

    public void setTwoExpireTimeUnit(TimeUnit twoExpireTimeUnit) {
        this.twoExpireTimeUnit = twoExpireTimeUnit;
    }

    public String getRedisTopic() {
        return redisTopic;
    }

    public void setRedisTopic(String redisTopic) {
        this.redisTopic = redisTopic;
    }
}

package com.snowriver.cache;

import cn.hutool.core.lang.Singleton;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.MultiLevelCacheBuilder;
import com.alicp.jetcache.embedded.CaffeineCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import com.snowriver.push.MessagePushModel;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 消息推送本地缓存队列
 */
public class MessageCache {

    public Set<Long> messageKeySet = new HashSet();

    public static MessageCache instance() {
        return Singleton.get(MessageCache.class);
    }

    public Cache<Long, MessagePushModel> getCache() {
        if (this.cache == null) {
            synchronized (this) {
                if (this.cache == null) {
                    this.cache = builderCache();
                }
            }
        }
        return this.cache;
    }


    private MessageCacheConfig config;
    private Cache<Long, MessagePushModel> cache;

    private MessageCache() {
        this.config = MessageCacheConfig.getDefault();
    }

    public MessageCache(MessageCacheConfig config) {
        this.config = config;
    }


    private Cache<Long, MessagePushModel> builderMemoryCache() {
        Cache<Long, MessagePushModel> result = CaffeineCacheBuilder.createCaffeineCacheBuilder().limit(config.getOneCacheLimit())
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .expireAfterAccess(config.getOneExpireAfterAccess(), config.getOneExpireTimeUnit()).buildCache();
        return result;
    }

    private Cache<Long, MessagePushModel> builderRedisCache() {
        GenericObjectPoolConfig pc = new GenericObjectPoolConfig();
        pc.setMinIdle(2);
        pc.setMaxIdle(10);
        pc.setMaxTotal(10);
        MessageCacheConfig cfg = MessageCacheConfig.getDefault();
        JedisPool pool = new JedisPool(pc, cfg.getRedisHost(), cfg.getRedisPort());
        Cache<Long, MessagePushModel> result = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(JavaValueEncoder.INSTANCE)
                .valueDecoder(JavaValueDecoder.INSTANCE)
                .jedisPool(pool)
                .keyPrefix(config.getRedisTopic())
                .expireAfterWrite(config.getTwoExpireAfterAccess(), config.getTwoExpireTimeUnit())
                .buildCache();
        return result;
    }

    /**
     * create cache
     *
     * @return
     */
    protected Cache<Long, MessagePushModel> builderCache() {
        Cache<Long, MessagePushModel> cache = builderMemoryCache();

        if (config.isUseTwoLevel()) {
            Cache<Long, MessagePushModel> redisCache = builderRedisCache();

            cache = MultiLevelCacheBuilder.createMultiLevelCacheBuilder()
                    .addCache(cache, redisCache)
                    .buildCache();
        }
        return cache;
    }

}

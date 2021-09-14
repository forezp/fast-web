package io.github.forezp.fastwebadmin.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import io.github.forezp.fastwebcache.caffine.AbstractCaffineCache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserCaffineCache<SysUser> extends AbstractCaffineCache {
    @Override
    protected LoadingCache createLoadingCache() {
        LoadingCache loadingCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .initialCapacity(10)
                .maximumSize(99999999)
                .recordStats()
                .build(new CacheLoader<String, SysUser>() {
                    @Override
                    public SysUser load(String key) throws Exception {
                        return null;
                    }
                });
        return loadingCache;
    }
}

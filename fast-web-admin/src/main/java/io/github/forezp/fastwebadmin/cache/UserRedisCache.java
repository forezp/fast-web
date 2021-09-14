package io.github.forezp.fastwebadmin.cache;

import io.github.forezp.fastwebadmin.entity.SysUser;
import io.github.forezp.fastwebcache.redis.AbstratcRedisCache;
import org.springframework.stereotype.Component;


@Component
public class UserRedisCache extends AbstratcRedisCache {
    @Override
    public String getPrefixKey() {
        return "sysuser";
    }

    @Override
    public Class getTClass() {
        return SysUser.class;
    }
}

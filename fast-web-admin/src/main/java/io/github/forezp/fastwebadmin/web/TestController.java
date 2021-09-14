package io.github.forezp.fastwebadmin.web;

import io.github.forezp.fastwebadmin.cache.UserCaffineCache;
import io.github.forezp.fastwebadmin.cache.UserRedisCache;
import io.github.forezp.fastwebadmin.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @Autowired
    UserRedisCache userCache;

    @Autowired
    UserCaffineCache userCaffineCache;

    @GetMapping("/redis")
    public String test() {
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setUserId("yes");
        userCache.setById(String.valueOf(sysUser.getId()), sysUser);
        SysUser sysUserCache = (SysUser) userCache.get(String.valueOf(sysUser.getId()));
        log.info(sysUserCache.getUserId());
        return sysUserCache.getUserId();

    }

    @GetMapping("/caffine")
    public String test2() {
        SysUser sysUser = new SysUser();
        sysUser.setId(122L);
        sysUser.setUserId("caffine");
        userCaffineCache.put(String.valueOf(sysUser.getId()), sysUser);
        SysUser sysUserCache = (SysUser) userCaffineCache.get(String.valueOf(sysUser.getId()));
        log.info(sysUserCache.getUserId());
        return sysUserCache.getUserId();

    }
}

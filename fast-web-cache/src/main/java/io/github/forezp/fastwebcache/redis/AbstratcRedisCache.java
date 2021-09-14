package io.github.forezp.fastwebcache.redis;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by forezp on 2017/10/18.
 */

public abstract class AbstratcRedisCache<T> implements IBaseCache<T> {

    @Autowired
    RedisTemplateUtils redisTemplateUtils;

    public int defaultTimeout;

    public abstract String getPrefixKey();

    public abstract Class<T> getTClass();

    @Override
    public void setById(String id, T o) {
        String key = getPrefixKey() + id;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemplateUtils.setKey(key, JSON.toJSONString(o));
        }
    }

    @Override
    public void setById(String id, T o, int timeout) {
        String key = getPrefixKey() + id;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemplateUtils.setKey(key, JSON.toJSONString(o), timeout);
        }
    }

    @Override
    public void set(String key, T o) {
        key = getPrefixKey() + key;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemplateUtils.setKey(key, JSON.toJSONString(o));
        }
    }

    @Override
    public void set(String key, T o, int timeout) {
        key = getPrefixKey() + key;
        if (!ObjectUtils.isEmpty(o)) {
            redisTemplateUtils.setKey(key, JSON.toJSONString(o), timeout);
        }
    }

    @Override
    public T getById(String id) {
        String key = getPrefixKey() + id;
        String value = redisTemplateUtils.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            T o = JSON.parseObject(value, getTClass());
            return o;
        } else {
            return null;
        }
    }

    @Override
    public T get(String key) {
        key = getPrefixKey() + key;
        String value = redisTemplateUtils.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            T o = JSON.parseObject(value, getTClass());
            return o;
        } else {
            return null;
        }
    }

    @Override
    public void setList(List<T> list) {
        String key = getPrefixKey();
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemplateUtils.setKey(key, value);
        }


    }

    @Override
    public void setList(List<T> list, int timeout) {

        String key = getPrefixKey();
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemplateUtils.setKey(key, value, timeout);
        }
    }

    @Override
    public boolean hasList() {
        return redisTemplateUtils.hasKey(getPrefixKey());
    }

    @Override
    public List<T> getList() {
        String key = getPrefixKey();
        String value = redisTemplateUtils.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            List<T> list = JSON.parseArray(value, getTClass());
            return list;
        } else {
            return null;
        }

    }

    @Override
    public void setList(String key, List<T> list) {
        key = getPrefixKey() + key;
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemplateUtils.setKey(key, value);
        }

    }

    @Override
    public void setList(String key, List<T> list, int timeout) {
        key = getPrefixKey() + key;
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            redisTemplateUtils.setKey(key, value, timeout);
        }
    }

    @Override
    public boolean hasList(String key) {
        return redisTemplateUtils.hasKey(getPrefixKey() + key);
    }

    @Override
    public List<T> getList(String key) {
        key = getPrefixKey() + key;
        String value = redisTemplateUtils.getValue(key);
        if (StringUtils.isNotBlank(value)) {
            List<T> list = JSON.parseArray(value, getTClass());
            return list;
        } else {
            return null;
        }

    }

    @Override
    public boolean hasKeyById(String id) {
        String key = getPrefixKey() + id;
        return redisTemplateUtils.hasKey(key);
    }

    @Override
    public boolean hasKey(String key) {
        key = getPrefixKey() + key;
        return redisTemplateUtils.hasKey(key);
    }

    @Override
    public void deleteKeyById(String id) {
        String key = getPrefixKey() + id;
        redisTemplateUtils.deleteKey(key);

    }

    @Override
    public void deleteKey(String key) {
        key = getPrefixKey() + key;
        redisTemplateUtils.deleteKey(key);

    }

    @Override
    public void deleteList() {
        String key = getPrefixKey();
        redisTemplateUtils.deleteKey(key);

    }

    @Override
    public void deleteList(String key) {
        key = getPrefixKey() + key;
        redisTemplateUtils.deleteKey(key);

    }

    @Override
    public void deleteAllEntityKeys() {
        Set<String> set = redisTemplateUtils.getPatternKeys(getPrefixKey());
        set.stream().forEach(s -> redisTemplateUtils.deleteKey(s));
    }

}

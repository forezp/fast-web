package io.github.forezp.fastwebcommon.util;

import cn.hutool.core.util.StrUtil;
import io.github.forezp.fastwebcommon.exception.ErrorCode;
import io.github.forezp.fastwebcommon.exception.FastwebException;
import org.hashids.Hashids;

public class GenHashIdUtils {

    private static final String SALT = "three-kings@wenruo";
    private static final String ALPHABET = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static final Integer MIN_HASH_LENGTH = 10;
    private static final String SEPARATION = "-";

    private static final String NS_PREFIX = "ns";
    private static final String TRIGGER_PREFIX = "tgr";
    static Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH, ALPHABET);

    /**
     * 根据long 类型的id ,生成hash-id,比如ns-sx3dscr3ded这种类型的
     *
     * @param prefix 比如 ns app group
     * @param id     long 类型的id
     * @return 比如ns-sx3dscr3ded这种类型的
     */
    public static String genHashId(String prefix, Long id) {
        if (StrUtil.isBlank(prefix) || id == null) {
            throw new FastwebException(ErrorCode.GEN_HASH_ID_PARA_ERROR);
        }
        String hashid = hashids.encode(id);
        hashid = prefix + SEPARATION + hashid;
        return hashid;
    }


    public static String genTriggerHashId(Long id) {
        return genHashId(TRIGGER_PREFIX, id);
    }

    public static String genNsHashId(Long id) {
        return genHashId(NS_PREFIX, id);
    }


}

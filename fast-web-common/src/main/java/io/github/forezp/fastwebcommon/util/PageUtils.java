package io.github.forezp.fastwebcommon.util;



import io.github.forezp.fastwebcommon.exception.ErrorCode;
import io.github.forezp.fastwebcommon.exception.FastwebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PageUtils {

    private static Logger logger = LoggerFactory.getLogger(PageUtils.class);

    public static void check(int page, int pageSize) {
        if (!(page > 0) || !(pageSize > 0)) {
            logger.error("分页参数错误 page={} , pageSize={}", page, pageSize); //by cjn 2017/08/09
            throw new FastwebException(ErrorCode.ERROR_ARGS, "分页参数错误");
        }
    }
}

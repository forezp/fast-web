package io.github.forezp.fastwebadmin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.fastwebadmin.entity.SysUser;
import io.github.forezp.fastwebadmin.mapper.SysUserMapper;
import io.github.forezp.fastwebadmin.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.fastwebcommon.dto.PageResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Struct;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2021-09-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {



//    @Override
//    public Boolean addRecord(SysUser sysUser) {
//        return save(sysUser);
//
//    }
//
//    @Override
//    public Boolean updateRecord(SysUser sysUser) {
//        return updateById(sysUser);
//    }
//
//    @Override
//    public Boolean deleteRecord(SysUser sysUser) {
//        return removeById(sysUser);
//    }
//
//    @Override
//    public PageResultsDTO selectPage(int page, int pageSize, String userId, String mobile) {
//        Page<SysUser> sysDictPage = new Page<>(page, pageSize);
//        QueryWrapper wrapper = new QueryWrapper();
//        if (StrUtil.isNotBlank(userId)) {
//            wrapper.eq("user_id", userId);
//        }
//        if (StrUtil.isNotBlank(mobile)) {
//            wrapper.eq("mobile", mobile);
//        }
//        IPage<SysUser> sysLogIPage = page(sysDictPage, wrapper);
//        PageResultsDTO result = new PageResultsDTO(page, pageSize);
//        result.setTotalCount(sysLogIPage.getTotal());
//        result.setList(sysLogIPage.getRecords());
//        result.setTotalPage((int) sysLogIPage.getTotal(), pageSize);
//        return result;
//
//    }
}

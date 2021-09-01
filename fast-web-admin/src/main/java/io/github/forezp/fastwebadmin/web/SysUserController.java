package io.github.forezp.fastwebadmin.web;


import io.github.forezp.fastwebadmin.service.SysUserService;
import io.github.forezp.fastwebcommon.dto.RespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2021-09-01
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/list")
    public RespDTO getList() {
        return RespDTO.onSuc(sysUserService.list());
    }
}

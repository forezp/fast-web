package io.github.forezp.fastwebadmin.entity;


import io.github.forezp.fastwebmybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author forezp
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userId;

    /**
     * 密码，需加密存储
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 类型,1管理员 2.员工 3.普通用户
     */
    private Integer type;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 0停用1启用2锁定
     */
    private Boolean status;

    /**
     * 用户头像
     */
    private String avatar;

    private Integer sex;


}

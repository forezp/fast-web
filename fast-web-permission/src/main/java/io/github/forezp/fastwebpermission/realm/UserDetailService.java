package io.github.forezp.fastwebpermission.realm;

public interface UserDetailService {

    /**
     * 可以先根据token获取userid，然后再获取UserDetail
     *
     * @return
     */
    UserDetail getUserDetail();

    UserDetail getUserDetail(String userId);

}

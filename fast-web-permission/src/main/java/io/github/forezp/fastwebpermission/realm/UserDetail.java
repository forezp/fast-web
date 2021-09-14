package io.github.forezp.fastwebpermission.realm;

import java.util.List;

public interface UserDetail {

    String getCurrentUser();

    List<String> getUserRoles();

    List<String> getUserPermissions();
}

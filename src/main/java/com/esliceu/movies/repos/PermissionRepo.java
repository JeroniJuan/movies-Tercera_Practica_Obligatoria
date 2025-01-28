package com.esliceu.movies.repos;

import com.esliceu.movies.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer> {

    Permission findByPermissionName(Permission.permission_name permissionName);
}

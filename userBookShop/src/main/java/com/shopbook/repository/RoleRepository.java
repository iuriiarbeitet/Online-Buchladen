package com.shopbook.repository;


import com.shopbook.entity.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String name);
}

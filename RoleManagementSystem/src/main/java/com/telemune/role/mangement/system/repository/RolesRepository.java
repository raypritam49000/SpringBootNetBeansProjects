package com.telemune.role.mangement.system.repository;

import com.telemune.role.mangement.system.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	public Roles findByRoleId(Integer roleId);
	public void deleteByRoleId(Integer roleId);	
	@Query(value = "SELECT max(r.roleId) FROM Roles r")
	public int maxRoleId();
}
    


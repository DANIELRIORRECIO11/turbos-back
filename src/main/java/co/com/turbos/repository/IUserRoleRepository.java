package co.com.turbos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.turbos.entity.UserRoleEntityPK;
import co.com.turbos.entity.UsersRoleEntity;

public interface IUserRoleRepository extends JpaRepository<UsersRoleEntity, UserRoleEntityPK>{

}

package co.com.turbos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.turbos.entity.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, String>{

	Users findByUserName(String userName);
	
	Optional<List<Users>> findByUserNameOrEmail(String userName, String email);

}

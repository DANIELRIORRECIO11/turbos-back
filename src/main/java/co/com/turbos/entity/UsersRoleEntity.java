package co.com.turbos.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_roles")
@IdClass(value = UserRoleEntityPK.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersRoleEntity implements Serializable {
		
	@Id
	@Column(name = "role_id")
	private Long roleId;
	
	@Id
	@Column(name = "user_id")
	private String userId;
	
	private static final long serialVersionUID = 1L;
}

package co.com.turbos.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRoleEntityPK implements Serializable {	

	private Long roleId;

	private String userId;
	
	private static final long serialVersionUID = 1L;

}

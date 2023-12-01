package co.com.turbos.parser;

import co.com.turbos.entity.Users;
import co.com.turbos.request.UserRequest;

public class UserParser {
	
	public static UserRequest buildUser(Users entity) {	
		
		return UserRequest.builder()
				.userName(entity.getUserName())
				.enabled(entity.getEnabled())
				.name(entity.getName())
				.lastName(entity.getLastName())
				.email(entity.getEmail())
				.documentNumber(entity.getDocumentNumber())
				.gender(entity.getGender())
				.photo(entity.getPhoto())
				.role(RoleParser.buildRoleAndRequest(entity.getRole()))
				.build();
	}
	
	public static Users buildAddUser(UserRequest requestEvent) {	
		return Users.builder()
				.userName(requestEvent.getUserName())
				.enabled(requestEvent.getEnabled())
				.name(requestEvent.getName())
				.lastName(requestEvent.getLastName())
				.email(requestEvent.getEmail())
				.documentNumber(requestEvent.getDocumentNumber())
				.gender(requestEvent.getGender())
				.photo(requestEvent.getPhoto())
//				.role(requestEvent.getRole())
				.build();
	}

}

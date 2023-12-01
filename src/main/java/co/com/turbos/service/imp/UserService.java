package co.com.turbos.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.turbos.entity.Role;
import co.com.turbos.entity.Users;
import co.com.turbos.entity.UsersRoleEntity;
import co.com.turbos.parser.RoleParser;
import co.com.turbos.parser.UserParser;
import co.com.turbos.repository.IRoleRepository;
import co.com.turbos.repository.IUserRepository;
import co.com.turbos.repository.IUserRoleRepository;
import co.com.turbos.request.RoleRequest;
import co.com.turbos.request.UserRequest;
import co.com.turbos.response.CommandEvent;
import co.com.turbos.response.ResponseEvent;
import co.com.turbos.service.IUserService;

@Service
public class UserService implements IUserService, UserDetailsService {

	private Logger LOG = LoggerFactory.getLogger(UserService.class);

	private final IUserRepository usuarioRepository;

	private final IRoleRepository roleRepository;
	
	private final IUserRoleRepository userRoleRepository;

	@Autowired
	public UserService(IUserRepository usuarioRepository, IRoleRepository roleRepository, IUserRoleRepository userRoleRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users usuario = usuarioRepository.findByUserName(username);

		if (usuario == null) {
			LOG.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + username + "' en el sistema!");
		}

		List<GrantedAuthority> authorities = usuario.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> LOG.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUserName(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Users findByUsername(String username) {
		return usuarioRepository.findByUserName(username);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEvent<List<UserRequest>> getUsers() {
		try {
			List<Users> usersList = usuarioRepository.findAll();
			if (usersList.isEmpty() || Objects.isNull(usersList)) {
				return new ResponseEvent<List<UserRequest>>().notFound("No existen usuarios.");
			}

			List<UserRequest> requestList = new ArrayList<>();
			usersList.forEach((user) -> {
				requestList.add(UserParser.buildUser(user));
			});

			return new ResponseEvent<List<UserRequest>>().ok("Success", requestList);
		} catch (Exception e) {
			LOG.error("Ocurrio un error al obtener los usuarios: " + e);
			return new ResponseEvent<List<UserRequest>>().applicationError("Ocurrio un error al obtener los usuarios.");
		}
	}

	@Override
//	@Transactional(readOnly = false)
	public ResponseEvent<UserRequest> addUsers(CommandEvent<UserRequest> requestEvent) {

		if (Objects.isNull(requestEvent)) {
			return new ResponseEvent<UserRequest>().badRequest("Evento null al crear usuario.");
		}
		if (Objects.isNull(requestEvent.getRequest().getEmail())
				|| Objects.isNull(requestEvent.getRequest().getUserName())) {
			return new ResponseEvent<UserRequest>().badRequest("Correo o usuario vacio.");
		}

		if (validateUserExist(requestEvent.getRequest())) {
			return new ResponseEvent<UserRequest>().badRequest("El usuario ya existe.");
		}

		Users userNew = UserParser.buildAddUser(requestEvent.getRequest());
		usuarioRepository.save(userNew);
		
		
		
		UsersRoleEntity sd = UsersRoleEntity.builder()
				.roleId(requestEvent.getRequest().getRole().get(0).getId())
				.userId(requestEvent.getRequest().getUserName())
				.build();
		
		userRoleRepository.save(sd);
		
		
		
//		Users userCreated = usuarioRepository.findByUserName(userNew.getUserName());

//		Role rol = RoleParser.buildRole(requestEvent.getRequest().getRole());

//		roleRepository.saveAll(RoleParser.buildRole(requestEvent.getRequest().getRole(), userNew.getUserName()));

//		UserRequest request = UserParser.buildUser(usuarioRepository.save(userNew));

		return new ResponseEvent<UserRequest>().created("Success", null);
	}

	private Boolean validateUserExist(UserRequest userRequest) {
		Optional<List<Users>> existUser = usuarioRepository.findByUserNameOrEmail(userRequest.getUserName(),
				userRequest.getEmail());

//		if (existUser.isPresent() || Objects.nonNull(existUser)) {
//			return Boolean.TRUE;
//		}
		return Boolean.FALSE;
	}

}

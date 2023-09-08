package com.adminportal.entity.security;


import com.adminportal.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@Table(name="user_role")
public class UserRole implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userRoleId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_id")
	private Role role;

	public UserRole(){}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}


	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	@Override
	public String getAuthority() {
		return role.getName();
	}
}

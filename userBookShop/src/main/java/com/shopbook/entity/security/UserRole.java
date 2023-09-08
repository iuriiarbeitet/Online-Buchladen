package com.shopbook.entity.security;


import com.shopbook.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Setter
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

	@Override
	public String getAuthority() {
		return role.getName();
	}
}

package com.sms.teacher.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sms.teacher.to.UserInfo;



public class TeacherUserDeatils implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2826279721276013123L;

	private UserInfo usr;

	private List<GrantedAuthority> roles;

	public TeacherUserDeatils(UserInfo userInfo) {
		super();
		this.usr = userInfo;
		this.roles = Arrays.stream(userInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return usr.getPassword();
	}

	@Override
	public String getUsername() {
		return usr.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

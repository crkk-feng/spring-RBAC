package com.relaper.cr.springrbac.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.relaper.cr.springrbac.entity.MyUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cr
 * @date 2023/7/11
 */
@Data
@AllArgsConstructor
public class JwtUserDto implements UserDetails {

    /**
     * 用户数据
     */
    private MyUser myUser;
    /**
     * 用户权限的集合
     */
    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public List<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }


    /**
     * 加密后的密码
     * @return
     */
    @Override
    public String getPassword() {
        return myUser.getPassword();
    }


    /**
     * 用户名
     * @return
     */
    @Override
    public String getUsername() {
        return myUser.getUserName();
    }


    /**
     * 是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * 凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return myUser.getStatus() == 1 ? true : false;
    }

}
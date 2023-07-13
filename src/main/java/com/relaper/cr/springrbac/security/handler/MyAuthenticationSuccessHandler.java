package com.relaper.cr.springrbac.security.handler;

import com.alibaba.fastjson.JSON;
import com.relaper.cr.springrbac.security.utils.JwtUtils;
import com.relaper.cr.springrbac.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Cr
 * @createTime 2023/7/11
 * 登录成功
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // JwtUserDto userDetails = (JwtUserDto)authentication.getPrincipal();//拿到登录用户信息
        // String jwtToken = jwtUtils.generateToken(userDetails.getUsername());//生成token
        Result result = Result.ok().message("登录成功");
        //修改编码格式
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");
        //输出结果
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
        // httpServletResponse.sendRedirect("/api/menu");//重定向到api/admin页面
        // httpServletResponse.setStatus(302);
        // httpServletResponse.setHeader("Authorization", "Bearer "+jwtToken);
    }
}

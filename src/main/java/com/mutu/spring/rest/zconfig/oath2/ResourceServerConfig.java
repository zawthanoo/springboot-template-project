package com.mutu.spring.rest.zconfig.oath2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @author Zaw Than Oo
 * @since 01-DEC-2018 <br/>
 *        This class is used to configure how the resource owner/user access the
 *        resources/api.<br/>
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().authorizeRequests()
		.antMatchers("/user/**").access("hasAuthority('ADMIN')").antMatchers("/employee/**")
		.access("hasAuthority('USER') OR hasAuthority('ADMIN')").and().exceptionHandling()
		.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}

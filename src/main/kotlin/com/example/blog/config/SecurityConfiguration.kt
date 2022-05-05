package com.example.blog.config

import com.example.blog.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter(){
    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
            .and().csrf().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(service)?.passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder():PasswordEncoder = BCryptPasswordEncoder()

    @Autowired
    private lateinit var service: IUserService
}
package com.martin.cms;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.config.WebIniSecurityManagerFactory;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(MyController.class);
        register(GeneralExceptionMapper.class);
   //     register(BasicHttpAuthenticationFilter.class);
        // packages("com.martin.cms");
        // register(LoggingFilter.class);
    }


    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager() {
        /* Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
         return securityManager;*/
 
        WebIniSecurityManagerFactory securityManagerFactory = new WebIniSecurityManagerFactory();
        SecurityManager securityManager= securityManagerFactory.getInstance();
        return securityManager;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor configLifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    } 
  
    /**
     * Enable Shiro Annotations for Spring-configured beans.
     * Only run after the lifecycleBeanProcessor has run.
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator configDefaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor configAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }
    
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(getSecurityManager()); // referring to previous
        
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/index");
        shiroFilter.setUnauthorizedUrl("/forbidden");
        Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
        filterChainDefinitionMapping.put("/**", "authcBasic");
//        filterChainDefinitionMapping.put("/home", "authc,roles[guest]");
//        filterChainDefinitionMapping.put("/admin", "authc,roles[admin]");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
       
        Map<String, Filter> filters = new HashMap<String, Filter>();
         BasicHttpAuthenticationFilter filter=new BasicHttpAuthenticationFilter();
        filters.put("authcBasic", filter);
//        filters.put("anon", new AnonymousFilter());
//        filters.put("authc", new FormAuthenticationFilter());
//        filters.put("logout", new LogoutFilter());
        filters.put("roles", new RolesAuthorizationFilter());
//        filters.put("user", new UserFilter());
        shiroFilter.setFilters(filters);
        System.out.println(shiroFilter.getFilters().size());
        return shiroFilter;
    }
}

package com.martin.shirotest4;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.glassfish.jersey.server.ResourceConfig;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        register(TestController.class);
        register(GeneralExceptionMapper.class);
    }
    

    @Bean
    public Realm realm() {
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setAuthenticationQuery("SELECT hashpwd as password,salt FROM sec_user WHERE user_name = ?");
        jdbcRealm.setUserRolesQuery("SELECT rolename FROM t_role r JOIN t_user_role m ON r.id=m.role_id " + 
                " JOIN sec_user u ON u.user_id=m.user_id " + 
                "where u.user_name= ?");
        
        jdbcRealm.setPermissionsQuery("SELECT permissions FROM t_permission p JOIN t_role t ON p.role_id=t.id " + 
                "WHERE t.rolename= ?");
        @SuppressWarnings("deprecation")
        PGPoolingDataSource datasource=new PGPoolingDataSource();
        datasource.setUrl("jdbc:postgresql://10.222.41.241:5432/MartinDemo?ssl=true&user=postgres&password=IGTtest1");
        jdbcRealm.setDataSource(datasource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setCachingEnabled(false);
        jdbcRealm.setSaltStyle(SaltStyle.COLUMN);
        jdbcRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        return jdbcRealm;
    }

    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher(){  
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
       hashedCredentialsMatcher.setHashAlgorithmName("MD5"); //MD2、MD5、SHA1、SHA256、SHA384、SHA512
       hashedCredentialsMatcher.setHashIterations(1);
       hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
       return hashedCredentialsMatcher;  
    }  
    
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/**", "authcBasic");
        return chainDefinition;
    }
    
}

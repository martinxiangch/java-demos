package com.martin.shirotest4;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.glassfish.jersey.server.ResourceConfig;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;


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
        jdbcRealm.setCachingEnabled(true);
        jdbcRealm.setCacheManager(cacheManager());
        jdbcRealm.setSaltStyle(SaltStyle.COLUMN);
        jdbcRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        return jdbcRealm;
    }
    
    @Bean
    public Realm realm1() {
        // use define account
        SimpleAccountRealm realm=new SimpleAccountRealm();
        realm.addAccount("test1", "123456", "admin","officer");
        realm.addAccount("test2", "123456", "admin","officer1");
        return realm;
    }

    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher(){  
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
       hashedCredentialsMatcher.setHashAlgorithmName("MD5"); //MD2、MD5、SHA1、SHA256、SHA384、SHA512
       hashedCredentialsMatcher.setHashIterations(1);
       hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
       return hashedCredentialsMatcher;  
    }  
    
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinition("/**", "authcBasic");
//        return chainDefinition;
//    }
    
     @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager  securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        Map<String, Filter> filters = new LinkedHashMap<>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
        filterChainDefinitionManager.put("/**", "authcBasic");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }
    
     @Bean
     public CacheManager cacheManager() {
         return new RedisCacheManager();
     }
     
     @Bean
     public SessionDAO sessionDao() {
         return new RedisSessionDao();
        
     }
     
     @Bean  
     public DefaultWebSessionManager sessionManager(){  
         DefaultWebSessionManager manager = new DefaultWebSessionManager();  
         manager.setCacheManager(cacheManager());
         manager.setSessionDAO(sessionDao());  
         manager.setDeleteInvalidSessions(true);  
       //  manager.setGlobalSessionTimeout(sessionDao.getExpireTime());
         manager.setSessionValidationSchedulerEnabled(true);
           
         return manager;  
     }  
     
     @Bean(name = "redisTemplate")
     public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
         RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
         redisTemplate.setConnectionFactory(factory);
      /*   Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
         ObjectMapper om = new ObjectMapper();
         om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         jackson2JsonRedisSerializer.setObjectMapper(om);
         redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);*/
         redisTemplate.setValueSerializer(new RedisSerializer<Object>() {
             @Override
             public byte[] serialize(Object object) throws SerializationException {
                 if (object == null) {
                     return new byte[0];
                 }
                 if (!(object instanceof Serializable)) {
                     throw new IllegalArgumentException("RedisSerializer.serialize requires a Serializable payload "
                             + "but received an object of type [" + object.getClass().getName() + "]");
                 }
                 return SerializationUtils.serialize((Serializable) object);
             }
             @Override
             public Object deserialize(byte[] bytes) throws SerializationException {
                 if (bytes == null || bytes.length == 0) {
                     return null;
                 }
                 return SerializationUtils.deserialize(bytes);
             }
         });
         redisTemplate.afterPropertiesSet();
         return redisTemplate;
     }
     
     @Bean(name = "connectionFactory")
     public RedisConnectionFactory connectionFactory() {
         JedisConnectionFactory conn = new JedisConnectionFactory();
         //conn.setDatabase("");
         conn.setHostName("10.222.41.241");
         conn.setPassword("IGTtest1");
         conn.setPort(6379);
         conn.setTimeout(2000);
         return conn;
     }
}

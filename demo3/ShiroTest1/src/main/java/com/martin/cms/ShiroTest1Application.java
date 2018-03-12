package com.martin.cms;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShiroTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(ShiroTest1Application.class, args);
	}
	
	@Bean
    public Realm realm() {

	    /*uses 'classpath:shiro-users.properties' by default
        // uses 'classpath:shiro-users.properties' by default
        PropertiesRealm realm = new PropertiesRealm();

        // Caching isn't needed in this example, but we can still turn it on
        realm.setCachingEnabled(false);
        return realm;
        */
	    
	    // use define account
	    SimpleAccountRealm realm=new SimpleAccountRealm();
	    realm.addAccount("root", "123456", "admin1","officer");
	    realm.addAccount("martin", "123456", "admin1","officer1");
	    return realm;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // use permissive to NOT require authentication, our controller Annotations will decide that
        chainDefinition.addPathDefinition("/**", "authcBasic");
        return chainDefinition;
    }
}

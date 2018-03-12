package com.martin.Sdemo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.martin.Bean.IPersonBean;
import com.martin.Bean.SingleEntity;
import com.martin.SimpleBean.SPerson;
import com.martin.zookeeper.ZKeeperAdaptor;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.martin.Bean","com.martin.SimpleBean,com.martin.zookeeper"})
public class ShiroDemoApplication implements CommandLineRunner{

    @Autowired
    @Qualifier("teacher")
    private IPersonBean teacher;
    
    @Autowired
    @Qualifier("student")
    private IPersonBean student;
    
    @Autowired
    private SPerson sPerson;
    
    public static void main(String[] args) {
        SpringApplication.run(ShiroDemoApplication.class, args);
    }

    /**
     * Create security manager option1
     * 
     * */
    public void securityManager() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Martin", "IGTtest1");
        try {
            // 4、登录，即身份验证
            subject.login(token);
            System.out.println("login success!");
        } catch (AuthenticationException e) {
           e.printStackTrace();
        }
        // Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        // 6、退出
     //  
       // subject.getPreviousPrincipals();
        Object principal=subject.getPrincipal();
        UserInfo userInfo=(UserInfo)principal;
        System.out.println("UserInfo:"+ userInfo.toString());
        subject.logout();
    }

    @Override
    public void run(String... arg0) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("begin to run console");
        calcPWD();
        TestZooKeeper();
        sPerson.getName();
        teacher.ShowName();
        student.ShowName();
        CreateToken();
        securityManager();
        

        /*
        SecurityManager securityManager=getSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("martin", "123");
        try {
            // 4、登录，即身份验证
            subject.login(token);
            System.out.println("login success!");
        } catch (AuthenticationException e) {
           e.printStackTrace();
        }
        // Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        // 6、退出
        subject.logout();
        */
        System.out.println("complete run console");
      //  System.exit(0);
    }
    
    private void calcPWD() {
        String str = "123456";  
        String salt = "4805C55E-1AB7-4D77-B1F6-5198375808D1";  
        String md5 = new Md5Hash(str).toString();
        System.out.println("PWD:"+md5);
        
        String algorithmName = "md5";  

        int hashIterations = 1;  
          
        SimpleHash hash = new SimpleHash(algorithmName, str, salt, hashIterations);  
        String encodedPassword = hash.toHex();  
        System.out.println("PWD1:"+encodedPassword);
    }
    
//    @Bean
//    public com.martin.Sdemo.UserRealm userRealm(){
//        com.martin.Sdemo.UserRealm myrealm = new com.martin.Sdemo.UserRealm();
//        return myrealm;
//    }
    
//    
//    @Bean
//    public SecurityManager getSecurityManager() {
//        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
//        defaultSecurityManager.setRealm(userRealm());
//        System.out.println("create security manager");
//        return defaultSecurityManager;
//        
//    }
    
    
    @Bean
    public SingleEntity getSingleEntity() {
        return new SingleEntity();
    }
    
    private void CreateToken() {
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token="";
        String id=UUID.randomUUID().toString();
        try {
            token = JWT.create()
                    .withHeader(map)
                    .withClaim("name", "Martin")
                    .withClaim("age", "17")
                    .withJWTId(id)
                    .withClaim("id", id)
                    .sign(Algorithm.HMAC256("secret"));
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JWTCreationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Token:"+token);
         
        JWT jwt= JWT.decode(token);
        System.out.println("Dname:"+jwt.getClaim("name").asString()+";age"+ jwt.getClaim("age").asString()+";header:"+jwt.getContentType());
        System.out.println("ID:"+jwt.getId() +"; ClaimID:"+jwt.getClaim("id").asString());
        try {
            verifyJWTToken(token,"secret");
            verifyJWTToken(token,"secret1");
        } catch (JWTVerificationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public  void verifyJWTToken(String token,String secret) 
            throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException 
    {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Verification v = JWT.require(algorithm);
        JWTVerifier ver= v.build();
        DecodedJWT jwt =  ver.verify(token);
        
    }

    
    @Bean
    public ScheduleDemo getSchedule() {
       Log("begin to init schedule");
       return new ScheduleDemo();
    }
    
    public static void Log(String msg) {
        System.out.println(msg);
    }

    @Autowired
    private ZKeeperAdaptor adaptor;
    
    private void TestZooKeeper() {
      //  ZKeeperAdaptor adaptor=new ZKeeperAdaptor();
        final String znode="/Martin";
        try {
            adaptor.ConnectSupport();
            Stat stat=  adaptor.znode_exists(znode);
            if(stat==null){
                adaptor.Create(znode, "This is a martin demo");
            }
            stat=  adaptor.znode_exists(znode);
            if(stat!=null)
            {
                Log("Exist znode,version="+stat.getVersion());
            }
            else {
                Log("Not exist znode");
            }
            
          String data=  adaptor.getData(znode);
          Log("Receive data:"+data);
          
          for(int i=0;i<3;i++)
          {
              adaptor.setData(znode, "This is a new martin data "+UUID.randomUUID().toString());
              Log("Receive new data:"+ adaptor.getData(znode));
          }
          
          
          
         adaptor.deleteZnode(znode);
          
          stat=adaptor.znode_exists(znode);
          if(stat==null)
          {
              Log("Znode is not exist");
          } 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

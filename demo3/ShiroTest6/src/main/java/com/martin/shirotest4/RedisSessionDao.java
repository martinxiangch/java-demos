package com.martin.shirotest4;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSessionDao extends AbstractSessionDAO{

    private long expireTime=60000; //60seconds
    
    @Autowired 
    private RedisTemplate redisTemplate;
    
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisSessionDao() {  
        super();  
        
    }  
    
    public RedisSessionDao(long expireTime, RedisTemplate redisTemplate) {  
        super();  
        this.expireTime = expireTime;  
        this.redisTemplate = redisTemplate;  
    }  
    
    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("===============update================");  
        if (session == null || session.getId() == null) {  
            return;  
        }  
        session.setTimeout(expireTime);  
        redisTemplate.opsForValue().set(session.getId(), session, expireTime, TimeUnit.MILLISECONDS);  
    }

    @Override
    public void delete(Session session) {
        System.out.println("===============delete================");  
        if (null == session) {  
            return;  
        }  
        
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        System.out.println("===============getActiveSessions================");  
        return redisTemplate.keys("*");
    }

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("===============doCreate================");  
        Serializable sessionId=this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(session.getId(), session,expireTime,TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("===============doReadSession================");  
        if(sessionId==null) {
            return null;
        }
        return (Session)redisTemplate.opsForValue().get(sessionId);
    }

}

package com.martin.zookeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.martin.Sdemo.ShiroDemoApplication;

@Service
public class ZKeeperAdaptor implements Watcher {
    
    @Value("${zookeeper.host:192.168.211.129}")
    private String zooHost;
    
    @Value("${zookeeper.port:5000}")
    private int port;
    
    private int sessionTimeout=5000;
    
    private ZooKeeper zooKeeper;
    final CountDownLatch countDownLatch=new CountDownLatch(1);
    
    public void ConnectSupport() throws Exception {
        zooKeeper=new ZooKeeper(zooHost, sessionTimeout, this);
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent we) {
        // TODO Auto-generated method stub
        if (we.getState() ==KeeperState.SyncConnected) {
            countDownLatch.countDown();
         }
        String log="Path:"+ we.getPath()+",State:"+we.getState()+",WE:"+we.toString();
        ShiroDemoApplication.Log(log);
    }
    
    public void Close() throws InterruptedException {
        zooKeeper.close();
    }
    
    
    public void Create(String path, String data) throws KeeperException, InterruptedException {
        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);
    }
    
    public Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, this);
    }
    
    public String getData(String path) throws KeeperException, InterruptedException, UnsupportedEncodingException {
       byte[] bn= zooKeeper.getData(path, this, null);
       String data=new String(bn, "UTF-8");
       return data;
    }
    
    public void setData(String path, String data) throws KeeperException, InterruptedException {
        zooKeeper.setData(path, data.getBytes(), -1);
    }
    
    public void deleteZnode(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path,-1);
    } 

}

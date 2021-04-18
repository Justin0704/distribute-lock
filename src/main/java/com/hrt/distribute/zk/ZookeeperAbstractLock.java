package com.hrt.distribute.zk;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements Lock{

    //zk链接地址
    private static final String CONNECT_STRING = "127.0.0.1:2181";
    //创建zk链接
    protected ZkClient zkClient = new ZkClient(CONNECT_STRING);
    protected static final String PATH = "/lock";

    public void getLock() {
        if(tryLock()){
            System.out.println("获取所资源");
        }else{
            waitLock();
            getLock();
        }
    }

    //获取所资源
    abstract boolean tryLock();
    //等待
    abstract boolean waitLock();
}

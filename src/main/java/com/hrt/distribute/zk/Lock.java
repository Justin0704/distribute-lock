package com.hrt.distribute.zk;

public interface Lock {
    //获取锁资源
    void getLock();
    //释放锁
    void unLock();
}

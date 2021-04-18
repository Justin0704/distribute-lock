package com.hrt.distribute.zk;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistributeLock extends ZookeeperAbstractLock {

    private CountDownLatch countDownLatch = null;

    @Override
    boolean tryLock() {
        try{
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    boolean waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                //唤醒被等待的线程
                if(countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        //注册事件
        zkClient.subscribeDataChanges(PATH, iZkDataListener);
        if(zkClient.exists(PATH)){
            countDownLatch = new CountDownLatch(1);
            try{
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public void unLock() {
        if(zkClient != null){
            zkClient.delete(PATH);
            zkClient.close();
            System.out.println("释放所资源");
        }
    }


}

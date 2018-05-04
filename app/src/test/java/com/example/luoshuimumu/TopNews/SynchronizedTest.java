package com.example.luoshuimumu.TopNews;

/**
 * Created by luoshuimumu on 2018/4/26.
 */


public class SynchronizedTest {
    //1.synchronized
    //1.1

    private byte[] lock = new byte[0];

    public void syncInMethod() {
        synchronized (lock) {
            //do sth
        }
    }

    class SyncThread extends Thread {
        private int count = 0;

        public SyncThread(String name) {
            super(name);
        }


        @Override
        public synchronized void run() {
            super.run();
            for (int i = 0; i < 5; i++) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {

                }
                System.out.println("count:" + count++ + ",thread:" + getName());
            }

        }
    }

    @org.junit.Test
    public void testSyncThread() {
        Thread thread1 = new SyncThread("thread1");
        Thread thread2 = new SyncThread("thread2");
        thread1.start();
        thread2.start();
    }

    Thread thread;

    {
        //主线程
        boolean runnable = true;
        synchronized (thread) {
            try {
                thread.start();
                while (!runnable) {
                    thread.wait();
                }
                thread.wait();
            } catch (InterruptedException e) {
            }
        }
        //thread线程

        synchronized (this) {
            while (runnable) {
                thread.notify();
            }
        }

    }

}

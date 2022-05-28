package Synchronization;

import java.util.concurrent.Semaphore;

public class ReaderWriter {
    private static int read_count = 0;
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore rw_mutex = new Semaphore(1);

    private static class Writer extends Thread {
        public void run() {
            try {
                while(true) {
                    rw_mutex.acquire();
                    System.out.println("Thread: " + Thread.currentThread().getName() + " ID: "
                            + Thread.currentThread().getId() + " is writing");
                    rw_mutex.release();
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    private static class Reader extends Thread {
        public void run() {
            try {
                mutex.acquire();
                read_count++;
                if(read_count == 1)
                    rw_mutex.acquire();
                mutex.release();

                System.out.println("Thread: " + Thread.currentThread().getName() + " ID: "
                        + Thread.currentThread().getId() + " is writing");

                mutex.acquire();
                read_count--;
                if(read_count == 0)
                    rw_mutex.release();
                mutex.release();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Reader read = new Reader();
        Writer write = new Writer();

        Thread t1 = new Thread(write);
        t1.setName("Thread1");

        Thread t2 = new Thread(write);
        t2.setName("Thread2");

        Thread t3 = new Thread(read);
        t3.setName("Thread3");

        Thread t4 = new Thread(read);
        t4.setName("Thread4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
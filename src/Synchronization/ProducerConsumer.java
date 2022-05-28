package Synchronization;

import java.util.concurrent.Semaphore;

public class ProducerConsumer<E>
{
    Semaphore semaphore = new Semaphore(5);

    private int count, in, out, SIZE;
    private E[] buffer;

    public ProducerConsumer() {
        count = 0;
        in = 0;
        out = 0;
        buffer = (E[]) new Object[semaphore.availablePermits()];
        SIZE = semaphore.availablePermits();
    }

    public void insert(E item) {
        buffer[in] = item;
        in = (in + 1) % SIZE;
        while (count >= SIZE) {
            try {
                semaphore.acquire();
            }
            catch (InterruptedException ie) { }
        }
        count++;

        semaphore.release();
        System.out.println("Producer: " + item + " - Counter: " + count);
    }

    public E remove() {
        E item;

        item = buffer[out];
        out = (out + 1) % SIZE;
        while (count == 0) {
            try {
                semaphore.acquire();
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        count--;

        semaphore.release();

        System.out.println("Consumer: " + item + " - Counter: " + count);

        return item;
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    double x = Math.random();
                    pc.insert(x);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    pc.remove();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
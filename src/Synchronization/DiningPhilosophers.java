package Synchronization;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    private static final int PHILOSOPHERS = 5;

    public static void main(String[] args) {
        Semaphore[] chopsticks = new Semaphore[PHILOSOPHERS];

        for (int i = 0; i < PHILOSOPHERS; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[PHILOSOPHERS];

        for (int i = 0; i < PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % PHILOSOPHERS]);
            new Thread(philosophers[i]).start();
        }

    }

}

class Philosopher extends Thread {
    private Random randomTime = new Random();

    private int id;

    private Semaphore right;
    private Semaphore rightChopstick;

    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.id = id;
        this.right = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run() {
        try {
            while (true) {
                think();
                pickUpLeft();
                pickUpRight();
                eat();
                putDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep(randomTime.nextInt(10));
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking  ");
        Thread.sleep(randomTime.nextInt(10));
    }

    private void pickUpLeft() throws InterruptedException{
        right.acquire();
        System.out.println("Philosopher " + id + " pick up left chopstick");
    }

    private void pickUpRight()  throws InterruptedException{
        rightChopstick.acquire();
        System.out.println("Philosopher " + id + " pick up right chopstick");
    }

    private void putDown() {
        right.release();
        System.out.println("Philosoper " + id + " put down left chopstick");
        rightChopstick.release();
        System.out.println("Philosoper " + id + " put down right chopstick");
    }

}
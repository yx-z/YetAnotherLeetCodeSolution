package citadel;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurQueueSem {
    private int[] data;
    private int size = 0;
    private Lock lock = new ReentrantLock();
    private Semaphore semSize = new Semaphore(0);
    private Semaphore semRem;

    public ConcurQueueSem(int capacity) {
        data = new int[capacity];
        semRem = new Semaphore(capacity);
    }

    public void add(int i) throws InterruptedException {
        semRem.acquire();

        lock.lock();
        data[size] = i;
        size++;
        lock.unlock();

        semSize.release();
    }

    public int remove() throws InterruptedException {
        semSize.acquire();

        lock.lock();
        size--;
        int num = data[size];
        lock.unlock();

        semRem.release();
        return num;
    }
}

class ConcurQueueCondVar {
    private int[] data;
    private int size = 0;
    private Lock lock = new ReentrantLock();
    private Condition sizeCond = lock.newCondition();
    private Condition remCond = lock.newCondition();

    public ConcurQueueCondVar(int capacity) {
        data = new int[capacity];
    }

    public void add(int i) throws InterruptedException {
        lock.lock();
        while (size == data.length) {
            remCond.wait();
        }

        data[size] = i;
        size++;

        if (size == 1) {
            sizeCond.notify();
        }
        lock.unlock();
    }

    public int remove() throws InterruptedException {
        lock.lock();
        while (size == 0) {
            sizeCond.wait();
        }

        size--;
        int num = data[size];
        if (size == data.length - 1) {
            remCond.notify();
        }

        return num;
    }
}

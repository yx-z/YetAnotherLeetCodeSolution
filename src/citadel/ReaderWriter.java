package citadel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReaderWriter {
    private int readers = 0;
    private int writers = 0;
    private boolean writing = false;
    private String data = null;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public String read() throws InterruptedException {
        lock.lock();
        while (writers > 0) {
            cond.await();
        }
        readers++;
        lock.unlock();

        // read data
        String data = this.data;

        lock.lock();
        readers--;
        if (writers > 0) {
            cond.signal();
        }
        lock.unlock();
        return data;
    }

    public void write(String data) throws InterruptedException {
        lock.lock();
        writers++;
        while (readers > 0 || writing) {
            cond.await();
        }
        writing = true;
        lock.unlock();

        // write data
        this.data = data;

        lock.lock();
        writing = false;
        writers--;
        if (writers > 0 || readers == 1) {
            cond.signal();
        } else if (readers > 1) {
            cond.signalAll();
        }
        lock.unlock();
    }
}

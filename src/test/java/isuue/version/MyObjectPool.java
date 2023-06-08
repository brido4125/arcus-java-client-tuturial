package isuue.version;

import java.util.List;

public class MyObjectPool {

    private int size;
    private List<MyObject> pool = new java.util.ArrayList<>();

    public MyObjectPool(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            pool.add(new MyObject());
        }
    }

    public List<MyObject> getPool() {
        return pool;
    }
}

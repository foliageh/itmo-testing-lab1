package task2;

public class HashTable {
    private final int capacity;
    private final Integer[] table;
    private final boolean[] isDeleted;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Integer[capacity];
        this.isDeleted = new boolean[capacity];
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void insert(int key) {
        int index = hash(key);
        int startIndex = index;
        do {
            if (table[index] == null || isDeleted[index]) {
                table[index] = key;
                isDeleted[index] = false;
                return;
            }
            //if (table[index] == key)
            //    return;
            index = (index + 1) % capacity;
        } while (index != startIndex);

        throw new IllegalStateException("Хеш-таблица переполнена");
    }

    public boolean contains(int key) {
        int index = hash(key);
        int startIndex = index;
        do {
            if (table[index] != null && table[index] == key && !isDeleted[index])
                return true;
            index = (index + 1) % capacity;
        } while (index != startIndex && table[index] != null);
        return false;
    }

    public void delete(int key) {
        int index = hash(key);
        int startIndex = index;
        do {
            if (table[index] != null && table[index] == key && !isDeleted[index]) {
                isDeleted[index] = true;
                return;
            }
            index = (index + 1) % capacity;
        } while (index != startIndex && table[index] != null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append(i).append("[");
            if (table[i] != null)
                sb.append(isDeleted[i] ? "~"+table[i]+"~" : table[i]);
            sb.append("] ");
        }
        return sb.toString();
    }
}


package com.test.hashmap;

public class MyHashMap<K, V> {

    // 初始化容器大小,必须是2的幂次方，2^4 = 16
    private static int DEFAULT_CAPACITY = 1 << 4;
    private static double A = (Math.pow(5, 0.5) - 1) / 2;

    // 容器
    private int capacity;

    // 大小
    private int size = 0;

    // 节点
    private Node<K, V>[] buckets;

    /**
     * 构造器
      */
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 构造器
     * @param capacity
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {

        // 判断容器是否大于0
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity can not be negative or zero");
        }

        // 保证 capacity 是2的n次方
        int temp = 1;
        while (temp < capacity) {
            temp <<= 2;
        }
        this.capacity = temp;

        buckets = new Node[this.capacity];
    }

    /**
     * 插入
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        int position = index(key);

        Node<K, V> node = new Node<K, V>(key, value);

        if (buckets[position] != null) {
            node.setNext(buckets[position]);
        }

        buckets[position] = node;
        size++;
    }

    /**
     * put方法
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        int position = index(key);

        Node<K, V> node = buckets[position];

        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }

            node = node.next;
        }

        Node<K, V> newNode = new Node<K, V>(key, value);
        if (buckets[position] != null) {
            newNode.setNext(buckets[position]);
        }

        buckets[position] = newNode;
        size++;
    }

    /**
     * 删除
     *
     * @param key
     */
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        int position = index(key);
        Node<K, V> node = buckets[position];

        if (node == null) {
            return;
        }

        if (node.key.equals(key)) {
            buckets[position] = node.next;
            size--;
        }

        while (node.next != null) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                size--;
                break;
            }

            node = node.next;
        }
    }

    /**
     * 通过key值获取Value值
     *
     * @param key
     * @return
     */
    public V search(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }

        int position = index(key);
        Node<K, V> node = buckets[position];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }

            node = node.next;
        }

        return null;
    }

    /**
     * 获取集合长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");

        for (int i = 0; i < capacity; i++) {
            Node<K, V> node = buckets[i];
            while (node != null) {
                buffer.append(node.key + ":" + node.value + ", ");
                node = node.next;
            }
        }

        if (buffer.length() > 1) {
            buffer.delete(buffer.length() - 2, buffer.length());
        }

        buffer.append("}");

        return buffer.toString();
    }

    /**
     * 根据传入的key获取index
     * @param key
     * @return
     */
    private int index(K key) {
        int hashCode = key.hashCode();

        double temp = hashCode * A;
        double digit = temp - Math.floor(temp);

        return (int) Math.floor(digit * capacity);
    }

    /**
     * 节点静态内部类
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<String, String>();
        map.put("001", "James");
        map.put("002", "Antony");
        map.put("003", "Bosh");
        map.put("004", "Wade");
        map.put("004", "WestBrook");

        map.insert("005","------------------");

        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.search("004"));
    }
}
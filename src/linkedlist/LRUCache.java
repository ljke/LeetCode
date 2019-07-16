package linkedlist;

import java.util.HashMap;

/**
 * 146. LRU缓存机制
 * https://leetcode-cn.com/problems/lru-cache/
 * 使用一个双向链表和一个HashMap
 * HashMap保存每个节点的地址，可以基本保证在 O(1) 时间内查找节点
 * 双向链表能后在 O(1) 时间内添加和删除节点，单链表则不行
 */
class LRUCache {
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private final HashMap<Integer, Node> map; //哈希表保存所有结点
    //记录头尾结点
    private Node head;
    private Node end;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            //最近访问：将访问结点移到头部
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { //存在则更新
            Node old = map.get(key);
            old.value = value;
            //最近访问：将访问结点移到头部
            remove(old);
            setHead(old);
        } else { //不存在则添加
            Node n = new Node(key, value);
            if (map.size() >= capacity) { //添加时注意超出边界的问题
                map.remove(end.key); //此时还要移除map中的值
                remove(end); //删除尾结点，end的指向由remove()维护
            }
            setHead(n);
            map.put(key, n);
        }
    }

    private void remove(Node n) {
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next; //更新head
        }

        if (n.next != null) {
            n.next.prev = n.prev;
        } else {
            end = n.prev; //更新end
        }
    }

    private void setHead(Node n) {
        //构建结点
        n.next = head;
        n.prev = null;
        //更新原头结点
        if (head != null) {
            head.prev = n;
        }
        //更新head指向
        head = n;
        //插入第一个结点时更新end
        if (end == null) {
            end = head;
        }
    }

}

package com.whendada.logic.concurrency.basic;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class EnhanceMap<K, V> {

    Map<K, V> map;

    public EnhanceMap(Map<K, V> map) {
        this.map = Collections.synchronizedMap(map);
    }

    // 多个线程执行这一个方法的时候，可能都判断了没有，然后都去加，所以这里线程安全应该是给方法加锁
    // 如果直接给putIfAbsent方法加锁，这里有问题，调用putIfAbsent方法的是EnhanceMap对象，但是实际上使用的是map，
    // 所以应该加锁的地方是map，如下
    public V putIfAbsent(K key, V val) {
        // 优化后的代码
        synchronized (map) {
            V old = map.get(key);
            if (Objects.nonNull(old)) {
                return old;
            }
            return map.put(key, val);
        }
    }

    public V put(K key, V val) {
        return map.put(key, val);
    }
}

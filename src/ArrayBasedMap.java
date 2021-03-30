import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private final List<Pair> keyAndValues = new ArrayList<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return  keyAndValues.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return  keyAndValues.isEmpty();
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (Pair kv : keyAndValues) {
            if (key == null) {
                if (kv.getKey() == null) {
                    return true;
                }
            } else {
                if (kv.getKey() == null) {
                    continue;
                }
                if (kv.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for (Pair kv : keyAndValues) {
            if (value == null) {
                if (kv.getValue() == null) {
                    return true;
                }
            } else {
                if (kv.getValue() == null) {
                    continue;
                }
                if (kv.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        for (Pair kv : keyAndValues) {
            if (key == null) {
                if (kv.getKey() == null) {
                    return null;
                }
            } else {
                if (kv.getKey() == null) {
                    continue;
                }
                if (kv.getKey().equals(key)) {
                    return kv.getValue();
                }
            }
        }
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        for (Pair kv : keyAndValues) {
            if (key == null) {
                if (kv.getKey() == null) {
                    kv.setValue(value);
                    return null;
                }
            } else {
                if (kv.getKey() == null) {
                    continue;
                }
                if (kv.getKey().equals(key)) {
                    V returnV = kv.getValue();
                    kv.setValue(value);
                    return returnV;
                }
            }
        }
        keyAndValues.add(new Pair(key, value));
        return null;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        for (Pair kv : keyAndValues) {
            if (key == null) {
                if (kv.getKey() == null) {
                    keyAndValues.remove(kv);
                    return null;
                }
            } else {
                if (kv.getKey() == null) {
                    continue;
                }
                if (kv.getKey().equals(key)) {
                    V returnV = kv.getValue();
                    keyAndValues.remove(kv);
                    return returnV;
                }
            }
        }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Entry<K, V>>) (Set) m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        keyAndValues.clear();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<>();
        for (Pair p : keyAndValues) {
            keys.add(p.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        Collection<V> c = new ArrayList<>();
        for (Pair kv : keyAndValues) {
            c.add(kv.getValue());
        }
        return c;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>) (Set) new HashSet<>(keyAndValues);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) {
                return false;
            }
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }
    }
}
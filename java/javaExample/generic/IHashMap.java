package generic;

public interface IHashMap<K,V> {
	public void put(K key, V object);
	public V get(K key);
}
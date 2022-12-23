/** key-value */
package generic;

public class Entry<K,V> {

	public Entry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	public K key;
	public V value;
	@Override
	public String toString() {
		return "[key=" + key + ", value=" + value + "]";
	}
	
}

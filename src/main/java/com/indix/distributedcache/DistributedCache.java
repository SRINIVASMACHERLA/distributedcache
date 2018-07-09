package com.indix.distributedcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Distributed cache implementation. In case of cache miss, uses
 * {@link KeyFinder} to find the key.
 *
 * @author macherla
 *
 */
@Component
@Qualifier("DistributedCache")
public final class DistributedCache implements Cache {

	@Autowired
	@Qualifier("LocalCache")
	private Cache localCache;

	@Autowired
	private KeyFinder keyFinder;

	public String set(String key, String value) {
		return localCache.set(key, value);
	}

	public String get(String key) {
		String value = localCache.get(key);
		if (value == null) {
			return keyFinder.getKeyFromPeers(key);
		}
		return value;
	}
}
package com.indix.distributedcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
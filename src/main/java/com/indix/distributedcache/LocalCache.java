package com.indix.distributedcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * @author macherla
 *
 */
@Component
@Qualifier("LocalCache")
public class LocalCache implements Cache {
	private Map<String, String> map;

	public LocalCache() {
		map = new ConcurrentHashMap<String, String>();
	}

	public String set(String key, String value) {
		return map.putIfAbsent(key, value);
	}

	public String get(final String key) {
		return map.get(key);
	}
}

package com.indix.distributedcache;

/**
 * Cache interface contains APIs which allows to perform operations on it. The
 * implementation must guarantees constant time operations. The data should be
 * eventually consistent in case of distributed cache system.
 *
 * @author macherla
 *
 */
public interface Cache {

	/**
	 * Issues set command on cache.
	 *
	 * @param key
	 * @param value
	 * @return old value if exists otherwise null
	 */
	String set(final String key, final String value);

	/**
	 * Issues get command on cache.
	 *
	 * @param key
	 * @return value associated with key otherwise null.
	 */
	String get(final String key);
}
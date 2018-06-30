package com.indix.distributedcache;

public interface Cache {

	String set(final String key, final String value);
	String get(final String key);
}
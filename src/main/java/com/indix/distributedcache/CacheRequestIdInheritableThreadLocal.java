package com.indix.distributedcache;

public class CacheRequestIdInheritableThreadLocal {
	private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

	public static void set(final String value) {
		inheritableThreadLocal.set(value);
	}

	public static String get() {
		return inheritableThreadLocal.get();
	}
}
package com.indix.distributedcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

	@Autowired
	@Qualifier("DistributedCache")
	private DistributedCache distributedCache;

	@Autowired
	@Qualifier("LocalCache")
	private LocalCache localCache;

	// @Autowired
	// private PeerURLDiscoverer peerURLDiscoverer;

	// public CacheController() {
	// distributedCache = new DistributedCache(new LocalCache(),
	// new KeyFinder(Executors.newFixedThreadPool(1), new
	// RestHttpClient(peerURLDiscoverer.getPeerURL())));
	// }

	@GetMapping("/get")
	public String get(@RequestParam("key") String key) {
		return distributedCache.get(key);
	}

	@PostMapping("/set")
	public String set(@RequestParam("key") String key, @RequestParam("value") String value) {
		return distributedCache.set(key, value);
	}

	/**
	 * Peer communication URL patterns for cache misses.
	 */
	@GetMapping("/cache/miss/get")
	public String getRemote(@RequestParam("key") String key) {
		return localCache.get(key);
	}
}
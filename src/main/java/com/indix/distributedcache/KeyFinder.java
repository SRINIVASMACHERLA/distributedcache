package com.indix.distributedcache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeyFinder {

	@Autowired
	private RestHttpClient httpClient;

	@Autowired
	private PeerURLDiscoverer peerURLDiscoverer;

	private final ExecutorService executorService = Executors.newWorkStealingPool();

	public String getKeyFromPeers(final String key) {
		try {
			final String requestURL = String.format(peerURLDiscoverer.getPeerURLToKeyLookup(), key);
			return executorService.submit(() -> httpClient.getResult(requestURL)).get();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
}
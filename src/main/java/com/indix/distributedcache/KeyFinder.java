package com.indix.distributedcache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * In case of cache miss, it identifies all peers running cache instances using
 * {@link PeerURLDiscoverer} and issue get command on each cache peer and
 * returns response.
 *
 * @importantNote Current implementation works with only 1 peer.
 *
 * @author macherla
 *
 */
@Component
public class KeyFinder {

	@Autowired
	private RestHttpClient httpClient;

	@Autowired
	private PeerURLDiscoverer peerURLDiscoverer;

	/*
	 * Submit task to make a remote call to look up key with it's peers.
	 */
	private final ExecutorService executorService = Executors.newWorkStealingPool();

	/**
	 * Uses {@link ExecutorService} to look up key.
	 *
	 * @param key
	 * @return value associated with key
	 * @throws RuntimeException
	 *             for any execution exception or thread interruption.
	 */
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
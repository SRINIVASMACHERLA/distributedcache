package com.indix.distributedcache;

/**
 * Discovers running cache instance's peers.
 *
 * Currently it provides one API to look up only one peer based on current
 * requirements.
 *
 * @author macherla
 *
 */
public interface PeerURLDiscoverer {
	/**
	 *
	 * @return peer name
	 */
	String getPeerURLToKeyLookup();
}
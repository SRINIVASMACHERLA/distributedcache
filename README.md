# distributedcache

This is the main package. It contain core components of distributed cache. It's used by distributecache_process1 and distributecache_process2 modules.

Request flow:

for /get url pattern:

CacheController -> DistributedCache -> LocalCache (if key exists returns its value)
                                    -> KeyFinder (for cache miss)  uses PeerURLDiscoverer to identify peers -> makes RestHttpClient -> returns value.
 
for /set url pattern:

CacheController -> LocalCache -> update local cache instance and returns

Limitaions:

1. To update any existing key, the current implementation does not look for any key existing key with its peer in case of miss. It always try to update local cache instance.
2. Not scalable for Tier 1 systems.

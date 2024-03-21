package org.example.hotrod;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;

public class Client {

    public static void main(String[] args) {
        final ConfigurationBuilder builder = new ConfigurationBuilder();
        // hotrod without authentication()
        builder.addServer()
                .host("127.0.0.1")
                .port(ConfigurationProperties.DEFAULT_HOTROD_PORT);
        /*
        // hotrod with authentication()
        builder.addServer()
                .host("127.0.0.1")
                .port(ConfigurationProperties.DEFAULT_HOTROD_PORT)
                .security().authentication()
                .username("admin")
                .password("admin")
                .saslMechanism("SCRAM-SHA-512");
         */
        final RemoteCacheManager remoteCacheManager = new RemoteCacheManager(builder.build());
        System.out.println("start operations()");
        // 1. list_operation() all caches names
        System.out.println("list_operation() output: " + remoteCacheManager.getCacheNames());
        // 2. get_single_cache_operation()
        final RemoteCache<String, String> cache = remoteCacheManager.getCache("mycache");
        System.out.println("get_single_cache_operation() output: " + cache);
        // 3. get_stats_operation() on this single cache
        System.out.println("get_stats_operation() output: " + cache.serverStatistics().getStatsMap());
        // 4. put_operation() on this single cache
        System.out.println("put_operation() output: " + cache.put("mykey1", "myvalue1"));
        // 5. getKey_operation() on this single cache
        System.out.println("getKey_operation() output: " + cache.get("mykey1"));
        // 6. remove_operation() on this single cache
        System.out.println("remove_operation() output: " + cache.remove("mykey1"));
        System.out.println("end operations()");
    }
}

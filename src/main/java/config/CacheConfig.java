//package config;
//
//import net.sf.ehcache.config.CacheConfiguration;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurer;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.interceptor.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableCaching
//public class CacheConfig implements CachingConfigurer {
//
//    @Bean(destroyMethod = "shutdown")
//    public net.sf.ehcache.CacheManager ehCacheManager() {
//        CacheConfiguration cacheConfiguration = new CacheConfiguration();
//        cacheConfiguration.setName("detailedTotalStatsCache");
//        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
//        cacheConfiguration.setMaxEntriesLocalHeap(1);
//
//        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
//        config.addCache(cacheConfiguration);
//
//        return net.sf.ehcache.CacheManager.newInstance(config);
//    }
//
//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager(ehCacheManager());
//    }
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        return new SimpleKeyGenerator();
//    }
//
//    @Bean
//    @Override
//    public CacheResolver cacheResolver() {
//        return new SimpleCacheResolver(cacheManager());
//    }
//
//    @Bean
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new SimpleCacheErrorHandler();
//    }
//
//}
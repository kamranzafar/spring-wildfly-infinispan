package org.kamranzafar.spring.infinispan;

import org.infinispan.Cache;
import org.jboss.as.clustering.infinispan.DefaultCacheContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;

/**
 * Created by kamran on 05/08/16.
 */
@Configuration
@SpringBootApplication
@ComponentScan("org.kamranzafar.spring.infinispan")
public class Application extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Value("${infinispan.jndi.url}")
    private String infinispanJndiUrl;

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public Cache defaultCache() throws NamingException {
        return (Cache) ((DefaultCacheContainer) new JndiTemplate().lookup(infinispanJndiUrl)).getCache();
    }
}

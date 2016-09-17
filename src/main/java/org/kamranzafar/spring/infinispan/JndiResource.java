package org.kamranzafar.spring.infinispan;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kamran on 17/09/16.
 */
@RestController
@RequestMapping("rest/jndi")
public class JndiResource {
    @Autowired
    private Cache defaultCache;

    @RequestMapping("{key}")
    public String get(@PathVariable("key") String key) {
        return defaultCache.get(key).toString();
    }

    @RequestMapping("{key}/{value}")
    public void set(@PathVariable("key") String key, @PathVariable("value") String value) {
        defaultCache.putForExternalRead(key, value);
    }
}

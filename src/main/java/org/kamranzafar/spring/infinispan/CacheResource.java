package org.kamranzafar.spring.infinispan;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kamran on 17/09/16.
 */
@RestController
@RequestMapping("rest/cache")
public class CacheResource {
    @Autowired
    private Cache defaultCache;

    @RequestMapping("{key}")
    public String get(@PathVariable("key") String key) {
        return defaultCache.get(key).toString();
    }

    @RequestMapping(value = "{key}/{value}", method = RequestMethod.PUT)
    public void set(@PathVariable("key") String key, @PathVariable("value") String value) {
        defaultCache.putForExternalRead(key, value);
    }

    @RequestMapping(value = "{key}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("key") String key){
        defaultCache.remove(key);
    }
}

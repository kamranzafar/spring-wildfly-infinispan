# Spring Infinispan Integration on Wildfly

This is a sample spring boot application that demonstrates Infinispan caching integration on Wildfly servers.
It works for both local and distributed caching on both standalone and clustered Wildfly instances.

This application is tested on Wildfly 10.1.0.Final.

## Running the application

The application war file can be built using maven.

<pre><code>    mvn clean install</code></pre>

The application also requires a custom cache container, below is how it can be created on Wildfly.

### Standalone server mode

When running the server in a standalone mode add the following cache container config in standalone.xml file.

<pre><code>    &lt;cache-container name="mycache" aliases="ha-partition" default-cache="default" module="org.wildfly.clustering.server"&gt;
        &lt;local-cache name="default" /&gt;
    &lt;/cache-container&gt;
</code></pre>

### Clustered server mode

For clustered mode, the server must be run with full-ha profile. And add the following cache container to standalone-full-ha.xml 

<pre><code>    &lt;cache-container name="mycache" aliases="ha-partition" default-cache="default" module="org.wildfly.clustering.server"&gt;
        &lt;transport lock-timeout="60000"/&gt;
        &lt;replicated-cache name="default" mode="SYNC"&gt;
            &lt;transaction mode="BATCH"/&gt;
        &lt;/replicated-cache&gt;
    &lt;/cache-container&gt;
</code></pre>

## Usage

The application exposes the following rest endpoints to add, retrieve and remove cache entries

GET /rest/cache/{key}</br>
PUT /rest/cache/{key}/{value}</br>
DELETE /rest/cache/{key}</br>
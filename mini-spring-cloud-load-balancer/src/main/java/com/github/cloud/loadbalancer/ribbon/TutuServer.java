package com.github.cloud.loadbalancer.ribbon;

import com.netflix.loadbalancer.Server;

/**
 * 图图服务实例
 *
 * @author w
 * @date 2023/3/13
 */
public class TutuServer extends Server {

    public TutuServer(String host, int port) {
        super(host, port);
    }
}

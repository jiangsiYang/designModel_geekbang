package com.design.u043;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 多例模式，一个类可以创建多个对象，但是个数是有限的，比如3个
 */
public class BackendServer {
    private static final int SERVER_COUNT = 3;
    private static final Map<Long, BackendServer> serverInstances = new HashMap<>();

    private long serverNo;
    private String serverAddress;

    private BackendServer(long serverNo, String serverAddress) {
        this.serverNo = serverNo;
        this.serverAddress = serverAddress;
    }


    static {
        serverInstances.put(1L, new BackendServer(1L, "192.134.22.138:8080"));
        serverInstances.put(2L, new BackendServer(2L, "192.134.22.139:8080"));
        serverInstances.put(3L, new BackendServer(3L, "192.134.22.140:8080"));
    }

    public BackendServer getInstance() {
        Random random = new Random();
        int no = random.nextInt(SERVER_COUNT + 1);
        return serverInstances.get(no);
    }
}

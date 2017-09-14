package com.an.inshorts.rest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RESTExecutorService {

    private static final ExecutorService _restAPIThreadPool = Executors.newFixedThreadPool(5);

    public static void submit(Runnable runnable) {
        getRestAPIThreadPool().submit(runnable);
    }

    private static ExecutorService getRestAPIThreadPool() {
        return _restAPIThreadPool;
    }
}

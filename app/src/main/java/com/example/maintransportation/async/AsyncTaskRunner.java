package com.example.maintransportation.async;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Executor executor = Executors.newCachedThreadPool();

    public <R> void executeAsync(Callable<R> callable, Callback<R> callback) {
        try {
            executor.execute(new RunnableTask<R>(handler, callable, callback));
        } catch (Exception e) {
            Log.i("AsyncTaskRunner", "failed call executeAsync " + e.getMessage());
        }
    }
}

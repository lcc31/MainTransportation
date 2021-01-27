package com.example.maintransportation.async;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Callable;

public class RunnableTask<R> implements Runnable {
    private final Handler handler;
    private final Callable<R> callable;
    private final Callback<R> callback;

    public RunnableTask(Handler handler, Callable<R> callable, Callback<R> callback) {
        this.handler = handler;
        this.callable = callable;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            final R result = callable.call();
            handler.post(new HandlerMessage<R>(callback, result));
        } catch (Exception e) {
            Log.i("RunnableTask", "failed call runnable " + e.getMessage());
        }
    }
}
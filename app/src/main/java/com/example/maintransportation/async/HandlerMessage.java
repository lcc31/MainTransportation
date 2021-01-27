package com.example.maintransportation.async;


public class HandlerMessage<R> implements Runnable {
    private final Callback<R> callback;
    private final R result;

    public HandlerMessage(Callback<R> callback, R result) {
        this.callback = callback;
        this.result = result;
    }

    @Override
    public void run() {
        callback.runResultOnUiThread(result);
    }
}

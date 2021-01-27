package com.example.maintransportation.async;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
package com.example.maintransportation.database.service;

import android.content.Context;

import com.example.maintransportation.async.AsyncTaskRunner;
import com.example.maintransportation.async.Callback;
import com.example.maintransportation.database.DatabaseManager;
import com.example.maintransportation.database.dao.ClientDBDao;
import com.example.maintransportation.database.model.ClientDB;

import java.util.List;
import java.util.concurrent.Callable;

public class ClientDBService {
    private final ClientDBDao clientDBDao;
    private final AsyncTaskRunner asyncTask;

    public ClientDBService(Context context) {
        clientDBDao = DatabaseManager.getInstance(context)
                .getClientDBDao();
        asyncTask = new AsyncTaskRunner();
    }


    public void getAllByFirma(final String firma,
                              final Callback<List<ClientDB>> callback) {
        Callable<List<ClientDB>> callable = new Callable<List<ClientDB>>() {
            @Override
            public List<ClientDB> call() {
                if (firma == null || firma.trim().isEmpty()) {
                    return null;
                }
                return clientDBDao.getAllByFirma(firma);
            }
        };
        asyncTask.executeAsync(callable, callback);
    }


    public void getAll(Callback<List<ClientDB>> callback) {
        Callable<List<ClientDB>> callable = new Callable<List<ClientDB>>() {
            @Override
            public List<ClientDB> call() {
                return clientDBDao.getAll();
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void insert(final ClientDB clientDB,
                       Callback<ClientDB> callback) {
        Callable<ClientDB> callable = new Callable<ClientDB>() {
            @Override
            public ClientDB call() {
                if (clientDB == null) {
                    return null;
                }
                long id = clientDBDao.insert(clientDB);
                if (id == -1) {
                    return null;
                }
                clientDB.setId(id);
                return clientDB;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void update(final ClientDB clientDB,
                       Callback<ClientDB> callback) {
        Callable<ClientDB> callable = new Callable<ClientDB>() {
            @Override
            public ClientDB call() {
                if (clientDB == null) {
                    return null;
                }
                int count = clientDBDao.update(clientDB);
                if (count != 1) {
                    return null;
                }
                return clientDB;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void delete(final ClientDB clientDB,
                       Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (clientDB == null) {
                    return -1;
                }
                return clientDBDao.delete(clientDB);
            }
        };
        asyncTask.executeAsync(callable, callback);
    }
}

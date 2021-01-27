package com.example.maintransportation.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.maintransportation.database.model.ClientDB;

import java.util.List;

@Dao
public interface ClientDBDao {

    @Query("select * from clientidb")
    List<ClientDB> getAll();

    @Query("select * from clientidb where firma=:firma")
    List<ClientDB> getAllByFirma(String firma);

    @Insert
    long insert(ClientDB clientDB);

    @Update
    int update(ClientDB clientDB);

    @Delete
    int delete(ClientDB clientDB);
}

package ru.mirea.sysoeva.mireaproject.DataTask8pr;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DiscipDao {
    @Query("SELECT * FROM Disciplines")
    LiveData<List<Disciplines>> getAllDisciplines();
    @Insert
    void insert(Disciplines disciplines);
    @Update
    void update(Disciplines disciplines);
    @Delete
    void delete(Disciplines disciplines);
}

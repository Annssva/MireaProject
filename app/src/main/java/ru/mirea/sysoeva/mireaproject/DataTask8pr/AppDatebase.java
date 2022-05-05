package ru.mirea.sysoeva.mireaproject.DataTask8pr;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Disciplines.class}, version = 3)
public abstract class AppDatebase extends RoomDatabase{
    public abstract DiscipDao discipDao();
}
package ru.mirea.sysoeva.mireaproject.DataTask8pr;


import androidx.room.PrimaryKey;

public class Disciplines {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String lecturersName;
}
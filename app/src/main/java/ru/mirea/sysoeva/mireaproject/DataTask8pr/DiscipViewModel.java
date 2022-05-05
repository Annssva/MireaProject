package ru.mirea.sysoeva.mireaproject.DataTask8pr;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class DiscipViewModel extends ViewModel {
    private final LiveData<List<Disciplines>> disciplines;
    private final AppDatebase appDatabase = App.instance.getDatabase();
    private final DiscipDao discipDao = appDatabase.discipDao();

    public DiscipViewModel(){
        disciplines = discipDao.getAllDisciplines();
    }

    public void addDiscip(Disciplines disciplines){
        discipDao.insert(disciplines);
    }

    public LiveData<List<Disciplines>> getDiscipLiveData(){
        return disciplines;
    }
    public List<Disciplines> getDiscip(){
        return disciplines.getValue();
    }
}
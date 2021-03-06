package ru.mirea.sysoeva.mireaproject.DataTask8pr;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.sysoeva.mireaproject.R;


public class DataDiscipFragment extends Fragment {
    private List<Disciplines> disciplines = new ArrayList<>();
    private DiscipViewModel discipViewModel;
    private DiscipAdapter discipAdapter = new DiscipAdapter(disciplines);
    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> launcher;
    public static final int ADD_CAT_RESULT_CODE=1;
    public static final String NAME_LABEL="name";
    public static final String TYPE_LABEL="lecturersName";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);

        if (getActivity() != null){
            discipViewModel = new ViewModelProvider(getActivity()).get(DiscipViewModel.class);
            discipViewModel.getDiscipLiveData().observe(getActivity(), this::onChanged);
        }
        view.findViewById(R.id.btnAddDiscip).setOnClickListener(this::onNewDiscipClicked);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.discipRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(discipAdapter);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result) -> {
                    if (result.getResultCode() == ADD_CAT_RESULT_CODE
                            && result.getData() != null){
                        Disciplines disciplines = new Disciplines();
                        disciplines.name = result.getData().getStringExtra(NAME_LABEL);
                        disciplines.lecturersName = result.getData().getStringExtra(TYPE_LABEL);
                        discipViewModel.addDiscip(disciplines);
                        discipAdapter.notifyDataSetChanged();
                    }
                });
        return view;
    }

    public void onChanged(List<Disciplines> brawlerfromDB) {
        if (!disciplines.isEmpty()){
            disciplines.clear();
        }
        disciplines.addAll(brawlerfromDB);
        discipAdapter.notifyDataSetChanged();
    }

    private void onNewDiscipClicked(View view){
        Intent intent = new Intent(getActivity(), DiscipAdd.class);
        launcher.launch(intent);
    }
}
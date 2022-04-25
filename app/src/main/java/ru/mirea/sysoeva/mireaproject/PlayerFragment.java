package ru.mirea.sysoeva.mireaproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayerFragment extends Fragment {

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        view.findViewById(R.id.btnPlay).setOnClickListener(this::onClickPlayMusic);
        view.findViewById(R.id.btnStop).setOnClickListener(this::onClickStopMusic);

        return view;
    }
    public void onClickPlayMusic(View view) {
        getActivity().startService(
                new Intent(getActivity(), PlayerService.class));
    }
    public void onClickStopMusic(View view) {
        getActivity().stopService(
                new Intent(getActivity(), PlayerService.class));
    }
}
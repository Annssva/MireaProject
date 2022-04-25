package ru.mirea.sysoeva.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SettingsFragment extends Fragment {
    private Button btnSave;
    private EditText nameText;
    private EditText ageText;
    private EditText studyText;
    private SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this::updateButoonClick);

        nameText = view.findViewById(R.id.nameEdit);
        ageText = view.findViewById(R.id.ageEdit);
        studyText = view.findViewById(R.id.StudyEdit);

        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);;

        try {
            nameText.setText(preferences.getString("name", null));
            ageText.setText(preferences.getString("age", null));
            studyText.setText(preferences.getString("job", null));
        }catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return view;
    }


    private void updateButoonClick(View view) {
        Log.d("TAG", "button clicked");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", nameText.getText().toString());
        editor.putString("age", ageText.getText().toString());
        editor.putString("job", studyText.getText().toString());
        editor.apply();
        Toast.makeText(getActivity(), "Update saved", Toast.LENGTH_SHORT).show();
    }
}

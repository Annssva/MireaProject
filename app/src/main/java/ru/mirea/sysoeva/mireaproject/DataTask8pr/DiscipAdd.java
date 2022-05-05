package ru.mirea.sysoeva.mireaproject.DataTask8pr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.sysoeva.mireaproject.MainActivity;
import ru.mirea.sysoeva.mireaproject.R;

public class DiscipAdd extends AppCompatActivity {
    private EditText discipName;
    private EditText discipLectName;
    private Button button;

    private AppDatebase db;
    private DiscipDao discipDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discip_view);

        db = App.getInstance().getDatabase();
        discipDao = db.discipDao();

        discipName = findViewById(R.id.editNameDiscip);
        discipLectName = findViewById(R.id.editDiscipLectName);

        button = findViewById(R.id.btnSaveDiscip);
        button.setOnClickListener(this::saveBtn);
    }

    public void saveBtn(View view) {

        Disciplines discip = new Disciplines();
        discip.name = discipName.getText().toString();
        discip.lecturersName = discipLectName.getText().toString();

        discipDao.insert(discip);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

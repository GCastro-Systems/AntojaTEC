package com.ccastro.antojatec;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.ccastro.antojatec.ui.auth.AuthContainerFragment;
import com.ccastro.antojatec.ui.user.UserContainerFragment; // Para en un futuro desarrollo

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new AuthContainerFragment()) // Carga auth por defecto
                    .commit();
        }
    }
}

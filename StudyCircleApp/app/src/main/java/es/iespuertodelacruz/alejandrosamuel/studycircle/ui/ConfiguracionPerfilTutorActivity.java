package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityConfiguracionPerfilTutorBinding;

public class ConfiguracionPerfilTutorActivity extends AppCompatActivity {

    private ActivityConfiguracionPerfilTutorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_perfil_tutor);
        binding = ActivityConfiguracionPerfilTutorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSalir4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.valerio.android.aplicacionprofequiz.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.valerio.android.aplicacionprofequiz.R;
import com.valerio.android.aplicacionprofequiz.databinding.ActivityLoginBinding;

import java.util.concurrent.ExecutorService;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail, editPassword;
    Button btnLogin;
    ActivityLoginBinding mActivityLoginBinding;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        setContentView(mActivityLoginBinding.getRoot());

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.iniciarSesion_login_btn);
        executorService = Executors.newSingleThreadExecutor();

        btnLogin.setOnClickListener(v -> {
            String user = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            LoginUsuario(user, password);
        });

        // Ir al registro
        TextView textViewRegistro = findViewById(R.id.textView_registro);
        textViewRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LoginUsuario(String email, String password) {
        executorService.execute(() -> {
            String response;
            try {
                URL url = new URL("https://profequiz.000webhostapp.com/login/index.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoOutput(true);

                String postData = "&email=" + email + "&contrasena=" + password;
                byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

                OutputStream os = conn.getOutputStream();
                os.write(postDataBytes);
                os.flush();
                os.close();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    response = "Inicio de sesión satisfactorio";
                } else {
                    response = "Error al iniciar sesión";
                }

                conn.disconnect();
            } catch (Exception e) {
                Log.e("Registrar", "Error:" + e.getMessage());
                response = "Error al iniciar sesión";
            }

            final String finalResponse = response;


            runOnUiThread(() -> Toast.makeText(LoginActivity.this, finalResponse, Toast.LENGTH_LONG).show());

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
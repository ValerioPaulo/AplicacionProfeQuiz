package com.valerio.android.aplicacionprofequiz.Vista;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.json.JSONObject;

import com.valerio.android.aplicacionprofequiz.R;

public class RegistroActivity extends AppCompatActivity {
    EditText editEmail, editUser, editPassword;
    Button btnRegister;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        editEmail = findViewById(R.id.editEmail);
        editUser = findViewById(R.id.editUsuario);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.filledButton2);
        executorService = Executors.newSingleThreadExecutor();

        btnRegister.setOnClickListener(v -> {
            String user = editUser.getText().toString();
            String mail = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            RegistrarUsuario(user, mail, password);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    private void RegistrarUsuario(String user, String email, String password) {
        executorService.execute(() -> {
            String respuesta;
            try {
                URL url = new URL("https://profequiz.000webhostapp.com/login/index.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoOutput(true);

                String postData = "usuario=" + user + "&email=" + email + "&contrasena=" + password;
                byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

                OutputStream os = conn.getOutputStream();
                os.write(postDataBytes);
                os.flush();
                os.close();

                // Leer la respuesta
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                is.close();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    respuesta = sb.toString();
                } else {
                    respuesta = "Error al registrarse";
                }

                conn.disconnect();
            } catch (Exception e) {
                Log.e("Registrar", "Error:" + e.getMessage());
                respuesta = "Error al registrar el usuario";
            }

            final String finalRespuesta = respuesta;

            runOnUiThread(() -> {
                try {
                    JSONObject jsonResponse = new JSONObject(finalRespuesta);
                    String mensaje = jsonResponse.getString("mensaje");
                    Toast.makeText(RegistroActivity.this, mensaje, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(RegistroActivity.this, finalRespuesta, Toast.LENGTH_LONG).show();
                }
            });

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    private void clearInputs() {
        editEmail.setText("");
        editUser.setText("");
        editPassword.setText("");
    }
}
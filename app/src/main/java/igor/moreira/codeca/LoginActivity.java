package igor.moreira.codeca;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import igor.moreira.codeca.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {

    LoginBinding binding;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = LoginBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);
    }
}


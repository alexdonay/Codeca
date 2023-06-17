package igor.moreira.codeca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_TIMEOUT = 12222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("MinhaActivity", "Entrando na Activity splash");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BdSingleton bd = BdSingleton.getInstance();
                Intent intent;
                if(bd.getUsuarios()!=null){
                    intent = new Intent(SplashActivity.this,CadastroActivity.class);
                }else{
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}

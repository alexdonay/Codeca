package igor.moreira.codeca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SolicitacaoActivity extends AppCompatActivity {
    Button nova;
    Button consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);


    }

    @Override
    protected void onResume() {
        super.onResume();

        nova = (Button) findViewById(R.id.btnNova);
        consultar = (Button) findViewById(R.id.btnConsultar);
        nova.setOnClickListener(view -> {
            Intent intent = new Intent(SolicitacaoActivity.this, NovaActivity.class);
            startActivity(intent);
        });
        consultar.setOnClickListener(view -> {
            Intent intent = new Intent(SolicitacaoActivity.this, ConsultarActivity.class);
            startActivity(intent);
        });
    }
}
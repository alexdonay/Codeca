package igor.moreira.codeca;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {
    Button btnCadastrar;
    TextView txNome;
    TextView txEmail;
    TextView txSenha;
    TextView txCPF;
    TextView txCelular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar2);
        Log.d("MinhaActivity", "Entrando na Activity cadastro");
        btnCadastrar.setOnClickListener(view -> {
            BdSingleton bd = BdSingleton.getInstance();
            txNome = findViewById(R.id.txtNome);
            txEmail = findViewById(R.id.txtemail);
            txSenha = findViewById(R.id.txtsenha);
            txCPF = findViewById(R.id.txtcpf);
            txCelular = findViewById(R.id.txtcontato);
            if (TextUtils.isEmpty(txNome.getText()) || TextUtils.isEmpty(txEmail.getText()) ||
                    TextUtils.isEmpty(txSenha.getText()) || TextUtils.isEmpty(txCPF.getText()) ||
                    TextUtils.isEmpty(txCelular.getText())) {
                Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                ModeloUsuario usuario = new ModeloUsuario(txNome.getText().toString(), txEmail.getText().toString(),
                        txSenha.getText().toString(), txCPF.getText().toString(), txCelular.getText().toString());
                        usuario.setIDbackend(bd.cadastraUsuario(usuario));
                DbHelperUsuario userDB = new DbHelperUsuario(this,null,null,1);
                userDB.criarUsuario(usuario);
                userDB.close();
                Intent intent = new Intent(CadastroActivity.this, SolicitacaoActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }
}


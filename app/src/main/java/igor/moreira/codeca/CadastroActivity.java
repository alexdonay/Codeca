package igor.moreira.codeca;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txNome = (TextView) findViewById(R.id.txtNome);
                txEmail = (TextView) findViewById(R.id.txtemail);
                txSenha = (TextView) findViewById(R.id.txtsenha);
                txCPF = (TextView) findViewById(R.id.txtcpf);
                txCelular = (TextView) findViewById(R.id.txtcontato);
                ModeloUsuario usuario = new ModeloUsuario((String) txNome.getText(), (String) txEmail.getText(), (String) txSenha.getText(), (String) txCPF.getText(), (String) txCelular.getText());

            }
        });
    }
}


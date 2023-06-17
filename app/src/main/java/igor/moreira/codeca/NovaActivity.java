package igor.moreira.codeca;

import android.os.Bundle;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;


public class NovaActivity extends AppCompatActivity {

    private Spinner tiposdeservico;



    String [] servicos = {"teste01","teste02","teste03"};
    Button btnEnviaSol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);



    }
    @Override
    protected void onResume() {
        super.onResume();
        tiposdeservico = findViewById(R.id.tiposdeservico);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,servicos);
        tiposdeservico.setAdapter(adapter);
        btnEnviaSol.setOnClickListener(view -> {
            double latitude = 12;
            double longitude = 13;
            String caminhoFoto = "Caminho da foto";
            String descricao = "descricao";
            String status = "enviado";
            ModeloSolicitacao solicitacao = new ModeloSolicitacao(tiposdeservico.getSelectedItemPosition(),latitude, longitude, caminhoFoto, descricao, status);
            solicitacao.setIdUserApi(BdSingleton.getInstance().getUsuarioId());
            DbHelperSolicitacao db = new DbHelperSolicitacao(this,null,null,1);
            db.criaSolicitacao(solicitacao);

        });
    }
    public int enviaSolicitacaoAPi(ModeloSolicitacao solicitacao){
        BdSingleton bd = BdSingleton.getInstance();

        return bd.cadastraSolicitacao(solicitacao);
    }
    public void gravarSolicitacao(int id, ModeloSolicitacao solicitacao){
        DbHelperSolicitacao db = new DbHelperSolicitacao(this,null,null,1);
        solicitacao.setIdSolicitacaoApi(id);
        db.criaSolicitacao(solicitacao);
    }
}

package igor.moreira.codeca;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        ListView listViewSolicitacoes = findViewById(R.id.lsSolicitacoes);
        BdSingleton bd = BdSingleton.getInstance();
        List<ModeloSolicitacao> listaDeSolicitacoes = bd.getSolicitacoes();
        Toast.makeText(this, listaDeSolicitacoes.toString(), Toast.LENGTH_SHORT).show();
        SolicitacoesAdapter adapter = new SolicitacoesAdapter(this, listaDeSolicitacoes);
        listViewSolicitacoes.setAdapter(adapter);
    }
}
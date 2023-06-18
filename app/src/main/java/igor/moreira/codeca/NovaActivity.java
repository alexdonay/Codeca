package igor.moreira.codeca;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class NovaActivity extends AppCompatActivity {
    private Spinner tiposdeservico;

    String[] servicos = {"teste01", "teste02", "teste03"};
    Button btnEnviaSol;
    Button btnTirarFoto;
    ImageView imageView;
    Context mContext;
    static final int CAMERA = 1;
    private String currentPhotoPath;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    XCamera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nova);
        camera = new XCamera(NovaActivity.this);

        tiposdeservico = findViewById(R.id.tiposdeservico);
        btnEnviaSol = findViewById(R.id.btnEnviarSol);
        btnTirarFoto = (Button) findViewById(R.id.btnTirarFoto);
        imageView = (ImageView) findViewById(R.id.fotoView);

        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.dispatchTakePictureIntent();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {
                camera.setPic(imageView);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                camera.dispatchTakePictureIntent();
            } else {
                Toast.makeText(mContext, "A permissão da câmera foi negada.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, servicos);
        tiposdeservico.setAdapter(adapter);
        btnEnviaSol.setOnClickListener(view -> {
            double latitude = 12;
            double longitude = 13;
            String caminhoFoto = "currentPhotoPath";
            String descricao = "descricao";
            String status = "enviado";
            ModeloSolicitacao solicitacao = new ModeloSolicitacao(tiposdeservico.getSelectedItemPosition(), latitude, longitude, caminhoFoto, descricao, status);
            solicitacao.setIdUserApi(BdSingleton.getInstance().getUsuarioId());
            DbHelperSolicitacao db = new DbHelperSolicitacao(this, null, null, 1);
            db.criaSolicitacao(solicitacao);
        });
    }
    public int enviaSolicitacaoAPi(ModeloSolicitacao solicitacao) {
        BdSingleton bd = BdSingleton.getInstance();

        return bd.cadastraSolicitacao(solicitacao);
    }
    public void gravarSolicitacao(int id, ModeloSolicitacao solicitacao) {
        DbHelperSolicitacao db = new DbHelperSolicitacao(this, null, null, 1);
        solicitacao.setIdSolicitacaoApi(id);
        db.criaSolicitacao(solicitacao);
    }

}

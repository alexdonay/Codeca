package igor.moreira.codeca;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NovaActivity extends AppCompatActivity {
    private Spinner tiposdeservico;
    static final int CAMERA = 1;
    private String currentPhotoPath;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    String[] servicos = {"teste01", "teste02", "teste03"};
    Button btnEnviaSol;
    Button btnTirarFoto;
    ImageView imageView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nova);
        mContext = NovaActivity.this;
        tiposdeservico = findViewById(R.id.tiposdeservico);
        btnEnviaSol = findViewById(R.id.btnEnviarSol);
        btnTirarFoto = (Button) findViewById(R.id.btnTirarFoto);

        imageView = (ImageView) findViewById(R.id.fotoView);

        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (cameraIntent.resolveActivity(mContext.getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(mContext,
                                "igor.moreira.android.fileprovider",
                                photoFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        ((Activity) mContext).startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath =image.getAbsolutePath();
        return image;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
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
            String caminhoFoto = currentPhotoPath;
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
    private void setPic() {
        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();

            // Carrega a foto tirada usando o caminho da imagem atual
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            Toast.makeText(getApplicationContext(), "Caminho da foto: " + currentPhotoPath, Toast.LENGTH_SHORT).show();

            // Redimensiona a imagem para caber no ImageView
            int photoW = bitmap.getWidth();
            int photoH = bitmap.getHeight();
            int scaleFactor = 3; // Define o fator de escala como 3 para reduzir para 1/3 do tamanho original
            int resizedWidth = photoW / scaleFactor;
            int resizedHeight = photoH / scaleFactor;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);

            // Define a imagem redimensionada no ImageView
            imageView.setImageBitmap(resizedBitmap);
        }
    }
}

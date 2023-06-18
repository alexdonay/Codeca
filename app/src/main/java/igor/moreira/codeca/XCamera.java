package igor.moreira.codeca;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class XCamera {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private final Context mContext;
    private String currentPhotoPath;
    private File photoFile;
    public XCamera(Context context) {
        mContext = context;
    }

    public void dispatchTakePictureIntent() {
        Toast.makeText(mContext, "abrindo camera", Toast.LENGTH_SHORT).show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            photoFile = createImageFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Uri photoURI = FileProvider.getUriForFile(mContext,
                "igor.moreira.android.fileprovider",
                photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        ((Activity) mContext).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    private File createImageFile() throws IOException {
        Toast.makeText(mContext, "Criando foto", Toast.LENGTH_SHORT).show();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void setPic(ImageView imageView) {
        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            int photoW = bitmap.getWidth();
            int photoH = bitmap.getHeight();
            int scaleFactor = 3;
            int resizedWidth = photoW / scaleFactor;
            int resizedHeight = photoH / scaleFactor;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
            imageView.setImageBitmap(resizedBitmap);
        }
    }

    public String getmCurrentPhotoPath(){
        return currentPhotoPath;
    }
}


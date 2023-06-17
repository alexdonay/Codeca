package igor.moreira.codeca;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperSolicitacao extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "codeca.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "solicitacoes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_tpServico = "tpServico";
    private static final String COLUMN_latitude = "latitude";
    private static final String COLUMN_longitude = "longitude";
    private static final String COLUMN_caminhoFoto = "caminhoFoto";
    private static final String COLUMN_descricao = "descricao";
    private static final String COLUMN_status = "status";
    private static final String COLUMN_idUser = "idUser";

    public DbHelperSolicitacao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_tpServico + " TEXT," +
                COLUMN_latitude + " TEXT," +
                COLUMN_longitude + " TEXT," +
                COLUMN_caminhoFoto + " TEXT," +
                COLUMN_descricao + " TEXT," +
                COLUMN_status +" TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }
    public long criaSolicitacao (ModeloSolicitacao solicitacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_tpServico, solicitacao.getTpServico());
        values.put(COLUMN_latitude, solicitacao.getLatitude());
        values.put(COLUMN_longitude, solicitacao.getLongitude());
        values.put(COLUMN_caminhoFoto, solicitacao.getCaminhoFoto());
        values.put(COLUMN_descricao, solicitacao.getDescricao());
        values.put(COLUMN_status, solicitacao.getStatus());

        long idSolicitacao = db.insert(TABLE_NAME, null, values);
        db.close();
        return idSolicitacao;

    }
}

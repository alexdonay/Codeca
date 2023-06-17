package igor.moreira.codeca;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperUsuario extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "codeca.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "usuario";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CPF = "cpf";
    public static final String COLUMN_CELULAR = "celular";
    public DbHelperUsuario(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_CPF + " TEXT," +
                COLUMN_CELULAR +" TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }
    public void criarUsuario(ModeloUsuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, usuario.getNome());
        values.put(COLUMN_EMAIL, usuario.geteMail());
        values.put(COLUMN_CPF, usuario.getCPF());
        values.put(COLUMN_CELULAR, usuario.getTelefone());

        long resultado = db.insert(TABLE_NAME, null, values);
        db.close();

    }
}

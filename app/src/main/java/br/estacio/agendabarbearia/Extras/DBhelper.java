package br.estacio.agendabarbearia.Extras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando on 29/05/2017.
 */

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "DB_AGENDA.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TB_AGENDAMENTO(DATA DATE," +
                " HORA DATE," +
                " NOME_CLIENTE VARCHAR(20)," +
                " TELEFONE VARCHAR(20)," +
                " FOTO_ANTES VARCHAR(100)," +
                " FOTO_DEPOIS VARCHAR(100)," +
                " PROCEDIMENTO VARCHAR(1)); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

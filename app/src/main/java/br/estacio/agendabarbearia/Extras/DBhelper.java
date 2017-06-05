package br.estacio.agendabarbearia.Extras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.estacio.agendabarbearia.Classes.Agendamento;

/**
 * Created by Fernando on 29/05/2017.
 */

public class DBhelper extends SQLiteOpenHelper {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    private Calendar calendar = Calendar.getInstance();
    private Context context;

    public DBhelper(Context context) {
        super(context, "DB_AGENDA.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TB_AGENDAMENTO(ID_AGENDAMENTO INTEGER PRIMARY KEY AUTOINCREMENT," +
                " DATA TIMESTAMP," +
                " HORA TIMESTAMP," +
                " NOME_CLIENTE VARCHAR(200)," +
                " TELEFONE VARCHAR(20)," +
                " FOTO_ANTES VARCHAR(100)," +
                " FOTO_DEPOIS VARCHAR(100)," +
                " PROCEDIMENTO VARCHAR(1)); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserirTB_AGENDAMENTO(Agendamento agendamento) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();

        content.put("DATA", String.valueOf(agendamento.getData()));
        content.put("HORA", String.valueOf(agendamento.getHora()));
        content.put("NOME_CLIENTE", agendamento.getNomeCliente());
        content.put("TELEFONE", agendamento.getTelefone());
        content.put("FOTO_ANTES", agendamento.getFotoAntes());
        content.put("FOTO_DEPOIS", agendamento.getFotoDepois());
        content.put("PROCEDIMENTO", agendamento.getProcedimento());

        db.insert("TB_AGENDAMENTO", null, content);
    }

    public void atualizaTB_AGENDAMENTO(Agendamento agendamento) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();

        content.put("DATA", String.valueOf(agendamento.getData()));
        content.put("HORA", String.valueOf(agendamento.getHora()));
        content.put("NOME_CLIENTE", agendamento.getNomeCliente());
        content.put("TELEFONE", agendamento.getTelefone());
        content.put("FOTO_ANTES", agendamento.getFotoAntes());
        content.put("FOTO_DEPOIS", agendamento.getFotoDepois());
        content.put("PROCEDIMENTO", agendamento.getProcedimento());

        db.update("TB_AGENDAMENTO", content, "ID_AGENDAMENTO = " + agendamento.getIdAgendamento(), null);
    }

    public List<Agendamento> listaAgendamento(String SQL) {
        List<Agendamento> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();

        do {
            Agendamento agendamento = new Agendamento();

            agendamento.setIdAgendamento(cursor.getInt(cursor.getColumnIndex("ID_AGENDAMENTO")));
            try {
                calendar.setTime(dateFormat.parse(cursor.getString(cursor.getColumnIndex("DATA"))));
                Toast.makeText(context, cursor.getString(cursor.getColumnIndex("DATA")), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                calendar.setTime(new Date());
            }
            agendamento.setData(calendar);
            agendamento.setHora(cursor.getString(cursor.getColumnIndex("HORA")));
            agendamento.setNomeCliente(cursor.getString(cursor.getColumnIndex("NOME_CLIENTE")));
            agendamento.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
            agendamento.setFotoAntes(cursor.getString(cursor.getColumnIndex("FOTO_ANTES")));
            agendamento.setFotoDepois(cursor.getString(cursor.getColumnIndex("FOTO_DEPOIS")));
            agendamento.setProcedimento(cursor.getString(cursor.getColumnIndex("PROCEDIMENTO")));

            lista.add(agendamento);
        } while (cursor.moveToNext());
        cursor.close();
        System.gc();

        return lista;
    }
}

package br.estacio.agendabarbearia.interfaces;

import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.estacio.agendabarbearia.Adapter.ListaAgendaAdapter;
import br.estacio.agendabarbearia.Classes.Agendamento;
import br.estacio.agendabarbearia.Extras.DBhelper;
import br.estacio.agendabarbearia.R;

public class MainActivity extends AppCompatActivity {

    private ListaAgendaAdapter listaAdapter;
    private ListView lstListaAgenda;
    private Button btnCadastroAgenda;
    private Toolbar toolbarListaAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarListaAgenda = (Toolbar) findViewById(R.id.toolbarListaAgenda);
        toolbarListaAgenda.setTitle("Lista de Agendamentos");
        setSupportActionBar(toolbarListaAgenda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCadastroAgenda = (Button) findViewById(R.id.btnCadastroAgenda);
        btnCadastroAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agenda = new Intent(MainActivity.this, CadastroAgendaActivity.class);
                startActivity(agenda);
            }
        });
        lstListaAgenda = (ListView) findViewById(R.id.lstListaAgenda);
        lstListaAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent agenda = new Intent(MainActivity.this, CadastroAgendaActivity.class);
                agenda.putExtra("ID_AGENDAMENTO", listaAdapter.getItem(position).getIdAgendamento());
                startActivity(agenda);
            }
        });

    }

    @Override
    protected void onResume() {
        DBhelper db = new DBhelper(this);

        try {
            List<Agendamento> listaAgendamento = db.listaAgendamento("SELECT * FROM TB_AGENDAMENTO");
            listaAdapter = new ListaAgendaAdapter(this, listaAgendamento);
            lstListaAgenda.setAdapter(listaAdapter);
            if (listaAgendamento.size() == 0) {
                Toast.makeText(this, "Não há Agendamentos cadastrados a serem exibidos", Toast.LENGTH_LONG).show();
            }
        } catch (CursorIndexOutOfBoundsException e) {
            Toast.makeText(this, "Não há Agendamentos cadastrados a serem exibidos", Toast.LENGTH_LONG).show();
        }

        super.onResume();
    }
}

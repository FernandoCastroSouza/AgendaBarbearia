package br.estacio.agendabarbearia.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.estacio.agendabarbearia.Adapter.ListaAgendaAdapter;
import br.estacio.agendabarbearia.Classes.Agendamento;
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

        List<Agendamento> lista = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            Agendamento agendamento = new Agendamento();
            agendamento.setNomeCliente("Cliente: " + (i + 1));
            lista.add(agendamento);
        }

        listaAdapter = new ListaAgendaAdapter(this, lista);
        lstListaAgenda.setAdapter(listaAdapter);
    }
}

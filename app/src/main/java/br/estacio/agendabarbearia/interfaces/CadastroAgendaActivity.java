package br.estacio.agendabarbearia.interfaces;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.estacio.agendabarbearia.Classes.Agendamento;
import br.estacio.agendabarbearia.DatePickerFragment;
import br.estacio.agendabarbearia.Extras.DBhelper;
import br.estacio.agendabarbearia.R;

public class CadastroAgendaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ImageView foto;
    private Button formFotoButton;
    private EditText edtNome;
    private EditText edtDataAgend;
    private EditText edtHora;
    private EditText edtFone;
    private Spinner spinnerProcedimento;
    private Button btnSalvarAgenda;
    private Toolbar toolbarAgenda;
    private Agendamento agendamento = new Agendamento();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agenda);

        toolbarAgenda = (Toolbar) findViewById(R.id.toolbarAgenda);
        toolbarAgenda.setTitle("Agendamentos");

        setSupportActionBar(toolbarAgenda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DBhelper db = new DBhelper(this);

        foto = (ImageView) findViewById(R.id.foto);
        formFotoButton = (Button) findViewById(R.id.formFotoButton);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtDataAgend = (EditText) findViewById(R.id.edtDataAgend);
        edtHora = (EditText) findViewById(R.id.edtHora);
        edtFone = (EditText) findViewById(R.id.edtFone);
        spinnerProcedimento = (Spinner) findViewById(R.id.spinnerProcedimento);
        btnSalvarAgenda = (Button) findViewById(R.id.btnSalvarAgenda);

        edtDataAgend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });

        btnSalvarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agendamento.setNomeCliente(edtNome.getText().toString());
                agendamento.setData(getDate());
                agendamento.setHora(edtHora.getText().toString());
                agendamento.setTelefone(edtFone.getText().toString().replaceAll("[^0-9]", ""));
                if (agendamento.getIdAgendamento() <= 0) {
                    db.inserirTB_AGENDAMENTO(agendamento);
                } else {
                    db.atualizaTB_AGENDAMENTO(agendamento);
                }
                finish();
            }
        });

        if (getIntent().getIntExtra("ID_AGENDAMENTO", 0) > 0) {
            agendamento = db.listaAgendamento("SELECT * FROM TB_AGENDAMENTO WHERE ID_AGENDAMENTO = " + getIntent().getIntExtra("ID_AGENDAMENTO", 0) + ";").get(0);
            edtNome.setText(agendamento.getNomeCliente());
            setDate(agendamento.getData());
            edtHora.setText(agendamento.getHora());
            edtFone.setText(agendamento.getTelefone());
        }
    }

    public void datePicker(View v) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable("calendar", agendamento.getData());
        fragment.setArguments(args);
        fragment.show(this.getFragmentManager(), "");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private Calendar getDate() {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateFormat.parse(edtDataAgend.getText().toString()));
        } catch (Exception e) {
            c.setTime(new Date());
        }
        return c;
    }

    private void setDate(Calendar calendar) {
        try {
            edtDataAgend.setText(dateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            edtDataAgend.setText(dateFormat.format(new Date()));
        }
    }
}

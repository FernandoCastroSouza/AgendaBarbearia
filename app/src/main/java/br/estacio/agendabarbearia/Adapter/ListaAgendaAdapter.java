package br.estacio.agendabarbearia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.estacio.agendabarbearia.Classes.Agendamento;
import br.estacio.agendabarbearia.R;

/**
 * Created by Fernando on 29/05/2017.
 */

public class ListaAgendaAdapter extends ArrayAdapter<Agendamento> {

    private Context context;
    private List<Agendamento> lista;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ListaAgendaAdapter(Context context, List<Agendamento> lista) {
        super(context, 0, lista);

        this.context = context;
        this.lista = lista;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Agendamento agenda = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.agendamento_lista, null);

        TextView txtNomeCliente = (TextView) convertView.findViewById(R.id.txtNomeCliente);
        TextView txtHoraCliente = (TextView) convertView.findViewById(R.id.txtHoraCliente);
        TextView txtDataCliente = (TextView) convertView.findViewById(R.id.txtDataCliente);
        TextView txtFoneCliente = (TextView) convertView.findViewById(R.id.txtFoneCliente);

        txtNomeCliente.setText(agenda.getNomeCliente());
        txtHoraCliente.setText(agenda.getHora());
        txtFoneCliente.setText(agenda.getTelefone());

        try {
            txtDataCliente.setText(dateFormat.format(agenda.getData().getTime()));
        } catch (Exception e) {
            txtDataCliente.setText(dateFormat.format(new Date()));
        }

        return convertView;
    }
}

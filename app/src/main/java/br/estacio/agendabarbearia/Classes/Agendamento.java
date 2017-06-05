package br.estacio.agendabarbearia.Classes;

import java.util.Calendar;

/**
 * Created by Fernando on 29/05/2017.
 */

public class Agendamento {
    private Calendar data;
    private String hora;
    private String nomeCliente;
    private String telefone;
    private String fotoAntes;
    private String fotoDepois;
    private String procedimento;
    private int idAgendamento;

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFotoAntes() {
        return fotoAntes;
    }

    public void setFotoAntes(String fotoAntes) {
        this.fotoAntes = fotoAntes;
    }

    public String getFotoDepois() {
        return fotoDepois;
    }

    public void setFotoDepois(String fotoDepois) {
        this.fotoDepois = fotoDepois;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }
}

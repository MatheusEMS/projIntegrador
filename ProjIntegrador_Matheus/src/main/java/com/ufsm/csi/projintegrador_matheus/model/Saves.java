package com.ufsm.csi.projintegrador_matheus.model;
//ver se é esse
import java.sql.Date;

public class Saves {
    private int id_save;
    private String descricao;
    private String Resumo;
    private String emuladores;
    private String arquivo;
    private String habilitado;
    private Date data_Hora;
    private String requisicao_nomeJogo;
    private int idUsuario;
    private int idJogo;

    public int getIdConsole() {
        return idConsole;
    }

    public void setIdConsole(int idConsole) {
        this.idConsole = idConsole;
    }

    private int idConsole;

    public int getId_save() {
        return id_save;
    }

    public void setId_save(int id_save) {
        this.id_save = id_save;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResumo() {
        return Resumo;
    }

    public void setResumo(String resumo) {
        Resumo = resumo;
    }

    public String getEmuladores() {
        return emuladores;
    }

    public void setEmuladores(String emuladores) {
        this.emuladores = emuladores;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Date getData_Hora() {
        return data_Hora;
    }

    public void setData_Hora(Date data_Hora) {
        this.data_Hora = data_Hora;
    }

    public String getRequisicao_nomeJogo() {
        return requisicao_nomeJogo;
    }

    public void setRequisicao_nomeJogo(String requisicao_nomeJogo) {
        this.requisicao_nomeJogo = requisicao_nomeJogo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }
}

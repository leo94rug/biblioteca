/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 
 */
public class Utente {

    private int tipo;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String professione;

    public Utente(ResultSet rs) throws SQLException {
        this.tipo = rs.getInt("tipo");
        this.nome = rs.getString("nome");
        this.cognome = rs.getString("cognome");
        this.email = rs.getString("email");
        this.indirizzo = rs.getString("indirizzo");
        this.professione=rs.getString("professione");
    }

    public Utente(String email) {
        this.email = email;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public String getProfessione() {
        return professione;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

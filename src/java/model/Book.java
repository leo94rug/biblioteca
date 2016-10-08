/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import uti.Gestione;

/**
 *
 * @author leo
 */
public class Book {

    private int tipo;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String professione;
    private String isbn;
    private String titolo;
    private String autore;
    private String editore;
    private String descrizione;
    private String url_img;
    private String utente_fk;
    private int anno_pub;
    private String lingua;
    private Date ultima_mod;
    private int buy;
    private String link_buy;
    private String link_download;
    private Double size;
    public String type;
    private Date data_ins;


    public Book(ResultSet rs) throws SQLException {

        this.isbn = rs.getString("isbn");
        this.titolo = rs.getString("titolo");
        this.autore = rs.getString("autore");
        this.editore = rs.getString("editore");
        this.descrizione = rs.getString("descrizione");
        this.url_img = rs.getString("url_img");
        this.url_img = "cover" + "\\" + url_img;
        this.utente_fk = rs.getString("utente_fk");
        this.anno_pub = rs.getInt("anno_pub");
        this.lingua = rs.getString("lingua");
        this.ultima_mod = rs.getDate("ultima_mod");
        this.buy = rs.getInt("buy");
        this.link_buy = rs.getString("link_buy");
        this.link_download = rs.getString("link_download");
        this.size = rs.getDouble("size");
        this.type = rs.getString("type");
        this.data_ins = rs.getDate("data_ins");
        if (type.equals("image/jpeg")) {
            type = "jpg";
        }
        if (type.equals("image/png")) {
            type = "png";
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public void setUtente_fk(String utente_fk) {
        this.utente_fk = utente_fk;
    }

    public void setAnno_pub(int anno_pub) {
        this.anno_pub = anno_pub;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public void setUltima_mod(Date ultima_mod) {
        this.ultima_mod = ultima_mod;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public void setLink_buy(String link_buy) {
        this.link_buy = link_buy;
    }

    public void setLink_download(String link_download) {
        this.link_download = link_download;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData_ins(Date data_ins) {
        this.data_ins = data_ins;
    }


    public int getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getProfessione() {
        return professione;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getEditore() {
        return editore;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getUrl_img() {
        return url_img;
    }

    public String getUtente_fk() {
        return utente_fk;
    }

    public int getAnno_pub() {
        return anno_pub;
    }

    public String getLingua() {
        return lingua;
    }

    public Date getUltima_mod() {
        return ultima_mod;
    }

    public int getBuy() {
        return buy;
    }

    public String getLink_buy() {
        return link_buy;
    }

    public String getLink_download() {
        return link_download;
    }

    public Double getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public Date getData_ins() {
        return data_ins;
    }

    
}
package com.github.nicolediana.model;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by pcnicole on 30/10/2014.
 */

@Entity
public class Utente extends JsonObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long idUtente;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String ruolo;

    @OneToOne
    private Profilo profilo;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Film> films;

    // Constructors
    /** default constructor */
    public Utente(){}

    public Utente(HttpServletRequest request) throws Exception {
        this.username=this.validate(String.class,request.getParameter("username"));
        this.password=this.validate(String.class,request.getParameter("password"));
    }

    /** minimal constructor */
    public Utente(Long idUtente){
        this.idUtente=idUtente;
    }

    /** full constructor */
    public Utente(Long idUtente,String username,String password,String ruolo, Profilo profilo, Set<Film> films){
        this.idUtente=idUtente;
        this.username=username;
        this.password=password;
        this.ruolo=ruolo;
        this.profilo=profilo;
        this.films=films;
    }

    //Metodi
    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Profilo getProfilo() {
        return profilo;
    }

    public void setProfilo(Profilo profilo) {
        this.profilo = profilo;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

}
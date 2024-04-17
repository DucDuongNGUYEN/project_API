package com.example.myapplication;

public class Client {
    private int id;
    private String nom;
    private String date_creation;
    private String email;
    private String tel;
    private String adresse;
    private String code_postal;
    private String ville;

    public Client(int id, String nom, String date_creation, String email,
                  String tel, String adresse, String code_postal, String ville) {
        this.id = id;
        this.nom = nom;
        this.date_creation = date_creation;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateCreation() {
        return date_creation;
    }

    public void setDateCreation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return code_postal;
    }

    public void setCodePostal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", date_creation='" + date_creation + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}

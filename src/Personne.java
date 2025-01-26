
public class Personne {
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;

    //Constructeur
    public Personne(String nom, String prenom, String telephone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
    }
    
    //Setters& Getters
    public String getNom() {
        return this.nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public String getAdresse() {
        return this.adresse;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String details() {
        return "Nom: " + getNom() + "\n Prénom: " + getPrenom() + "\n Téléphone: " + getTelephone() + "\n Adresse: " + getAdresse();
    }
}

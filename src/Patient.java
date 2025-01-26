public class Patient extends Personne {

    private DossierMedical dossierMedical;

    //Constructeurs
    public Patient(String nom, String prenom, String telephone, String adresse, double poids, double taille ) {
        super(nom, prenom, telephone, adresse);
        this.dossierMedical = new DossierMedical(poids,taille);
    }
    public Patient() {
        super("defaultNom", "defaultPrenom", "defaultTelephone", "defaultAdresse");
        this.dossierMedical = new DossierMedical(0.0, 0.0);
    }

    // Setters & Getters
    public DossierMedical getDossierMedical() {
        return this.dossierMedical;
    }
    public void setDossierMedical(DossierMedical dossierMedical) {
        this.dossierMedical = dossierMedical;
    }
    @Override
    public String details() {
        return "Patient : " + getNom() + " " + getPrenom() +"\nTelephone : " + getTelephone() +"\nAdresse :  " + getAdresse() ;
    }
}

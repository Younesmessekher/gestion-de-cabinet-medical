import java.util.ArrayList;

public class DossierMedical {
    private ArrayList<String> consultations;
    private ArrayList<String> allergies;
    private ArrayList<String> antecedants;
    private double poids;
    private double taille;

    public DossierMedical(ArrayList<String> consultations, ArrayList<String> allergies, ArrayList<String> antecedants, double poids, double taille) {
        this.consultations = consultations;
        this.allergies = allergies;
        this.antecedants = antecedants;
        this.poids = poids;
        this.taille = taille;
    }

    public DossierMedical(double poids,double taille){
        this.consultations = new ArrayList<>();
        this.allergies = new ArrayList<>();
        this.antecedants = new ArrayList<>();
        this.poids = poids;
        this.taille = taille;
    }

    //gestion du DM
    public void ajouterConsultation(String consultation) {
        this.consultations.add(consultation);
    }
    public void ajouterAllergie(String allergie) {
        this.allergies.add(allergie);
    }
    public void ajouterAntecedants(String antecedant) {
        this.antecedants.add(antecedant);
    }


    public void afficherConsultations() {
        System.out.println("Consultations: ");
        for (String consultation : this.consultations) {
            System.out.println(consultation);
        }
    }
    public void afficherAllergies() {
        System.out.println("Allergies: ");
        for (String allergie : this.allergies) {
            System.out.println(allergie);
        }
    }
    public void afficherAntecedants() {
        System.out.println("Antécédents: ");
        for (String antecedant : this.antecedants) {
            System.out.println(antecedant);
        }
    }


    //Setters & Getters
    public ArrayList<String> getConsultations() {
        return this.consultations;
    }
    public ArrayList<String> getAllergies() {
        return this.allergies;
    }
    public ArrayList<String> getAntecedants() {
        return this.antecedants;
    }
    public double getPoids() {
        return this.poids;
    }
    public double getTaille() {
        return this.taille;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }
    public void setAntecedants(ArrayList<String> antecedants) {
        this.antecedants = antecedants;
    }
    public void setPoids(double poids) {
        this.poids = poids;
    }
    public void setTaille(double taille) {
        this.taille = taille;
    }
    public void setConsultations(ArrayList<String> consultations) {
        this.consultations = consultations;
    }

    public String dossierM() {
        return 
            "Consultations : " + getConsultations() +
            "\nAllergies : " + getAllergies() + 
            "\nTraitements : " + getAntecedants() +
            "\nPoids : " + getPoids() +
            "\nTaille : " + getTaille() ;
    }
}
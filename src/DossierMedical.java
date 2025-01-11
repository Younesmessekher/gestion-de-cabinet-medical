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
    public void ajouterConsultation(String date, String heure) {
        getConsultations().add("Consultation Le:"+date +",a: "+heure);
    }
    public void ajouterAllergie(String allergie) {
        this.allergies.add(allergie);
    }
    public void ajouterAntecedants(String antecedant) {
        this.antecedants.add(antecedant);
    }


   public String consultationsStr() {
        StringBuilder strb = new StringBuilder();
        for (String consultation : getConsultations()) {
            strb.append(consultation).append("\n");
        }        
        return strb.toString();
    } 
    public String allergiesStr() {
        StringBuilder strb = new StringBuilder();
        for (String allergie : getAllergies()) {
            strb.append(allergie).append("\n| ");
        }        
        return strb.toString();
    }

    public String antecedantsStr() {
        StringBuilder strb = new StringBuilder();
        for (String antecedant : getAntecedants()) {
            strb.append(antecedant).append("\n| ");
        }        
        return strb.toString();
    }
    public String dossierM() {
        return 
            "\nConsultations : " + consultationsStr() +
            "\nAllergies : " + allergiesStr() + 
            "\nAntecedants : " + antecedantsStr() +
            "\nPoids : " + getPoids() +
            "\nTaille : " + getTaille() ;
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

}

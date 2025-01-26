import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionnairePt {
    private ArrayList<Patient> patients;

    //Constructeur
    public GestionnairePt() {
        this.patients = new ArrayList<>();
    }

    //Methodes sert a la gestion des Patients et leur Dossiers Medicaux
    public void ajouterPatient(Patient patient, Scanner in) {
        System.out.println("\n====> Les Informations du Patient :");
        System.out.print("\n Nom: ");
        patient.setNom(in.next());
        in.nextLine();
        System.out.print(" Prenom: ");
        patient.setPrenom(in.nextLine());
        System.out.print(" Numero de Telephone: ");
        patient.setTelephone(in.nextLine());
        System.out.print(" Adresse: ");
        patient.setAdresse(in.nextLine());
        boolean poidsValide = false;
        while (!poidsValide) {
            try {
                System.out.print(" Poids: ");
                float poids = in.nextFloat();
                if (poids >= 30 && poids <= 300) {
                    patient.getDossierMedical().setPoids(poids);
                    poidsValide = true;
                }else{
                    System.out.println(" (!) ENTRER UN POIDS VALIDE (!)");
                }
            } catch (InputMismatchException e) {
                System.out.println(" (!) ENTRER UN POIDS VALIDE (!)");
            }
        }
        boolean tailleValide = false;
        while (!tailleValide) {
            try {
                System.out.print(" Taille: ");
                float taille = in.nextFloat();
                if (taille >= 0.5 && taille <= 2) {
                    patient.getDossierMedical().setTaille(taille);
                    tailleValide = true;
                }else{
                    System.out.println("\n (!) ENTRER UNE TAILLE VALIDE (!)\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n (!) ENTRER UNE TAILLE VALIDE (!)\n");
            }
        }
        if (this.patients.contains(patient)) {
            System.out.println("\n (!) '" + patient.getNom() + "' EST DEJA DANS LA LISTE (!)\n");
        } else {
            this.patients.add(patient);
            System.out.println("\n (+) PATIENT AJOUTER AVEC SUCCES (+)\n");
        }
    }
    public void supprimerPatient(Scanner in) {
        System.out.print(" Index du patient a supprimer : ");
        int index = in.nextInt();
        if(index>=0 && index<getPatients().size()) {
            patients.remove(index);
            System.out.println("\n (-) PATIENT SUPPRIMER AVEC SUCCES (-)\n");
        }else {
            System.out.println("\n (!) VEUILLEZ ENTRER UN INDEX CORRECT (!)\n");
            supprimerPatient(in);
        }
    }
    public void listePatients(){
        System.out.println(
            " ___|LISTE DES PATIENTS "+
            "/");
        if(getPatients().isEmpty()) System.out.println("\n |liste des patients vide pour l'instant");
        else {
            for (int index = 0; index < getPatients().size(); index++) {
                System.out.println(" |(" + index + ") "+ getPatients().get(index).getNom()+" "+ getPatients().get(index).getPrenom());            
            }
        }
    }
    public void gestionDM(DateTimeFormatter frDate, DateTimeFormatter frHeure, Scanner in){
        System.out.print("\n\n Index du patient pour afficher son DM: ");
        int indexDM = in.nextInt();
        try{
            if(indexDM>=0 && indexDM<getPatients().size()){
                Patient patient = getPatients().get(indexDM);
                System.out.println(
                    "\n|+++++++++++++++++++++++++++++++++++++++|\n "+
                    " DOSSIER MEDICAL de M(Mme)."+ patient.getNom() +patient.getDossierMedical().dossierM()+
                    "|+++++++++++++++++++++++++++++++++++++++|\n\n");
                System.out.println(
                "\n\n 1\\Ajouter Consultation"+
                "\n 2\\Ajouter allergie "+
                "\n 3\\Ajouter antecedant"+
                "\n 4\\Afficher les Consultations"+
                "\n 5\\Afficher les allergies du patient"+
                "\n 6\\Afficher les antecedants du patient"+
                "\n 7\\RETOUR\n");
                int action=in.nextInt();
                in.nextLine();
            switch (action) {
                case 1:
                    patient.getDossierMedical().ajouterConsultation(LocalDate.now().format(frDate),LocalTime.now().format(frHeure));
                break;
                case 2:
                    System.out.println("\n Entrez l'allergie à ajouter: ");
                    String allergie = in.nextLine();
                    patient.getDossierMedical().ajouterAllergie(allergie);
                break;
                case 3:
                    System.out.println("\n Entrez l'antecedant à ajouter: ");
                    String antecedant = in.nextLine();
                    patient.getDossierMedical().ajouterAntecedants(antecedant);
                break;
                case 4:
                    System.out.print("\n_______________\n CONSULTATIONS\n"+
                    patient.getDossierMedical().consultationsStr());
                break;
                case 5:
                    System.out.print("\n_____________\n ALLERGIES\n"+
                    patient.getDossierMedical().allergiesStr());
                break;
                case 6:
                    System.out.println("\n_______________\n ANTECEDANTS \n"+
                    patient.getDossierMedical().antecedantsStr());
                break;
                default:
                break;
            }
        } else {
            System.out.println(" (!) ENTRER UN INDEX CORRECT (!)\n");
            gestionDM(frDate, frHeure, in);
            }
        }catch(InputMismatchException e){
            System.out.println(" (!) ENTRER UN INDEX CORRECT (!)\n");
            gestionDM(frDate, frHeure, in);
            }
        }
    public ArrayList<Patient> getPatients() {
        return this.patients;
    }
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}

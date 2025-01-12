import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionnairePt {
    private ArrayList<Patient> patients;

    public GestionnairePt() {
        this.patients = new ArrayList<>();
    }

    public void ajouterPatient(Patient patient, Scanner in) {
        System.out.println("\n>>> Les Informations du Patient à ajouter:");
        System.out.print("\nNom: ");
        patient.setNom(in.next());
        in.nextLine();
        System.out.print("Prenom: ");
        patient.setPrenom(in.nextLine());
        System.out.print("Numero de Telephone: ");
        patient.setTelephone(in.nextLine());
        System.out.print("Adresse: ");
        patient.setAdresse(in.nextLine());

  
        boolean poidsValide = false;
        while (!poidsValide) {
            try {
                System.out.print("Poids: ");
                double poids = in.nextDouble();
                if (poids <= 0) {
                    throw new IllegalArgumentException("Le poids doit être un nombre positif.");
                }
                patient.getDossierMedical().setPoids(poids);
                poidsValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide pour le poids.");
                in.next(); 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

   
        boolean tailleValide = false;
        while (!tailleValide) {
            try {
                System.out.print("Taille: ");
                double taille = in.nextDouble();
                if (taille <= 0) {
                    throw new IllegalArgumentException("La taille doit être un nombre positif.");
                }
                patient.getDossierMedical().setTaille(taille);
                tailleValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide pour la taille.");
                in.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (this.patients.contains(patient)) {
            System.out.println("____" + patient.getNom() + " EST DEJA DANS LA LISTE____");
        } else {
            this.patients.add(patient);
            System.out.println("____AJOUTER AVEC SUCCES____\n");
        }
    }
    public void supprimerPatient(Scanner in) {
        System.out.print("Index du patient a supprimer : ");
        int index = in.nextInt();
        if(index>=0 && index<getPatients().size()) {
            patients.remove(index);
            System.out.println("____SUPPRIMER AVEC SUCCES____\n");
        }else {
            System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
            supprimerPatient(in);
        }
    }
    public void listePatients(){
        System.out.println("LISTE DES PATIENTS");
        if(getPatients().isEmpty()) System.out.println("\n|liste des patients vide pour l'instant");
        else {
            for (int index = 0; index < getPatients().size(); index++) {
                System.out.println("|(" + index + ") "+ getPatients().get(index).getNom()+" "+ getPatients().get(index).getPrenom());            
            }
        }
    }


    public void gestionDM(Scanner in){
        System.out.print("\n\nIndex du patient pour afficher DM: ");
        int indexDM = in.nextInt();
        try{
            if(indexDM>=0 && indexDM<getPatients().size()){
                Patient patient = getPatients().get(indexDM);
                System.out.println(patient.getDossierMedical().dossierM());
                System.out.println(
                "\n\n1\\Ajouter Consultation"+
                "\n2\\Ajouter allergie "+
                "\n3\\Ajouter antecedant"+
                "\n4\\Afficher les Consultations"+
                "\n5\\Afficher les allergies du patient"+
                "\n6\\Afficher les antecedants du patient"+
                "\n7\\RETOUR\n");
                int action=in.nextInt();
                in.nextLine();
            switch (action) {
                case 1:
                    LocalDate date = LocalDate.now();
                    LocalTime heure = LocalTime.now();
                    DateTimeFormatter frDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter frHeure = DateTimeFormatter.ofPattern("HH:mm");
                    patient.getDossierMedical().ajouterConsultation(date.format(frDate),heure.format(frHeure));
                break;
                case 2:
                    System.out.println("\nEntrez l'allergie à ajouter: ");
                    String allergie = in.nextLine();
                    patient.getDossierMedical().ajouterAllergie(allergie);
                break;
                case 3:
                    System.out.println("\nEntrez l'antecedant à ajouter: ");
                    String antecedant = in.nextLine();
                    patient.getDossierMedical().ajouterAntecedants(antecedant);
                break;
                case 4:
                    System.out.print("\n______________\nCONSULTATIONS\n"+
                                     patient.getDossierMedical().consultationsStr());
                break;
                case 5:
                    System.out.print("\n____________\nALLERGIES\n"+
                                     patient.getDossierMedical().allergiesStr());
                break;
                case 6:
                    "\n______________\nANTECEDANTS\n"+
                    System.out.print(patient.getDossierMedical().antecedantsStr());
                break;
                default:
                break;
            }
        } else {
            System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
            gestionDM(in);
            }
        }catch(InputMismatchException e){
            System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
            gestionDM(in);
            }
        
    }


    public ArrayList<Patient> getPatients() {
        return this.patients;
    }
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

}

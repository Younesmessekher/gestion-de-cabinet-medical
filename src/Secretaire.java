
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Secretaire extends Personne{
    private LocalDateTime dateHeure;
    private ArrayList<RendezVous> listRdV;
    private DateTimeFormatter fr = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    private DateTimeFormatter frDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter frHeure = DateTimeFormatter.ofPattern("HH:mm");


    public Secretaire() {
        super(null, null,"+213000000000", null);
        this.listRdV = new ArrayList<>();
    }

    public Secretaire(String telephone ) {
        super(null, null, telephone,null);
        this.listRdV = new ArrayList<>();
    }

//planifier rendez-vous pour un patient existant
        public void  planifierRdVP(Medecin medecin ,GestionnairePt gestionPt,Scanner in) {
        System.out.print("Index du patient : ");
        int index = in.nextInt();
        if(index>=0 && index<gestionPt.getPatients().size()) {
            Patient patient = gestionPt.getPatients().get(index);
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Entrez la date du rendez-vous (dd/MM/yyyy): ");
                String dateStr = in.next();
                System.out.print("Entrez l'heure du rendez-vous (HH:mm): ");
                String heureStr = in.next();
                try {
                    dateHeure = LocalDateTime.of(LocalDate.parse(dateStr, frDate), LocalTime.parse(heureStr, frHeure));           
                    if (medecin.disponible(dateHeure)) {
                        this.listRdV.add(new RendezVous(patient, dateHeure));
                        medecin.getPlaning().add(dateHeure);
                        System.out.println("____Rendez-vous Ajouté____");
                        validInput = true;
                    } else {
                        System.out.println("____Date non disponible____");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("!! Format de date ou d'heure invalide !! ");
                }
            }
        }else {
            System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
            planifierRdVP(medecin, gestionPt, in);
        }
        }

//planifier rendez-vous pour un nouveau patient
        public void planifierRdVNP(Medecin medecin, GestionnairePt gestionPt, Scanner in) {
            System.out.print("\nNom : ");
            String nom = in.next();
            in.nextLine();
            System.out.print("Prenom: ");
            String prenom = in.nextLine();
            System.out.print("Numero de Telephone: ");
            String telephone = in.nextLine();
            System.out.print("Adresse: ");
            String adresse = in.nextLine();
            int poids = 0;
            boolean poidsValide = false;
            while (!poidsValide) {
                try {
                    System.out.print("Poids: ");
                    poids = in.nextInt();
                    if (poids <= 0) {
                        throw new IllegalArgumentException("Le poids doit être un nombre positif.");
                    }
                    poidsValide = true;
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre valide pour le poids.");
                    in.next(); 
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }          
            int taille = 0;
            boolean tailleValide = false;
            while (!tailleValide) {
                try {
                    System.out.print("Taille: ");
                    taille = in.nextInt();
                    if (taille <= 0) {
                        throw new IllegalArgumentException("La taille doit être un nombre positif.");
                    }
                    tailleValide = true;
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre valide pour la taille.");
                    in.next(); 
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            Patient patient = new Patient(nom, prenom, telephone, adresse, poids, taille);
            if (!gestionPt.getPatients().contains(patient)) {
                gestionPt.getPatients().add(patient);
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Entrez la date du rendez-vous (dd/MM/yyyy): ");
                    String dateStr = in.next();
                    System.out.print("Entrez l'heure du rendez-vous (HH:mm): ");
                    String heureStr = in.next();
                    try {
                        dateHeure = LocalDateTime.of(LocalDate.parse(dateStr, frDate), LocalTime.parse(heureStr, frHeure));
                        if (medecin.disponible(dateHeure)) {
                            this.listRdV.add(new RendezVous(patient, dateHeure));
                            medecin.getPlaning().add(dateHeure);
                            System.out.println("____Rendez-vous Ajouté____");
                            validInput = true;
                        } else {
                            System.out.println("____Planing Chargé____");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("!! Format de date ou d'heure invalide !! ");
                    }
                }
            }
        }

    public void annulerRdV(Scanner in) {
        System.out.print("Index du Rendez-Vous a annuler : ");
        try {
            int indexRdV = in.nextInt();
            if(indexRdV<this.listRdV.size()){    
                this.listRdV.remove(indexRdV);
                System.out.println("____ANNULER AVEC SUCCES____\n");
            }else{
                System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
                annulerRdV(in);
            }
        } catch (InputMismatchException e) {
            System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
            annulerRdV(in);
        }
        
    }

    public void ficheRdV(){
        if(this.listRdV.isEmpty())System.out.println("\n|Pas de rendez vous pour l'instant");
        else {
            for (int index = 0; index < getListRdV().size(); index++) {
                System.out.println("|(" + index + ") " + "Avec : " +getListRdV().get(index).getPatient().getNom()+ " " +getListRdV().get(index).getPatient().getPrenom()+", ("+getListRdV().get(index).getDateHeure().format(fr)+")");
            }
        }   
    }


    // Setters & Getters
    public LocalDateTime getDateHeure() {
        return this.dateHeure;
    }
    public ArrayList<RendezVous> getListRdV() {
        return this.listRdV;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }
    public void setListRdV(ArrayList<RendezVous> listRdV) {
        this.listRdV = listRdV;
    }

    @Override
    public String details() {
        return "N Telephone de Secretaire: " + getTelephone() + "\n";
    }
}

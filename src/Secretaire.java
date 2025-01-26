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

    //Constructeurs
    public Secretaire() {
        super(null, null,"+213000000000", null);
        this.listRdV = new ArrayList<>();
    }
    public Secretaire(String telephone ) {
        super(null, null, telephone,null);
        this.listRdV = new ArrayList<>();
    }

    //planifier rendez-vous pour un patient existant
    public void planifierRdVP(Medecin medecin ,GestionnairePt gestionPt, DateTimeFormatter frDate, DateTimeFormatter frHeure,Scanner in) {
        System.out.print(" Index du patient : ");
        int index = in.nextInt();
        if(index>=0 && index<gestionPt.getPatients().size()) {
            Patient patient = gestionPt.getPatients().get(index);
            boolean validInput = false;
            while (!validInput) {
                System.out.print(" Entrez la date du rendez-vous (dd/MM): ");
                String dateStr = in.next()+"/2025";
                System.out.print(" Entrez l'heure du rendez-vous (HH:mm): ");
                String heureStr = in.next();
                try {
                    dateHeure = LocalDateTime.of(LocalDate.parse(dateStr, frDate), LocalTime.parse(heureStr, frHeure));           
                    if (medecin.disponible(dateHeure)) {
                        RendezVous rdV = new RendezVous(patient, dateHeure);
                        getListRdV().add(rdV);
                        medecin.getPlaning().add(dateHeure);
                        System.out.println("\n (+) RENDEZ-VOUS AJOUTÉ AVEC SUCCÈS (+)\n");
                        validInput = true;
                    } else {
                        System.out.println("\n (!) DATE ET HEURE NON DISPONIBLE (!)\n");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("\n (!) FORMAT DE DATE OU HEURE INVALIDE (!)\n");
                }
            }
        }else {
            System.out.println("\n (!) VEUILLEZ ENTRER UN INDEX CORRECT (!)\n");
            planifierRdVP(medecin, gestionPt,frDate,frHeure,  in);
        }
        }
    //planifier rendez-vous pour un nouveau patient
    public void planifierRdVNP(Medecin medecin, GestionnairePt gestionPt,DateTimeFormatter frDate, DateTimeFormatter frHeure,  Scanner in) {
            Patient patient = new Patient();
            gestionPt.ajouterPatient(patient,in);
            boolean validInput = false;
            while (!validInput) {
                System.out.print(" Entrez la date du rendez-vous (dd/MM): ");
                String dateStr = in.next()+"/2025";
                System.out.print(" Entrez l'heure du rendez-vous (HH:mm): ");
                String heureStr = in.next();
                try {
                    dateHeure = LocalDateTime.of(LocalDate.parse(dateStr, frDate), LocalTime.parse(heureStr, frHeure));
                    if (medecin.disponible(dateHeure)) {
                        RendezVous rdV = new RendezVous(patient, dateHeure);
                        getListRdV().add(rdV);
                        medecin.getPlaning().add(dateHeure);
                        System.out.println("\n (+) RENDEZ-VOUS AJOUTÉ AVEC SUCCÈS (+)\n");
                        validInput = true;
                    } else {
                        System.out.println("\n (!) DATE ET HEURE NON DISPONIBLE (!)\n");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("\n (!) FORMAT DE DATE OU HEURE INVALIDE (!)\n");
                }
            }
        }
    public void annulerRdV(Medecin medecin, Scanner in) {
        System.out.print(" Index du Rendez-Vous a annuler : ");
        try {
            int indexRdV = in.nextInt();
            if(indexRdV<getListRdV().size()){    
                getListRdV().remove(indexRdV);
                medecin.getPlaning().remove(indexRdV);
                System.out.println("\n (-) ANNULER AVEC SUCCÈS (-)\n");
            }else{
                System.out.println("\n (!) ENTRER UN INDEX CORRECT (!)\n");
                annulerRdV(medecin,in);
            }
        } catch (InputMismatchException e) {
            System.out.println("\n (!) ENTRER UN INDEX CORRECT (!)\n");
            annulerRdV(medecin,in);
        }
        
    }
    public void ficheRdV(DateTimeFormatter frDate, DateTimeFormatter frHeure){
        if(getListRdV().isEmpty())System.out.println("\n |Pas de rendez vous pour l'instant");
        else {
            for (int index = 0; index < getListRdV().size(); index++) {
                System.out.println(" |(" + index + ") " + "Rendez-Vous Avec : " +getListRdV().get(index).getPatient().getNom()+ " " +getListRdV().get(index).getPatient().getPrenom()+", Le: "+ getListRdV().get(index).getDateHeure().format(frDate)+", a: "+getListRdV().get(index).getDateHeure().format(frHeure));
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

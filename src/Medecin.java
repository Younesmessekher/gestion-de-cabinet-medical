import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Medecin extends Personne {    
    
    private String specialite;
    private ArrayList<LocalDateTime> planing;

    //Constructeurs
    public Medecin() {
        super("","" , "", "");
        this.specialite = "";
        this.planing = new ArrayList<>();
    }

    public boolean disponible(LocalDateTime dateHeure) {
        return ((dateHeure.getHour()<18 && dateHeure.getHour()>8) && (!dateHeure.getDayOfWeek().equals(FRIDAY) && !dateHeure.getDayOfWeek().equals(SATURDAY)) && !getPlaning().contains(dateHeure));
    }
    public void initMedecin(Scanner in){
        System.out.print(" Nom: ");
        setNom(in.next());
        in.nextLine();
        System.out.print(" Prenom: ");
        setPrenom(in.nextLine());
        System.out.print(" Adresse: ");
        setAdresse(in.nextLine());
        System.out.print(" Specialite: ");
        setSpecialite(in.nextLine());
    }

    //Travail du Medecin 
    public void declareCertificat(GestionnairePt gestionPt, DateTimeFormatter frDate, DateTimeFormatter frHeure, Scanner in){
            System.out.print(" Entrer l'index du patient : ");
            int index = in.nextInt();
            if (index >= 0 && index<gestionPt.getPatients().size()) {
                Patient patient = gestionPt.getPatients().get(index);
                System.out.println(
                    "\n\n |=+=+=+=+=+=+=+=| CERTIFICAT MEDICAL |=+=+=+=+=+=+=+==|"+
                    "\n\n | Dr." + getNom() + 
                    "\n | Specialite: "+getSpecialite()+
                    "\n | Adresse: "+getAdresse()+
                    "\n |\n |    Je soussigné (e), Docteur "+getNom()+
                    "\n | certifie avoir examiné Mme/ M "+patient.getNom()+" "+patient.getPrenom()+"."+
                    "\n |\n| Fait à "+getAdresse()+", le "+LocalDate.now().format(frDate) + ", à " + LocalTime.now().format(frHeure) +"."+ 
                    "\n |=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=|");
            }else{
                System.out.println(" (!) ENTRER UN INDEX CORRECT (!)\n");
                declareCertificat(gestionPt,frDate,frHeure, in);
            }
        }
    public void ecritOrdonnance( DateTimeFormatter frDate, DateTimeFormatter frHeure, Scanner in) {
        Ordonnance ordn = new Ordonnance();
        ordn.ecritMedicament(in);
        System.out.println(
            "\n |+=+=+=+=+=+=+=+=+=+=| ORDONNANCE |=+=+=+=+=+=+=+=+=+=+=|\n\n" + 
            " |"+LocalTime.now().format(frHeure) +"        |--|        "+ LocalDate.now().format(frDate) +"|\n |"+ 
            "\n | Dr." + getNom() + "\n\n" + 
            ordn.medicamentsString() + 
            "\n |=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=|");
    }

    //Setters & Getters
    public String getSpecialite() {
        return this.specialite;
    }
    public void setSpecialite(String specialite){
        this.specialite = specialite;
    }
    public ArrayList<LocalDateTime> getPlaning() {
            return this.planing;
        }
    public void setPlaning(ArrayList<LocalDateTime> planing) {
        this.planing = planing;
    }
    @Override
    public String details() {
        return "Dr." + getNom() +" "+getPrenom()+ "\nSpécialité: " + getSpecialite() ;
    }
}

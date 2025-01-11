import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Medecin extends Personne {    
    private String specialite;
    private LocalDateTime dateHeure = LocalDateTime.now();
    private ArrayList<LocalDateTime> planing;
    private final DateTimeFormatter frDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter frHeure = DateTimeFormatter.ofPattern("HH:mm");


    public Medecin() {
        super("","" , "", "");
        this.specialite = "";
        this.planing = new ArrayList<>();
    }

    public boolean disponible(LocalDateTime dateHeure) {
        return ((dateHeure.getHour()<18 && dateHeure.getHour()>8) && (!dateHeure.getDayOfWeek().equals(FRIDAY) && !dateHeure.getDayOfWeek().equals(SATURDAY)) && !getPlaning().contains(dateHeure));
    }

    public void initMedecin(Scanner in){
        System.out.print("Nom: ");
        setNom(in.next());
        in.nextLine();
        System.out.print("Prenom: ");
        setPrenom(in.nextLine());
        System.out.print("Adresse: ");
        setAdresse(in.nextLine());
        System.out.print("Specialite: ");
        setSpecialite(in.nextLine());
    }


    //Medecin travail

    public void declareCertificat(GestionnairePt gestionPt,Scanner in){
            System.out.print("Enter l'index du patient : ");
            int index = in.nextInt();
            if (index >= 0 && index<gestionPt.getPatients().size()) {
                Patient patient = gestionPt.getPatients().get(index);
                System.out.println("\n\n_______________CERTIFICAT MEDICAL________________"+
                "Dr." + getNom() + 
                "\nSpecialite: "+getSpecialite()+
                "\nAdresse: "+getAdresse()+
                "\nTelephone(au cabinet médical): "+getTelephone()+
                "\n\n  Je soussigné (e), Docteur "+getNom()+
                "\ncertifie avoir examiné Mme/ M "+patient.getNom()+
                " "+patient.getPrenom()+"\nFait à "+getAdresse()+",le "+getDateHeure().format(frDate) +
                " ,à " + getDateHeure().format(frHeure) + "\n____________________________________________");
            }else{
            System.out.println("____VEUILLEZ ENTRER UN INDEX CORRECT!____\n");
            declareCertificat(gestionPt, in);
            }
        }

    public void ecritOrdonnance(Scanner in) {
        Ordonnance ordn = new Ordonnance();
        ordn.ecritMedicament(in);
        System.out.println("\n_______ORDONNANCE________\n" + 
        getDateHeure().format(frHeure) +"     --     "+ getDateHeure().format(frDate) + 
        "\nDr." + getNom() + "\n\n" + 
        ordn.medicamentsString() + 
        "\n__________________________");
    }


    //Setters & Getters
    public String getSpecialite() {
        return this.specialite;
    }
    public void setSpecialite(String specialite){
        this.specialite = specialite;
    }
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }
    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
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

import java.util.ArrayList;
import java.util.Scanner;

public class Ordonnance {
    private ArrayList<String> medicaments;

    //Constructeur
    public Ordonnance() {
        this.medicaments = new ArrayList<>();
    }
    
    //Generation de l'Ordonnance
    public void  ecritMedicament(Scanner in){
        System.out.print("\n Nom du Medicament : ");
        String medicament = in.next();
        in.nextLine();
        System.out.print(" Dosage : ");
        String dosage = in.nextLine();
        System.out.print(" Duree : ");
        String duree = in.nextLine();
        System.out.println(
            "\n\n 1\\Ajouter Medicament"+
            "\n 2\\TERMINER");
        int action = in.nextInt();
        switch (action) { 
            case 1 :
                ecritMedicament(in);
                break;
            default :
                break;
            }
        getMedicaments().add(" | "+medicament + " ,Dosage: " + dosage + " ,Pour: " + duree);
    }
    public String medicamentsString() {
        StringBuilder strb = new StringBuilder();
        for (String medicament : medicaments) {
            strb.append(medicament).append("\n");
        }
        return strb.toString();
    }

    //Setters & Getters
    public ArrayList<String> getMedicaments() {
        return this.medicaments;
    }
    public void setMedicaments(ArrayList<String> medicaments) {
        this.medicaments = medicaments;
    }
}

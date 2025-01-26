import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionCM {
    public static void main(String[] args) {
        //CabinetMedicalGUI cabinetGUI = new CabinetMedicalGUI();
        Scanner in = new Scanner(System.in);
        GestionnairePt gestionPt = new GestionnairePt();
        int choix = 0, action = 0, retour = 0;
        String telephone;
        DateTimeFormatter frDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter frHeure = DateTimeFormatter.ofPattern("HH:mm");

        //Initialisation du Staff (Docteur & Secrtaire)
        System.out.println(
            " \n (!) VEUILLEZ REMPLIR LES CHAMPS SUIVANTS POUR BIEN VOUS SERVIR (!)\n"+
            "\n|===========================|"+
            "\n| Initialisation du Medecin |"+
            "\n|===========================|\n");
        Medecin medecin = new Medecin();
        medecin.initMedecin(in);
        System.out.println(
            "\n|=====================================|\n"+
            "| Initialisation de la(le) Secretaire |\n"+
            "|=====================================|\n");
        System.out.print(" Numero de Telephone: ");
        telephone = in.nextLine();
        Secretaire secretaire = new Secretaire(telephone);
        
        //Debut du Program
        System.out.println(
            "\n\n|===============================================================|\n"+
            "| BIENVENUE A VOTRE CABINET MEDICAL VIRTUEL Dr." + medecin.getNom() +" |\n"+ 
            "|===============================================================|\n");
        boolean running = true;
        System.out.println(
            "| "+LocalDate.now().format(frDate) +
            "\n| Debut de Travail à " + LocalTime.now().format(frHeure));
        while (running) {
            System.out.println(
                "\n|===============================================================|\n\n" + 
                LocalTime.now().format(frHeure) +"\n\n"+
                " 1_Gestion Des Rendez-Vous\n" +
                (gestionPt.getPatients().isEmpty() ? " 2_Gestion Des Dossiers Medicaux(DM) (Pas de DM pour l'instant)\n" : " 2_Gestion Des Dossiers Medicaux(DM)\n") +
                " 3_Gestion Des Patients\n" +
                " 4_Ordonnance\n" +
                " 5_Certificat Medical\n" +
                " 6_QUITTER\n"
            );
            try {
                System.out.print("--> Choisissez une option : ");
                choix = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                in.next();
                continue; 
            }
            switch (choix) {
                case 1:
                    System.out.println(
                        "\n|=========================|\n"+
                        "| GESTION DES RENDEZ VOUS |\n"+
                        "|=========================|\n");
                    secretaire.ficheRdV(frDate, frHeure);
                    System.out.println(
                        "\n\n 1.Ajouter un Rendez-Vous Pour un Patient Existant\n" +
                        " 2.Ajouter un Rendez-Vous Pour un Nouveau Patient\n" +
                        " 3.Supprimer un Rendez-Vous\n" +
                        " 4.RETOUR\n"
                    );
                    try {
                        System.out.print("--> Choisissez une action : ");
                        action = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                        in.next(); 
                        break;
                    }
                    if (action == 1) {
                        if (gestionPt.getPatients().isEmpty()) {
                            gestionPt.listePatients();
                        } else {
                            gestionPt.listePatients();
                            secretaire.planifierRdVP(medecin, gestionPt,frDate, frHeure, in);
                        }
                    } else if (action == 2) {
                        secretaire.planifierRdVNP(medecin, gestionPt,frDate, frHeure, in);
                    } else if (action == 3) {
                        secretaire.annulerRdV(medecin,in);
                    } else {
                        break;
                    }
                    System.out.println("\n 1.RETOUR\n");
                    try {
                        System.out.print("--> Choisissez une action : ");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                        in.next();
                        break;
                    }
                    if (retour == 1) {
                        break;
                    }
                    break;
                case 2:
                    System.out.println(
                        "\n|===============================|\n"+
                        "| GESTION DES DOSSIERS MEDICAUX |\n"+
                        "|===============================|\n");
                    if (gestionPt.getPatients().isEmpty()) {
                        gestionPt.listePatients();
                        System.out.println("\n 1.RETOUR\n");
                        try {
                            System.out.print("--> Choisissez une action : ");
                            retour = in.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                            in.next();
                            break;
                        }
                        if (retour == 1) {
                                break;
                            }
                        break;
                    } else {
                        gestionPt.listePatients();
                        gestionPt.gestionDM(frDate, frHeure, in);
                        System.out.println("\n 1.RETOUR\n");
                        try {
                            System.out.print("--> Choisissez une action : ");
                            retour = in.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                            in.next();
                            break;
                        }
                        if (retour == 1) {
                                break;
                            }
                        break;
                        
                    }
                case 3:
                    System.out.println(
                        "\n|======================|\n"+
                        "| GESTION DES PATIENTS |\n"+
                        "|======================|\n");
                    gestionPt.listePatients();
                    System.out.println(
                        "\n 1.Ajouter Patient\n"+
                        " 2.Supprimer Patient\n"+
                        " 3.RETOUR\n");
                    try {
                        System.out.print("--> Choisissez une action : ");
                        action = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                        in.next(); 
                        break;
                    }
                    if (action == 1) {
                        Patient patient = new Patient();
                        gestionPt.ajouterPatient(patient, in);
                    } else if (action == 2) {
                        gestionPt.supprimerPatient(in);
                    } else {
                        break;
                    }
                    System.out.println("\n 1.RETOUR\n");
                    try {
                        System.out.print("--> Choisissez une action : ");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                        in.next();
                        break;
                    }
                    if (retour == 1) {
                        break;
                    }
                    break;
                case 4:
                    System.out.println(
                        "\n|============|\n"+
                        "| ORDONNANCE |\n"+
                        "|============|\n");
                    medecin.ecritOrdonnance(frDate, frHeure, in);
                    System.out.println("\n 1.RETOUR\n");
                    try {
                        System.out.print("--> Choisissez une action : ");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                        in.next();
                        break;
                    }
                    if (retour == 1) {
                        break;
                    }
                    break;
                case 5:
                    System.out.println(
                        "\n|====================|\n"+
                        "| CERTIFICAT MEDICAL |\n"+
                        "|====================|\n");
                    if (gestionPt.getPatients().isEmpty()) {
                        gestionPt.listePatients();
                    } else {
                        gestionPt.listePatients();
                        System.out.println(
                            "\n 1.Certificat pour Patient"+
                            "\n 2.RETOUR\n");
                        try {
                            System.out.print("--> Choisissez une action : ");
                            action = in.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                            in.next();
                            break;
                        }
                        if (action == 1) {
                            medecin.declareCertificat(gestionPt,frDate, frHeure, in);
                        } else {
                            break;
                        }
                    }
                    System.out.println("\n 1.RETOUR\n");
                    try {
                        System.out.print("--> Choisissez une action : ");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("\n (!) ENTRER UN NOMBRE VALIDE (!)\n");
                        in.next();
                        break;
                    }
                    if (retour == 1) {
                        break;
                    }
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("\n (!) ENTRER UN NOMBRE ENTRE 1 ET 6 (!)\n");
                    break;
            }
        }
        System.out.println(
            "\n|=======================|"+
            "| A BIENTOT Dr." + medecin.getNom()+
            "|=======================|\n");
        in.close();
    }
}

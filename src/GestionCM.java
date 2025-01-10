import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionCM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GestionnairePt gestionPt = new GestionnairePt();
        int choix = 0, action = 0, retour = 0;
        String telephone;
        LocalDate date = LocalDate.now();
        LocalTime heure = LocalTime.now();
        DateTimeFormatter frDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter frHeure = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("VEUILLEZ REMPLIR LES CHAMPS SUIVANTS POUR BIEN VOUS SERVIR\n___________________________\nInitialisation du Medecin:\n___________________________");
        Medecin medecin = new Medecin();
        medecin.initMedecin(in);
        System.out.println("\n_____________________________________\nInitialisation de la(le) Secretaire:\n_____________________________________");
        System.out.print("Numero de Telephone: ");
        telephone = in.nextLine();
        Secretaire secretaire = new Secretaire(telephone);
        System.out.println("\n\n____________________________________________________\nBIENVENUE A VOTRE CABINET MEDICAL VIRTUEL Dr." + medecin.getNom() + "\n____________________________________________________");
        boolean running = true;
        System.out.println(date.format(frDate));

        while (running) {
            System.out.println("\n________________________\n" + heure.format(frHeure) + "\n");
            System.out.println(
                "1_Gestion Des Rendez-Vous\n" +
                (gestionPt.getPatients().isEmpty() ? "2_Gestion Des Dossiers Medicaux(DM) (Pas de DM pour l'instant)\n" : "2_Gestion Des Dossiers Medicaux(DM)\n") +
                "3_Gestion Des Patients\n" +
                "4_Ordonnance\n" +
                "5_Certificat Medical\n" +
                "6_QUITTER\n"
            );

            try {
                System.out.print("Choisissez une option : ");
                choix = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                in.next();
                continue; 
            }

            switch (choix) {
                case 1:
                    secretaire.ficheRdV();
                    System.out.println(
                        "\n\n1.Ajouter un Rendez-Vous Pour un Patient Existant\n" +
                        "2.Ajouter un Rendez-Vous Pour un Nouveau Patient\n" +
                        "3.Supprimer un Rendez-Vous\n" +
                        "4.RETOUR\n"
                    );

                    try {
                        System.out.print("Choisissez une action : ");
                        action = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next(); 
                        break;
                    }

                    if (action == 1) {
                        if (gestionPt.getPatients().isEmpty()) {
                            gestionPt.listePatients();
                        } else {
                            gestionPt.listePatients();
                            secretaire.planifierRdVP(medecin, gestionPt, in);
                        }
                    } else if (action == 2) {
                        secretaire.planifierRdVNP(medecin, gestionPt, in);
                    } else if (action == 3) {
                        secretaire.annulerRdV(in);
                    } else {
                        break;
                    }

                    try {
                        System.out.println("\n1.RETOUR\n");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next(); 
                        break;
                    }

                    if (retour == 1) {
                        break;
                    }
                    break;

                case 2:
                    if (gestionPt.getPatients().isEmpty()) {
                        gestionPt.listePatients();
                    } else {
                        gestionPt.listePatients();
                        gestionPt.gestionDM(in);
                    }

                    try {
                        System.out.println("\n1.RETOUR\n");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next(); 
                        break;
                    }

                    if (retour == 1) {
                        break;
                    }
                    break;

                case 3:
                    System.out.println("\nListe des patients :\n\n");
                    gestionPt.listePatients();
                    System.out.println("\n1.Ajouter Patient\n2.Supprimer Patient\n3.RETOUR\n");

                    try {
                        System.out.print("Choisissez une action : ");
                        action = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next(); 
                        break;
                    }

                    if (action == 1) {
                        Patient patient = new Patient();
                        gestionPt.ajouterPatient(patient, in);
                    } else if (action == 2) {
                        gestionPt.supprimerPatient(in);
                    } else if (action == 3) {
                        break;
                    }

                    try {
                        System.out.println("\n1.RETOUR\n");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next(); 
                        break;
                    }

                    if (retour == 1) {
                        break;
                    }
                    break;

                case 4:
                    System.out.println("\nORDONNANCE :\n");
                    medecin.ecritOrdonnance(in);

                    try {
                        System.out.println("\n1.RETOUR\n");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
                        in.next();
                        break;
                    }

                    if (retour == 1) {
                        break;
                    }
                    break;

                case 5:
                    System.out.println("\nCERTIFICAT MEDICAL :\n");
                    if (gestionPt.getPatients().isEmpty()) {
                        gestionPt.listePatients();
                    } else {
                        gestionPt.listePatients();
                        System.out.println("\n1.Certificat pour Patient\n2.RETOUR\n");

                        try {
                            System.out.print("Choisissez une action : ");
                            action = in.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Erreur : Veuillez entrer un nombre valide.");
                            in.next();
                            break;
                        }

                        if (action == 1) {
                            medecin.declareCertificat(gestionPt, in);
                        } else {
                            break;
                        }
                    }

                    try {
                        System.out.println("\n1.RETOUR\n");
                        retour = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Erreur : Veuillez entrer un nombre valide.");
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
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 6.");
                    break;
            }
        }

        System.out.println("A BIENTOT Dr." + medecin.getNom());
        in.close();
    }
}
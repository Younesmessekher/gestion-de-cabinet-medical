Gestion de Cabinet Médical - Projet Java
Ce projet est une application de gestion de cabinet médical développée en Java. Il permet de gérer les patients, les rendez-vous, les dossiers médicaux, les ordonnances et les certificats médicaux.

Fonctionnalités
Gestion des Patients :

Ajouter un nouveau patient.

Supprimer un patient.

Afficher la liste des patients.

Gestion des Rendez-Vous :

Planifier un rendez-vous pour un patient existant ou nouveau.

Annuler un rendez-vous.

Afficher la liste des rendez-vous.

Gestion des Dossiers Médicaux (DM) :

Ajouter des consultations, des allergies et des antécédents médicaux.

Afficher les informations du dossier médical d'un patient.

Ordonnances :

Créer et afficher des ordonnances pour les patients.

Certificats Médicaux :

Générer des certificats médicaux pour les patients.

Structure du Projet
Le projet est organisé en plusieurs classes Java :

GestionCM : Classe principale qui contient le point d'entrée du programme (main).

Patient : Représente un patient avec ses informations personnelles et son dossier médical.

Medecin : Représente un médecin avec ses informations et ses fonctionnalités (consultations, ordonnances, certificats).

Secretaire : Gère les rendez-vous et les interactions avec les patients.

DossierMedical : Contient les informations médicales d'un patient (consultations, allergies, antécédents, poids, taille).

RendezVous : Représente un rendez-vous entre un patient et un médecin.

GestionnairePt : Gère la liste des patients et leurs dossiers médicaux.

Ordonnance : Représente une ordonnance médicale avec une liste de médicaments.

Prérequis
Java Development Kit (JDK) : Assure-toi d'avoir installé Java (version 8 ou supérieure).

IDE : Tu peux utiliser un IDE comme IntelliJ IDEA, Eclipse ou NetBeans pour exécuter le projet.

Comment Exécuter le Projet
Cloner le dépôt :

bash
Copy
git clone https://github.com/ton-utilisateur/gestion-cabinet-medical.git
cd gestion-cabinet-medical
Compiler et exécuter :

Ouvre le projet dans ton IDE.

Compile et exécute la classe GestionCM.

Utilisation :

Suis les instructions affichées dans la console pour interagir avec l'application.

Exemples d'Utilisation
Ajouter un Patient
Choisis l'option "Gestion Des Patients" dans le menu principal.

Sélectionne "Ajouter Patient".

Saisis les informations du patient (nom, prénom, téléphone, adresse, poids, taille).

Planifier un Rendez-Vous
Choisis l'option "Gestion Des Rendez-Vous" dans le menu principal.

Sélectionne "Ajouter un Rendez-Vous Pour un Patient Existant" ou "Ajouter un Rendez-Vous Pour un Nouveau Patient".

Saisis la date et l'heure du rendez-vous.

Afficher un Dossier Médical
Choisis l'option "Gestion Des Dossiers Medicaux (DM)" dans le menu principal.

Sélectionne un patient dans la liste.

Affiche ou modifie les informations du dossier médical.

Auteur
HAMDAOUI YACINE , MESSEKHER YOUNES , DJEBBAR ADEL SEDDIK , CHEDDIDI RAHIM

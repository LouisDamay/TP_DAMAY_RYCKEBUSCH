import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class Main {

    static java.util.Scanner sc = new java.util.Scanner(System.in);
    private static List<Armee> armees = new ArrayList<>();

    static int MENU_WIDTH = 90;

    /* J'ai chippé cette fonction sur Internet.
     * Elle fonctionne super bien sauf sur la console des IDE.
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }

    /* Fonction qui rend un affichage de titres très compliqué,
     * mais adaptif aux écrans selon une seule valeur et ça, 
     * c'est pas mal.
     */
    private static String createFiller(int length) {
        return "-".repeat(Math.max(0, length));
    }
    public static void sweetTitle(int menu, String text) {
        int fill = 0;
        int offset = 0;

        clearConsole();

        if (text != null) {
            offset = text.length();
        }

        switch (menu) {
            case 0: {
                fill = MENU_WIDTH - 32;
                System.out.println("\n----- Éditeur de liste d'armée " + createFiller(fill) + "\n");
                break;
            }
            case 1: {
                fill = MENU_WIDTH - 53 - offset;
                System.out.println("\n----- Éditeur de liste d'armée --> Gérer l'armée \"" + text + "\" " + createFiller(fill) + "\n");
                break;
            }
            case 2: {
                fill = MENU_WIDTH - 52;
                System.out.println("\n----- Éditeur de liste d'armée --> Créer une armée " + createFiller(fill) + "\n");
                break;
            }
            case 3: {
                fill = MENU_WIDTH - 56;
                System.out.println("\n----- Éditeur de liste d'armée --> Supprimer une armée " + createFiller(fill) + "\n");
                break;
            }
            case 4: {
                fill = MENU_WIDTH - 53 - offset;
                System.out.println("\n----- Éditeur de liste d'armée --> Gérer l'armée \"" + text + "\" --> Ajouter un groupe d'unité " + createFiller(fill) + "\n");
                break;
            }
            case 5: {
                fill = MENU_WIDTH - 57 - offset;
                System.out.println("\n----- Éditeur de liste d'armée --> Gérer l'armée \"" + text + "\" --> Supprimer un groupe d'unités " + createFiller(fill) + "\n");
                break;
            }
            case 6: {
                fill = MENU_WIDTH - 64 - offset;
                System.out.println("\n----- Éditeur de liste d'armée --> Gérer l'armée \"" + text + "\" --> Supprimer une unité dans un groupe " + createFiller(fill) + "\n");
                break;
            }
        }
    }

    // MENU PRINCPAL --------------------------------------------------------------------
    
    // 1. Gérer une armée ---------------------------------------------------------------
    public static void gererArmee(int choix) {
	    boolean terminé = false;
	
	    while (!terminé) {
	        sweetTitle(1, armees.get(choix - 2).getNom());
	
	        System.out.println("\t1 : Ajouter un groupe d'unités");
	        System.out.println("\t2 : Afficher un récapitulatif");
	        System.out.println("\t3 : Supprimer une unité");
	        System.out.println("\t4 : Supprimer un groupe");
	        System.out.println("\t5 : Ajouter une unité à un groupe"); // Nouvelle option
	        System.out.println("\t6 : Retour");
	
	        System.out.print("\nChoix : ");
	        int choix1 = -1;
	
	        try {
	            choix1 = sc.nextInt();
	            sc.nextLine(); // Consommer la ligne restante
	
	            switch (choix1) {
	                case 1: {
	                    armees.get(choix - 2).ajouterGroupe();
	                    break;
	                }
	                case 2: {
	                    armees.get(choix - 2).print();
	                    break;
	                }
	                case 3: {
	                    supprimerUnite(armees.get(choix - 2));
	                    break;
	                }
	                case 4: {
	                    supprimerGroupe(armees.get(choix - 2));
	                    break;
	                }
	                case 5: { // Appeler la méthode pour ajouter une unité
	                    ajouterUniteGroupe(armees.get(choix - 2));
	                    break;
	                }
	                case 6: {
	                    terminé = true;
	                    break;
	                }
	                default: {
	                    System.out.println("Choix invalide. Réessayez.");
	                }
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Choix invalide. Réessayez.");
	            sc.next(); // Vider le buffer du scanner
	        }
	    }
	}

    public static void ajouterGroupesUnites(Armee armee) {
        sweetTitle(4, armee.getNom());

        GroupeUnite groupe_a_ajouter = new GroupeUnite();
        armee.addGroupe(groupe_a_ajouter); // L'ajout lie automatiquement le groupe à l'armée
        armee.updatePts(); // Ajout explicite : recalcul des points après ajout
    }

    public static void supprimerUnite(Armee armee) {
        sweetTitle(6, armee.getNom());

        System.out.println("\nDans quel groupe voulez-vous supprimer une unité ?\n");

        if (!armee.getGroupes().isEmpty()) {
            for (int i = 0; i < armee.getGroupes().size(); i++) {
                System.out.println("\t" + i + " : Groupe \"" + armee.getGroupes(i).nom + "\"");
            }
            System.out.println("\t" + armee.getGroupes().size() + " : Retour");

            int choixGroupe = -1;
            while (choixGroupe == -1) {
                System.out.print("Choix : ");
                try {
                    choixGroupe = sc.nextInt();
                    if (choixGroupe == armee.getGroupes().size()) return;

                    GroupeUnite groupe = armee.getGroupes(choixGroupe);

                    System.out.println("\nUnités dans le groupe " + groupe.nom + " :");
                    for (int i = 0; i < groupe.getUnites().size(); i++) {
                        System.out.println("\t" + i + " : " + groupe.getUnites().get(i).nom);
                    }
                    System.out.println("\t" + groupe.getUnites().size() + " : Retour");

                    System.out.print("Choix de l'unité à supprimer : ");
                    int choixUnite = sc.nextInt();
                    if (choixUnite == groupe.getUnites().size()) return;

                    groupe.supprimerUnite(choixUnite);

                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Entrée invalide. Réessayez.");
                    sc.nextLine();
                }
            }
        } else {
            System.out.println("Aucun groupe à gérer.");
        }
    }

    public static void supprimerGroupe(Armee armee) {
        sweetTitle(5, armee.getNom());

        System.out.println("\nQuel groupe voulez-vous supprimer ?\n");

        if (!armee.getGroupes().isEmpty()) {
            for (int i = 0; i < armee.getGroupes().size(); i++) {
                System.out.println("\t" + i + " : Groupe \"" + armee.getGroupes(i).nom + "\"");
            }
            System.out.println("\t" + armee.getGroupes().size() + " : Retour");

            boolean termine = false;
            while (!termine) {
                System.out.print("Choix : ");
                try {
                    int choix = sc.nextInt();
                    if (choix == armee.getGroupes().size()) return;

                    armee.supprimerGroupe(choix);
                    termine = true;
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Entrée invalide. Réessayez.");
                    sc.nextLine();
                }
            }
        } else {
            System.out.println("Aucun groupe à supprimer.");
        }
    }
    
    public static void ajouterUniteGroupe(Armee armee) {
        System.out.println("\nDans quel groupe voulez-vous ajouter une unité ?\n");

        if (!armee.getGroupes().isEmpty()) {
            for (int i = 0; i < armee.getGroupes().size(); i++) {
                System.out.println("\t" + i + " : Groupe \"" + armee.getGroupes(i).nom + "\"");
            }
            System.out.println("\t" + armee.getGroupes().size() + " : Retour\n");

            int choixGroupe = -1;
            while (choixGroupe == -1) {
                System.out.print("Choix : ");
                try {
                    choixGroupe = sc.nextInt();
                    sc.nextLine(); // Consommer la ligne restante

                    if (choixGroupe == armee.getGroupes().size()) return;

                    GroupeUnite groupe = armee.getGroupes(choixGroupe);
                    System.out.println("Ajout d'une unité dans le groupe \"" + groupe.nom + "\".");
                    groupe.ajouterUnite();
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Entrée invalide. Réessayez.");
                    sc.nextLine();
                }
            }
        } else {
            System.out.println("Aucun groupe disponible pour ajouter une unité.");
        }
    }

    // 2. Supprimer une armée -----------------------------------------------------------
    public static void supprimerArmee() {
        sweetTitle(3, null);

        if (!armees.isEmpty()) {
            // Afficher les options pour supprimer une armée
            for (int i = 0; i < armees.size(); i++) {
                System.out.println("\t" + i + " : Supprimer armée \"" + armees.get(i).getNom() + "\"");
            }
            System.out.println("\t" + armees.size() + " : Retour");

            boolean termine = false;
            while (!termine) {
                System.out.print("Choix : ");
                try {
                    int choix = sc.nextInt();
                    sc.nextLine();

                    if (choix >= 0 && choix < armees.size()) {
                        System.out.println("Armée \"" + armees.get(choix).getNom() + "\" supprimée.");
                        armees.remove(choix);
                        termine = true;
                    } else if (choix == armees.size()) {
                        termine = true;
                    } else {
                        System.out.println("Choix invalide. Réessayez.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                    sc.next(); // Vider le buffer du scanner
                }
            }
        } else {
            System.out.println("Aucune armée à supprimer. Commencez par en créer une.");
            sc.next();
            sc.next();
        }
    }

    // Afficher le menu principal de l'application
    public static void main(String[] args) {
    	
    	// DEBUG ONLY
        armees.add(new Armee("François", "Space Marines", 45000));
        armees.add(new Armee("Louis", "Elfes Magiques", 12000));
        armees.get(0).addGroupe(new GroupeUnite("Escadron 44"));
        armees.get(0).addGroupe(new GroupeUnite("Commandement 3"));
        // /!\ TO REMOVE
        
        while (true) {
            clearConsole();
            sweetTitle(0, null);
            System.out.println("1 : Créer une armée");

            if (!armees.isEmpty()) {
                for (int i = 0; i < armees.size(); i++) {
                    System.out.println((i + 2) + " : Sélectionner armée \"" + armees.get(i).getNom() + "\"");
                }
                System.out.println((armees.size() + 2) + " : Supprimer une armée");
            }
            System.out.println("0 : Quitter");

            System.out.print("\nChoix : ");

            try {
                int choix = sc.nextInt();

                if (choix == 0) {
                    System.out.println("\nAu revoir !");
                    break;

                } else if (choix == 1) {
                    clearConsole();
                    sweetTitle(2, null);
                    armees.add(new Armee());

                } else if (choix > 1 && choix <= armees.size() + 1) {
                    gererArmee(choix);

                } else if (choix == armees.size() + 2) {
                    supprimerArmee();

                } else {
                    System.out.println("Choix invalide. Réessayez.");
                    sc.nextLine();
                    sc.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println("Choix invalide. Réessayez.");
                sc.nextLine();
                sc.nextLine();
            }
        }
    }
}

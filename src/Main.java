import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class Main {

	static java.util.Scanner sc = new java.util.Scanner(System.in);
    private static List<Armee> armees = new ArrayList<>();
    
	static int menu_width = 120;
	
	/* J'ai chippé cette fonction sur Internet.
	 * Elle fonctionne super bien sauf sur la console des IDE.
	 */
	public static void clearConsole() {
	    try {
	        if (System.getProperty("os.name").contains("Windows")) {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        }
	        else {
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
		
		if(text != null) {offset = text.length(); }
		
		switch(menu) {
			case 0:{
				fill = menu_width - 32;
				System.out.println("\n----- Éditeur de liste d'armée "+ createFiller(fill) +"\n");
				break;
			}
			case 1:{
				fill = menu_width - 53 - offset;
				System.out.println("\n----- Éditeur de liste d'armée --> Gérer l'armée \"" + text +"\" " +  createFiller(fill) +"\n");
		        break;
			}
			case 2:{
				fill = menu_width - 52;
				System.out.println("\n----- Éditeur de liste d'armée --> Créer une armée " + createFiller(fill) + "\n");
				break;
			}
			case 3:{
				fill = menu_width - 56;
				System.out.println("\n----- Éditeur de liste d'armée --> Supprimer une armée " + createFiller(fill) + "\n");
				break;
			}
		}
	}

    public static void main(String[] args) {
        
        // DEBUG ONLY : ENLEVER LES LIGNES SUIVANTES
        armees.add(new Armee("François", "Space Marines", 45000));
        armees.add(new Armee("Louis", "Elfes Magiques", 12000));
        
        

        // Appel initial du menu
    	while (true) {
        	clearConsole();
            sweetTitle(0,null);
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
                	// Créer une armée
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
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Choix invalide. Réessayez.");
                sc.nextLine();
            }

        }
    	
    }
    
    public static void gererArmee(int choix) {
        
    	boolean terminé = false;
        
        while(!terminé) {
        	sweetTitle(1, armees.get(choix - 2).getNom());

            System.out.println("\t 1 : Ajouter des groupes d'unités");
            System.out.println("\t 2 : Afficher un récapitulatif");
            System.out.println("\t 3 : Supprimer une unité");
            System.out.println("\t 4 : Supprimer un groupe");
            System.out.println("\t 5 : Retour");
            
            System.out.print("\nChoix : ");
            int choix1 = -1;
            try {
                choix1 = sc.nextInt();                
	        } catch (InputMismatchException e) {
	            System.out.println("Choix invalide. Réessayez.");
	            sc.next(); // Pour vider le buffer du scanner
	        }
            
            if(choix1 != -1) {
                sc.nextLine(); // Consommer la ligne restante	
                switch(choix1) {
	                case 1:{
	                	// Ajouter des groupes d'unités
	                	ajouterGroupesUnites(armees.get(choix - 2));
	                	break;
	                }
	                case 2:{
	                	// Afficher un récapitulatif
	                	break;
	                }
	                case 3:{
	                	// Supprimer une unité
	                	break;
	                }
	                case 4:{
	                	// Supprimer un groupe
	                	break;
	                }
	                case 5:{
	                	terminé = true;
	                	break;
	                }
                }
            }


        }
    }
    
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
                    int choix = sc.nextInt(); // Lire le choix de l'utilisateur
                    sc.nextLine(); // Consommer le reste de la ligne

                    if (choix >= 0 && choix < armees.size()) {
                        System.out.println("Armée \"" + armees.get(choix).getNom() + "\" supprimée.");
                        armees.remove(choix); // Supprimer l'armée sélectionnée
                        termine = true; // Terminer la boucle
                    } else if (choix == armees.size()) {
                        termine = true; // Retour
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
        }
    }
    
    public static void ajouterGroupesUnites(Armee armee) {
    	System.out.println(armee.nom);
    	
    }
}
	
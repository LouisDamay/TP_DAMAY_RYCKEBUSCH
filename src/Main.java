import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	static java.util.Scanner sc = new java.util.Scanner(System.in);
	
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
	
	private static void afficherMenu(ArrayList<Armee> armee_tab) {
	    // Liste des options définie en dehors de la boucle
	    ArrayList<Menu.Option> optionsList = new ArrayList<>();

	    while (true) {
	        clearConsole();

	        // Effacer les options existantes pour les régénérer
	        optionsList.clear();

	        // Ajouter les options pour chaque armée existante
	        for (Armee armee : armee_tab) {
	            optionsList.add(new Menu.Option(
	                "Sélectionner " + armee.nom,
	                armee.ajouterGroupeUnite
	            ));
	        }

	        // Ajouter l'option pour créer une nouvelle armée
	        optionsList.add(new Menu.Option("Créer une nouvelle armée", () -> {
	            armee_tab.add(new Armee());
	        }));

	        // Convertir la liste en tableau et afficher le menu
	        Menu.Option[] options = optionsList.toArray(new Menu.Option[0]);
	        Menu menu_selectArmy = new Menu(60, "Sélectionner une armée", options);

	        // Afficher le menu et exécuter l'option sélectionnée
	        if(menu_selectArmy.show()) break;
	    }
	}




    public static void main(String[] args) {
    	
    	 // Liste des armées
        ArrayList<Armee> armee_tab = new ArrayList<>();

        // Appel initial du menu
        afficherMenu(armee_tab);
        
        // Vider tout
        clearConsole();
        
        System.out.print("Fin du programme.");
    	
    	// 3. L'armée est sélectionnée : remplir, afficher, supprimer des unités OU groupes
    		// 3. a. Remplir l'armée : ajouter une unité
    			// Choisir entre les types
    			// "Ce type n'existe pas : créer ?"
    	
    		// 3. b. Afficher l'armée
    		// 3. c. Supprimer une unité ou un groupe
    }
	
}
	
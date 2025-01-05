import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GroupeUnite {
    String nom;
    int total_points;
    private List<Unite> unites = new ArrayList<>();
    private Armee armeeParente;	

    static java.util.Scanner sc = new java.util.Scanner(System.in);

    public GroupeUnite() {
        boolean name_enter = true;
        while (name_enter) {
            System.out.println("  Entrez un nom pour le groupe d'unité : ");
            String res = sc.nextLine();
            if (res.isBlank()) {
                System.out.println("  Entrez un nom valide.");
            } else {
                this.nom = res;
                name_enter = false;
            }
        }
    }

    public GroupeUnite(String nom) {
        this.nom = nom;
    }

    private void updateTotalPoints() {
        int total = 0;
        for (Unite unite : unites) {
            total += unite.cout;
        }
        this.total_points = total;
    }

    public List<Unite> getUnites() {
        return unites;
    }
    
    public void ajouterUnite() {
        boolean ajouté = false;
        while (!ajouté) {
            try {
                System.out.println("\nEntrez le type d'unité (Infanterie ou Véhicule) : ");
                String type = sc.nextLine().toLowerCase();

                if ("infanterie".equals(type)) {
                    System.out.println("Entrez le nom de l'infanterie : ");
                    String nom = sc.nextLine();

                    System.out.println("Entrez le coût en points : ");
                    int cout = sc.nextInt();
                    sc.nextLine(); // Consommer la ligne restante

                    System.out.println("Entrez le type d'infanterie (SOLDAT, LOURD, SPECIAL, CHEF) : ");
                    String typeInf = sc.nextLine().toUpperCase();
                    TypeInfanterie typeInfanterie = TypeInfanterie.valueOf(typeInf);

                    Infanterie infanterie = new Infanterie(nom, cout, typeInfanterie);
                    addUnite(infanterie);
                    ajouté = true;

                } else if ("vehicule".equals(type)) {
                    System.out.println("Entrez le nom du véhicule : ");
                    String nom = sc.nextLine();

                    System.out.println("Entrez le coût en points : ");
                    int cout = sc.nextInt();
                    sc.nextLine(); // Consommer la ligne restante

                    System.out.println("Entrez le type de véhicule (Transport ou Attaque) : ");
                    String typeVeh = sc.nextLine();

                    int capacite = 0;
                    if ("Transport".equalsIgnoreCase(typeVeh)) {
                        System.out.println("Entrez la capacité de transport : ");
                        capacite = sc.nextInt();
                        sc.nextLine();
                    }

                    Vehicule vehicule = new Vehicule(nom, cout, typeVeh, capacite);
                    addUnite(vehicule);
                    ajouté = true;

                } else {
                    System.out.println("Type d'unité invalide. Réessayez.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage() + ". Réessayez.");
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer des valeurs valides.");
                sc.nextLine();
            }
        }
    }
    
    public void print() {
        System.out.println("    Groupe : " + nom + " (" + total_points + " pts)");
        for (Unite unite : unites) {
            unite.print();
        }
    }
    

	public void setArmeeParente(Armee armee) {
	    this.armeeParente = armee;
	}
	
	private void notifyArmeeParent() {
	    if (armeeParente != null) {
	        armeeParente.updatePts();
	    }
	}
	 
	public void addUnite(Unite unite) {
	    if (armeeParente != null && armeeParente.pts + unite.cout > armeeParente.pts_max) {
	        System.out.println("Erreur : L'ajout de cette unité dépasse le nombre maximal de points de l'armée.");
	        return; // Arrête l'ajout
	    }

	    this.unites.add(unite);
	    updateTotalPoints();
	    notifyArmeeParent();
	}


	public void removeUnite(Unite unite) {
	    this.unites.remove(unite);
	    updateTotalPoints();
	    notifyArmeeParent();
	}
	
	public void supprimerUnite(int index) {
	    if (index >= 0 && index < unites.size()) {
	        Unite uniteSupprimee = unites.get(index);
	        unites.remove(index);
	        System.out.println("Unité " + uniteSupprimee.nom + " supprimée avec succès.");
	        updateTotalPoints();
	        notifyArmeeParent();
	    } else {
	        System.out.println("Erreur : Index invalide. Aucune unité supprimée.");
	    }
	}

	public void supprimerUnite(Unite unite) {
	    if (unites.remove(unite)) {
	        System.out.println("Unité " + unite.nom + " supprimée avec succès.");
	        updateTotalPoints();
	        notifyArmeeParent();
	    } else {
	        System.out.println("Erreur : Unité non trouvée.");
	    }
	}


}

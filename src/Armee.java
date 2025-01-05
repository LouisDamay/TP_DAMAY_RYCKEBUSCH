import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Armee {
    String nom;
    String faction;
    int pts_max;
    int pts;
    private List<GroupeUnite> groupes = new ArrayList<>();

    static java.util.Scanner sc = new java.util.Scanner(System.in);

    public Armee(){
    	boolean name_enter = true;
		while (name_enter) {
			System.out.println("  Entrez un nom pour l'armée : ");
			String res = sc.nextLine();
			if (res.isBlank() || res.equals("Damien Louet")){
				System.out.println("  Entrez un nom valide.");
			} else {
				this.nom = res;
				name_enter = false;
			}
		}
		
		boolean fac_enter = true;
		while (fac_enter) {
			System.out.println("  Entrez un nom de faction : ");
			String res = sc.nextLine();
			if (res.isBlank()){
				System.out.println("  Entrez un nom valide.");
			} else {
				this.faction = res;
				fac_enter = false;
			}
		}
		
		boolean pts_max_enter = true;
		int res = 0;

		while (pts_max_enter) {
		    try {
		        System.out.println("  Entrez le nombre maximal de points d'armée.");
		        res = sc.nextInt();
		        if (res < 1) {
		            throw new IllegalArgumentException("  Le nombre de points doit être supérieur à 0.");
		        }
		        pts_max_enter = false;
		    } catch (InputMismatchException e) {
		        System.out.println("  Erreur : Veuillez entrer un entier valide.");
		        sc.nextLine();
		    } catch (IllegalArgumentException e) {
		        System.out.println("  Erreur : " + e.getMessage());
		    }
		}
		this.pts_max = res;

		System.out.println("  Armée crée avec succès !\n  Appuyez sur entrée pour revenir au menu principal.");
		sc.nextLine(); sc.nextLine();
    }

    public Armee(String nom, String faction, int pts_max) {
        this.nom = nom;
        this.faction = faction;
        this.pts_max = pts_max;
    }

    public String getNom() {
        return this.nom;
    }

    public List<GroupeUnite> getGroupes() {
        return this.groupes;
    }

    public GroupeUnite getGroupes(int i) {
        return this.groupes.get(i);
    }

    public void addGroupe(GroupeUnite groupe_a_ajouter) {
        this.groupes.add(groupe_a_ajouter);
        groupe_a_ajouter.setArmeeParente(this); // Nouvel ajout : lier le groupe à l'armée
        updatePts(); // Recalcule les points après l'ajout
    }


    public void remGroupe(GroupeUnite groupe_a_enlever) {
        if (groupes.remove(groupe_a_enlever)) {
            System.out.println("Groupe " + groupe_a_enlever.nom + " supprimé avec succès.");
            updatePts();
        } else {
            System.out.println("Erreur : Ce groupe n'existe pas. Aucune suppression effectuée.");
        }
    }

    public void supprimerGroupe(int index) {
        if (index >= 0 && index < groupes.size()) {
            GroupeUnite groupeSupprime = groupes.get(index);
            remGroupe(groupeSupprime);
        } else {
            System.out.println("Erreur : Index invalide. Aucun groupe supprimé.");
        }
    }

    void updatePts() {
        int totalPoints = 0;
        for (GroupeUnite groupe : groupes) {
            totalPoints += groupe.total_points;
        }
        this.pts = totalPoints;
    }

    public void ajouterGroupe() {
        GroupeUnite groupe = new GroupeUnite();
        addGroupe(groupe);

        boolean terminé = false;
        while (!terminé) {
            System.out.println("\n\tQue voulez-vous faire ?");
            System.out.println("\t1 : Ajouter une unité dans le groupe");
            System.out.println("\t2 : Terminer l'ajout de ce groupe");

            try {
                int choix = sc.nextInt();
                sc.nextLine(); // Consommer la ligne restante

                if (choix == 1) {
                    groupe.ajouterUnite();
                } else if (choix == 2) {
                    terminé = true;
                } else {
                    System.out.println("\tChoix invalide. Réessayez.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\\tEntrée invalide. Veuillez entrer un nombre.");
                sc.next(); // Vider le buffer du scanner
            }
        }
    }

    
    public void print() {
        System.out.println("\n  Armée : " + this.nom);
        System.out.println("  Points max : " + this.pts_max);
        System.out.println("  Points utilisés : " + this.pts);

        if (this.groupes.size() > 0) {
            System.out.println("\n  Groupes : ");
            for (GroupeUnite groupe : groupes) {
                groupe.print();
            }
        } else {
            System.out.println("\n  Aucun groupe ajouté pour le moment.");
        }

        sc.nextLine();
    }
}
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Armee{
	
	// VARIABLES
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

    // Pour le debug
    public Armee(String nom, String faction, int pts_max) {
    	this.nom = nom;
    	this.faction = faction;
    	this.pts_max = pts_max;
    }
    
	public String getNom() {
		return this.nom;
	}
	
	public void addGroupe(GroupeUnite groupe_a_ajouter) {
		this.groupes.add(groupe_a_ajouter);
	}
	
	public void print() {
		System.out.println("\n  Armée : " + this.nom);
		System.out.println("  Points max : " + this.pts_max);
		System.out.println("  Points utilisés : " + this.pts);
		
		if(this.groupes.size() > 0) {
			System.out.println("\n  Groupes : ");
			
			for (GroupeUnite groupe : groupes) {
				System.out.println("  - "+ groupe.nom);
				// Autre boucle sur les unités ensuite
			}
		
		} else {
			System.out.println(	"\n  Aucun groupe ajouté pour le moment.");
		}

		sc.nextLine();
	}
	
	// TODO
	private void updatePts() {
		this.pts = 100;
	}
	
}
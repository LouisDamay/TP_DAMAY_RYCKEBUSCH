import java.util.InputMismatchException;

public class Armee{
	
	// VARIABLES
	String nom;
	String faction;
	int pts_max;
	
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
	
}
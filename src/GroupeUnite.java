public class GroupeUnite{
	String nom;
	int total_points;
	
	
	static java.util.Scanner sc = new java.util.Scanner(System.in);
	
	/* Crée un groupe d'unités à partir des données de l'utilisateur.
	 */
	public GroupeUnite() {
		boolean name_enter = true;
		while (name_enter) {
			System.out.println("  Entrez un nom pour le groupe d'unité : ");
			String res = sc.nextLine();
			if (res.isBlank()){
				System.out.println("  Entrez un nom valide.");
			} else {
				this.nom = res;
				name_enter = false;
			}
		}
		
		
		// total_points est calculé automatiquement selon les unités ajoutées.
	}
	
	// DEBUG ONLY
	public GroupeUnite(String nom) {
		this.nom = nom;
	}
}
public class Vehicule extends Unite {
    String type;
    int capacite;

    public Vehicule(String nom, int cout, String type, int capacite) {
        this.nom = nom;
        this.cout = cout;
        this.type = type;
        this.capacite = capacite;
    }

    @Override
    public void print() {
        if ("Transport".equalsIgnoreCase(type)) {
            System.out.println("\tVéhicule : " + type + " - " + nom + " (" + cout + " pts, capacité : " + capacite + ")");
        } else {
            System.out.println("\tVéhicule : " + type + " - " + nom + " (" + cout + " pts)");
        }
    }
}

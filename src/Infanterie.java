public class Infanterie extends Unite {
    private TypeInfanterie type;

    public Infanterie(String nom, int cout, TypeInfanterie type) {
        this.nom = nom;
        this.cout = cout;
        this.type = type;
    }

    @Override
    public void print() {
        System.out.println("\tInfanterie : " + type + " - " + nom + " (" + cout + " pts)");
    }
}

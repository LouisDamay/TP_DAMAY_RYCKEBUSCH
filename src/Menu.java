public class Menu {
	
	static java.util.Scanner sc = new java.util.Scanner(System.in);

    private int width;
    private String title;
    private Option[] options;

    public static class Option {
        String label;
        Runnable action;

        public Option(String label, Runnable action) {
            this.label = label;
            this.action = action;
        }
    }

    public Menu(int width, String title, Option[] options) {
        this.width = width;
        this.title = title;
        this.options = options;
    }

    public boolean show() {
        boolean quit = false;
        while (!quit) {
            System.out.println(getHeader());

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + " : " + options[i].label);
            }
            
            System.out.println("0 : Quitter");

            System.out.print("\nChoisissez une option : ");
            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consomme la ligne restante

                if (choice == 0) {
                	quit = true;
                	return true;
                } else if (choice > 0 && choice <= options.length) {
                    options[choice - 1].action.run();
                } else {
                    System.out.println("Option invalide, veuillez réessayer.");
                }

            } catch (Exception e) {
                System.out.println("Entrée invalide, veuillez saisir un nombre.");
                sc.nextLine(); // Consomme l'entrée invalide
            }
        }
        return false;
    }

    private String getHeader() {
        String header = "-- " + this.title + " --";
        int remaining = this.width - this.title.length() - 6;
        
        for (int i = 0; i < remaining; i++) {
            header += "-";
        }
        
        return header;
    }

}

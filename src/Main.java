public class Main {
    static Usuario user = null;

    public static void main(String[] args) {

        System.out.println("===== SISTEMA DE GESTÃO DE ESTUDOS =====");

        // MENU INICIAL (LOGIN OU CADASTRO)
        while (user == null) user = Menu.logMenu();

        // ADICIONAR MATÉRIAS PADRÃO
        System.out.println("\nCarregando matérias padrão...\n");
        user.initMateriasPadrao();
        System.out.println("10 matérias básicas adicionadas automaticamente!\n");

        // MENU PRINCIPAL
        boolean loop = true;
        while (loop) loop = Menu.mainMenu(user);

        Menu.sair();
    }
}

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println("===== SISTEMA DE GESTÃO DE ESTUDOS =====");

        // Cadastro inicial do usuário
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        Usuario user = new Usuario(email, senha);
        System.out.println("Usuário criado com sucesso!\n");

        // Autenticação
        System.out.print("Digite o email para login: ");
        String loginEmail = sc.nextLine();

        System.out.print("Digite a senha: ");
        String loginSenha = sc.nextLine();

        boolean autenticado = user.auth(loginEmail, loginSenha);

        if (!autenticado) {
            System.out.println("Credenciais inválidas. Encerrando.");
            return;
        }

        System.out.println("Login realizado com sucesso!\n");

        // MENU PRINCIPAL
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Cadastrar matéria");
            System.out.println("2. Adicionar tarefa");
            System.out.println("3. Registrar sessão de estudo");
            System.out.println("4. Exibir resumo geral");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {

                case 1:
                    System.out.print("Nome da matéria: ");
                    String nomeMateria = sc.nextLine();
                    Materia m = new Materia(nomeMateria);
                    user.addMateria(m);
                    System.out.println("Matéria cadastrada com sucesso!");
                    break;

                case 2:
                    System.out.print("Descrição da tarefa: ");
                    String descricao = sc.nextLine();

                    System.out.print("Prazo (dd/MM/yyyy HH:mm): ");
                    String prazoStr = sc.nextLine();

                    Date prazo = null;
                    try {
                        prazo = sdf.parse(prazoStr);
                    } catch (Exception e) {
                        System.out.println("Formato inválido! Voltando ao menu.");
                        break;
                    }

                    System.out.print("Prioridade (1-5): ");
                    int prio = sc.nextInt(); sc.nextLine();

                    Tarefa t = new Tarefa(descricao, prazo, prio);
                    user.addTarefa(t);
                    System.out.println("Tarefa adicionada!");
                    break;

                case 3:
                    if (user.getMaterias().isEmpty()) {
                        System.out.println("Nenhuma matéria cadastrada!");
                        break;
                    }

                    System.out.println("Escolha a matéria:");
                    for (int i = 0; i < user.getMaterias().size(); i++) {
                        System.out.println((i+1) + ". " + user.getMaterias().get(i).getNome());
                    }

                    int idx = sc.nextInt(); sc.nextLine();

                    Materia materiaSessao = user.getMaterias().get(idx - 1);

                    System.out.print("Duração da sessão (min): ");
                    int dur = sc.nextInt(); sc.nextLine();

                    SessaoEstudo sessao = new SessaoEstudo(dur, new Date(), materiaSessao);

                    user.addSessao(sessao);
                    sessao.iniciar();
                    sessao.pausar();

                    System.out.println("Sessão registrada!");
                    break;

                case 4:
                    System.out.println("\n===== RESUMO =====");
                    System.out.println("Matérias: " + user.getMaterias().size());
                    System.out.println("Tarefas: " + user.getTarefas().size());
                    System.out.println("Sessões: " + user.getSessoes().size());
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}

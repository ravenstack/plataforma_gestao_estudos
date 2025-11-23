import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private static void prtMenuLogin(){
        System.out.println("\n===== BEM-VINDO =====");
        System.out.println("1. Já tenho conta (Login)");
        System.out.println("2. Cadastrar nova conta");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void prtMainMenu(){
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Cadastrar matéria");
        System.out.println("2. Adicionar tarefa");
        System.out.println("3. Registrar sessão de estudo");
        System.out.println("4. Registrar data de prova");
        System.out.println("5. Iniciar modo foco (Pomodoro)");
        System.out.println("6. Exibir resumo geral");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static Usuario logMenu(){
        prtMenuLogin();

        int escolha = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        return switch (escolha) {
            case 1 ->
                // LOGIN COM USUÁRIO PADRÃO
                    logUsr();
            case 2 ->
                // CADASTRO DE NOVO USUÁRIO
                    cadUsr();
            case 0 -> {
                System.out.println("Encerrando...");
                System.exit(0);
                yield null;
            }
            default -> {
                System.out.println("Opção inválida!");
                yield null;
            }
        };
    }

    public static boolean mainMenu(Usuario user){
        prtMainMenu();

        int opcao = sc.nextInt();
        sc.nextLine();

        return switch (opcao) {
            case 1 -> {
                cadMateria(user);
                yield true;
            }
            case 2 -> {
                cadTarefa(user);
                yield true;
            }
            case 3 -> {
                cadSessaoEstudo(user);
                yield true;
            }
            case 4 -> {
                cadProva(user);
                yield true;
            }
            case 5 -> {
                initPomodoro();
                yield true;
            }
            case 6 -> {
                resumo(user);
                yield true;
            }
            case 0 -> {
                System.out.println("Encerrando...");
                yield false;
            }
            default -> {
                System.out.println("Opção inválida!");
                yield true;
            }
        };
    }

    private static void cadMateria(Usuario user){
        System.out.print("Nome da matéria: ");
        String nomeMateria = sc.nextLine();
        user.addMateria(new Materia(nomeMateria));
        System.out.println("Matéria cadastrada com sucesso!");
    }

    private static void cadTarefa(Usuario user){
        System.out.print("Descrição da tarefa: ");
        String descricao = sc.nextLine();

        System.out.print("Prazo (dd/MM/yyyy HH:mm): ");
        String prazoStr = sc.nextLine();

        Date prazo = null;
        try {
            prazo = sdf.parse(prazoStr);
        } catch (Exception e) {
            System.out.println("Formato inválido! Voltando ao menu.");
            return;
        }

        System.out.print("Prioridade (1-5): ");
        int prio = sc.nextInt();
        sc.nextLine();

        Tarefa t = new Tarefa(descricao, prazo, prio);
        user.addTarefa(t);
        System.out.println("Tarefa adicionada!");
    }

    private static void cadSessaoEstudo(Usuario user){
        if (user.getMaterias().isEmpty()) {
            System.out.println("Nenhuma matéria cadastrada!");
            return;
        }

        System.out.println("Escolha a matéria:");
        for (int i = 0; i < user.getMaterias().size(); i++) {
            System.out.println((i + 1) + ". " + user.getMaterias().get(i).getNome());
        }

        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 1 || idx > user.getMaterias().size()) {
            System.out.println("Matéria inválida. Voltando ao menu.");
            return;
        }

        Materia materiaSessao = user.getMaterias().get(idx - 1);

        System.out.print("Duração da sessão (min): ");
        int dur = sc.nextInt();
        sc.nextLine();

        if (dur <= 0) {
            System.out.println("Duração inválida. Voltando ao menu.");
            return;
        }

        SessaoEstudo sessao = user.registrarSessao(dur, materiaSessao);
        sessao.iniciar();
        sessao.pausar();

        System.out.println("Sessão registrada!");
    }

    private static void cadProva(Usuario user){
        if (user.getMaterias().isEmpty()) {
            System.out.println("Cadastre uma matéria antes de registrar provas.");
            return;
        }

        System.out.println("Escolha a matéria para registrar a prova:");
        for (int i = 0; i < user.getMaterias().size(); i++) {
            System.out.println((i + 1) + ". " + user.getMaterias().get(i).getNome());
        }

        int materiaIdx = sc.nextInt();
        sc.nextLine();

        if (materiaIdx < 1 || materiaIdx > user.getMaterias().size()) {
            System.out.println("Matéria inválida.");
            return;
        }

        Materia materiaSelecionada = user.getMaterias().get(materiaIdx - 1);

        System.out.print("Descrição da prova: ");
        String descProva = sc.nextLine();

        System.out.print("Data da prova (dd/MM/yyyy HH:mm): ");
        String dataProvaStr = sc.nextLine();

        Date dataProva;
        try {
            dataProva = sdf.parse(dataProvaStr);
        } catch (Exception e) {
            System.out.println("Formato inválido!");
            return;
        }

        user.addProva(materiaSelecionada, new Prova(descProva, dataProva));
        System.out.println("Prova registrada!");
    }

    private static void initPomodoro(){
        System.out.print("Minutos de foco por ciclo: ");
        int foco = sc.nextInt();
        sc.nextLine();

        System.out.print("Minutos de pausa por ciclo: ");
        int pausa = sc.nextInt();
        sc.nextLine();

        System.out.print("Número de ciclos: ");
        int ciclos = sc.nextInt();
        sc.nextLine();

        try {
            PomodoroTimer timer = new PomodoroTimer(foco, pausa, ciclos);
            timer.iniciar();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void resumo(Usuario user){
        System.out.println("\n===== RESUMO =====");
        System.out.println("Matérias: " + user.getMaterias().size());
        System.out.println("Tarefas: " + user.getTarefas().size());
        System.out.println("Sessões: " + user.getSessoes().size());

        if (!user.getTarefas().isEmpty()) {
            System.out.println("\n-- Tarefas --");
            user.getTarefas().stream()
                    .sorted(Comparator.comparingInt(Tarefa::getPrioridade).reversed())
                    .forEach(tarefa -> {
                        System.out.println(
                                "Prioridade: " + tarefa.getPrioridade() +
                                        " | Descrição: " + tarefa.getDescricao() +
                                        " | Prazo: " + sdf.format(tarefa.getPrazo()) +
                                        " | Concluída: " + tarefa.isConcluido()
                        );
                    });
        }

        if (!user.getMaterias().isEmpty()) {
            System.out.println("\n-- Provas --");
            for (Materia materia : user.getMaterias()) {
                if (materia.getProvas().isEmpty()) {
                    System.out.println(materia.getNome() + ": sem provas cadastradas.");
                } else {
                    for (Prova p : materia.getProvas()) {
                        System.out.println(
                                materia.getNome() +
                                        " | Prova: " + p.getDescricao() +
                                        " | Data: " + sdf.format(p.getData())
                        );
                    }
                }
            }
        }

        if (!user.getSessoes().isEmpty()) {
            System.out.println("\n-- Sessões registradas --");
            for (SessaoEstudo s : user.getSessoes()) {
                System.out.println(
                        "Matéria: " + s.getMateria().getNome() +
                                " | Duração: " + s.getDuracaoMinutos() +
                                " min | Início: " + sdf.format(s.getDataHora())
                );
            }

            System.out.println("\n-- Estatísticas --");
            user.calcularMinutosPorMateria().forEach((materia, minutos) -> {
                double horas = minutos / 60.0;
                System.out.printf("%s: %.2f h\n", materia, horas);
            });
        }
    }

    private static Usuario cadUsr(){
        System.out.print("Digite um novo email: ");
        String novoEmail = sc.nextLine();

        System.out.print("Digite uma nova senha: ");
        String novaSenha = sc.nextLine();

        System.out.println("Conta criada com sucesso!");
        return new Usuario(novoEmail, novaSenha);
    }

    private static Usuario logUsr(){
        System.out.print("Digite seu email: ");
        String loginEmail = sc.nextLine();

        System.out.print("Digite sua senha: ");
        String loginSenha = sc.nextLine();

        // Usuário padrão
        String emailPadrao = "admin@sistema.com";
        String senhaPadrao = "1234";

        Usuario usuarioPadrao = new Usuario(emailPadrao, senhaPadrao);

        if (usuarioPadrao.auth(loginEmail, loginSenha)) {
            System.out.println("\nLogin realizado com sucesso!");
            return usuarioPadrao;
        } else {
            System.out.println("Credenciais inválidas!");
        }
        return null;
    }

    public static void sair(){
        sc.close();
    }
}

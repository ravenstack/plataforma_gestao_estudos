import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.println("7. Concluir tarefa pendente");
        System.out.println("8. Ver alertas de prazos próximos");
        System.out.println("9. Sugestão de rotina de estudos");
        System.out.println("10. Compartilhar cronograma");
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
            case 7 -> {
                concluirTarefa(user);
                yield true;
            }
            case 8 -> {
                exibirAlertas(user);
                yield true;
            }
            case 9 -> {
                sugestaoRotina(user);
                yield true;
            }
            case 10 -> {
                compartilharCronograma(user);
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
private static void concluirTarefa(Usuario user) {
        List<Integer> indicesPendentes = new ArrayList<>();

        System.out.println("===== TAREFAS PENDENTES =====");
        for (int i = 0; i < user.getTarefas().size(); i++) {
            Tarefa tarefa = user.getTarefas().get(i);
            if (!tarefa.isConcluido()) {
                indicesPendentes.add(i);
                System.out.printf("%d. [%d★] %s | Prazo: %s\n", indicesPendentes.size(), tarefa.getPrioridade(), tarefa.getDescricao(), sdf.format(tarefa.getPrazo()));
            }
        }

        if (indicesPendentes.isEmpty()) {
            System.out.println("Nenhuma tarefa pendente encontrada.");
            return;
        }

        System.out.print("Digite o número da tarefa para concluir: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > indicesPendentes.size()) {
            System.out.println("Opção inválida. Voltando ao menu.");
            return;
        }

        user.concluirTarefa(indicesPendentes.get(escolha - 1));
        System.out.println("Tarefa marcada como concluída!");
    }

    private static void exibirAlertas(Usuario user) {
        Date agora = new Date();
        long limiteTarefasMillis = 72L * 60 * 60 * 1000; // 72 horas
        long limiteProvasMillis = 7L * 24 * 60 * 60 * 1000; // 7 dias

        List<Tarefa> tarefasCriticas = new ArrayList<>();
        for (Tarefa t : user.getTarefas()) {
            if (!t.isConcluido() && t.getPrazo().after(agora) && (t.getPrazo().getTime() - agora.getTime()) <= limiteTarefasMillis) {
                tarefasCriticas.add(t);
            }
        }
        tarefasCriticas.sort(Comparator.comparing(Tarefa::getPrazo));

        List<ProvaAgenda> provasProximas = new ArrayList<>();
        for (Materia materia : user.getMaterias()) {
            for (Prova prova : materia.getProvas()) {
                if (prova.getData().after(agora) && (prova.getData().getTime() - agora.getTime()) <= limiteProvasMillis) {
                    provasProximas.add(new ProvaAgenda(materia, prova));
                }
            }
        }
        provasProximas.sort(Comparator.comparing(p -> p.prova.getData()));

        System.out.println("===== ALERTAS =====");
        if (tarefasCriticas.isEmpty() && provasProximas.isEmpty()) {
            System.out.println("Nenhum prazo crítico nas próximas horas/dias.");
            return;
        }

        if (!tarefasCriticas.isEmpty()) {
            System.out.println("\nTarefas para concluir em até 72h:");
            for (Tarefa t : tarefasCriticas) {
                System.out.printf("- [%d★] %s | Prazo: %s\n", t.getPrioridade(), t.getDescricao(), sdf.format(t.getPrazo()));
            }
        }

        if (!provasProximas.isEmpty()) {
            System.out.println("\nProvas nos próximos 7 dias:");
            for (ProvaAgenda agenda : provasProximas) {
                System.out.printf("- %s | Prova: %s | Data: %s\n", agenda.materia.getNome(), agenda.prova.getDescricao(), sdf.format(agenda.prova.getData()));
            }
        }
    }

    private static void sugestaoRotina(Usuario user) {
        if (user.getMaterias().isEmpty()) {
            System.out.println("Cadastre ao menos uma matéria para receber sugestões.");
            return;
        }

        Map<String, Integer> minutosPorMateria = user.calcularMinutosPorMateria();

        Materia menosEstudada = null;
        int minutosMenor = Integer.MAX_VALUE;
        for (Materia materia : user.getMaterias()) {
            int minutos = minutosPorMateria.getOrDefault(materia.getNome(), 0);
            if (minutos < minutosMenor) {
                minutosMenor = minutos;
                menosEstudada = materia;
            }
        }

        Tarefa prioridade = null;
        for (Tarefa tarefa : user.getTarefas()) {
            if (tarefa.isConcluido()) continue;
            if (prioridade == null) {
                prioridade = tarefa;
            } else if (tarefa.getPrioridade() > prioridade.getPrioridade() ||
                    (tarefa.getPrioridade() == prioridade.getPrioridade() && tarefa.getPrazo().before(prioridade.getPrazo()))) {
                prioridade = tarefa;
            }
        }

        ProvaAgenda proximaProva = null;
        for (Materia materia : user.getMaterias()) {
            for (Prova prova : materia.getProvas()) {
                if (proximaProva == null || prova.getData().before(proximaProva.prova.getData())) {
                    proximaProva = new ProvaAgenda(materia, prova);
                }
            }
        }

        System.out.println("===== SUGESTÃO DE ROTINA =====");

        if (menosEstudada != null) {
            double horasEstudadas = minutosMenor / 60.0;
            System.out.printf("1) Comece revisando %s (estudada por apenas %.2f h). Reserve um bloco de 50 minutos.\n", menosEstudada.getNome(), horasEstudadas);
        }

        if (prioridade != null) {
            System.out.printf("2) Em seguida, finalize a tarefa de maior prioridade: %s (prioridade %d, prazo %s).\n", prioridade.getDescricao(), prioridade.getPrioridade(), sdf.format(prioridade.getPrazo()));
        }

        if (proximaProva != null) {
            System.out.printf("3) Dedique tempo extra para a prova de %s em %s: faça um simulado curto e revise anotações.\n", proximaProva.materia.getNome(), sdf.format(proximaProva.prova.getData()));
        }

        if (menosEstudada == null && prioridade == null && proximaProva == null) {
            System.out.println("Nenhum dado suficiente para sugerir uma rotina. Cadastre provas, tarefas ou sessões de estudo.");
        }
    }

    private static void compartilharCronograma(Usuario user) {
        StringBuilder cronograma = new StringBuilder();
        cronograma.append("===== CRONOGRAMA PARA COMPARTILHAR =====\n");
        cronograma.append("Usuário: ").append(user.getEmail()).append("\n\n");

        List<Tarefa> tarefasOrdenadas = new ArrayList<>(user.getTarefas());
        tarefasOrdenadas.sort(Comparator.comparing(Tarefa::getPrazo));
        if (tarefasOrdenadas.isEmpty()) {
            cronograma.append("Tarefas: nenhuma cadastrada.\n");
        } else {
            cronograma.append("Tarefas:\n");
            for (Tarefa t : tarefasOrdenadas) {
                String status = t.isConcluido() ? "(concluída)" : "(pendente)";
                cronograma.append(String.format("- %s | prazo: %s %s | prioridade: %d\n", t.getDescricao(), sdf.format(t.getPrazo()), status, t.getPrioridade()));
            }
        }

        List<ProvaAgenda> provas = new ArrayList<>();
        for (Materia materia : user.getMaterias()) {
            for (Prova prova : materia.getProvas()) {
                provas.add(new ProvaAgenda(materia, prova));
            }
        }
        provas.sort(Comparator.comparing(p -> p.prova.getData()));

        if (provas.isEmpty()) {
            cronograma.append("\nProvas: nenhuma prova cadastrada.\n");
        } else {
            cronograma.append("\nProvas:\n");
            for (ProvaAgenda agenda : provas) {
                cronograma.append(String.format("- %s | %s em %s\n", agenda.materia.getNome(), agenda.prova.getDescricao(), sdf.format(agenda.prova.getData())));
            }
        }

        System.out.println(cronograma);
        System.out.println("Copie o bloco acima para compartilhar seu cronograma com colegas.");
    }

    private static class ProvaAgenda {
        private final Materia materia;
        private final Prova prova;

        private ProvaAgenda(Materia materia, Prova prova) {
            this.materia = materia;
            this.prova = prova;
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

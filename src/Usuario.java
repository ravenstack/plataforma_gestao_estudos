import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Usuario {

    private String email;
    private String senha;

    private List<Materia> materias;
    private List<Tarefa> tarefas;
    private List<SessaoEstudo> sessoes;

    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
        this.materias = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }

    public void initMateriasPadrao(){
        String[] materiasPadrao = {
                "Matemática",
                "Português",
                "Inglês",
                "História",
                "Geografia",
                "Biologia",
                "Física",
                "Química",
                "Sociologia",
                "Filosofia"
        };

        for (String nome : materiasPadrao) {
            this.addMateria(new Materia(nome));
        }
    }

    // autenticacao
    public boolean auth(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    // materias
    public void addMateria(Materia materia){
        this.materias.add(materia);
    }

    // tarefas
    public void addTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }

    public void concluirTarefa(int index){
        if (index >= 0 && index < tarefas.size()) {
            tarefas.get(index).concluir();
        }
    }

    // sesssão estudos
    public void addSessao(SessaoEstudo sessao) {
        this.sessoes.add(sessao);
    }

    public SessaoEstudo registrarSessao(int duracaoMinutos, Materia materia) {

        if (materia == null) {
            throw new IllegalArgumentException("Matéria não pode ser nula");
        }

        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("A duração da sessão deve ser maior que zero");
        }

        SessaoEstudo sessao = new SessaoEstudo(duracaoMinutos, new Date(), materia);
        this.addSessao(sessao);
        return sessao;
    }

    // prova
    public void addProva(Materia materia, Prova prova) {
        materia.adicionarProva(prova);
    }

    // estatistica
    public Map<String, Integer> calcularMinutosPorMateria() {
        Map<String, Integer> minutosPorMateria = new HashMap<>();

        for (SessaoEstudo sessao : sessoes) {
            String nomeMateria = sessao.getMateria().getNome();
            int minutos = sessao.getDuracaoMinutos();
            minutosPorMateria.put(nomeMateria, minutosPorMateria.getOrDefault(nomeMateria, 0) + minutos);
        }
        return minutosPorMateria;
    }

    public List<Materia> getMaterias() { return materias; }
    public List<Tarefa> getTarefas() { return tarefas; }
    public List<SessaoEstudo> getSessoes() { return sessoes; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}

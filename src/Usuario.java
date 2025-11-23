import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String email;
    private String senha;

    private List<Materia> materias;
    private List<Tarefa> tarefas;
    private List<SessaoEstudo> sessoes;

    Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
        this.materias = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.sessoes = new ArrayList<>();
    }

    public boolean auth(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void addTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }

    public void addMateria(Materia materia){
        this.materias.add(materia);
    }

    public void addSessao(SessaoEstudo sessao) {
        this.sessoes.add(sessao);
    }


    public List<Materia> getMaterias() { return materias; }
    public List<Tarefa> getTarefas() { return tarefas; }
    public List<SessaoEstudo> getSessoes() {
        return this.sessoes;
    }
    public String getEmail() { return email; }
}

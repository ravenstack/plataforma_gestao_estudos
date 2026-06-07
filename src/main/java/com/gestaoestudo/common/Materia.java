import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String nome;
    private List<Prova> provas;

    public Materia(String nome){
        this.nome = nome;
        this.provas = new ArrayList<>();
    }

    public void adicionarProva(Prova prova) {
        this.provas.add(prova);
    }

    public List<Prova> getProvas() {
        return provas;
    }

    public String getNome(){
        return this.nome;
    }

    @Override
    public String toString() {
        return "Matéria: " + nome;
    }
}

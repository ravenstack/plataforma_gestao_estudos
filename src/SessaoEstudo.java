import java.util.Date;

public class SessaoEstudo {
    private int duracaoMinutos;
    private Date dataHora;
    private Materia materia;

    public SessaoEstudo(int duracao, Date dataHora, Materia materia){
        this.duracaoMinutos = duracao;
        this.dataHora = dataHora;
        this.materia = materia;
    }

    public void iniciar(){
        System.out.println("Sessão de " + this.materia.getNome() + " iniciada (" + this.duracaoMinutos + " min).");
    }

    public void pausar(){
        System.out.println("Sessão finalizada/pausada com sucesso.");
    }

    public Materia getMateria(){
        return this.materia;
    }
}
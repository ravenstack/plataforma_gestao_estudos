import java.util.Date;

public class SessaoEstudo{
    private int duracaoMinitos;
    private Date dataHora;
    private Materia materia;

    public SessaoEstudo(int duracao, Date dataHora, Materia materia){
        this.duracaoMinitos = duracao;
        this.dataHora = dataHora; 
        this.materia = materia;
    }

    public void iniciar(){
        System.out.println("Iniciando a sessão de " + this.materia + " " + this.duracaoMinitos + "m.");
    }

    public void pausar(){
        System.out.println("Sessão Pausada");
    }

    public Materia getMateria(){
        return this.materia;
    }
}

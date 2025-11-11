import java.util.Date;

public class Tarefa{
    private String descricao;
    private Date prazo;
    private int prioridade;
    private boolean concluido;

    public Tarefa(String descricao, Date prazo, int prioridade){
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.concluido = false;
    }

    public void concluir(){
        this.concluido = true;
    }

    public String getDescricao(){
        return this.descricao;
    }
 
    public int getPrazo(){
        return this.prazo;
    }

    public int getPrioridade(){
        return this.prioridade;
    }  
}

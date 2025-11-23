import java.util.Date;

public class Prova {
    private final String descricao;
    private final Date data;

    public Prova(String descricao, Date data) {
        this.descricao = descricao;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }
}
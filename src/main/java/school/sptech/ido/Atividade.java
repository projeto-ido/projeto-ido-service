package school.sptech.ido;

public abstract class Atividade {

    private int id;
    private String descricao;
    private boolean status;

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

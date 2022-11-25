package school.sptech.ido.domain.model;

public class AcoesUsuario {

    private Integer idUsuario;

    private PilhaObj<Acao> acoes;

    public AcoesUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
        this.acoes = new PilhaObj<>(10);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PilhaObj<Acao> getAcoes() {
        return acoes;
    }
}

package school.sptech.ido.domain.model;

import lombok.Data;

@Data
public abstract class Atividade {
    private int id;
    private String descricao;
    private boolean status;
}

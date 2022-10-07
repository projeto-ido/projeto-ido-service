package school.sptech.ido.domain.model;

import lombok.Getter;
import lombok.Setter;

public abstract class Atividade {
    @Getter @Setter private int id;
    @Getter @Setter private String descricao;
    @Getter @Setter private boolean status;
}

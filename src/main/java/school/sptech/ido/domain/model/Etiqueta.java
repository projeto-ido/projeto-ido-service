package school.sptech.ido.domain.model;

import lombok.Getter;
import lombok.Setter;

public class Etiqueta implements Comparable<Etiqueta> {

    @Getter @Setter private int id;
    @Getter @Setter private String titulo;
    @Getter @Setter private CorEtiqueta cor;

    @Override
    public int compareTo(Etiqueta etiqueta) {
        return this.titulo.toLowerCase().compareTo(etiqueta.getTitulo().toLowerCase());
    }
}

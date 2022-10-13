package school.sptech.ido.domain.model;

import lombok.Data;
import school.sptech.ido.enums.CorEtiqueta;

@Data
public class Etiqueta implements Comparable<Etiqueta> {

    private int id;
    private String titulo;
    private CorEtiqueta cor;

    @Override
    public int compareTo(Etiqueta etiqueta) {
        return this.titulo.toLowerCase().compareTo(etiqueta.getTitulo().toLowerCase());
    }
}

package school.sptech.ido.domain.model;

import lombok.Getter;

import java.util.*;

public class GerenciadorEtiquetas implements Ordenavel {

    @Getter private final List<Etiqueta> etiquetas = new ArrayList<>();

    public Etiqueta cadastrarEtiqueta(Etiqueta etiqueta) {
        if (etiquetas.size() < 5) {
            etiqueta.setId(etiquetas.size() + 1);
            etiquetas.add(etiqueta);
            return etiqueta;
        }
        return null;
    }

    public Etiqueta removerEtiqueta(int id) {
        if (isIdValid(id)) return etiquetas.remove(id - 1);

        return null;
    }

    public Etiqueta editarEtiqueta(int id, Etiqueta etiquetaAtualizada) {
        if (isIdValid(id)) {
            int posicaoDaEtiqueta = id - 1;
            etiquetas.set(posicaoDaEtiqueta, etiquetaAtualizada);
            return etiquetas.get(posicaoDaEtiqueta);
        }
        return null;
    }

    private boolean isIdValid(int id) {
        return id > 0 && id <= etiquetas.size();
    }

    @Override
    public List<Etiqueta> ordenar() {
         Collections.sort(etiquetas);
         return etiquetas;
    }
}

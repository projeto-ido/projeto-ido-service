package school.sptech.ido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorEtiquetas  implements Ordenavel{

    private List<Etiqueta>etiquetas = new ArrayList<>();


    public void cadastratarEtiqueta(Etiqueta etiqueta){
        etiquetas.add(etiqueta);
    }

    public void removerEtiqueta(int id){
        for (int i = 0; i < etiquetas.size(); i++) {
            if(etiquetas.get(i).getId() == id) etiquetas.remove(i);
        }
    }

    public void editarEtiqueta(int id, Etiqueta etiquetaAtualizada){
        for (int i = 0; i < etiquetas.size(); i++) {
            if(etiquetas.get(i).getId() == id) etiquetas.set(i, etiquetaAtualizada);
        }
    }

    @Override
    public List ordenar() {
        return etiquetas.stream().sorted().collect(Collectors.toList());
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }



}

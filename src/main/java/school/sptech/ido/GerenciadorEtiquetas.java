package school.sptech.ido;

import java.util.*;

public class GerenciadorEtiquetas  implements Ordenavel{

    private List<Etiqueta> etiquetas= new ArrayList<>();


    public Etiqueta cadastrarEtiqueta(Etiqueta etiqueta){
        etiqueta.setId(etiquetas.size() + 1);
        etiquetas.add(etiqueta);
        return etiqueta;
    }

    public void removerEtiqueta(int id){
        for (Etiqueta e : etiquetas) {
            if (e.getId() == id) etiquetas.remove(e);
        }
    }

    public Etiqueta editarEtiqueta(int id, Etiqueta etiquetaAtualizada){
        for (int i = 0; i < etiquetas.size(); i++) {
            if(etiquetas.get(i).getId() == id){
                etiquetas.set(i, etiquetaAtualizada);
                return etiquetas.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Etiqueta> ordenar() {
         Collections.sort(etiquetas);
         return etiquetas;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }



}

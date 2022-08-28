package school.sptech.ido;

import java.util.List;

public class GerenciadorEtiquetas {

    private List<Etiqueta>etiquetas;


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

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }



}

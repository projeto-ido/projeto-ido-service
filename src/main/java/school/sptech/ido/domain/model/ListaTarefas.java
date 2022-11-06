package school.sptech.ido.domain.model;

import school.sptech.ido.resources.repository.entity.TarefaEntity;

public class ListaTarefas extends ListaObj<TarefaEntity>{

    public ListaTarefas(int capacidade) {
        super(capacidade);
    }


    @Override
    public void ordenar() {
        for (int i = 0; i < super.nroElem; i++) {
            for (int j = i + 1; j < super.nroElem; j++) {
                if (super.getElemento(j).getDataCriacao().isAfter(super.getElemento(i).getDataCriacao())){
                    TarefaEntity aux = super.getElemento(i);
                    super.adicionaPeloIndice(i, super.getElemento(j));
                    super.adicionaPeloIndice(j, aux);
                }
            }
        }
    }

}

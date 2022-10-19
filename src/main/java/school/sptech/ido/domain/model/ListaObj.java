package school.sptech.ido.domain.model;

import school.sptech.ido.resources.repository.entity.TarefaEntity;

public class ListaObj<T> {
        private T[] vetor;

        private int nroElem;

        public ListaObj(int capacidade) {
            this.vetor = (T[]) new Object[capacidade];
            this.nroElem = 0;
        }

        public void adiciona(T elemento){
            if (nroElem == vetor.length) throw new IllegalStateException();
            vetor[nroElem++] = elemento;
        }

        public int busca(T elemento){
            for (int i = 0; i < vetor.length; i++) {
                if (vetor[i].equals(elemento)){
                    return i;
                }
            }
            return -1;
        }

        public boolean removePeloIndice(int indice){
            if (indice >= 0 && indice < vetor.length){
                for (int i = indice; i < (vetor.length - 1); i++) {
                    vetor[i] = vetor[i+1];
                }
                nroElem--;
                return true;
            }
            return false;
        }

        public boolean removeElemento(T elemento) {
            return removePeloIndice(busca(elemento));
        }

        public int getTamanho(){ return nroElem; }

        public T getElemento(int indice){
            if (indice >= 0 && indice < nroElem){
                return vetor[indice];
            }
            return null;
        }

        public void limpa(){
            vetor = (T[]) new Object[vetor.length];
            nroElem = 0;
        }

        public void exibe(){
            System.out.print("Exibindo elementos da lista: ");
            for (int i = 0; i < nroElem; i++) {
                System.out.printf("%s ", vetor[i]);
            }
            System.out.println();
        }

    public void ordenarTarefa(ListaObj<TarefaEntity> tarefas) {
        for (int i = 0; i < nroElem; i++) {
            for (int j = i + 1; j < nroElem - 1; j++) {
                if (tarefas.getElemento(j).getDataCriacao().isBefore(tarefas.getElemento(i).getDataCriacao())){
                    TarefaEntity aux = tarefas.getElemento(i);
                    tarefas.adicionaPeloIndice(i, tarefas.getElemento(j));
                    tarefas.adicionaPeloIndice(j, aux);
                }
            }
        }
    }

    private void adicionaPeloIndice(int indice, T elemento) {
        vetor[indice] = elemento;
    }
}








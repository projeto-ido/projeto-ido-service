package school.sptech.ido.domain.model;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull())
            throw new IllegalStateException("Fila cheia!");

        fila[tamanho++] = info;
    }
    public T peek() {
        return fila[0];
    }

    public T poll() {

        T primeElemento = fila[0];

        int index = 0;

        for (int i = 0; i < tamanho; i++) {
            if (i == tamanho - 1)
                fila[i] = null;
            else
                fila[i] = fila[++index];
        }

        tamanho--;

        return primeElemento;
    }

    public void exibe() {
        for (T elemento: fila) {
            System.out.println(elemento);
        }
    }

    public T[] getFila() {
        return fila;
    }

    public int getTamanho() {
        return tamanho;
    }
}

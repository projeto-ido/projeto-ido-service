package school.sptech.ido.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Usuario implements Ordenavel{
    private int idUsuario;
    private String nome;
    private Date dataNascimento;
    private String telefone;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String bio;
    private GerenciadorEtiquetas gerenciadorEtiquetas = new GerenciadorEtiquetas();
    private final List<Tarefa> tarefas = new ArrayList<>();
    private boolean isAutenticado;

    public void adicionarTarefa(Tarefa tarefa) {
        tarefa.setId(tarefas.size() + 1);
        tarefas.add(tarefa);
    }

    public Tarefa editarTarefa(int id, Tarefa tarefa) {
        if (isIdValid(id)) {
            int posicaoTarefa = id - 1;
            tarefas.set(posicaoTarefa, tarefa);
            return tarefas.get(posicaoTarefa);
        }
        return null;
    }

    public Tarefa removerTarefa(int id) {
        if (isIdValid(id)) return tarefas.remove(id - 1);
        return null;
    }

    public Tarefa isConcluido(int id, Tarefa tarefa) {
        if (isIdValid(id)) {
            tarefas.get(id - 1).setStatus(tarefa.isStatus());
            return tarefas.get(id - 1);
        }
        return null;
    }

    private boolean isIdValid(int id) {
        return id > 0 && id <= tarefas.size();
    }

    @Override
    public List<List<Tarefa>> ordenar() {
        List<Tarefa> fazerAgora = new ArrayList<>();
        List<Tarefa> agendar = new ArrayList<>();
        List<Tarefa> delegar = new ArrayList<>();
        List<Tarefa> naoPriorizar = new ArrayList<>();

        List<List<Tarefa>> listaOrdenada = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.calcularPrioridade().equalsIgnoreCase("Fazer Agora")){
                fazerAgora.add(tarefa);
            } else if(tarefa.calcularPrioridade().equalsIgnoreCase("Agendar")){
                agendar.add(tarefa);
            } else if (tarefa.calcularPrioridade().equalsIgnoreCase("Delegar")){
                delegar.add(tarefa);
            } else {
                naoPriorizar.add(tarefa);
            }
        }

        listaOrdenada.add(fazerAgora);
        listaOrdenada.add(agendar);
        listaOrdenada.add(delegar);
        listaOrdenada.add(naoPriorizar);

        return listaOrdenada;
    }
}

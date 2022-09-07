package school.sptech.ido;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario implements Ordenavel{
    private int idUsuario;
    private String nome;
    private Date dataNascimento;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String bio;
    private GerenciadorEtiquetas gerenciadorEtiquetas = new GerenciadorEtiquetas();
    private List<Tarefa> tarefas = new ArrayList<>();
    private boolean isAutenticado;

    public void adicionarTarefa(Tarefa tarefa){
        tarefa.setId(tarefas.size() + 1);
        tarefas.add(tarefa);
    }
    public void editarTarefa(int id, Tarefa tarefa){
        for (int i = 0; i < tarefas.size(); i++) {
            if(tarefas.get(i).getId() == id) tarefas.set(i, tarefa);
        }
    }

    public void removerTarefa(int id){
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == id) tarefas.remove(tarefa);
        }
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void isConcluido(int id , Tarefa tarefaStatus){
        for (Tarefa tarefa: tarefas) {
            if(tarefa.getId() == id) tarefa.setStatus(tarefaStatus.isStatus());
        }
    }

    @Override
    public List ordenar() {
        List<Tarefa> fazerAgora = new ArrayList();
        List<Tarefa> agendar = new ArrayList();
        List<Tarefa> delegar = new ArrayList();
        List<Tarefa> naoPriorizar = new ArrayList();

        List listaOrdenada = new ArrayList();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getCalcularPrioridade().equalsIgnoreCase("Fazer Agora")){
                fazerAgora.add(tarefa);
            } else if(tarefa.getCalcularPrioridade().equalsIgnoreCase("Agendar")){
                agendar.add(tarefa);
            } else if (tarefa.getCalcularPrioridade().equalsIgnoreCase("Delegar")){
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public GerenciadorEtiquetas getGerenciadorEtiquetas() {
        return gerenciadorEtiquetas;
    }

    public void setGerenciadorEtiquetas(GerenciadorEtiquetas gerenciadorEtiquetas) {
        this.gerenciadorEtiquetas = gerenciadorEtiquetas;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public boolean isAutenticado() {
        return isAutenticado;
    }

    public void setAutenticado(boolean autenticado) {
        isAutenticado = autenticado;
    }
}

package school.sptech.ido;

import java.util.Date;

public class Tarefa extends Atividade {

    private String titulo;
    private SubTarefa[] subtarefas = new SubTarefa[5];
    private Date dataInicio;
    private Date dataFinal;
    private Date dataCriacao;
    private Etiqueta[] etiquetas = new Etiqueta[5];
    private boolean urgencia;
    private boolean importancia;

    public String getCalcularPrioridade() {
        if (urgencia && importancia) {
            return "Fazer Agora";
        } else if (!urgencia && importancia) {
            return "Agendar";
        } else if (urgencia) {
            return "Delegar";
        }

        return "Não priorizar";
    }

    public void cadastrarSubTarefa(SubTarefa subTarefa) {
        for (int i = 0; i < subtarefas.length; i++) {
            if (subtarefas[i] == null) {
                subtarefas[i] = subTarefa;
                break;
            }
        }
    }

    public void removerSubTarefa(int idSubtarefa){
        for (int i = 0; i < subtarefas.length; i++) {
            if(subtarefas[i].getId() == idSubtarefa) subtarefas[i] = null;
        }
    }
    public void editarSubtarefa(int id, SubTarefa subTarefa){
        for (int i = 0; i < subtarefas.length; i++) {
            if(subtarefas[i].getId() == id) subtarefas[i] = subTarefa;
        }
    }
    public void associarEtiqueta(Etiqueta etiqueta){
        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i] == null) {
                etiquetas[i] = etiqueta;
                break;
            }
        }
    }
    public void desassociarEtiqueta(int id){
        for (int i = 0; i < etiquetas.length; i++) {
            if(etiquetas[i].getId() == id) etiquetas[i] = null;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public SubTarefa[] getSubtarefas() {
        return subtarefas;
    }

    public void setSubtarefas(SubTarefa[] subtarefas) {
        this.subtarefas = subtarefas;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Etiqueta[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Etiqueta[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public boolean isUrgencia() {
        return urgencia;
    }

    public void setUrgencia(boolean urgencia) {
        this.urgencia = urgencia;
    }

    public boolean isImportancia() {
        return importancia;
    }

    public void setImportancia(boolean importancia) {
        this.importancia = importancia;
    }
}

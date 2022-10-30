package school.sptech.ido.domain.model;
import school.sptech.ido.application.controller.dto.TarefaExportacaoDto;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Exportacao {

        public static void gravarCsv(List<TarefaExportacaoDto> lista, String nomeArq){
            FileWriter arq = null;
            Formatter saida = null;
            nomeArq += ".csv";
            boolean hasErro = false;

            try {
                arq = new FileWriter(nomeArq);
                saida = new Formatter(arq);

            } catch (IOException e){
                System.out.println("Erro ao abrir o arquivo");
                hasErro = true;
            }

            try{
                for (int i = 0; i < lista.size(); i++) {
                    TarefaExportacaoDto tarefa = lista.get(i);

                    saida.format("%s;%s;%s;%s;%b;%s;%s\n",
                            tarefa.getTitulo(),
                            tarefa.getDescricao(),
                            tarefa.getDataInicio(),
                            tarefa.getDataFinal(),
                            tarefa.getStatus(),
                            tarefa.getSubTarefas(),
                            tarefa.getEtiquetasTarefa());
                }

            } catch (FormatterClosedException e){
                System.out.println("Erro ao gravar no arquivo");
                hasErro = true;
            } finally {
                saida.close();

                try {
                    arq.close();
                } catch (IOException e ){
                    System.out.println("Erro ao fechar o arquivo");
                    hasErro = true;
                }

                if(hasErro){
                    System.exit(1);
                }

            }
        }

        public static void lerCsv(String nomeArq){
            FileReader arq = null;
            Scanner entrada = null;
            nomeArq += ".csv";
            boolean hasErro = false;

            try {
                arq = new FileReader(nomeArq);
                entrada = new Scanner(arq).useDelimiter(";|\n");
            } catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado");
                hasErro = true;
            }

            try {
                System.out.printf("%-30S %-150S %10S %10S %12S %-15S %-13S\n",
                        "titulo",
                        "subtarefas",
                        "data inicio",
                        "data final",
                        "data criação",
                        "etiquetas",
                        "prioridade");

                while(entrada.hasNext()){
                    String titulo = entrada.next();
                    String subtarefas = entrada.next();
                    String dataInicio = entrada.next();
                    String dataFinal = entrada.next();
                    String dataCriacao = entrada.next();
                    String etiquetas = entrada.next();
                    String prioridade = entrada.next();

                    System.out.printf("%-30S %-150S %10S %10S %12S %-15S %-13S\n",
                            titulo,
                            subtarefas,
                            dataInicio,
                            dataFinal,
                            dataCriacao,
                            etiquetas,
                            prioridade);


                }

            } catch (NoSuchElementException e){
                System.out.println("Arquivo com problemas");
                hasErro = true;
            } catch (IllegalStateException e){
                System.out.println("Erro na leitura do Arquivo");
                hasErro = true;
            } finally {
                entrada.close();
                try {
                    arq.close();
                } catch (IOException e){
                    System.out.println("Erro ao fechar o Arquivo");
                    hasErro = true;
                }

                if (hasErro){
                    System.exit(1);
                }
            }


        }
}

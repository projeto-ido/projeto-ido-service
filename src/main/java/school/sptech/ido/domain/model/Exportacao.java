package school.sptech.ido.domain.model;
import org.springframework.stereotype.Service;
import school.sptech.ido.application.controller.dto.TarefaExportacaoDto;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

                    saida.format("%s;%s;%s;%s;%b;%s;%s;s%\n",
                            tarefa.getTitulo(),
                            tarefa.getDescricao(),
                            tarefa.getDataInicio(),
                            tarefa.getDataFinal(),
                            tarefa.getStatus(),
                            tarefa.getSubTarefas(),
                            tarefa.getEtiqueta1().getTitulo(),
                            tarefa.getEtiqueta2().getTitulo());
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

        public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + nomeArq);
            e.printStackTrace();
        }

        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + nomeArq);
            e.printStackTrace();
        }
    }

        public static void gravaArquivoTxt(List<TarefaExportacaoDto> lista, String nomeUsuario ,String nomeArq) {
            nomeArq += ".txt";
            int contaRegDdos = 0;

        String header = "00TAREFA";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        header += "01";
        header += String.format("%-50.50S", nomeUsuario);
        gravaRegistro(header, nomeArq);

        String corpo;
        for(TarefaExportacaoDto a : lista) {
            corpo = "02";
            corpo += String.format("%-50.50S", a.getTitulo()); //nome tarefa
            corpo += String.format("%10.10S", a.getDataInicio()); //data inicial
            corpo += String.format("%-12.12S", a.getPrioridade()); //prioridade atual
            corpo += String.format("%-12.12S", a.getStatus()); //tarefa concluida ou não
            corpo += String.format("%10.10S", a.getDataFinal()); //data final
            corpo += String.format("%-10.10S", a.getEtiqueta1()); //primeira etiqueta
            corpo += String.format("%-10.10S", a.getEtiqueta2()); //segunda etiqueta
            corpo += String.format("%2.2S", a.getIdTarefa()); //ID da tarefa

            contaRegDdos++;
            gravaRegistro(corpo, nomeArq);
        }

        String trailer = "01";
        trailer += String.format("%05d", contaRegDdos);
        gravaRegistro(trailer, nomeArq);
    }

        public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        nomeArq += ".txt";
        String registro, tipoRegistro;

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo: " + nomeArq);
            e.printStackTrace();
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if(tipoRegistro.equals("00")) {
                    System.out.println("Registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 8));
                    System.out.println("Data/hora: " + registro.substring(8, 29));
                    System.out.println("Usuário: " + registro.substring(29, 79));
                    System.out.println();
                }
                else if(tipoRegistro.equals("02")) {
                    System.out.println("Registro de tarefa");
                    System.out.println("Nome da tarefa: " + registro.substring(2, 52));
                    System.out.println("Data inicial da tarefa: " + registro.substring(52, 62));
                    System.out.println("Data final da tarefa: " + registro.substring(86, 96));
                    System.out.println("Prioridade: " + registro.substring(62, 74));
                    System.out.println("Status: " + registro.substring(74, 86));
                    System.out.println("Etiqueta 1: " + registro.substring(96, 106));
                    System.out.println("Etiqueta 2: " + registro.substring(106, 116));
                    System.out.println("ID da tarefa: " + registro.substring(116, 118));
                    System.out.println();
                }
                else if(tipoRegistro.equals("03")) {
                    System.out.println("Registro de subtarefa");
                    System.out.println("Nome da subtarefa: " + registro.substring(2, 52));
                    System.out.println("Status: " + registro.substring(52, 64));
                    System.out.println("ID da tarefa relacionada: " + registro.substring(64, 66));
                    System.out.println();
                }
                else if(tipoRegistro.equals("01")) {
                    System.out.println("Registro de trailer");
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                registro = entrada.readLine();
            }
        }
        catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + nomeArq);
            e.printStackTrace();
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
}

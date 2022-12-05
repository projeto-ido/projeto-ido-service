package school.sptech.ido.domain.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.ido.application.controller.dto.SubTarefaExportacaoDto;
import school.sptech.ido.application.controller.dto.TarefaExportacaoDto;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class Exportacao {

    @Autowired
    private  TarefaRepository tarefaRepository;

    @Autowired
    private  SubTarefaRepository subTarefaRepository;

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    public  void gravarCsv(List<TarefaExportacaoDto> lista, String nomeArq){
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

                saida.format("%s;%s;%s;%s;%b;%s;%s;%s\n",
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getDataInicio(),
                        tarefa.getDataFinal(),
                        tarefa.getStatus(),
                        tarefa.getSubTarefas(),
                        tarefa.getEtiqueta1(),
                        tarefa.getEtiqueta2());
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

    public  void lerCsv(String nomeArq){
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

    public  void gravaRegistro(String registro, String nomeArq) {
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

    public void gravaArquivoTxt(List<TarefaExportacaoDto> lista, String nomeUsuario ,String nomeArq) {
        nomeArq += ".txt";
        int contaRegDdos = 0;

        String header = "00TAREFA";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        header += "01";
        header += String.format("%-50.50S", nomeUsuario);
        gravaRegistro(header, nomeArq);

        String corpo;
        String corpo2;
        for(int i = 0; i < lista.size(); i++) {
            TarefaExportacaoDto a = lista.get(i);

            corpo = "02";
            corpo += String.format("%-50.50S", a.getTitulo()); //nome tarefa
            corpo += String.format("%16.16S", a.getDataInicio()); //data inicial
            corpo += String.format("%-12.12S", a.getPrioridade()); //prioridade atual
            corpo += String.format("%-13.13S", a.getStatus() ? "concluida" : "nao concluida"); //tarefa concluida ou não
            corpo += String.format("%16.16S", a.getDataFinal()); //data final
            corpo += String.format("%-10.10S", a.getEtiqueta1()); //primeira etiqueta
            corpo += String.format("%-10.10S", a.getEtiqueta2()); //segunda etiqueta
            corpo += String.format("%2.2S", a.getIdTarefa()); //ID da tarefa

            gravaRegistro(corpo, nomeArq);

            if (!a.getSubTarefas().isEmpty()){
                for (SubTarefaExportacaoDto s : a.getSubTarefas()) {
                    corpo2 = "03";
                    corpo2 += String.format("%-50.50S", s.getTitulo());
                    corpo2 += String.format("%-13.13S", s.getStatus() ? "concluida" : "não concluida");
                    corpo2 += String.format("%2.2S", a.getIdTarefa());

                    contaRegDdos++;
                    gravaRegistro(corpo2, nomeArq);
                }
            }

            contaRegDdos++;
    }

    String trailer = "01";
    trailer += String.format("%05d", contaRegDdos);
    gravaRegistro(trailer, nomeArq);
}

    public void leArquivoTxt(InputStream nomeArq, UsuarioEntity usuario){
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        boolean hasErro = false;


        TarefaEntity tarefa = null;
        SubTarefaEntity subTarefa;
        EtiquetaEntity etiquetaEntity;

        entrada = new BufferedReader(new InputStreamReader(nomeArq));

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
                    tarefa = null;
                    etiquetaEntity = null;

                    String titulo = registro.substring(2, 52).trim();
                    LocalDateTime dataInicial = LocalDateTime.parse(registro.substring(52, 68));
                    Boolean importante = Boolean.valueOf(registro.substring(68, 73).trim());
                    Boolean urgente = Boolean.valueOf(registro.substring(73, 78).trim());
                    Boolean status = Boolean.valueOf(registro.substring(78, 83).trim());
                    LocalDateTime dataFinal = LocalDateTime.parse(registro.substring(83, 99));
                    System.out.println("ID da tarefa: " + registro.substring(99, 101));
                    System.out.println();


                    tarefa = new TarefaEntity(titulo, status,
                            dataInicial, dataFinal,
                            LocalDateTime.now(), importante,
                            urgente, usuario );

                    tarefaRepository.save(tarefa);


                }
                else if(tipoRegistro.equals("03")) {
                    subTarefa = null;

                    System.out.println("Registro de subtarefa");
                    String titulo = registro.substring(2, 52).trim();
                    Boolean status = Boolean.valueOf(registro.substring(52, 57).trim());
                    System.out.println("ID da tarefa relacionada: " + registro.substring(57, 59));
                    System.out.println();

                    subTarefa = new SubTarefaEntity(titulo, status, tarefa);

                    System.out.println(subTarefaRepository.save(subTarefa));

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
        }  finally {
            try {
                entrada.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o Arquivo");
                hasErro = true;
            }

            if (hasErro){
                System.exit(1);
            }
        }
    }
}

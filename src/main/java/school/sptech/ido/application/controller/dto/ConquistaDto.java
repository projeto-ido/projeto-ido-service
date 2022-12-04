package school.sptech.ido.application.controller.dto;


import school.sptech.ido.resources.repository.entity.ConquistaEntity;

import javax.validation.constraints.NotBlank;

public class ConquistaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public ConquistaDto(ConquistaEntity conquistaEntity) {
        this.nome = conquistaEntity.getNome();
        this.descricao = conquistaEntity.getDescricao();
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

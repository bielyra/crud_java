package com.testandocrud.cadastro_usuario.business.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.testandocrud.cadastro_usuario.infraestrutura.entitys.Usuario}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto implements Serializable {
    Integer id;
    String email;
    String nome;
}
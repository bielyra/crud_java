package com.testandocrud.cadastro_usuario.business.mapper;

import com.testandocrud.cadastro_usuario.business.dto.UsuarioDto;
import com.testandocrud.cadastro_usuario.infraestrutura.entitys.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto usuarioDto);

    UsuarioDto toDto(Usuario usuario);

    List<Usuario> toEntityList(List<UsuarioDto> dtos);

    List<UsuarioDto> toDtoList(List<Usuario> usuarios);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioDto usuarioDto, @MappingTarget Usuario usuario);
}
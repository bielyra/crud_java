package com.testandocrud.cadastro_usuario.infraestrutura.repository;

import com.testandocrud.cadastro_usuario.infraestrutura.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    //Optional obriga a criar uma excessão ou alternativa caso nao exista um email
    Optional<Usuario> findByEmail(String email);

    //@Transactional é para evitar, caso haja erro, não deletar o email
    @Transactional
    void deleteByEmail(String email);

}

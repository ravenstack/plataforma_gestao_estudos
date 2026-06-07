package com.gestaoestudo.taskapi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // Busca TODAS as tarefas de um usuário específico
    List<Tarefa> findByUsuarioId(Long usuarioId);

    // Busca tarefas filtrando por Usuário E pelo Status (concluída ou não)
    List<Tarefa> findByUsuarioIdAndConcluida(Long usuarioId, boolean concluida);
}
package com.gestaoestudo.taskapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    // Função 1: Create Task (Criar tarefa)
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        // Toda tarefa nova começa como não concluída (pendente)
        tarefa.setConcluida(false);
        Tarefa salva = repository.save(tarefa);
        return ResponseEntity.ok(salva);
    }

    // Função 2: Mark task as done (Marcar como concluída)
    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(@PathVariable Long id) {
        return repository.findById(id).map(tarefa -> {
            tarefa.setConcluida(true); // Muda o status para verdadeiro
            Tarefa atualizada = repository.save(tarefa);
            return ResponseEntity.ok(atualizada);
        }).orElse(ResponseEntity.notFound().build()); // Retorna erro 404 se a tarefa não existir
    }

    // Função 3: Fetch tasks by status (Buscar tarefas)
    @GetMapping
    public ResponseEntity<List<Tarefa>> buscarTarefas(
            @RequestParam Long usuarioId,
            @RequestParam(required = false) Boolean concluida) {

        List<Tarefa> tarefas;

        // Se o usuário passou na URL o filtro de concluída (?concluida=true ou false)
        if (concluida != null) {
            tarefas = repository.findByUsuarioIdAndConcluida(usuarioId, concluida);
        } else {
            // Se não passou filtro, traz todas as tarefas dele
            tarefas = repository.findByUsuarioId(usuarioId);
        }

        return ResponseEntity.ok(tarefas);
    }
}
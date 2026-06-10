package com.gestaoestudo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8">
                    <title>PAES - Plataforma de Gestao de Estudos</title>
                    <style>
                        body { font-family: Arial, sans-serif; background: #1a1a2e; color: #eee; padding: 40px; line-height: 1.6; }
                        h1 { color: #4ade80; }
                        .card { background: #16213e; border-radius: 8px; padding: 24px; max-width: 700px; margin: auto; }
                        code { background: #0f3460; padding: 2px 8px; border-radius: 4px; color: #4ade80; }
                        ul { list-style: none; padding: 0; }
                        li { padding: 8px 0; border-bottom: 1px solid #2a2a4a; }
                        .status { color: #4ade80; font-weight: bold; }
                    </style>
                </head>
                <body>
                    <div class="card">
                        <h1>PAES - Plataforma de Gestao de Estudos</h1>
                        <p class="status">Aplicacao no ar e funcionando!</p>
                        <p>Esta e uma API REST. Endpoints disponiveis:</p>
                        <ul>
                            <li><code>GET /auth/all</code> - Listar usuarios</li>
                            <li><code>POST /auth/register</code> - Registrar usuario</li>
                            <li><code>GET /academic/subjects</code> - Listar materias</li>
                            <li><code>GET /tarefas?usuarioId=1</code> - Listar tarefas</li>
                            <li><code>POST /tarefas</code> - Criar tarefa</li>
                            <li><code>POST /pomodoro/start</code> - Iniciar pomodoro</li>
                            <li><code>POST /notifications/settings</code> - Config. notificacoes</li>
                        </ul>
                    </div>
                </body>
                </html>
                """;
    }
}

package com.gestaoestudo.notificacaoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe recebe requisições REST e devolve dados (como JSON)
@RequestMapping("/notifications") // Prefixo da URL: todos os endpoints aqui começarão com /notifications
public class NotificationController {

    @Autowired // Injeta o repositório automaticamente, sem precisar fazer "new Repository()"
    private NotificationSettingsRepository repository;

    @PostMapping("/settings")
    // @RequestBody pega o JSON que o usuário enviou e transforma no objeto NotificationSettings
    public ResponseEntity<NotificationSettings> salvarConfiguracoes(@RequestBody NotificationSettings configuracoes) {

        // Salva as configurações recebidas no banco de dados
        NotificationSettings salvo = repository.save(configuracoes);

        // Retorna status 200 (OK) e devolve os dados que acabaram de ser salvos
        return ResponseEntity.ok(salvo);
    }
}
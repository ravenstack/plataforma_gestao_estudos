package com.gestaoestudo.notificacaoapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository //Diz ao Spring que esta interface cuida do acesso aos dados (Data Access Object)
public interface NotificationSettingsRepository extends JpaRepository<NotificationSettings, Long> {

    //Método personalizado para buscar as configurações usando apenas o ID do usuário.
    Optional<NotificationSettings> findByUsuarioId(Long usuarioId);
}
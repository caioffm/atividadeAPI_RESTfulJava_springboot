package br.edu.umfg.exercicioAPI.controllers;

import br.edu.umfg.entities.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final List<Client> clientList = new CopyOnWriteArrayList<>();

    @PostMapping
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        clientList.add(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> listClients() {
        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findClient(@PathVariable(value = "clientId") UUID clientId) {
        return clientList.stream()
                .filter(client -> client.getClientId().equals(clientId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> removeClient(@PathVariable UUID clientId) {
        return clientList.removeIf(client -> client.getClientId().equals(clientId))
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> modifyClient(@PathVariable UUID clientId, @RequestBody Client updatedClient) {
        for (Client client : clientList) {
            if (client.getClientId().equals(clientId)) {
                client.setFirstName(updatedClient.getFirstName());
                client.setSurname(updatedClient.getSurname());
                client.setIdNumber(updatedClient.getIdNumber());
                return ResponseEntity.ok(client);
            }
        }
        return ResponseEntity.notFound().build();
    }
}

package pe.telco.recargas.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.telco.recargas.entities.Recarga;
import pe.telco.recargas.messages.request.RecargaCreateRequest;
import pe.telco.recargas.messages.responses.RecargaHistorialResponse;
import pe.telco.recargas.services.RecargaService;

@RestController
@RequestMapping(value = "/recargas")
public class RecargaController {

	@Autowired
	private RecargaService recargaService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Validated @RequestBody RecargaCreateRequest request) {
		try {
			Recarga recarga = Recarga.builder().userId(request.getUserId()).numero(request.getNumero())
					.monto(request.getMonto()).build();
			Recarga nuevaRecarga = recargaService.save(recarga);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(nuevaRecarga.getId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/historial/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecargaHistorialResponse>> fetchById(@PathVariable("userId") Long userId) {
		// TODO verificar que el usuario exista

		try {
			List<Recarga> recargasHistorial = recargaService.findByUserId(userId);

			if (recargasHistorial.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

			List<RecargaHistorialResponse> historial = recargasHistorial.stream()
					.map(recarga -> RecargaHistorialResponse.builder().numero(recarga.getNumero())
							.monto(recarga.getMonto()).build())
					.collect(Collectors.toList());

			return ResponseEntity.ok(historial);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
}

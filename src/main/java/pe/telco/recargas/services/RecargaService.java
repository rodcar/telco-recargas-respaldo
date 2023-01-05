package pe.telco.recargas.services;

import java.util.List;

import pe.telco.recargas.entities.Recarga;

public interface RecargaService extends CrudService<Recarga> {
	List<Recarga> findByUserId(Long userId);
}

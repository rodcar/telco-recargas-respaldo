package pe.telco.recargas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.telco.recargas.entities.Recarga;
import pe.telco.recargas.repositories.RecargaRepository;
import pe.telco.recargas.services.RecargaService;

@Service
public class RecargaServiceImpl implements RecargaService {

	@Autowired
	private RecargaRepository recargaRepository;

	@Override
	public List<Recarga> findAll() throws Exception {
		return recargaRepository.findAll();
	}

	@Override
	public Recarga save(Recarga t) throws Exception {
		return recargaRepository.save(t);
	}

	@Override
	public Recarga update(Recarga t) throws Exception {
		return recargaRepository.save(t);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		recargaRepository.deleteById(id);
	}

	@Override
	public Optional<Recarga> findById(Long id) throws Exception {
		return recargaRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) throws Exception {
		return recargaRepository.existsById(id);
	}

	@Override
	public List<Recarga> findByUserId(Long userId) {
		return recargaRepository.findByUserId(userId);
	}

}

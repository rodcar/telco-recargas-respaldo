package pe.telco.recargas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.telco.recargas.entities.Recarga;

@Repository
public interface RecargaRepository extends JpaRepository<Recarga, Long> {
	List<Recarga> findByUserId(Long userId);
}

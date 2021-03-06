package Ficha8.Ficha8_resolucao.services;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ficha8.Ficha8_resolucao.model.CentroComercial;
import Ficha8.Ficha8_resolucao.repository.CentroComercialRepository;

@Service
public class CentroComercialService {

	private final CentroComercialRepository centroComercialRepository;

	@Autowired
	public CentroComercialService(CentroComercialRepository aCentroComercialRepository) {
		centroComercialRepository = aCentroComercialRepository;

	}

	public boolean addCentroComercial(CentroComercial aCentroComercial) {

//		importa a ordem, normalmente o null vem primeiro que o isBlank

		if (aCentroComercial.getId() == null || aCentroComercial.getAndares().isEmpty()
				|| aCentroComercial.getNumeroMaxAndar() != 0) {

			centroComercialRepository.save(aCentroComercial);

			return true;
		}
		return false;
	}

	public List<CentroComercial> getCentroComerciais() {
		List<CentroComercial> centrosComerciais = new ArrayList<>();

		centroComercialRepository.findAll().forEach(centrosComerciais::add);

		return centrosComerciais;

	}

	public Optional<CentroComercial> getCentroComercial(String aId) {
		try {
			Long id_long = parseLong(aId);
			Optional<CentroComercial> centroComercialOpcional = centroComercialRepository.findById(id_long);
			if (aId == null || id_long == NaN || centroComercialOpcional.isEmpty()) {
				return null;
			}
			return centroComercialOpcional;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean deleteCentroComercialById(String aId) {

		try {
			Long id_long = parseLong(aId);

			Optional<CentroComercial> centroComercialOpcional = centroComercialRepository.findById(id_long);

			if (aId == null || id_long == NaN || centroComercialOpcional.isEmpty()) {
				return false;
			}

			CentroComercial centroComercial = centroComercialOpcional.get();
			centroComercialRepository.delete(centroComercial);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

}

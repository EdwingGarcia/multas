package com.app.multas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.multas.model.Multa;
import com.app.multas.repo.MultaRepository;

@Service
public class MultaService {
	private MultaRepository multaRepository;
	private double aumentoDiarioPocentaje = 0.01;

	@Autowired
	public MultaService(MultaRepository multaRepository, JdbcTemplate jdbcTemplate) {
		this.multaRepository = multaRepository;
	}

	@Transactional
	public List<Multa> obtenerTodasLasMultas() {
		return multaRepository.findAll();
	}

	@Transactional
	public Multa obtenerMultaPorId(Long id) {
		return multaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void crearMulta(Multa multa) {
		multa.setFechaInicio(tiempoActual());
		multa.setfechaLimite(calcularFechaLimitePago(multa));
		multaRepository.save(multa);
	}

	@Transactional
	public void actualizarMulta(Multa multa, Long id) {
		Multa m = obtenerMultaPorId(id);
		if (multa.getEstadoPago() != null) {
			m.setEstadoPago(multa.getEstadoPago());
		} else if (multa.getDescripcion() != null) {
			m.setDescripcion(multa.getDescripcion());
		} else if (multa.getfechaLimite() != null) {
			m.setfechaLimite(multa.getfechaLimite());
		} else if (multa.getFechaInicio() != null) {
			m.setFechaInicio(multa.getFechaInicio());
		} else if (multa.getIdMultado() != null) {
			m.setIdMultado(multa.getIdMultado());
		} else if (multa.getLugar() != null) {
			m.setLugar(multa.getLugar());
		} else if (multa.getMonto() != null) {
			m.setMonto(multa.getMonto());
		}
		multaRepository.save(multa);
	}

	@Transactional
	public void eliminarMulta(Long id) {
		multaRepository.deleteById(id);
	}

	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void actualizarMultas() {
		List<Multa> multasExpiradas = getMultasPlazoPagoSobrepasado();

		for (Multa m : multasExpiradas) {
			double nuevoValor = m.getMonto() * (1 + aumentoDiarioPocentaje);
			m.setMonto(nuevoValor);
			multaRepository.save(m);
		}
	}

	public void setPorcentajeAumentoDiario(double porcentajeAumentoMulta) {
		aumentoDiarioPocentaje = porcentajeAumentoMulta / 100;

	}

	public void actualizarTiempoExpiracionPagoMultas(int dias) {
		Multa.setDiasExpiracion(dias);
	}

	private LocalDateTime calcularFechaLimitePago(Multa multa) {
		if (multa.getFechaInicio() == null) {
			return null;
		} else {
			return multa.getFechaInicio().plusDays(Multa.diasExpiracion);
		}
	}

	private LocalDateTime tiempoActual() {
		return LocalDateTime.now();
	}

	private List<Multa> getMultasPlazoPagoSobrepasado() {
		LocalDateTime tiempoActual = LocalDateTime.now();
		return multaRepository.findExpiredMultas(tiempoActual);
	}
}

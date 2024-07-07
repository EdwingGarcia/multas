package com.app.multas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.multas.model.Multa;
import com.app.multas.service.MultaService;

@RestController
@RequestMapping("/multa")
public class MultaController {
	private MultaService service;

	@Autowired
	public MultaController(MultaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Multa> obtenerMultas() {
		return service.obtenerTodasLasMultas();
	}

	@GetMapping("/{id}")
	public Multa obtenerMultaPorId(@PathVariable Long id) {
		return service.obtenerMultaPorId(id);
	}

	@PostMapping("/generar-multa")
	public void crearMulta(@RequestBody Multa multa) {
		service.crearMulta(multa);
	}

	@PutMapping("/{id}")
	public void actualizarMulta(@PathVariable Long id, @RequestBody Multa multa) {
		multa.setIdMulta(id);
		service.actualizarMulta(multa, id);
	}

	@DeleteMapping("/{id}")
	public void eliminarMulta(@PathVariable Long id) {
		service.eliminarMulta(id);
	}

	@PutMapping("/actualizar/multas-manual")
	public void actualizarMultasManual() {
		service.actualizarMultas();
	}

	@PostMapping("/actualizar/tiempo-expiracion/{dias}")
	public void actualizarTiempoExpiracion(@PathVariable("dias") int tiempo) {
	    service.actualizarTiempoExpiracionPagoMultas(tiempo);
	}


	@PostMapping("/actualizar/porcentaje-aumento/{porcentaje}")
	public void actualizarPorcentajeDiarioAumento(@PathVariable double porcentaje) {
		service.setPorcentajeAumentoDiario(porcentaje);
	}

}
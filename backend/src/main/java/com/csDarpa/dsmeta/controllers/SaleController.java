package com.csDarpa.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csDarpa.dsmeta.entities.Sale;
import com.csDarpa.dsmeta.services.SMSService;
import com.csDarpa.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales") //valor da rota, do caminho que vai ser acessado

public class SaleController {

	@Autowired
	private SaleService service;
	@Autowired
	private SMSService smsService;
	
	//método pra disponibilizar os métoods pro front
	@GetMapping 
	public Page<Sale> findSales(
			@RequestParam(value="minDate", defaultValue="") String minDate, 
			@RequestParam(value="maxDate", defaultValue="") String maxDate, 
			Pageable pageable) {
		return service.findSales(minDate, maxDate, pageable);
	}
	@GetMapping("/{id}/notification") //parametro id obrigatorio
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}
}

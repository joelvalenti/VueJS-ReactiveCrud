package com.utn.demo.controllers;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.utn.demo.dtos.InstrumentoDTO;
import com.utn.demo.services.InstrumentoService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/instrumento")
public class InstrumentoController {

	@Autowired
	InstrumentoService service;

	public InstrumentoController(InstrumentoService service) {
		this.service = service;
	}

	/*@PostMapping
	public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.uploadFile(file));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"message\": \"Error ocurrido en uploadFile\"}");
		}
	}*/
	@PostMapping("/uploadImg")
	@Transactional
	public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.uploadFile(file));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"message\": \"Error ocurrido en uploadFile\"}");
		}
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en GetAll\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en GetOne\"}");
		}
	}

	/*@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody InstrumentoDTO dto,@RequestParam("file") MultipartFile file) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto,file));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en Post\"}");
		}
	}*/
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody InstrumentoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en Post\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable long id, @RequestBody InstrumentoDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en Put\"}");
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en Delete\"}");
		}
	}
}

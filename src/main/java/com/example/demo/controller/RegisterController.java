package com.example.demo.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Register;
import com.example.demo.service.RegisterService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("register")
public class RegisterController {
    @Autowired
    RegisterService registerService;
	
	@PostMapping
    public ResponseEntity<String> create(@RequestBody Register register) {		
		Register newRegister = registerService.newRegister(register);
		if(newRegister != null) {
			return new ResponseEntity<>("Cadastro criado com sucesso", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Não foi possivel realizar o cadastro", HttpStatus.BAD_REQUEST);
    }
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Register register) {
		if(register != null) {
			String msg = registerService.update(register);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<>("Não foi possivel atualizar o cadastro", HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping("/{id}")
	public Register getRegisterById(@PathVariable("id") @Valid Integer id) {
		return registerService.findRegisterById(id);
	}
	
	@GetMapping("list")
	public Iterable<Register> getRegisters(){
		return registerService.registers();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") @Valid Integer id){
		registerService.deleteRegisterById(id);
		return new ResponseEntity<>("Cadastro deletado com sucesso", HttpStatus.OK); 
		
	}
}
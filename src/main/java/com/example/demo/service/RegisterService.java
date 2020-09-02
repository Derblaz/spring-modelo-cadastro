package com.example.demo.service;

import com.example.demo.model.Register;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.utils.ValidarCpf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public Register newRegister(Register newRegister) {
        if (newRegister != null && CpfIsValid(newRegister.getCpf())){
            registerRepository.save(newRegister);
            return newRegister;
        }
        return null;
    }

    public String update(Register register) {
        if(register == null  || !CpfIsValid(register.getCpf())){
            return "Não foi possivel autalizar seu cadastro";
        }
        registerRepository.save(register);
        return "Cadastro atualizado com sucesso";
    }

    public Register findRegisterById(Integer id) {
        return registerRepository.findById(id).get();
    }

    public Register findRegisterByName(String name) {
        return registerRepository.findByName(name);
    }
    
    public Iterable<Register> registers(){
    	return registerRepository.findAll();
    }

    public void deleteRegisterById(Integer id){
        Register register = registerRepository.findById(id).get();
        registerRepository.delete(register);
    }

    private boolean CpfIsValid(String cpf) {
    	ValidarCpf validacpf = new ValidarCpf();
    	return validacpf.validarCpf(cpf);
    }
}
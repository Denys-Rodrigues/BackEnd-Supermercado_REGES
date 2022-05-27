package br.com.reges.aula12backend.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.reges.aula12backend.modelos.Cliente;
import br.com.reges.aula12backend.rdn.ClienteRdn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ClienteController {
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cliente")
    public List<Cliente>ObterTodos(){

        ClienteRdn rdn = new ClienteRdn();
        return rdn.obterTodos();

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cliente/{id}")
    public Cliente GetById(@PathVariable("id") int id) {

        ClienteRdn rdn = new ClienteRdn();
        return rdn.obterPorId(id);
        
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/cliente")
    public int Post(@RequestBody Cliente pcli) throws SQLException {
        
        ClienteRdn rdn = new ClienteRdn();
        return rdn.inserir(pcli);

    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("cliente/{id}")
    public int Put(@PathVariable(value="id") int id, @RequestBody Cliente pCliente){

        ClienteRdn rdn = new ClienteRdn();
        
        if (rdn.obterPorId(id).getId() > 0){
            
            return rdn.alterar(pCliente);
        }
        else{
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "cliente não encontrado");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("cliente/{id}")
    public int Delete(@PathVariable(value="id") int id){

        ClienteRdn rdn = new ClienteRdn();
        
        if (rdn.obterPorId(id).getId() > 0)
        {
            return rdn.deletar(id);
        }
        else
        {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "cliente não encontrado");
        }
    }
}

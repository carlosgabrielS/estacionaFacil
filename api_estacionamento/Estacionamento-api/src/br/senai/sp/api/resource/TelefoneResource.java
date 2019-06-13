package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefones")
public class TelefoneResource {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@GetMapping
	public List<Telefone> getTelefones(){
		return telefoneRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Telefone> gravarTelefone(@RequestBody Telefone telefone,  HttpServletResponse response) {
		Telefone telefoneSalvo = telefoneRepository.save(telefone);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codTelefone}")
				.buildAndExpand(telefoneSalvo.getCodTelefone()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(telefoneSalvo);
		
	}
	
	@PutMapping("{codTelefone}")
	public ResponseEntity<Telefone> atualizarTelefone (@PathVariable Long codTelefone, @RequestBody Telefone atualizarTelefone){
		
		telefoneRepository.save(atualizarTelefone);
		
		
		return ResponseEntity.ok(atualizarTelefone);
	}
}

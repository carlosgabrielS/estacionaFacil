package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.repository.MensalistaRepository;

@RestController
@RequestMapping("/mensalistas")
public class MensalistaResource {

	@Autowired
	private MensalistaRepository mensalistaRepository;
	
	@GetMapping
	public List<Mensalista> getMensalistas(){
		return mensalistaRepository.findAll();
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Mensalista> gravarMensalista(@RequestBody Mensalista mensalista, HttpServletResponse response){
		
		Mensalista mensalistaSalvo = mensalistaRepository.save(mensalista);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codMensalista}")
				.buildAndExpand(mensalistaSalvo.getCodMensalista()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(mensalistaSalvo);
	}
	
	@PutMapping("/{codMensalista}")
	public ResponseEntity<Mensalista> mensalistaSalvo(@PathVariable Long codMensalista, @RequestBody Mensalista mensalistaSalvo){
		
		mensalistaRepository.save(mensalistaSalvo);
		
		return ResponseEntity.ok(mensalistaSalvo);
	}

	
}

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

import br.senai.sp.api.model.Fabricante;
import br.senai.sp.api.repository.FabricanteRepository;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteResource {
	
	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@GetMapping
	public List<Fabricante> getFabricantes(){
		
		return fabricanteRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Fabricante> gravarFabricante (@RequestBody Fabricante fabricante, HttpServletResponse response){
		Fabricante fabricanteSalvo = fabricanteRepository.save(fabricante);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codFabricante}")
				.buildAndExpand(fabricanteSalvo.getCodFabricante()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(fabricanteSalvo);
	}
	
	
	@PutMapping("/{codFabricante}")
	public ResponseEntity<Fabricante> atualizarFabricante (@PathVariable Long codFabricante, @RequestBody Fabricante fabricanteSalvo ){
		
		fabricanteRepository.save(fabricanteSalvo);
		
		return ResponseEntity.ok(fabricanteSalvo);
	}
	
	
}

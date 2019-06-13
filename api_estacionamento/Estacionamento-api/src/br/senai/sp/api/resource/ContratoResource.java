package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Contrato;
import br.senai.sp.api.repository.ContratoRepository;

@RestController
@RequestMapping("/contratos")
public class ContratoResource {

	@Autowired
	private ContratoRepository contratoRepository;
	
	public List<Contrato> getContratos(){
		return contratoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Contrato> gravarContrato(@RequestBody Contrato contrato, HttpServletResponse response){
		
		Contrato contratoSalvo = contratoRepository.save(contrato);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.buildAndExpand(contratoSalvo.getCodContrato()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(contratoSalvo);
	}
	
	@PutMapping("/{codContrato}")
	public ResponseEntity<Contrato> atualizarContrato(@PathVariable Long codContrato, @RequestBody Contrato contratoSalvo){
		contratoRepository.save(contratoSalvo);
		
		return ResponseEntity.ok(contratoSalvo);
	}
	
}

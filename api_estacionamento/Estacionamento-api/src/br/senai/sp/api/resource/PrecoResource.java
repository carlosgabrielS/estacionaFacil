package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

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

import br.senai.sp.api.conversao.DataBanco;
import br.senai.sp.api.model.Preco;

import br.senai.sp.api.repository.PrecoRepository;

@RestController
@RequestMapping("/precos")
public class PrecoResource {
	
	@Autowired
	private PrecoRepository precoRepository;
	
	@GetMapping
	public List<Preco> getPrecos(){
		return precoRepository.findAll();
		
	}
	
	@GetMapping("/vigente")
	public Preco getVigente() {
		return precoRepository.findByVigente();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Preco> gravarPreco(@RequestBody Preco preco, HttpServletResponse response){
		
		Preco precoAtual = precoRepository.findByVigente();
		
		precoAtual.setDataFim(DataBanco.getFormatoBanco("yyyy-MM-dd"));
		
		precoRepository.save(precoAtual);
		
		Preco precoSalvo = precoRepository.save(preco);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codPreco}")
				.buildAndExpand(precoSalvo.getCodPreco()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(precoSalvo);
	}
	
	
//	@PutMapping("/{codPreco}")
//	public ResponseEntity<Preco> finalizarPreco(@PathVariable Long codPreco, @RequestBody Preco precoSalvo){
//				
//		precoSalvo.setDataFim(DataBanco.getFormatoBanco("yyyy-MM-dd"));
//		
//		precoRepository.save(precoSalvo);
//		
//		return ResponseEntity.ok(precoSalvo);
//	}

}

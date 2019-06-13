package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.Ascii;
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

import br.senai.sp.api.model.Endereco;
import br.senai.sp.api.repository.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping
	public List<Endereco> getEnderecos(){
		return enderecoRepository.findAll();	
	}
	
	@PostMapping
	public ResponseEntity<Endereco> gravarEndereco(@RequestBody Endereco endereco, HttpServletResponse response){
		
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.buildAndExpand(enderecoSalvo.getCodEndereco()).toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(enderecoSalvo);
	}
	
	@PutMapping("/{codEndereco}")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long codEndereco, @RequestBody Endereco enderecoSalvo){
		enderecoRepository.save(enderecoSalvo);
		
		return ResponseEntity.ok(enderecoSalvo);
		
	}
	
}

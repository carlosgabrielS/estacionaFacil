package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.MensalistaTelefone;
import br.senai.sp.api.repository.MensalistaTelefoneRepository;

@RestController
@RequestMapping("/telefone_mensalistas")
public class MensalistaTelefoneResource {
	
	@Autowired
	private MensalistaTelefoneRepository mensalistaTelefoneRepository;
	
	@GetMapping
	public List<MensalistaTelefone> getTelefonesMensalista(){
		return mensalistaTelefoneRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void gravarTelefoneMensalistas(@RequestBody MensalistaTelefone mensalistaTelefone) {
		mensalistaTelefoneRepository.save(mensalistaTelefone);
	}
	
}

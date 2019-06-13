package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.calculo.Pagamento;
import br.senai.sp.api.conversao.DataBanco;
import br.senai.sp.api.model.Movimentacao;
import br.senai.sp.api.model.Preco;
import br.senai.sp.api.repository.MovimentacaoRepository;
import br.senai.sp.api.repository.PrecoRepository;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoResource {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private PrecoRepository precoRepository;
	
	@GetMapping
	public List<Movimentacao> getMovimentacoes(){
		return movimentacaoRepository.findAll();
	}
	
	@GetMapping("/estacionados")
	public List<Movimentacao> getMovimentados() {
		return movimentacaoRepository.findByEstacionados();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Movimentacao> gravarMovimentacao(@RequestBody Movimentacao movimentacao, HttpServletResponse response){
		
		movimentacao.setHoraEntrada(DataBanco.getFormatoBanco("yyyy-MM-dd HH:mm:00 "));
		
		Movimentacao movimentacaoSalvo = movimentacaoRepository.save(movimentacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codMovimentacao}")
				.buildAndExpand(movimentacaoSalvo.getCodMovimentacao()).toUri();
	
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(movimentacaoSalvo);
	}
	
	@GetMapping("/cod/{codMovimentacao}")
	public Optional<Movimentacao> getMovimentacao(@PathVariable Long codMovimentacao){
		
		return movimentacaoRepository.findById(codMovimentacao);
	}
	
	@GetMapping("/placa/{placa}")
	public Movimentacao getMovimentacaoPlaca(@PathVariable String placa) {
		
		Movimentacao movimentacao = movimentacaoRepository.findByPlaca(placa);
		Pagamento pagamento = new Pagamento(movimentacao);

		Preco precoSalvo = precoRepository.findByVigente();
		
		pagamento.fecharMovimento(precoSalvo);
		
		
		
		return movimentacao;
	}
	
	@PutMapping("/{placa}")
	public ResponseEntity<Movimentacao> atualizar(@PathVariable String placa){
		
		Movimentacao movimentacaoSalvo = movimentacaoRepository.findByPlaca(placa);
		
		Pagamento pagamento = new Pagamento(movimentacaoSalvo);

		Preco precoSalvo = precoRepository.findByVigente(); 
		
		pagamento.fecharMovimento(precoSalvo);
		
		movimentacaoRepository.save(movimentacaoSalvo);
		
		return ResponseEntity.ok(movimentacaoSalvo);
	}
	
	
	/* ****DELETAR******
	  
	 * @DeleteMapping("/{codMovimentacao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codMovimentacao) {
		
		movimentacaoRepository.deleteById(codMovimentacao);
		
	}*/
	
	
}

package com.orange.mercado.livre.cadastraProduto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.orange.mercado.livre.cadastraCategoria.Categoria;
import com.orange.mercado.livre.cadastraCategoria.CategoriaRepository;
import com.orange.mercado.livre.cadastraUsuario.Usuario;
import com.orange.mercado.livre.validator.ExistsId;

public class ProdutoForm {

	@NotBlank
	private String nome;

	@NotNull
	@Min(0)
	private BigDecimal valor;

	@NotNull
	@Min(0)
	private Integer qtdDisponivel;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	@Size(min = 3)
	@Valid
	private List<CaracteristicaForm> caracteristicas = new ArrayList<>();

	public ProdutoForm(@NotBlank String nome, @NotNull @Min(0) BigDecimal valor, @NotNull @Min(0) Integer qtdDisponivel,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Long idCategoria,
			List<CaracteristicaForm> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	/*
	 * Sem o método acessor GET pode retornar um erro parecido com
	 * "is not readable or has an invalid getter method", e caso aconteça esse erro
	 * devo fornecer um método acessor.
	 */
	public List<CaracteristicaForm> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<CaracteristicaForm> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Produto converterProduto(CategoriaRepository categoriaRepository, Usuario dono) {

		Categoria categoria = categoriaRepository.findById(idCategoria).get();

		return new Produto(this.nome, this.valor, this.qtdDisponivel, this.descricao, categoria, dono,
				this.caracteristicas);
	}

	public Set<String> buscaCaracteristicasIguais() {
		// HASHSET não suporta elementos iguais
		// STRING já tem método de comparação implementado
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();

		for (CaracteristicaForm caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();

			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

}

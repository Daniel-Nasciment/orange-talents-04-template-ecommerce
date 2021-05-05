package com.orange.mercado.livre.cadastraProduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.orange.mercado.livre.cadastraCategoria.Categoria;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@ManyToOne
	private Categoria categoria;

	// Sempre que cadastrar um novo produto, vaicadastrar junto us caracteristicas
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	@NotNull
	private LocalDateTime instCadastro = LocalDateTime.now();

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @NotNull @Min(0) BigDecimal valor, @NotNull @Min(0) Integer qtdDisponivel,
			@NotBlank @Length(max = 1000) String descricao, Categoria categoria,
			@Size(min = 3) @Valid Collection<CaracteristicaForm> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		
		
		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características");
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", qtdDisponivel=" + qtdDisponivel
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", caracteristicas=" + caracteristicas
				+ ", instCadastro=" + instCadastro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}

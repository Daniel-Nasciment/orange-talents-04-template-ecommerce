package com.orange.mercado.livre.cadastraPergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.orange.mercado.livre.cadastraProduto.Produto;
import com.orange.mercado.livre.cadastraUsuario.Usuario;


//Comparable para comparar os titulos das perguntas, com isso posso ordenar as perguntas de um determinado produto
@Entity
public class PerguntaProduto implements Comparable<PerguntaProduto>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	private LocalDateTime instPerguntado = LocalDateTime.now();

	@ManyToOne
	@NotNull
	private Produto produto;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@Deprecated
	public PerguntaProduto() {

	}

	public PerguntaProduto(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
		super();
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "PerguntaProduto [id=" + id + ", titulo=" + titulo + ", instPerguntado=" + instPerguntado + ", produto="
				+ produto + ", usuario=" + usuario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		PerguntaProduto other = (PerguntaProduto) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public Usuario getInteressada() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

	@Override
	public int compareTo(PerguntaProduto o) {
		return this.titulo.compareTo(o.titulo );
	}

	public String getTitulo() {
		return titulo;
	}

}

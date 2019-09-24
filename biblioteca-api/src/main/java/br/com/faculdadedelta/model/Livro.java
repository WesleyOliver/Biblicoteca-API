package br.com.faculdadedelta.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório!")
	private String nome;

	@NotNull(message = "O volume é obrigatório!")
	private Integer volume;

	@NotNull(message = "A data é obrigatória!")
	private LocalDate dataPublicacao;

	@NotNull(message = "O valor é obrigatório!")
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "id_genero")
	@NotNull(message = "O genero é obrigatório!")
	private Genero genero;

	@ManyToOne
	@JoinColumn(name = "id_editora")
	@NotNull(message = "A editora é obrigatório!")
	private Editora editora;

	@ManyToOne
	@JoinColumn(name = "id_autor")
	@NotNull(message = "O autor é obrigatório")
	private Autor autor;

	public Livro() {
		super();
	}

	public Livro(Long id, String nome, Integer volume, LocalDate dataPublicacao, BigDecimal valor, Genero genero,
			Editora editora, Autor autor) {
		super();
		this.id = id;
		this.nome = nome;
		this.volume = volume;
		this.dataPublicacao = dataPublicacao;
		this.valor = valor;
		this.genero = genero;
		this.editora = editora;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

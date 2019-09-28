package br.com.faculdadedelta.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Data do emprestimo é obrigatória!")
	@Column(name = "data_Emprestimo")
	private LocalDate dataDoEmprestimo;

	@Column(name = "data_Devolucao")
	private LocalDate dataDaDevolucao;

	@NotNull(message = "Valor do emprestimo é obrigatório!")
	@Column(name = "valor_emprestimo")
	private BigDecimal valorDoEmprestimo;

	@ManyToOne
	@JoinColumn(name = "id_livro")
	@NotNull(message = "Livro é obrigatório!")
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@NotNull(message = "Cliente é obrigatório!")
	private Cliente cliente;

	public Emprestimo() {
		super();
	}

	public Emprestimo(Long id, LocalDate dataDoEmprestimo, LocalDate dataDaDevolucao, BigDecimal valorDoEmprestimo,
			Livro livro, Cliente cliente) {
		super();
		this.id = id;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.dataDaDevolucao = dataDaDevolucao;
		this.valorDoEmprestimo = valorDoEmprestimo;
		this.livro = livro;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataDoEmprestimo() {
		return dataDoEmprestimo;
	}

	public void setDataDoEmprestimo(LocalDate dataDoEmprestimo) {
		this.dataDoEmprestimo = dataDoEmprestimo;
	}

	public LocalDate getDataDaDevolucao() {
		return dataDaDevolucao;
	}

	public void setDataDaDevolucao(LocalDate dataDaDevolucao) {
		this.dataDaDevolucao = dataDaDevolucao;
	}

	public BigDecimal getValorDoEmprestimo() {
		return valorDoEmprestimo;
	}

	public void setValorDoEmprestimo(BigDecimal valorDoEmprestimo) {
		this.valorDoEmprestimo = valorDoEmprestimo;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Emprestimo other = (Emprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

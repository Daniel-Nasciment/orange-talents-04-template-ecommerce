package com.orange.mercado.livre.cadastraPergunta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaProdutoRepository extends JpaRepository<PerguntaProduto, Long> {

}

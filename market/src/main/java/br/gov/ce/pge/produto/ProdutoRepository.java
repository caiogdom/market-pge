package br.gov.ce.pge.produto;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProdutoRepository extends JpaRepository<Produto, String> {
}

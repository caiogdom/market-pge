package br.gov.ce.pge.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface CompraRepository extends JpaRepository<Compra, Long>, JpaSpecificationExecutor<Compra> {

}

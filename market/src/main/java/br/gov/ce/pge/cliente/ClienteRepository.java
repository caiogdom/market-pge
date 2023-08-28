package br.gov.ce.pge.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

interface ClienteRepository extends JpaRepository<Cliente, String> {
}

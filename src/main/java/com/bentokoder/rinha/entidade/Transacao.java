package com.bentokoder.rinha.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transacoes")
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Tipos tipo;

    private String descricao;

    @Column(name = "realizada_em")
    private Instant realizadaEm;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

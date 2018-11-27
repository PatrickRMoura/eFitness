/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efitness.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author gustavo.fonseca
 */
public class Matricula {
    private int id;
    private Aluno aluno;
    private LocalDate data;
    private BigDecimal valor;
    private int periodicidade;
    private LocalDate vencimento;

  public Matricula(int id, Aluno aluno, LocalDate data, BigDecimal valor, int periodicidade, LocalDate vencimento) {
    this.id = id;
    this.aluno = aluno;
    this.data = data;
    this.valor = valor;
    this.periodicidade = periodicidade;
    this.vencimento = vencimento;
  }
  
  public Matricula(Aluno aluno, LocalDate data, BigDecimal valor, int periodicidade, LocalDate vencimento) {    
    this.aluno = aluno;
    this.data = data;
    this.valor = valor;
    this.periodicidade = periodicidade;
    this.vencimento = vencimento;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public LocalDate getData() {
    return data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public int getPeriodicidade() {
    return periodicidade;
  }

  public void setPeriodicidade(int periodicidade) {
    this.periodicidade = periodicidade;
  }

  public LocalDate getVencimento() {
    return vencimento;
  }

  public void setVencimento(LocalDate vencimento) {
    this.vencimento = vencimento;
  } 
  
}

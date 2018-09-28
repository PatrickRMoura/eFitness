/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eFitness.model;

/**
 *
 * @author gustavo.fonseca
 */
public class Exercicio {  
  private int id;
  private String nome;
  
  public Exercicio(int id, String nome) {
    this.id = id;
    this.nome = nome;
  }
  
  public Exercicio(String nome) {
    this.id = id;
    this.nome = nome;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getNome() {
    return this.nome;
  }
  
}

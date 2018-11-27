package efitness.model;

/**
 *
 * @author Gustavo Fonseca
 */

import java.time.LocalDate;

public class Avaliacao {
    private int id;
    private Aluno aluno;
    private LocalDate data;
    private double massaCorporal;
    private double frequenciaCardiaca;
    private double pressaoArterial;

    public Avaliacao(int id, Aluno aluno, LocalDate data, double massaCorporal, double frequenciaCardiaca, double pressaoArterial) {
        this.id = id;
        this.aluno = aluno;
        this.data = data;
        this.massaCorporal = massaCorporal;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.pressaoArterial = pressaoArterial;        
    }
        public Avaliacao(Aluno aluno, LocalDate data, double massaCorporal, double frequenciaCardiaca, double pressaoArterial) {        
        this.aluno = aluno;
        this.data = data;
        this.massaCorporal = massaCorporal;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.pressaoArterial = pressaoArterial;        
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

    public double getMassaCorporal() {
      return massaCorporal;
    }

    public void setMassaCorporal(double massaCorporal) {
      this.massaCorporal = massaCorporal;
    }

    public double getFrequenciaCardiaca() {
      return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(double frequenciaCardiaca) {
      this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public double getPressaoArterial() {
      return pressaoArterial;
    }

    public void setPressaoArterial(double pressaoArterial) {
      this.pressaoArterial = pressaoArterial;
    }
        
}

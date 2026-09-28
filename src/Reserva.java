public class Reserva {

    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {

        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        this.numeroDias = numeroDias;
        this.valorDiaria = valorDiaria;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

     public String getTipoQuarto() {
        return tipoQuarto;
     }

     public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
     }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    // Calcula o valor total da hospedagem
     public double calcularValorTotal() {
        return  numeroDias * valorDiaria;
     }

    @Override
    public String toString() {
        return  "\n===== RESERVA =====" +
                "\nHóspede: " + nomeHospede +
                "\nTipo do quarto: " + tipoQuarto +
                "\nNúmero de dias: " + numeroDias +
                String.format("\nValor da diária: R$ %.2f", valorDiaria) +
                String.format("\nValor total: R$ %.2f", calcularValorTotal());
    }
}

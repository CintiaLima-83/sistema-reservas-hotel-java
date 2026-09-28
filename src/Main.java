import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Vetor com capacidade máxima de 10 reservas
        Reserva[] reservas = new Reserva[10];

        // Controla quantas reservas foram cadastradas
        int quantidadeReservas = 0;

        int opcao;

        do {
            System.out.println("\n===== HOTEL =====");
            System.out.println("1 - Cadastrar nova reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Buscar reserva");
            System.out.println("4 - Ordenar reservas");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    quantidadeReservas = cadastrarReserva(
                            scanner,
                            reservas,
                            quantidadeReservas
                    );
                    break;

                case 2:
                    listarReservas(
                            reservas,
                            quantidadeReservas
                    );
                    break;

                case 3:
                    buscarReserva(
                            scanner,
                            reservas,
                            quantidadeReservas
                    );
                    break;

                case 4:
                    ordenarReservas(
                            reservas,
                            quantidadeReservas
                    );
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }

    public static int cadastrarReserva(
            Scanner scanner,
            Reserva[] reservas,
            int quantidadeReservas) {

        // Impede novos cadastros quando o vetor estiver cheio
        if (quantidadeReservas >= reservas.length) {
            System.out.println(
                    "Hotel cheio! Não é possível cadastrar novas reservas."
            );

            return quantidadeReservas;
        }

        scanner.nextLine();

        // Validação do nome do hóspede
        String nomeHospede;

        do {
            System.out.print("Nome do hóspede: ");
            nomeHospede = scanner.nextLine().trim();

            if (nomeHospede.isEmpty()) {
                System.out.println(
                        "O nome do hóspede não pode ficar vazio."
                );
            }

        } while (nomeHospede.isEmpty());


        // Validação do tipo do quarto
        String tipoQuarto;

        do {
            System.out.print(
                    "Tipo do quarto (Standard, Luxo ou Presidencial): "
            );

            tipoQuarto = scanner.nextLine();

            if (!tipoQuarto.equalsIgnoreCase("Standard")
                    && !tipoQuarto.equalsIgnoreCase("Luxo")
                    && !tipoQuarto.equalsIgnoreCase("Presidencial")) {

                System.out.println("Tipo de quarto inválido!");
            }

        } while (!tipoQuarto.equalsIgnoreCase("Standard")
                && !tipoQuarto.equalsIgnoreCase("Luxo")
                && !tipoQuarto.equalsIgnoreCase("Presidencial"));


        // Validação do número de dias
        int numeroDias;

        do {
            System.out.print("Número de dias: ");
            numeroDias = scanner.nextInt();

            if (numeroDias <= 0) {
                System.out.println("Número de dias inválido!");
            }

        } while (numeroDias <= 0);


        // Validação do valor da diária
        double valorDiaria;

        do {
            System.out.print("Valor da diária: R$ ");
            valorDiaria = scanner.nextDouble();

            if (valorDiaria <= 0) {
                System.out.println("Valor da diária inválido!");
            }

        } while (valorDiaria <= 0);


        // Confirmação da reserva
        int confirmar;

        do {
            System.out.print(
                    "Deseja confirmar a reserva? 1 - Sim / 2 - Não: "
            );

            confirmar = scanner.nextInt();

            if (confirmar != 1 && confirmar != 2) {
                System.out.println(
                        "Opção inválida! Digite 1 ou 2."
                );
            }

        } while (confirmar != 1 && confirmar != 2);


        if (confirmar == 1) {

            reservas[quantidadeReservas] = new Reserva(
                    nomeHospede,
                    tipoQuarto,
                    numeroDias,
                    valorDiaria
            );

            quantidadeReservas++;

            System.out.println(
                    "Reserva cadastrada com sucesso!"
            );

        } else {

            System.out.println(
                    "Reserva cancelada."
            );
        }

        return quantidadeReservas;
    }

    public static void listarReservas(
            Reserva[] reservas,
            int quantidadeReservas) {

        if (quantidadeReservas == 0) {
            System.out.println(
                    "Nenhuma reserva cadastrada."
            );

            return;
        }

        System.out.println(
                "\n===== RESERVAS CADASTRADAS ====="
        );

        // Percorre somente as posições preenchidas do vetor
        for (int i = 0; i < quantidadeReservas; i++) {
            System.out.println(reservas[i]);
        }
    }

    public static void buscarReserva(
            Scanner scanner,
            Reserva[] reservas,
            int quantidadeReservas) {

        if (quantidadeReservas == 0) {
            System.out.println(
                    "Nenhuma reserva cadastrada."
            );

            return;
        }

        scanner.nextLine();

        System.out.print(
                "Digite o nome do hóspede: "
        );

        String busca = scanner.nextLine();

        boolean encontrou = false;

        // Busca por parte do nome sem diferenciar
        // letras maiúsculas e minúsculas
        for (int i = 0; i < quantidadeReservas; i++) {

            if (reservas[i]
                    .getNomeHospede()
                    .toLowerCase()
                    .contains(busca.toLowerCase())) {

                System.out.println(reservas[i]);

                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println(
                    "Nenhuma reserva encontrada."
            );
        }
    }

    public static void ordenarReservas(
            Reserva[] reservas,
            int quantidadeReservas) {

        if (quantidadeReservas == 0) {
            System.out.println(
                    "Nenhuma reserva cadastrada."
            );

            return;
        }

        // Ordena as reservas pelo número de dias
        // em ordem decrescente
        for (int i = 0; i < quantidadeReservas - 1; i++) {

            for (int j = i + 1; j < quantidadeReservas; j++) {

                if (reservas[i].getNumeroDias()
                        < reservas[j].getNumeroDias()) {

                    Reserva temporaria = reservas[i];
                    reservas[i] = reservas[j];
                    reservas[j] = temporaria;
                }
            }
        }

        System.out.println(
                "Reservas ordenadas com sucesso!"
        );
    }
}
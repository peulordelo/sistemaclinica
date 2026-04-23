import clinica.manager;
import clinica.agendamento;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        manager gerente = new manager();
        int opcao = 0;

        System.out.println("Sistema de Clinica");

        while (opcao != 5) {
            System.out.println("\n1. Cadastrar Paciente");
            System.out.println("2. Marcar Consulta");
            System.out.println("3. Listar Pacientes");
            System.out.println("4. Listar Consultas");
            System.out.println("5. Salvar e Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("ID do paciente: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do paciente: ");
                    String nome = scanner.nextLine();
                    gerente.cadastrarPaciente(id, nome);
                    break;
                case 2:
                    System.out.print("ID do paciente: ");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Data e hora (ex: 25/10/2024 14:00): ");
                    String dataHora = scanner.nextLine();

                    try {
                        gerente.marcarConsulta(idBusca, dataHora);
                    } catch (agendamento e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    gerente.listarPacientes();
                    break;
                case 4:
                    gerente.listarConsultas();
                    break;
                case 5:
                    gerente.salvarDados();
                    System.out.println("Encerrando.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
        scanner.close();
    }
}
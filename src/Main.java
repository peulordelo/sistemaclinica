import clinica.Manager;
import clinica.AgendamentoException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager gerente = new Manager();
        int opcao = 0;

        System.out.println("Sistema de Clinica");

        while (opcao != 7) {
            System.out.println("\n1. Cadastrar Paciente");
            System.out.println("2. Marcar Consulta");
            System.out.println("3. Listar Pacientes");
            System.out.println("4. Listar Consultas");
            System.out.println("5. Editar Nome do Paciente");
            System.out.println("6. Excluir Paciente ");
            System.out.println("7. Salvar e Sair");
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
                    } catch (AgendamentoException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("Operacao de agendamento finalizada.");
                    }
                    break;
                case 3:
                    gerente.listarPacientes();
                    break;
                case 4:
                    gerente.listarConsultas();
                    break;
                case 5:
                    System.out.print("Digite o ID do paciente que deseja alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o NOVO nome do paciente: ");
                    String novoNome = scanner.nextLine();
                    gerente.atualizarPaciente(idAlterar, novoNome);
                    break;
                case 6:
                    System.out.print("Digite o ID do paciente que deseja excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    gerente.excluirPaciente(idExcluir);
                    break;
                case 7:
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
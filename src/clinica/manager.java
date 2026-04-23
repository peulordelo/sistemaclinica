package clinica;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class manager {
    private List<paciente> pacientes;
    private List<Consulta> consultas;
    private final String ARQUIVO = "dados_clinica.dat";

    public manager() {
        pacientes = new ArrayList<>();
        consultas = new ArrayList<>();
        carregarDados();
    }

    public void cadastrarPaciente(int id, String nome) {
        pacientes.add(new paciente(id, nome));
        System.out.println("Paciente cadastrado com sucesso.");
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (paciente p : pacientes) {
                System.out.println(p);
            }
        }
    }

    public void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            for (Consulta c : consultas) {
                System.out.println(c);
            }
        }
    }

    // A especialidade foi removida daqui
    public void marcarConsulta(int idPaciente, String dataHora) throws agendamento {
        paciente pacienteEncontrado = null;
        for (paciente p : pacientes) {
            if (p.getId() == idPaciente) {
                pacienteEncontrado = p;
                break;
            }
        }

        if (pacienteEncontrado == null) {
            throw new agendamento("Erro: Paciente com ID " + idPaciente + " nao encontrado.");
        }

        Consulta novaConsulta = new Consulta(pacienteEncontrado, dataHora);
        consultas.add(novaConsulta);
        System.out.println("Consulta marcada com sucesso.");
    }

    public void salvarDados() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO));
            oos.writeObject(pacientes);
            oos.writeObject(consultas);
            System.out.println("Dados salvos no disco.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo.");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            System.out.println("Iniciando sistema vazio.");
            return;
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(ARQUIVO));
            pacientes = (List<paciente>) ois.readObject();
            consultas = (List<Consulta>) ois.readObject();
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo.");
                }
            }
        }
    }
}
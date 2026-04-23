package clinica;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private final String ARQUIVO = "dados_clinica.dat";

    public Manager() {
        pacientes = new ArrayList<>();
        consultas = new ArrayList<>();
        carregarDados();
    }

    //CREATE
    public void cadastrarPaciente(int id, String nome) {
        pacientes.add(new Paciente(id, nome));
        System.out.println("Paciente cadastrado com sucesso.");
    }

    //READ
    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (Paciente p : pacientes) {
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

    // UPDATE
    public void atualizarPaciente(int id, String novoNome) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                pacientes.remove(p);
                pacientes.add(new Paciente(id, novoNome));
                System.out.println("Dados do paciente atualizados com sucesso.");
                return;
            }
        }
        System.out.println("Paciente com ID " + id + " não encontrado.");
    }

    // DELETE
    public void excluirPaciente(int id) {
        Paciente pacienteRemover = null;
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                pacienteRemover = p;
                break;
            }
        }
        if (pacienteRemover != null) {
            pacientes.remove(pacienteRemover);
            System.out.println("Paciente excluído com sucesso.");
        } else {
            System.out.println("Paciente com ID " + id + " não encontrado.");
        }
    }

    public void marcarConsulta(int idPaciente, String dataHora) throws AgendamentoException {
        Paciente pacienteEncontrado = null;
        for (Paciente p : pacientes) {
            if (p.getId() == idPaciente) {
                pacienteEncontrado = p;
                break;
            }
        }

        if (pacienteEncontrado == null) {
            throw new AgendamentoException("Erro: Paciente com ID " + idPaciente + " não encontrado.");
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
                try { oos.close(); } catch (IOException e) { System.out.println("Erro ao fechar."); }
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
            pacientes = (List<Paciente>) ois.readObject();
            consultas = (List<Consulta>) ois.readObject();
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        } finally {
            if (ois != null) {
                try { ois.close(); } catch (IOException e) { System.out.println("Erro ao fechar."); }
            }
        }
    }
}
package clinica;
import java.io.Serializable;

public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;
    private paciente pac;
    private String dataHora;

    public Consulta(paciente pac, String dataHora) {
        this.pac = pac;
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Paciente: " + pac.getNome() + "  Data/Hora: " + dataHora;
    }
}
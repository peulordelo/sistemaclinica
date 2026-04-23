package clinica;
import java.io.Serializable;

public class paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;

    public paciente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + id + "   Nome: " + nome;
    }
}
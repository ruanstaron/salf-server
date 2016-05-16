package Value;

/**
 * @author cristhian
 */
public class SalaValue {
    
    private int id_sala;
    private String descricao;
    
    public SalaValue(int id_sala, String descricao) {
        this.id_sala = id_sala;
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return "Sala [id_sala=" + id_sala + ", descricao=" + descricao + "]";
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}

package modelos;

/**
 * @author cristhian
 */
public class Motivo {
    
    private int id_motivo;
    private String descricao;
    
    public Motivo(int id_motivo, String descricao) {
        this.id_motivo = id_motivo;
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return "Motivo [id_motivo=" + id_motivo + ", descricao=" + descricao + "]";
    }

    public int getId_motivo() {
        return id_motivo;
    }

    public void setId_motivo(int id_motivo) {
        this.id_motivo = id_motivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}

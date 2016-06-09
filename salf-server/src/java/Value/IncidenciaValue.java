package Value;

import Util.ProfessorUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author cristhian
 */
public class IncidenciaValue {
    
    private int id;
    private String descricao;
    
    public IncidenciaValue() {
        this.id = -1;
        this.descricao = null;
    }
    
    public IncidenciaValue(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public IncidenciaValue(String json) throws Exception {
        System.out.println("json " + json);
        this.id = -1;
        this.descricao = null;
        
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readValue(json, JsonNode.class);

            if (node.has(ProfessorUtil.PAR_ID)) {
                this.id = node.get(ProfessorUtil.PAR_ID).asInt();
            }
            if (node.has(ProfessorUtil.PAR_DESCRICAO)) {
                this.descricao = node.get(ProfessorUtil.PAR_DESCRICAO).asText();
            }
        } catch (Exception e) {
            System.out.println("Exceção ao interpretar incidência: " + e.getMessage());
            throw e;
        }
    }
    
    @Override
    public String toString() {
        return "Incidencia [id=" + id + ", descricao=" + descricao + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}

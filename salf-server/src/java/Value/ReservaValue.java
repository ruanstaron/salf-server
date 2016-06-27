package Value;

import Util.ReservaUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Ruan
 */
public class ReservaValue {

    private int id;
    private int id_sala;
    private int id_usuario;
    private String data;
    private int id_horario;
    private int id_motivo;
    String sala;
    String horario;
    String motivo;

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public ReservaValue(int id_reserva) {
        this.id = id_reserva;
    }
    
    public ReservaValue(int id_reserva, int sala, String data, int id_horario, int motivo) {
        this.id = id_reserva;
        this.id_sala = sala;
        this.data = data;
        this.id_horario = id_horario;
        this.id_motivo = motivo;
        this.id_usuario = 1;
    }
    
    public ReservaValue(int id_reserva, String sala, String data, String horario, String motivo) {
        this.id = id_reserva;
        this.sala = sala;
        this.data = data;
        this.horario = horario;
        this.motivo = motivo;
        this.id_usuario = 1;
    }

    public ReservaValue(int sala, String data, int id_horario, int motivo) {
        this.id_sala = sala;
        this.data = data;
        this.id_horario = id_horario;
        this.id_motivo = motivo;
        this.id_usuario = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int sala) {
        this.id_sala = sala;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_motivo() {
        return id_motivo;
    }

    public void setId_motivo(int motivo) {
        this.id_motivo = motivo;
    }
        
    public ReservaValue(String json) throws Exception {
        System.out.println("json " + json);
        this.id = -1;
        this.id_sala = -1;
        this.data = null;
        this.id_horario = -1;
        this.id_motivo = -1;
        this.id_usuario = 1;
            
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readValue(json, JsonNode.class);
            
            if (node.has(ReservaUtil.PAR_RESERVA_ID)) {
                this.id = node.get(ReservaUtil.PAR_RESERVA_ID).asInt();
            }
            if (node.has(ReservaUtil.PAR_SALA)) {
                this.id_sala = node.get(ReservaUtil.PAR_SALA).asInt();
            }
            if (node.has(ReservaUtil.PAR_MOTIVO)) {
                this.id_motivo = node.get(ReservaUtil.PAR_MOTIVO).asInt();
            }
            if (node.has(ReservaUtil.PAR_DATA)) {
                this.data = node.get(ReservaUtil.PAR_DATA).asText();
            }
            if (node.has(ReservaUtil.PAR_HORA)) {
                this.id_horario = node.get(ReservaUtil.PAR_HORA).asInt();
            }
        } catch (Exception e) {
            System.out.println("Exceção ao interpretar reserva: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", sala=" + sala + ", data=" + data + 
                ", hora=" + horario + ", motivo="+ motivo + "]";
    }
}

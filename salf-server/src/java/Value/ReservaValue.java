package Value;

import Util.ReservaUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Ruan
 */
public class ReservaValue {

    private int id;
    private int sala;
    private int id_usuario;
    private String data;
    private String hora;
    private int motivo;

    public ReservaValue(int id_reserva) {
        this.id = id_reserva;
    }
    
    public ReservaValue(int id_reserva, int sala, String data, String hora, int motivo) {
        this.id = id_reserva;
        this.sala = sala;
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
        this.id_usuario = 1;
    }

    public ReservaValue(int sala, String data, String hora, int motivo) {
        this.sala = sala;
        this.data = data;
        this.hora = hora;
        this.motivo = motivo;
        this.id_usuario = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }
        
    public ReservaValue(String json) throws Exception {
        System.out.println("json " + json);
        this.id = -1;
        this.sala = -1;
        this.data = null;
        this.hora = null;
        this.motivo = -1;
        this.id_usuario = 1;
            
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readValue(json, JsonNode.class);
            
            if (node.has(ReservaUtil.PAR_RESERVA_ID)) {
                this.id = node.get(ReservaUtil.PAR_RESERVA_ID).asInt();
            }
            if (node.has(ReservaUtil.PAR_SALA)) {
                this.sala = node.get(ReservaUtil.PAR_SALA).asInt();
            }
            if (node.has(ReservaUtil.PAR_MOTIVO)) {
                this.motivo = node.get(ReservaUtil.PAR_MOTIVO).asInt();
            }
            if (node.has(ReservaUtil.PAR_DATA)) {
                this.data = node.get(ReservaUtil.PAR_DATA).asText();
            }
            if (node.has(ReservaUtil.PAR_HORA)) {
                this.hora = node.get(ReservaUtil.PAR_HORA).asText();
            }
        } catch (Exception e) {
            System.out.println("Exceção ao interpretar reserva: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", sala=" + sala + ", data=" + data + 
                ", hora=" + hora + ", motivo="+ motivo + "]";
    }
}

package Value;

import Util.ProfessorUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author cristhian
 */
public class ProfessorValue {

    private int id;
    private String nome;
    private String senha;
    private String email;
    private int id_departamento;
    
    private String departamento;

    public ProfessorValue() {
        this.id = -1;
        this.nome = null;
        this.senha = null;
        this.email = null;
        this.id_departamento = -1;
    }

    public ProfessorValue(int id, String nome, String senha, String email, int id_departamento) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.id_departamento = id_departamento;
    }

    public ProfessorValue(String json) throws Exception {
        System.out.println("json " + json);
        this.id = -1;
        this.nome = null;
        this.senha = null;
        this.email = null;
        this.id_departamento = -1;
        
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readValue(json, JsonNode.class);

            if (node.has(ProfessorUtil.PAR_ID)) {
                this.id = node.get(ProfessorUtil.PAR_ID).asInt();
            }
            if (node.has(ProfessorUtil.PAR_NOME)) {
                this.nome = node.get(ProfessorUtil.PAR_NOME).asText();
            }
            if (node.has(ProfessorUtil.PAR_SENHA)) {
                this.senha = node.get(ProfessorUtil.PAR_SENHA).asText();
            }
            if (node.has(ProfessorUtil.PAR_EMAIL)) {
                this.email = node.get(ProfessorUtil.PAR_EMAIL).asText();
            }
            if (node.has(ProfessorUtil.PAR_DEPARTAMENTO_ID)) {
                this.id_departamento = node.get(ProfessorUtil.PAR_DEPARTAMENTO_ID).asInt();
            }
        } catch (Exception e) {
            System.out.println("Exceção ao interpretar professor: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome + ", senha=" + senha + 
                ", email=" + email + ", id_departamento=" + id_departamento + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}

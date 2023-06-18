package igor.moreira.codeca;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModeloSolicitacao {
    private int _id;
    private String data;
    private int tpServico;
    private Double  latitude;
    private Double longitude;
    private String caminhoFoto;
    private String descricao;
    private String status;
    private int idSolicitacaoApi;
    private int idUserApi;

    public ModeloSolicitacao( int tpServico, Double latitude, Double longitude, String caminhoFoto, String descricao, String status) {
        this.data = getCurrentDate();
        this.tpServico = tpServico;
        this.latitude = latitude;
        this.longitude = longitude;
        this.caminhoFoto = caminhoFoto;
        this.descricao = descricao;
        this.status = status;

    }
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    public int getIdUserApi() {
        return idUserApi;
    }

    public void setIdUserApi(int idUserApi) {
        this.idUserApi = idUserApi;
    }

    public int getIdSolicitacaoApi() {
        return idSolicitacaoApi;
    }

    public void setIdSolicitacaoApi(int idSolicitacaoApi) {
        this.idSolicitacaoApi = idSolicitacaoApi;
    }

    public int get_id() {
        return _id;
    }

    public int getTpServico() {
        return tpServico;
    }

    public void setTpServico(int tpServico) {
        this.tpServico = tpServico;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setLocalizacaoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ModeloSolicitacao{" +
                "_id=" + _id +
                ", data='" + data + '\'' +
                ", tpServico=" + tpServico +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", caminhoFoto='" + caminhoFoto + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", idSolicitacaoApi=" + idSolicitacaoApi +
                ", idUserApi=" + idUserApi +
                '}';
    }
}

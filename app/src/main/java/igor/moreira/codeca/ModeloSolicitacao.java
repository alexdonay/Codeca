package igor.moreira.codeca;

public class ModeloSolicitacao {
    private int _id;
    private int tpServico;
    private Double  latitude;
    private Double longitude;
    private String localizacaoFoto;
    private String descricao;
    private String status;

    public ModeloSolicitacao(int _id, int tpServico, Double latitude, Double longitude, String localizacaoFoto, String descricao, String status) {
        this._id = _id;
        this.tpServico = tpServico;
        this.latitude = latitude;
        this.longitude = longitude;
        this.localizacaoFoto = localizacaoFoto;
        this.descricao = descricao;
        this.status = status;
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

    public String getLocalizacaoFoto() {
        return localizacaoFoto;
    }

    public void setLocalizacaoFoto(String localizacaoFoto) {
        this.localizacaoFoto = localizacaoFoto;
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
}

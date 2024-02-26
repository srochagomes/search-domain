package br.com.feriaz.domain.city.datatransfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
public class CityDTO {
    private Integer linha;
    private String skuMunicipio;
    private String municipio;
    private String skuPais;
    private String cep;
    private String tipo;
    private String h;


    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public String getSkuMunicipio() {
        return skuMunicipio;
    }

    public void setSkuMunicipio(String skuMunicipio) {
        this.skuMunicipio = skuMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getSkuPais() {
        return skuPais;
    }

    public void setSkuPais(String skuPais) {
        this.skuPais = skuPais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }
}

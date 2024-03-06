package br.com.feriaz.domain.city.datatransfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
public class CityDTO {
    private String skuIBGE;
    private String siglaPais;
    private String type;
    private String ordemAlfabetica;
    private String skuFeriaz;
    private String municipio;
    private String pais;
    private String regiao;
    private String uf;
    private String regiaoTuristica;
    private String arrecadacao;
    private String cluster;
    private String paisCamel;
    private String nomeEstado;
    private String continente;

    // Getters e Setters
    public String getSkuIBGE() {
        return skuIBGE;
    }

    public void setSkuIBGE(String skuIBGE) {
        this.skuIBGE = skuIBGE;
    }

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setSiglaPais(String siglaPais) {
        this.siglaPais = siglaPais;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrdemAlfabetica() {
        return ordemAlfabetica;
    }

    public void setOrdemAlfabetica(String ordemAlfabetica) {
        this.ordemAlfabetica = ordemAlfabetica;
    }

    public String getSkuFeriaz() {
        return skuFeriaz;
    }

    public void setSkuFeriaz(String skuFeriaz) {
        this.skuFeriaz = skuFeriaz;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getRegiaoTuristica() {
        return regiaoTuristica;
    }

    public void setRegiaoTuristica(String regiaoTuristica) {
        this.regiaoTuristica = regiaoTuristica;
    }

    public String getArrecadacao() {
        return arrecadacao;
    }

    public void setArrecadacao(String arrecadacao) {
        this.arrecadacao = arrecadacao;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getPaisCamel() {
        return paisCamel;
    }

    public void setPaisCamel(String paisCamel) {
        this.paisCamel = paisCamel;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }
}
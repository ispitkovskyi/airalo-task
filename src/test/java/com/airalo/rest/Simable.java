package com.airalo.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Simable extends BaseEntity{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("code")
    private String code;
    @JsonProperty("package_id")
    private String packageId;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("esim_type")
    private String esimType;
    @JsonProperty("validity")
    private String validity;
    @JsonProperty("package")
    private String _package;
    @JsonProperty("data")
    private String data;
    @JsonProperty("price")
    private String price;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("manual_installation")
    private String manualInstallation;
    @JsonProperty("qrcode_installation")
    private String qrcodeInstallation;
    @JsonProperty("installation_guides")
    private InstallationGuides installationGuides;
    @JsonProperty("text")
    private Object text;
    @JsonProperty("voice")
    private Object voice;
    @JsonProperty("net_price")
    private Double netPrice;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("user")
    private User user;
    @JsonProperty("sims")
    private List<Sim> sims;


    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("package_id")
    public String getPackageId() {
        return packageId;
    }

    @JsonProperty("package_id")
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("esim_type")
    public String getEsimType() {
        return esimType;
    }

    @JsonProperty("esim_type")
    public void setEsimType(String esimType) {
        this.esimType = esimType;
    }

    @JsonProperty("validity")
    public String getValidity() {
        return validity;
    }

    @JsonProperty("validity")
    public void setValidity(String validity) {
        this.validity = validity;
    }

    @JsonProperty("package")
    public String getPackage() {
        return _package;
    }

    @JsonProperty("package")
    public void setPackage(String _package) {
        this._package = _package;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("manual_installation")
    public String getManualInstallation() {
        return manualInstallation;
    }

    @JsonProperty("manual_installation")
    public void setManualInstallation(String manualInstallation) {
        this.manualInstallation = manualInstallation;
    }

    @JsonProperty("qrcode_installation")
    public String getQrcodeInstallation() {
        return qrcodeInstallation;
    }

    @JsonProperty("qrcode_installation")
    public void setQrcodeInstallation(String qrcodeInstallation) {
        this.qrcodeInstallation = qrcodeInstallation;
    }

    @JsonProperty("installation_guides")
    public InstallationGuides getInstallationGuides() {
        return installationGuides;
    }

    @JsonProperty("installation_guides")
    public void setInstallationGuides(InstallationGuides installationGuides) {
        this.installationGuides = installationGuides;
    }

    @JsonProperty("text")
    public Object getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(Object text) {
        this.text = text;
    }

    @JsonProperty("voice")
    public Object getVoice() {
        return voice;
    }

    @JsonProperty("voice")
    public void setVoice(Object voice) {
        this.voice = voice;
    }

    @JsonProperty("net_price")
    public Double getNetPrice() {
        return netPrice;
    }

    @JsonProperty("net_price")
    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("sims")
    public List<Sim> getSims() {
        return sims;
    }

    @JsonProperty("sims")
    public void setSims(List<Sim> sims) {
        this.sims = sims;
    }

}

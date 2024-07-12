package com.airalo.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimData extends BaseEntity{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("iccid")
    private String iccid;
    @JsonProperty("lpa")
    private String lpa;
    @JsonProperty("imsis")
    private Object imsis;
    @JsonProperty("matching_id")
    private String matchingId;
    @JsonProperty("confirmation_code")
    private Object confirmationCode;
    @JsonProperty("qrcode")
    private String qrcode;
    @JsonProperty("qrcode_url")
    private String qrcodeUrl;
    @JsonProperty("direct_apple_installation_url")
    private String directAppleInstallationUrl;
    @JsonProperty("voucher_code")
    private Object voucherCode;
    @JsonProperty("airalo_code")
    private Object airaloCode;
    @JsonProperty("apn_type")
    private String apnType;
    @JsonProperty("apn_value")
    private String apnValue;
    @JsonProperty("is_roaming")
    private Boolean isRoaming;
    @JsonProperty("msisdn")
    private Object msisdn;
    @JsonProperty("apn")
    private Apn apn;
    @JsonProperty("simable")
    private Simable simable;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("iccid")
    public String getIccid() {
        return iccid;
    }

    @JsonProperty("iccid")
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    @JsonProperty("lpa")
    public String getLpa() {
        return lpa;
    }

    @JsonProperty("lpa")
    public void setLpa(String lpa) {
        this.lpa = lpa;
    }

    @JsonProperty("imsis")
    public Object getImsis() {
        return imsis;
    }

    @JsonProperty("imsis")
    public void setImsis(Object imsis) {
        this.imsis = imsis;
    }

    @JsonProperty("matching_id")
    public String getMatchingId() {
        return matchingId;
    }

    @JsonProperty("matching_id")
    public void setMatchingId(String matchingId) {
        this.matchingId = matchingId;
    }

    @JsonProperty("confirmation_code")
    public Object getConfirmationCode() {
        return confirmationCode;
    }

    @JsonProperty("confirmation_code")
    public void setConfirmationCode(Object confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @JsonProperty("qrcode")
    public String getQrcode() {
        return qrcode;
    }

    @JsonProperty("qrcode")
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @JsonProperty("qrcode_url")
    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    @JsonProperty("qrcode_url")
    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    @JsonProperty("direct_apple_installation_url")
    public String getDirectAppleInstallationUrl() {
        return directAppleInstallationUrl;
    }

    @JsonProperty("direct_apple_installation_url")
    public void setDirectAppleInstallationUrl(String directAppleInstallationUrl) {
        this.directAppleInstallationUrl = directAppleInstallationUrl;
    }

    @JsonProperty("voucher_code")
    public Object getVoucherCode() {
        return voucherCode;
    }

    @JsonProperty("voucher_code")
    public void setVoucherCode(Object voucherCode) {
        this.voucherCode = voucherCode;
    }

    @JsonProperty("airalo_code")
    public Object getAiraloCode() {
        return airaloCode;
    }

    @JsonProperty("airalo_code")
    public void setAiraloCode(Object airaloCode) {
        this.airaloCode = airaloCode;
    }

    @JsonProperty("apn_type")
    public String getApnType() {
        return apnType;
    }

    @JsonProperty("apn_type")
    public void setApnType(String apnType) {
        this.apnType = apnType;
    }

    @JsonProperty("apn_value")
    public String getApnValue() {
        return apnValue;
    }

    @JsonProperty("apn_value")
    public void setApnValue(String apnValue) {
        this.apnValue = apnValue;
    }

    @JsonProperty("is_roaming")
    public Boolean getIsRoaming() {
        return isRoaming;
    }

    @JsonProperty("is_roaming")
    public void setIsRoaming(Boolean isRoaming) {
        this.isRoaming = isRoaming;
    }

    @JsonProperty("msisdn")
    public Object getMsisdn() {
        return msisdn;
    }

    @JsonProperty("msisdn")
    public void setMsisdn(Object msisdn) {
        this.msisdn = msisdn;
    }

    @JsonProperty("apn")
    public Apn getApn() {
        return apn;
    }

    @JsonProperty("apn")
    public void setApn(Apn apn) {
        this.apn = apn;
    }

    @JsonProperty("simable")
    public Simable getSimable() {
        return simable;
    }

    @JsonProperty("simable")
    public void setSimable(Simable simable) {
        this.simable = simable;
    }

}

package com.cscec8b.bond.module.object;

import java.math.BigDecimal;

/**
 * Created by admin on 2018/6/29.
 * CBS制单重传实体类
 */
public class CBSprePareRetran {
    private String sourceCode;//系统编码,
    private Integer sourceId;//表id
    private String pay_type_id;//支付类型
    private String payment_method_type_id;//结算方式
    private String payment_bank_account;//付款方银行账号
    private String desposit_bank_name;//收款方银行开户行
    private String desposit_account_name;//收款方银行账户名称
    private String desposit_bank_account;//收款方银行账号
    private String desposit_bank_type;//收款方银行类型
    private String desposit_province;//收款方银行开户行省
    private String desposit_city;//收款方银行开户行市
    private String desposit_mobile_phone;//收款方移动电话号码
    private String same_city_flag;//是否同城
    private String pay_channel;//支付渠道
    private String bank_number;//银行号
    private String union_bank_number;//联行号
    private String amount;//交易金额  PAY_AMOUNT
   // private double amount;//交易金额  PAY_AMOUNT
    private String purpose;//用途

    public String getPay_desc() {
        return pay_desc;
    }

    public void setPay_desc(String pay_desc) {
        this.pay_desc = pay_desc;
    }

    private String pay_desc;//摘要 PAY_USAGE



    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getPay_type_id() {
        return pay_type_id;
    }

    public void setPay_type_id(String pay_type_id) {
        this.pay_type_id = pay_type_id;
    }

    public String getPayment_method_type_id() {
        return payment_method_type_id;
    }

    public void setPayment_method_type_id(String payment_method_type_id) {
        this.payment_method_type_id = payment_method_type_id;
    }

    public String getPayment_bank_account() {
        return payment_bank_account;
    }

    public void setPayment_bank_account(String payment_bank_account) {
        this.payment_bank_account = payment_bank_account;
    }

    public String getDesposit_bank_name() {
        return desposit_bank_name;
    }

    public void setDesposit_bank_name(String desposit_bank_name) {
        this.desposit_bank_name = desposit_bank_name;
    }

    public String getDesposit_account_name() {
        return desposit_account_name;
    }

    public void setDesposit_account_name(String desposit_account_name) {
        this.desposit_account_name = desposit_account_name;
    }

    public String getDesposit_bank_account() {
        return desposit_bank_account;
    }

    public void setDesposit_bank_account(String desposit_bank_account) {
        this.desposit_bank_account = desposit_bank_account;
    }

    public String getDesposit_bank_type() {
        return desposit_bank_type;
    }

    public void setDesposit_bank_type(String desposit_bank_type) {
        this.desposit_bank_type = desposit_bank_type;
    }

    public String getDesposit_province() {
        return desposit_province;
    }

    public void setDesposit_province(String desposit_province) {
        this.desposit_province = desposit_province;
    }

    public String getDesposit_city() {
        return desposit_city;
    }

    public void setDesposit_city(String desposit_city) {
        this.desposit_city = desposit_city;
    }

    public String getDesposit_mobile_phone() {
        return desposit_mobile_phone;
    }

    public void setDesposit_mobile_phone(String desposit_mobile_phone) {
        this.desposit_mobile_phone = desposit_mobile_phone;
    }

    public String getSame_city_flag() {
        return same_city_flag;
    }

    public void setSame_city_flag(String same_city_flag) {
        this.same_city_flag = same_city_flag;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public String getUnion_bank_number() {
        return union_bank_number;
    }

    public void setUnion_bank_number(String union_bank_number) {
        this.union_bank_number = union_bank_number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

}

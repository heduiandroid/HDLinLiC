package com.linli.consumer.bean;

import java.math.BigDecimal;
import java.util.Date;

public class HdMallStoreoperate {
    private Long id;

    private Long storeId;

    private Long scope;

    private BigDecimal begingive;

    private Integer sendtype;

    private Integer sendtime;

    private BigDecimal packing;

    private BigDecimal sendcost;

    private BigDecimal reduction;

    private BigDecimal reductioncost;

    private BigDecimal sale;

    private String explains;

    private Date createTime;

    private Date modifyTime;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getScope() {
        return scope;
    }

    public void setScope(Long scope) {
        this.scope = scope;
    }

    public BigDecimal getBegingive() {
        return begingive;
    }

    public void setBegingive(BigDecimal begingive) {
        this.begingive = begingive;
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public Integer getSendtime() {
        return sendtime;
    }

    public void setSendtime(Integer sendtime) {
        this.sendtime = sendtime;
    }

    public BigDecimal getPacking() {
        return packing;
    }

    public void setPacking(BigDecimal packing) {
        this.packing = packing;
    }

    public BigDecimal getSendcost() {
        return sendcost;
    }

    public void setSendcost(BigDecimal sendcost) {
        this.sendcost = sendcost;
    }

    public BigDecimal getReduction() {
        return reduction;
    }

    public void setReduction(BigDecimal reduction) {
        this.reduction = reduction;
    }

    public BigDecimal getReductioncost() {
        return reductioncost;
    }

    public void setReductioncost(BigDecimal reductioncost) {
        this.reductioncost = reductioncost;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains == null ? null : explains.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
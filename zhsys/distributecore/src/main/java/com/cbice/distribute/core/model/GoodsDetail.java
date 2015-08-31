package com.cbice.distribute.core.model;


public class GoodsDetail  {
    private String gname;
    private Long ndate;
    private String ocode;
    private String cid;
    private String camount;
    private String cname;
    public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCamount() {
		return camount;
	}
	public void setCamount(String camount) {
		this.camount = camount;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Long getNdate() {
		return ndate;
	}
	public void setNdate(Long ndate) {
		this.ndate = ndate;
	}
	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
   

   
}

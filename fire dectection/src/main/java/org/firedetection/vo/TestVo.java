package org.firedetection.vo;

public class TestVo {
	String done;
	String dtwo;
	public TestVo() {
		super();
	}
	public TestVo(String done, String dtwo) {
		super();
		this.done = done;
		this.dtwo = dtwo;
	}
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	public String getDtwo() {
		return dtwo;
	}
	public void setDtwo(String dtwo) {
		this.dtwo = dtwo;
	}
	@Override
	public String toString() {
		return "TestVO [done=" + done + ", dtwo=" + dtwo + "]";
	}
}

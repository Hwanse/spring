package kr.or.ddit.lprod.model;

import org.springframework.stereotype.Component;

@Component
public class LprodVO {
	private int lprod_id;
	private String lprod_gu;
	private String lprod_nm;

	public LprodVO(int lprod_id, String lprod_gu, String lprod_nm) {
		this.lprod_id = lprod_id;
		this.lprod_gu = lprod_gu;
		this.lprod_nm = lprod_nm;
	}

	public LprodVO(){
		
	}
	
	public int getLprod_id() {
		return lprod_id;
	}
	public void setLprod_id(int lprod_id) {
		this.lprod_id = lprod_id;
	}
	public String getLprod_gu() {
		return lprod_gu;
	}
	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}
	public String getLprod_nm() {
		return lprod_nm;
	}
	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}

	@Override
	public String toString() {
		return "LprodVO [lprod_id=" + lprod_id + ", lprod_gu=" + lprod_gu + ", lprod_nm=" + lprod_nm + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lprod_gu == null) ? 0 : lprod_gu.hashCode());
		result = prime * result + lprod_id;
		result = prime * result + ((lprod_nm == null) ? 0 : lprod_nm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LprodVO other = (LprodVO) obj;
		if (lprod_gu == null) {
			if (other.lprod_gu != null)
				return false;
		} else if (!lprod_gu.equals(other.lprod_gu))
			return false;
		if (lprod_id != other.lprod_id)
			return false;
		if (lprod_nm == null) {
			if (other.lprod_nm != null)
				return false;
		} else if (!lprod_nm.equals(other.lprod_nm))
			return false;
		return true;
	}

}

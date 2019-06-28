package kr.or.ddit.paging.model;

/**
* PageVO.java
*
* @author PC14
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC14 최초 생성
*
* </pre>
*/
public class PageVO {

	private int page;		// 페이지 번호
	private int pageSize;	// 페이징 갯수
	

	public PageVO(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public PageVO(){
		
	}
	
	public int getPage() {
		return page == 0? 1: page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize == 0? 10: pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getPage();
		result = prime * result + getPageSize();
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
		PageVO other = (PageVO) obj;
		if (page != other.getPage())
			return false;
		if (pageSize != other.getPageSize())
			return false;
		return true;
	}
	
	
}

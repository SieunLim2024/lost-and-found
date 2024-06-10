package model;

public class FoundArticleVO {
	private String atcId;		//관리ID
	private String depPlace;	//보관장소
	private String fdPrdtNm;	//분실물명
	private String fdYmd;		//습득일자
	private String prdtClNm;	//물품분류명
	private String state;		//가져갔는지 여부
	
	public FoundArticleVO() {
		super();
	}

	public FoundArticleVO(String atcId, String depPlace, String fdPrdtNm, String fdYmd, String prdtClNm, String state) {
		super();
		this.atcId = atcId;
		this.depPlace = depPlace;
		this.fdPrdtNm = fdPrdtNm;
		this.fdYmd = fdYmd;
		this.prdtClNm = prdtClNm;
		this.state = state;
	}

	public String getAtcId() {
		return atcId;
	}

	public void setAtcId(String atcId) {
		this.atcId = atcId;
	}

	public String getDepPlace() {
		return depPlace;
	}

	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}

	public String getFdPrdtNm() {
		return fdPrdtNm;
	}

	public void setFdPrdtNm(String fdPrdtNm) {
		this.fdPrdtNm = fdPrdtNm;
	}

	public String getFdYmd() {
		return fdYmd;
	}

	public void setFdYmd(String fdYmd) {
		this.fdYmd = fdYmd;
	}

	public String getPrdtClNm() {
		return prdtClNm;
	}

	public void setPrdtClNm(String prdtClNm) {
		this.prdtClNm = prdtClNm;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
//	public String calprint() {
//		String lstPrdtNmPrint=null;
//		
//		if(fdPrdtNm.length()>15) {
//			lstPrdtNmPrint=fdPrdtNm.substring(0, 10)+"..,\t";
//		}else if(fdPrdtNm.length()<15) {
//			StringBuffer blank = new StringBuffer();
//			for(int i=0;i<14-fdPrdtNm.length();i++) {
//				blank.append(" ");
//			}
//			lstPrdtNmPrint=fdPrdtNm+","+blank.toString();
//		}else {
//			lstPrdtNmPrint=fdPrdtNm+",";
//		}
//		return lstPrdtNmPrint;
//	}

	@Override
	public String toString() {
		return "[" + atcId + ", " + fdYmd + ", " + 
				 depPlace + ", " + prdtClNm + ", " + state + ", " +fdPrdtNm +"]";
	}
	
	
	
}

package model;

public class MissingArticleVO {
	private String atcId;		//����ID
	private String lstPlace;	//�н�������
	private String lstPrdtNm;	//�нǹ���
	private String lstYmd;		//�н�����
	private String prdtClNm;	//��ǰ�з���
	private String state;		//ã�Ҵ��� ����
	
	public MissingArticleVO() {
		super();
	}
	public MissingArticleVO(String atcId, String lstPlace, String lstPrdtNm, String lstYmd,
			String prdtClNm,String state) {
		super();
		this.atcId = atcId;
		this.lstPlace = lstPlace;
		this.lstPrdtNm = lstPrdtNm;
		this.lstYmd = lstYmd;
		this.prdtClNm = prdtClNm;
		this.state=state;
	}

	public String getAtcId() {
		return atcId;
	}
	public void setAtcId(String atcId) {
		this.atcId = atcId;
	}
	public String getLstPlace() {
		return lstPlace;
	}
	public void setLstPlace(String lstPlace) {
		this.lstPlace = lstPlace;
	}
	public String getLstPrdtNm() {
		return lstPrdtNm;
	}
	public void setLstPrdtNm(String lstPrdtNm) {
		this.lstPrdtNm = lstPrdtNm;
	}
	public String getLstYmd() {
		return lstYmd;
	}
	public void setLstYmd(String lstYmd) {
		this.lstYmd = lstYmd;
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
	public String calprint() {
		String lstPrdtNmPrint=null;
		
		if(lstPrdtNm.length()>15) {
			lstPrdtNmPrint=lstPrdtNm.substring(0, 10)+"..,\t";
		}else if(lstPrdtNm.length()<15) {
			StringBuffer blank = new StringBuffer();
			for(int i=0;i<14-lstPrdtNm.length();i++) {
				blank.append(" ");
			}
			lstPrdtNmPrint=lstPrdtNm+","+blank.toString();
		}else {
			lstPrdtNmPrint=lstPrdtNm+",";
		}
		return lstPrdtNmPrint;
	}
	@Override
	public String toString() {
		
		return "[" + atcId + ", " + state + ", "+ lstYmd+ ", \t" + prdtClNm +
				 ", \t" + calprint()  +   "\t" + lstPlace +"]";
	}
	
	
	
}

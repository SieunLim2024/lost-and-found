package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.FoundArticleVO;

public class FoundArticleDAO {
	public static void searchDepPlace() {
		System.out.println("검색할 보관장소>>");
		String inputId="%"+main.LAndFMain.input.nextLine()+"%";
		String sql = "select * from FoundArticle where DEPPLACE like ?";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
        	boolean flag=false;
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, inputId);
            rs = pstmt.executeQuery();
            if(!rs.next()){
            	System.out.println("해당하는 곳이 없습니다.");
            }
            while(!flag) {
            	FoundArticleVO fa = new FoundArticleVO();
            	fa.setAtcId(rs.getString("ATCID"));
            	fa.setDepPlace(rs.getString("DEPPLACE"));
            	fa.setPrdtClNm(rs.getString("FDPRDTNM"));
            	fa.setFdYmd(rs.getString("FDYMD"));
            	fa.setPrdtClNm(rs.getString("PRDTCLNM"));
            	fa.setState(rs.getString("STATE"));
            	fa.setFdPrdtNm(rs.getString("FDPRDTNM"));
            	System.out.println(fa.toString());
            	if(!rs.next()) {
            		flag=true;
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
	}

	public static void insertArticle(ArrayList<FoundArticleVO> FoundArticleList) {
		if (FoundArticleList.size() < 1) {
			System.out.println("입력할 데이터가 없습니다.");
			return;
		}
		//저장하기 전에 테이블에 있는 내용을 삭제
		FoundArticleManager.deleteArticle();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = controller.DBUtil.makeConnection();
			for (FoundArticleVO data : FoundArticleList) {
				String sql = "insert into FoundArticle (atcid,depplace,fdprdtNm,fdymd,prdtclnm) values(?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data.getAtcId());
				pstmt.setString(2, data.getDepPlace());
				pstmt.setString(3, data.getFdPrdtNm());
				pstmt.setString(4, data.getFdYmd());
				pstmt.setString(5, data.getPrdtClNm());
				int value = pstmt.executeUpdate();

				if (value == 1) {
					System.out.println(data.getAtcId() + "등록 완료");
				} else {
					System.out.println(data.getAtcId() + "등록 실패");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	
	public static void searchFdPrdtNm() {
		System.out.println("검색할 분실물 명>>");
		String inputId="%"+main.LAndFMain.input.nextLine()+"%";
		String sql = "select * from FoundArticle where FDPRDTNM like ?";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
        	boolean flag=false;
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, inputId);
            rs = pstmt.executeQuery();
            if(!rs.next()){
            	System.out.println("해당하는 물품명이 없습니다.");
            }
            while(!flag) {
            	FoundArticleVO fa = new FoundArticleVO();
            	fa.setAtcId(rs.getString("ATCID"));
            	fa.setDepPlace(rs.getString("DEPPLACE"));
            	fa.setPrdtClNm(rs.getString("FDPRDTNM"));
            	fa.setFdYmd(rs.getString("FDYMD"));
            	fa.setPrdtClNm(rs.getString("PRDTCLNM"));
            	fa.setState(rs.getString("STATE"));
            	fa.setFdPrdtNm(rs.getString("FDPRDTNM"));
            	System.out.println(fa.toString());
            	if(!rs.next()) {
            		flag=true;
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
	}
	
	public static ArrayList<FoundArticleVO> selectArticle() {
		ArrayList<FoundArticleVO> FoundArticleList = new ArrayList<>();
		String sql = "select * from FoundArticle";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            FoundArticleList = new ArrayList<FoundArticleVO>();
            while(rs.next()) {
            	FoundArticleVO ma = new FoundArticleVO();
            	ma.setAtcId(rs.getString("ATCID"));
            	ma.setDepPlace(rs.getString("DEPPLACE"));
            	ma.setFdPrdtNm(rs.getString("FDPRDTNM"));
            	ma.setFdYmd(rs.getString("FDYMD"));
            	ma.setPrdtClNm(rs.getString("PRDTCLNM"));
            	ma.setState(rs.getString("STATE"));
            	FoundArticleList.add(ma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return FoundArticleList;
	}

	public static int getCountArticle() {
		int count = 0; 
        String sql = "select count(*) as cnt from FoundArticle";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(rs != null) {
                        rs.close();
                    }
                    if(pstmt != null) {
                        pstmt.close();
                    }
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return count; 
	}

	public static ArrayList<FoundArticleVO>  webConnection() {
		ArrayList<FoundArticleVO> list = new ArrayList<>();
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1320000/LosfundInfoInqireService/getLosfundInfoAccToClAreaPd"); /*URL*/
        try {
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("PRDT_CL_CD_01","UTF-8") + "=" + URLEncoder.encode("PRH000", "UTF-8")); /*대분류*/
        urlBuilder.append("&" + URLEncoder.encode("PRDT_CL_CD_02","UTF-8") + "=" + URLEncoder.encode("PRH200", "UTF-8")); /*중분류*/
        urlBuilder.append("&" + URLEncoder.encode("FD_COL_CD","UTF-8") + "=" + URLEncoder.encode("CL1002", "UTF-8")); /*습득물 색상*/
        urlBuilder.append("&" + URLEncoder.encode("START_YMD","UTF-8") + "=" + URLEncoder.encode("20231001", "UTF-8")); /*검색시작일*/
        urlBuilder.append("&" + URLEncoder.encode("END_YMD","UTF-8") + "=" + URLEncoder.encode("20240520", "UTF-8")); /*검색종료일*/
        urlBuilder.append("&" + URLEncoder.encode("N_FD_LCT_CD","UTF-8") + "=" + URLEncoder.encode("LCA000", "UTF-8")); /*습득지역*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*목록 건수*/
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 2. connection객체 생성
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 3.요청전송 및 응답 처리
		BufferedReader rd =null;
		try {
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			Document doc = parseXML(conn.getInputStream());
			// a. field 태그객체 목록으로 가져온다.
			NodeList descNodes = doc.getElementsByTagName("item");
			// b. List객체 생성
			
			// c. 각 item 태그의 자식태그에서 정보 가져오기
			for (int i = 0; i < descNodes.getLength(); i++) {
				// item
				Node item = descNodes.item(i);
				FoundArticleVO data = new FoundArticleVO();
				// item 자식태그에 순차적으로 접근
				for (Node node = item.getFirstChild(); node != null; 
					node =node.getNextSibling()) {
//					System.out.println(node.getNodeName() + " : " +node.getTextContent());

					switch (node.getNodeName()) {
					case "atcId":
						data.setAtcId(node.getTextContent());
						System.out.println(node.getTextContent()+"로드 완료");
						break;
					case "depPlace":
						data.setDepPlace(node.getTextContent());
						break;
					case "fdPrdtNm":
						data.setFdPrdtNm(node.getTextContent());
						break;
					case "fdYmd":
						data.setFdYmd(node.getTextContent());
						break;
					case "prdtClNm":
						data.setPrdtClNm(node.getTextContent());
						break;
					}
				}
				// d. List객체에 추가
				list.add(data);
			}
//			// e.최종확인
//			for (FoundArticle d : list) {
//			System.out.println(d);
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				rd.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		conn.disconnect();
		return list;
	}

	public static Document parseXML(InputStream stream) {
		DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
		objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
		doc = objDocumentBuilder.parse(stream);
		} catch (ParserConfigurationException e) {
		e.printStackTrace();
		} catch (SAXException e) { // Simple API for XML e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return doc;
	}
}

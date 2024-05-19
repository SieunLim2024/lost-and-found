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

import model.MissingArticleVO;

public class MissingArticleDAO {
	public static void searchArticle() {
		System.out.println("�˻��� ATCID>>");
		String inputId=main.LAndFMain.input.nextLine();
		String sql = "select * from missingarticle where ATCID=?";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, inputId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	MissingArticleVO ma = new MissingArticleVO();
            	ma.setAtcId(rs.getString("ATCID"));
            	ma.setLstPlace(rs.getString("LSTPLACE"));
            	ma.setLstPrdtNm(rs.getString("LSTPRDTNM"));
            	ma.setLstYmd(rs.getString("LSTYMD"));
            	ma.setPrdtClNm(rs.getString("PRDTCLNM"));
            	ma.setState(rs.getString("STATE"));
            	System.out.println(ma.toString());
            }else {
            	System.out.println("�ش� ATCID�� �����ϴ�.");
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

	public static void insertArticle(ArrayList<MissingArticleVO> missingArticleList) {
		if (missingArticleList.size() < 1) {
			System.out.println("�Է��� �����Ͱ� �����ϴ�.");
			return;
		}
		//�����ϱ� ���� ���̺� �ִ� ������ ����
		MissingArticleManager.deleteArticle();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = controller.DBUtil.makeConnection();
			for (MissingArticleVO data : missingArticleList) {
				String sql = "insert into missingarticle (atcid,lstplace,lstprdtnm,lstymd,prdtclnm) values(?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, data.getAtcId());
				pstmt.setString(2, data.getLstPlace());
				pstmt.setString(3, data.getLstPrdtNm());
				pstmt.setString(4, data.getLstYmd());
				pstmt.setString(5, data.getPrdtClNm());
				int value = pstmt.executeUpdate();

				if (value == 1) {
					System.out.println(data.getAtcId() + "��� �Ϸ�");
				} else {
					System.out.println(data.getAtcId() + "��� ����");
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
	

	
	public static ArrayList<MissingArticleVO> selectArticle() {
		ArrayList<MissingArticleVO> missingArticleList = new ArrayList<>();
		String sql = "select * from missingarticle";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        ResultSet rs = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            missingArticleList = new ArrayList<MissingArticleVO>();
            while(rs.next()) {
            	MissingArticleVO ma = new MissingArticleVO();
            	ma.setAtcId(rs.getString("ATCID"));
            	ma.setLstPlace(rs.getString("LSTPLACE"));
            	ma.setLstPrdtNm(rs.getString("LSTPRDTNM"));
            	ma.setLstYmd(rs.getString("LSTYMD"));
            	ma.setPrdtClNm(rs.getString("PRDTCLNM"));
            	ma.setState(rs.getString("STATE"));
            	missingArticleList.add(ma);
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
        return missingArticleList;
	}

	public static int getCountArticle() {
		int count = 0; 
        String sql = "select count(*) as cnt from missingarticle";
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

	public static ArrayList<MissingArticleVO>  webConnection() {
		ArrayList<MissingArticleVO> list = new ArrayList<>();
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1320000/LostGoodsInfoInqireService/getLostGoodsInfoAccToClAreaPd"); /* URL */
		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=����Ű");/* Service Key */
			urlBuilder.append("&" + URLEncoder.encode("START_YMD", "UTF-8") + "="
					+ URLEncoder.encode("20240101", "UTF-8")); /* �нǹ� ��ϳ�¥ */
			urlBuilder.append("&" + URLEncoder.encode("END_YMD", "UTF-8") + "="
					+ URLEncoder.encode("20240519", "UTF-8")); /* �нǹ� ��ϳ�¥ */
			urlBuilder.append("&" + URLEncoder.encode("PRDT_CL_CD_01", "UTF-8") + "="
					+ URLEncoder.encode("PRA000", "UTF-8")); /* ������ǰ�ڵ� */
			urlBuilder.append("&" + URLEncoder.encode("PRDT_CL_CD_02", "UTF-8") + "="
					+ URLEncoder.encode("PRA300", "UTF-8")); /* ������ǰ�ڵ� */
			urlBuilder.append("&" + URLEncoder.encode("LST_LCT_CD", "UTF-8") + "="
					+ URLEncoder.encode("LCA000", "UTF-8")); /* �н������ڵ� */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* ������ ��ȣ */
			urlBuilder.append(
					"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* ��� �Ǽ� */
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 2. connection��ü ����
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
		// 3.��û���� �� ���� ó��
		BufferedReader rd =null;
		try {
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			Document doc = parseXML(conn.getInputStream());
			// a. field �±װ�ü ������� �����´�.
			NodeList descNodes = doc.getElementsByTagName("item");
			// b. List��ü ����
			
			// c. �� item �±��� �ڽ��±׿��� ���� ��������
			for (int i = 0; i < descNodes.getLength(); i++) {
				// item
				Node item = descNodes.item(i);
				MissingArticleVO data = new MissingArticleVO();
				// item �ڽ��±׿� ���������� ����
				for (Node node = item.getFirstChild(); node != null; 
					node =node.getNextSibling()) {
//					System.out.println(node.getNodeName() + " : " +node.getTextContent());

					switch (node.getNodeName()) {
					case "atcId":
						data.setAtcId(node.getTextContent());
						System.out.println(node.getTextContent()+"�ε� �Ϸ�");
						break;
					case "lstPlace":
						data.setLstPlace(node.getTextContent());
						break;
					case "lstPrdtNm":
						data.setLstPrdtNm(node.getTextContent());
						break;
					case "lstYmd":
						data.setLstYmd(node.getTextContent());
						break;
					case "prdtClNm":
						data.setPrdtClNm(node.getTextContent());
						break;
					}
				}
				// d. List��ü�� �߰�
				list.add(data);
			}
//			// e.����Ȯ��
//			for (MissingArticle d : list) {
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

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MissingArticleVO;


public class MissingArticleManager {
	public static void deleteArticle() {
		int count = MissingArticleDAO.getCountArticle();
        if(count == 0) {
            System.out.println("������ �����ϴ�.");
            return; 
        }
        String sql = "delete from missingarticle";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            int value = pstmt.executeUpdate();
            if(value != 0) {
                System.out.println("���� �����Ϸ�");
            }else {
                System.out.println("���� ��������");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
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
	public static String updateArticle() {
		ArrayList<MissingArticleVO> list =MissingArticleDAO.selectArticle();
		printArticle(list);
		System.out.println("udate atcId>>");
		String data = main.LAndFMain.input.nextLine();
		return data;
	}
	
	//���� ã�ư� ���� ������Ʈ ����
	public static void updateArticle(String atcId) {
		String sql = "UPDATE missingarticle SET state = 'Y' WHERE atcid =?";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, atcId);
            int value = pstmt.executeUpdate();

            if(value == 1) {
                System.out.println(atcId+"�����Ϸ�");
            }else {
                System.out.println(atcId+"��������");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
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
	public static void printArticle(ArrayList<MissingArticleVO> missingArticleSelectList) {
		if(missingArticleSelectList.size()<1) {
			System.out.println("����� ������ �����ϴ�.");
			return;
		}
		for(MissingArticleVO data:missingArticleSelectList) {
			System.out.println(data.toString());
		}
	}

}

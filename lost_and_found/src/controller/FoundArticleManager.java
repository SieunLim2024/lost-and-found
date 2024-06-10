package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.FoundArticleVO;


public class FoundArticleManager {
	public static void deleteArticle() {
		int count = FoundArticleDAO.getCountArticle();
        if(count == 0) {
            System.out.println("내용이 없습니다.");
            return; 
        }
        String sql = "delete from foundarticle";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            int value = pstmt.executeUpdate();
            if(value != 0) {
                System.out.println("정보 삭제완료");
            }else {
                System.out.println("정보 삭제실패");
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
		ArrayList<FoundArticleVO> list =FoundArticleDAO.selectArticle();
		printArticle(list);
		System.out.println("udate atcId>>");
		String data = main.LAndFMain.input.nextLine();
		return data;
	}
	
	//주인 찾아간 여부 업데이트 해줌
	public static void updateArticle(String atcId) {
		String sql = "UPDATE FoundArticle SET state = 'Y' WHERE atcid =?";
        Connection con = null; 
        PreparedStatement pstmt = null; 
        try {
            con = controller.DBUtil.makeConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, atcId);
            int value = pstmt.executeUpdate();

            if(value == 1) {
                System.out.println(atcId+"수정완료");
            }else {
                System.out.println(atcId+"수정실패");
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
	public static void printArticle(ArrayList<FoundArticleVO> FoundArticleSelectList) {
		if(FoundArticleSelectList.size()<1) {
			System.out.println("출력할 정보가 없습니다.");
			return;
		}
		for(FoundArticleVO data:FoundArticleSelectList) {
			System.out.println(data.toString());
		}
	}

}

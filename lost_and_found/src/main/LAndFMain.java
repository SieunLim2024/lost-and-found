package main;

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
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.FoundArticleDAO;
import controller.FoundArticleManager;
import controller.MissingArticleDAO;
import controller.MissingArticleManager;
import model.FoundArticleVO;
import model.MissingArticleVO;
import view.FoundMenuChoice;
import view.LostMenuChoice;
import view.MainMenuChoice;

public class LAndFMain {
	public static Scanner input = new Scanner(System.in);

	public static ArrayList<MissingArticleVO> missingArticleList = null;
	public static ArrayList<MissingArticleVO> missingArticleSelectList = null;
	public static ArrayList<FoundArticleVO> foundArticleList = null;
	public static ArrayList<FoundArticleVO> foundArticleSelectList = null;
	
	public static void main(String[] args) throws IOException {
		mainMenu();
	}
	
	public static void mainMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			view.MainMenuViewer.mainMenuView();

			int choiceNum = input.nextInt();
			input.nextLine(); 				// 버퍼클리어
			switch (choiceNum) {
			case MainMenuChoice.LOST:		// 분실물
				lostMenu();
				break;
			case MainMenuChoice.FOUND:		// 습득물
				foundMenu();
				break;
				
			case MainMenuChoice.EXIT:		// 종료하기
				exitFlag = true;
				break;
			}
		} // end of while
	}

	public static void lostMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			view.LostMenuViewer.lostMenuView();

			int choiceNum = input.nextInt();
			input.nextLine(); // 버퍼클리어
			switch (choiceNum) {
			case LostMenuChoice.LOAD:// 웹 정보 가져오기
				missingArticleList = MissingArticleDAO.webConnection();
				break;
			case LostMenuChoice.INSERT: // 테이블 저장하기
				if (missingArticleList.size() < 1) {
					System.out.println("공공데이터로부터 가져온 자료가 없습니다.");
					continue;
				}
				MissingArticleDAO.insertArticle(missingArticleList);
				break;
			case LostMenuChoice.SELECT:// 테이블 읽어오기
				missingArticleSelectList = MissingArticleDAO.selectArticle();
				MissingArticleManager.printArticle(missingArticleSelectList);

				break;
			case LostMenuChoice.UPDATE:// 테이블 수정하기
				String data = MissingArticleManager.updateArticle();
				if (data.length() != 0) {
					MissingArticleManager.updateArticle(data);
				}
				break;
			case LostMenuChoice.DELETE:// 테이블 삭제하기
				MissingArticleManager.deleteArticle();
				break;
			case LostMenuChoice.SEARCH:
				MissingArticleDAO.searchArticle();
				break;
			case LostMenuChoice.EXIT:// 종료하기
				exitFlag = true;
				break;
			}
		} // end of while
	}
	
	public static void foundMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			view.FoundMenuViewer.foundMenuView();

			int choiceNum = input.nextInt();
			input.nextLine(); // 버퍼클리어
			switch (choiceNum) {
			case FoundMenuChoice.LOAD:// 웹 정보 가져오기
				foundArticleList = FoundArticleDAO.webConnection();
				break;
			case FoundMenuChoice.INSERT: // 테이블 저장하기
				if (foundArticleList.size() < 1) {
					System.out.println("공공데이터로부터 가져온 자료가 없습니다.");
					continue;
				}
				FoundArticleDAO.insertArticle(foundArticleList);
				break;
			case FoundMenuChoice.SELECT:// 테이블 읽어오기
				foundArticleSelectList = FoundArticleDAO.selectArticle();
				FoundArticleManager.printArticle(foundArticleSelectList);

				break;
			case FoundMenuChoice.UPDATE:// 테이블 수정하기
				String data = FoundArticleManager.updateArticle();
				if (data.length() != 0) {
					FoundArticleManager.updateArticle(data);
				}
				break;
			case FoundMenuChoice.DELETE:// 테이블 삭제하기
				FoundArticleManager.deleteArticle();
				break;
			case FoundMenuChoice.SEARCH_PL://보관장소로 검색
				FoundArticleDAO.searchDepPlace();
				break;
			case FoundMenuChoice.SEARCH_MN://물품명으로 검색
				FoundArticleDAO.searchFdPrdtNm();
				break;
			case FoundMenuChoice.EXIT:// 종료하기
				exitFlag = true;
				break;
			}
		} // end of while
	}

}

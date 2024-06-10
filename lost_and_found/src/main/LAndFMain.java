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
			input.nextLine(); 				// ����Ŭ����
			switch (choiceNum) {
			case MainMenuChoice.LOST:		// �нǹ�
				lostMenu();
				break;
			case MainMenuChoice.FOUND:		// ���湰
				foundMenu();
				break;
				
			case MainMenuChoice.EXIT:		// �����ϱ�
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
			input.nextLine(); // ����Ŭ����
			switch (choiceNum) {
			case LostMenuChoice.LOAD:// �� ���� ��������
				missingArticleList = MissingArticleDAO.webConnection();
				break;
			case LostMenuChoice.INSERT: // ���̺� �����ϱ�
				if (missingArticleList.size() < 1) {
					System.out.println("���������ͷκ��� ������ �ڷᰡ �����ϴ�.");
					continue;
				}
				MissingArticleDAO.insertArticle(missingArticleList);
				break;
			case LostMenuChoice.SELECT:// ���̺� �о����
				missingArticleSelectList = MissingArticleDAO.selectArticle();
				MissingArticleManager.printArticle(missingArticleSelectList);

				break;
			case LostMenuChoice.UPDATE:// ���̺� �����ϱ�
				String data = MissingArticleManager.updateArticle();
				if (data.length() != 0) {
					MissingArticleManager.updateArticle(data);
				}
				break;
			case LostMenuChoice.DELETE:// ���̺� �����ϱ�
				MissingArticleManager.deleteArticle();
				break;
			case LostMenuChoice.SEARCH:
				MissingArticleDAO.searchArticle();
				break;
			case LostMenuChoice.EXIT:// �����ϱ�
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
			input.nextLine(); // ����Ŭ����
			switch (choiceNum) {
			case FoundMenuChoice.LOAD:// �� ���� ��������
				foundArticleList = FoundArticleDAO.webConnection();
				break;
			case FoundMenuChoice.INSERT: // ���̺� �����ϱ�
				if (foundArticleList.size() < 1) {
					System.out.println("���������ͷκ��� ������ �ڷᰡ �����ϴ�.");
					continue;
				}
				FoundArticleDAO.insertArticle(foundArticleList);
				break;
			case FoundMenuChoice.SELECT:// ���̺� �о����
				foundArticleSelectList = FoundArticleDAO.selectArticle();
				FoundArticleManager.printArticle(foundArticleSelectList);

				break;
			case FoundMenuChoice.UPDATE:// ���̺� �����ϱ�
				String data = FoundArticleManager.updateArticle();
				if (data.length() != 0) {
					FoundArticleManager.updateArticle(data);
				}
				break;
			case FoundMenuChoice.DELETE:// ���̺� �����ϱ�
				FoundArticleManager.deleteArticle();
				break;
			case FoundMenuChoice.SEARCH_PL://������ҷ� �˻�
				FoundArticleDAO.searchDepPlace();
				break;
			case FoundMenuChoice.SEARCH_MN://��ǰ������ �˻�
				FoundArticleDAO.searchFdPrdtNm();
				break;
			case FoundMenuChoice.EXIT:// �����ϱ�
				exitFlag = true;
				break;
			}
		} // end of while
	}

}

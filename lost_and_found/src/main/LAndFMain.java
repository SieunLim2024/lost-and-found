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

import controller.MissingArticleDAO;
import controller.MissingArticleManager;
import model.MissingArticleVO;
import view.MenuChoice;

public class LAndFMain {
	public static Scanner input = new Scanner(System.in);

	public static ArrayList<MissingArticleVO> missingArticleList = null;
	public static ArrayList<MissingArticleVO> missingArticleSelectList = null;
	
	public static void main(String[] args) throws IOException {
		mainMenu();
	}

	public static void mainMenu() {
		boolean exitFlag = false;
		while (!exitFlag) {
			view.MenuViewer.mainMenuView();

			int choiceNum = input.nextInt();
			input.nextLine(); // ����Ŭ����
			switch (choiceNum) {
			case MenuChoice.LOAD:// �� ���� ��������
				missingArticleList = MissingArticleDAO.webConnection();
				break;
			case MenuChoice.INSERT: // ���̺� �����ϱ�
				if (missingArticleList.size() < 1) {
					System.out.println("���������ͷκ��� ������ �ڷᰡ �����ϴ�.");
					continue;
				}
				MissingArticleDAO.insertArticle(missingArticleList);
				break;
			case MenuChoice.SELECT:// ���̺� �о����
				missingArticleSelectList = MissingArticleDAO.selectArticle();
				MissingArticleManager.printArticle(missingArticleSelectList);

				break;
			case MenuChoice.UPDATE:// ���̺� �����ϱ�
				String data = MissingArticleManager.updateArticle();
				if (data.length() != 0) {
					MissingArticleManager.updateArticle(data);
				}
				break;
			case MenuChoice.DELETE:// ���̺� �����ϱ�
				MissingArticleManager.deleteArticle();
				break;
			case MenuChoice.SEARCH:
				MissingArticleDAO.searchArticle();
				break;
			case MenuChoice.EXIT:// �����ϱ�
				exitFlag = true;
				break;
			}
		} // end of while
	}

}

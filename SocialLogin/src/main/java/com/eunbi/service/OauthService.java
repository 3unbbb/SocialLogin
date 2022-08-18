package com.eunbi.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class OauthService {
	
	private static final Logger log = LoggerFactory.getLogger(OauthService.class);
	
	public String getAccessToken(String code) throws IOException {
			
			System.out.println("getAccessToken(Service) 호출");
		
			String client_id="9c2d5efce4c881343385d90eb3e693e4";
			String redirect_uri="http://localhost:8088/oauth";
			String authorization_code = code;
			String url ="https://kauth.kakao.com/oauth/token";
			
			String access_token = null;
			String refresh_token = null;
			
			try {
				URL con_url = new URL(url);
				
				//해당 주소 페이지로 접속 & 단일 http로 접속하기 위해 캐스팅
				HttpURLConnection conn = (HttpURLConnection) con_url.openConnection();
				
				//post요청
				conn.setRequestMethod("POST");
				//POST or PUT 방식으로 요청을 보내려면 output을 true로 설정해야 함
				conn.setDoOutput(true);
				
				//InputStream 으로 응답 헤더와 메시지를 읽어들이는 옵션 정의
				//OutputStream 으로 post 데이터를 넘겨주는 옵션 정의
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				
				sb.append("grant_type=authorization_code");
				sb.append("&client_id="+client_id);
				sb.append("&redirect_uri="+redirect_uri);
				sb.append("&code="+authorization_code);
				
				//write메소드로 stringBuilder로 작성된 파라미터정보를 전달
				bw.write(sb.toString());
				
				//스트림 버퍼 비위주기
				bw.flush();
				
				//response가 200이면 성공
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				//inputStream 정보 받아오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				String result = "";
				
				while ((line = br.readLine()) != null) {
					result += line;
				}
				
				System.out.println("responseBody : "+result);
				
				//Json형식의 데이터를 파싱(gson 라이브러리 추가 다운로드하기)
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				access_token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();
				
				System.out.println("access_token : "+access_token);
				System.out.println("refresh_token : "+refresh_token);
				
				
				//스트림 닫기
				br.close();
				bw.close();
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return access_token;

		
	}

	public HashMap<String, Object> getUserInfo(String access_token) throws IOException {
		
		String Access_token = access_token;
		String url ="https://kapi.kakao.com/v2/user/me";
		
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		
		URL con_url;
		try {
			con_url = new URL(url);
			
			//해당 주소 페이지로 접속 & 단일 http로 접속하기 위해 캐스팅
			HttpURLConnection conn = (HttpURLConnection) con_url.openConnection();
			
			//GET요청
			conn.setRequestMethod("GET");
			//POST or PUT 방식으로 요청을 보내려면 output을 true로 설정해야 함
			//conn.setDoOutput(true);
			
			//요청 헤더를 정의
			conn.setRequestProperty("Authorization", "Bearer "+Access_token);
			
			//InputStream 으로 응답 헤더와 메시지를 읽어들이는 옵션 정의
			//OutputStream 으로 post 데이터를 넘겨주는 옵션 정의
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";
			
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        
	        System.out.println("response body : " + result);
	        
	      //Json형식의 데이터를 파싱(gson 라이브러리 추가 다운로드하기)
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			//properties와 kakao_account 안의 정보를 가져오기위해서 JsonObject로 Json 객체로 바꿔주기
			
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			
			System.out.println("nickname : "+nickname);
			System.out.println("birthday : "+birthday);
			System.out.println("email : "+email);
			
			userInfo.put("nickname", nickname);
			userInfo.put("birthday", birthday);
			userInfo.put("email", email);
			
			br.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userInfo;
		
	}

}

package hhSignalR;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import java.util.Scanner;

public class MySignalR {
	public static void main(String[] args) throws Exception  {
		System.out.println("Signal R Java Client by Maven Project!!");
			
		//System.out.println("Please specify the URL of SignalR Hub");
		Scanner reader = new Scanner(System.in);
		//String input = reader.nextLine();
		
		
		String input = "http://hklab.hknu.ac.kr/chathub";		// chathub로 써줘야!!
		//String input = "http://localhost:8080/chathub";
		
		// 설정
		HubConnection hubConnection = HubConnectionBuilder.create(input)
		        .build();
		
		// 받는 함수 등록
		hubConnection.on("ReceiveMessage", (user, message) -> {		
		    System.out.println(user + ": " + message);
		}, String.class, String.class);		// 수신 메시지 개수에 따라 타입을 정의
		
		// 연결		
		hubConnection.start().blockingAwait();
		// hubConnection.start();  // 이것도 가능 (무슨 차이?)
		
		// 전송하기
		while (!input.equals("exit")){
			String name = "Java Console";
		    String msg = reader.nextLine();
		    hubConnection.send("SendMessage", name, msg);
		}
		
		// 종료
		hubConnection.stop();
	}
}




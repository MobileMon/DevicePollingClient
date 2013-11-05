package com.example.androidclient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class MonitorWorker extends Thread {

	private ServerSocket mSocket;
	private int mPortNumber = 4000;
	private Context mContext;
	

	public MonitorWorker(Context context) {
		
		mContext = context;

		boolean isPortAvailable = false;
		
		while (!isPortAvailable){

			try {
				mSocket = new ServerSocket(mPortNumber);
				isPortAvailable = true;
			} catch (IOException e) {
			
				mPortNumber++;
				
				if (mPortNumber > 7000){
					System.out.println("Could not open a socket");
					System.exit(-1);
				}
			}
			}
	}

	@Override
	public void run() {

		while (true) {
			
			
			//accept requests
			Socket clientSocket = null;
			
			try {
				clientSocket = mSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Could not connect to socket");
			}
			
			
			
			//write to socket the device is okay
			PrintWriter out = null;
			
			try{
			     out = new PrintWriter(clientSocket.getOutputStream(), 
			                 true);
			   } catch (UnknownHostException e) {
				   Toast.makeText(mContext, "Unknown host" + e.getMessage(), Toast.LENGTH_SHORT).show();
			   } catch  (IOException e) {
			     Toast.makeText(mContext, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
			   }
		
			
			
		
			out.println("1"); //1 for okay
		

			out.flush();
			out.close();
	
			
	
			//close client
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
			
	}
	
	
	public void cleanUp(){
		
		
		
		
		
		//close socket
		try {
			mSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//stop thread
				try {
					this.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				this.interrupt();
	}

	public int getPortNumber() {
		return mPortNumber;
	}


}

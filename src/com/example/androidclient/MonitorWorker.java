package com.example.androidclient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MonitorWorker extends Thread {

	private ServerSocket mSocket;
	private int mPortNumber = 4000;
	

	public MonitorWorker() {
		

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

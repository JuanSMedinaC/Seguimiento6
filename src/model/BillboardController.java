package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BillboardController {
	private ArrayList<Billboard> billboards;
	private ArrayList<Billboard> dangerousBillboards;
	

	
	public BillboardController() {
		billboards = new ArrayList<Billboard>();
		dangerousBillboards = new ArrayList<Billboard>();
	}
	
	public void addBillboard(int width, int height, boolean inUse, String brand) {
		billboards.add(new Billboard(width, height, inUse, brand));
	}

	public void importDataWithPath(String absPath){
		int width;
		int height;
		boolean inUse;
		String brand;
		
		try {
			FileReader pathInput=new FileReader(absPath);
			BufferedReader fileInput=new BufferedReader(pathInput);
			fileInput.readLine();
			while(fileInput.ready()) {
				String readLine=fileInput.readLine();
				String[] separatedLines=readLine.split("\\|");
				width=Integer.parseInt(separatedLines[0]);
				height=Integer.parseInt(separatedLines[1]);
				inUse=Boolean.parseBoolean(separatedLines[2]);
				brand=separatedLines[3];
				addBillboard(width, height, inUse, brand);
			}
			pathInput.close();
			fileInput.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addBillboardByText(String billboardInfo) {
		int width;
		int height;
		boolean inUse;
		String brand;
		
		String[] billboardLine=billboardInfo.split("\\+\\+");
		width=Integer.parseInt(billboardLine[0]);
		height=Integer.parseInt(billboardLine[1]);
		inUse=Boolean.parseBoolean(billboardLine[2]);
		brand=billboardLine[3];
		addBillboard(width, height, inUse, brand);
		
	}
	
	public String addDangerBillboards() {
		String text="";
		
		text=	"==========================="+
				"\nDANGEROUS BILLBOARD REPORT"+
				"\n==========================="+
				"\nThe dangerous billboard are:\n";
		
		int idx=0;
		for (int i=0; i<billboards.size();i++) {
			idx=i+1;
			int area= billboards.get(i).getWidth()*billboards.get(i).getHeight();
			if (area>200000) {
				text+=("\n"+idx+"."+" Billboard "+billboards.get(i).getBrand()+ " with area "+area);
			}
		}
		
		File file= new File("DangerousBillboards.txt");
		try {
			FileWriter filewriter=new FileWriter(file);
			BufferedWriter buffWrite=new BufferedWriter(filewriter);
			buffWrite.write(text);
			buffWrite.newLine();
			buffWrite.close();
			filewriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
	public void serializeBillboards() {
		File billboardsfile=new File("BillBoards.txt");
		try {
			FileOutputStream outputStream= new FileOutputStream(billboardsfile);
			ObjectOutputStream objOutputStream= new ObjectOutputStream(outputStream);
			objOutputStream.writeObject(returnBillBoards());
			
			outputStream.close();
			objOutputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void unserializeBillBoards() {
		File billboardsFile=new File("BillBoards.txt");
		try {
			FileInputStream inputStream= new FileInputStream(billboardsFile);
			ObjectInputStream objInputStream= new ObjectInputStream(inputStream);
			
			billboards.addAll((ArrayList <Billboard>) objInputStream.readObject());
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Billboard> returnBillBoards(){
		return billboards;
	}
	
	
	
}

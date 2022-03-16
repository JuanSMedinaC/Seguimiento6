package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class Main {
	public static Scanner reader=new Scanner(System.in);
	public static BillboardController bController=new BillboardController();
	
	public static void importDataWithPath(){
		String absPath="";		
		System.out.println("Type the absolute path to the information");
		absPath=reader.nextLine();
		bController.importDataWithPath(absPath);
	}
	
	public static void addBillboard() {
		System.out.println("Insert a billboard with the information following the next format"
				+ "\nwidth++height++inUse++brand");
		String inputLine=reader.nextLine();
		bController.addBillboardByText(inputLine);	
	}
	
	public static void printBillBoards() {
		ArrayList<Billboard> bBoards=bController.returnBillBoards();
		System.out.println("W"+"    "+"H"+"    "+"inUse"+"  "+"Brand");
		for (int i=0; i<bBoards.size();i++) {
			System.out.println(bBoards.get(i));
		}
	}
	
	public static void printDangerReport() {
		System.out.println(bController.addDangerBillboards());
	}
	
	public static int menu() {
		int opt=0;
		System.out.println("1)Import data with path"
				+ "\n2)Add billboard"
				+ "\n3)Show billboards"
				+ "\n4)Export and print danger report"
				+ "\n0)Leave");
		opt=reader.nextInt();
		reader.nextLine();
		return opt;
		
	}
	public static void main (String[]args) {
		int opt=-1;
		
		bController.unserializeBillBoards();
		
		while (opt!=0) {
			opt=menu();
			if (opt==1) {
				importDataWithPath();
			}
			else if(opt==2) {
				addBillboard();
			}
			else if(opt==3) {
				printBillBoards();
			}
			else if(opt==4) {
				printDangerReport();
			}
			else if(opt==0) {
				bController.serializeBillboards();
			}
		}
	}
}

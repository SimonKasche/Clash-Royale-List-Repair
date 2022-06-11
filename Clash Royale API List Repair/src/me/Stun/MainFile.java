package me.Stun;

import java.io.IOException;

public class MainFile {
	
	public static void main(String[]args) throws IOException {
		
		me.Stun.Repair.MainProcess.matches = me.Stun.IO.FileManagement.readPreviousMatchesArray();
		me.Stun.Repair.MainProcess.repair();
		me.Stun.Repair.MainProcess.removeDuplicates();
		System.out.println("successfully removed " + me.Stun.Repair.MainProcess.duplicateCount + " duplicateMatches");
		
	}
	
}

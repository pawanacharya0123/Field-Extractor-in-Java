package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;


public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter() {
		
		return new MemberExporterImpl();
		
	}

	@Override
	protected MemberImporter getMemberImporter() {
		
		return new MemberImporterImpl();
		
	}

	@Override
	protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
		// using Set to eliminate duplicates as it is Set's property to only have unique elements
		Set<Member> setWithNoDuplicates = membersFromFile.stream().collect(Collectors.toSet());
		
		//converting set to the required return type, i.e. List
		return setWithNoDuplicates.stream().collect(Collectors.toList());
	}

	@Override
	protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
		Map<String, List<Member>> mapSplitMembersByState = new HashMap<>();
		
		//Collecting unique States from the validMembers List into a Set
		Set<String> statesSet = validMembers.stream().map(Member::getState).collect(Collectors.toSet());
		
		for (String state : statesSet) {
			//appending mapSplitMembersByState Map with individual State and it's corresponding Member lists
			mapSplitMembersByState.put(state,
					validMembers.stream().filter(t -> t.getState().equalsIgnoreCase(state))
					.collect(Collectors.toList()));
		}
		
		return mapSplitMembersByState;
	}

	public static void main(String[] args) {
		
		//creating an Object of Main Class to call the a non-static function from Static main 
		Main main = new Main();
		
		File inputMemberFile =new File(System.getProperty("user.dir") + "/Members.txt");
		
		String memberExportPath = System.getProperty("user.dir")+"/memberExport/";
		
		//creating a folder "memberExport" if it doesn't already exists to output the CSV files 
		File theDir= new File(memberExportPath);
		if (!theDir.exists()){
			theDir.mkdirs();
		}else {
			for(File f: theDir.listFiles()) 
				  f.delete(); 
		}
		
		try {
			main.convert(inputMemberFile, memberExportPath, "outputFile.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("!!Check MemberExport folder for the CSV files!!");
	}

}

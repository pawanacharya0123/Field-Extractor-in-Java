package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List< Member > importMembers( File inputFile ) throws Exception {

		/*
		 * Creating Member objects from the sting of .txt file and 
		 * appending it to the returning List
		 */
		
		List< Member > memberList= new ArrayList<>();
		try (BufferedReader br = new BufferedReader( new FileReader( inputFile ) )) {
			String line = br.readLine( );
			while ( line != null ) {
				
				line = br.readLine( );
				Member newMember= new Member();
				
				/*splitting the substring of the line String as per the Test1.txt requisites:
				 *The fields are found to be in the following order and length:
				 *id(12) last name(25) first name(25) address(30) city(20) state(4) zip(5)
				 */ 
				
				newMember.setId(line.substring(0, 11).trim());
				newMember.setLastName(line.substring(12, 36).trim());
				newMember.setFirstName(line.substring(37, 60).trim());
				newMember.setAddress(line.substring(61, 90).trim());
				newMember.setCity(line.substring(91, 110).trim());		
				newMember.setState(line.substring(111, 114).trim());
				newMember.setZip(line.substring(115, 120).trim());
				
				memberList.add(newMember);
				
				line = br.readLine( );
			}
		}
		return memberList;
	}

}

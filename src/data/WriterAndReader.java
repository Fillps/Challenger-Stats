package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class WriterAndReader {
	public WriterAndReader(){
		
	}
	public void writeChallengerIds(ChallengerIds file, String local){
		
		try {
			FileOutputStream out = new FileOutputStream(local);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(file);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ChallengerIds readChallengerIds(String local){
		ChallengerIds ids = null; 
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			ids = (ChallengerIds) (ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public void writeChallengerMatchesIds(ChallengerMatchesIds file, String local){
		
		try {
			FileOutputStream out = new FileOutputStream(local);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(file);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ChallengerMatchesIds readChallengerMatchesIds(String local){
		ChallengerMatchesIds matchesIds = null; 
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			matchesIds = (ChallengerMatchesIds) (ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchesIds;
	}
	
	public void writeChallengerMatches(ChallengerMatches file, String local){
		
		try {
			FileOutputStream out = new FileOutputStream(local);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(file);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ChallengerMatches readChallengerMatches(String local){
		ChallengerMatches matches = null; 
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			matches = (ChallengerMatches) (ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matches;
	}
	
	public void writeListMatchDetail(List<MatchDetail> file, String local){
		
		try {
			FileOutputStream out = new FileOutputStream(local);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(file);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MatchDetail> readListMatchDetail(String local){
		List<MatchDetail> matches = null; 
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			matches = (List<MatchDetail>) (ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matches;
	}
}

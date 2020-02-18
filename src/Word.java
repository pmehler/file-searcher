import java.util.ArrayList;

public class Word implements Comparable<Word>{
	
	private String word;
	private ArrayList<String> fileList;
	
	public void word(){
		this.fileList = new ArrayList<String>();
	}
	//constructor
	public String getWord() {
		return this.word;
	}
	
	public void setWord(String w) {
		this.word = w;
	}
	
	public void addWord(String w) {
		this.fileList.add(w);
	}
	
	public ArrayList getList() {
		return this.fileList;
	}
	//String name for each word is the key
	public int compareTo(Word w) {
		if(this.word.equals(w.word)) {
			return 0;
		}
		else {
			return 1;
		}

	}

}

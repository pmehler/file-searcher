import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Character;

public class FileSearcher {
	
	static BinarySearchTree<Word> wordTree = new BinarySearchTree<Word>();
	static ArrayList<String> wordArr = new ArrayList<String>();  //List to hold each word encountered
	
	public static void main(String[] args) throws IOException, UnderflowException {
		File directory = new File(args[0]);
		scanFiles(directory);
		boolean keepPlaying = true;
		while(keepPlaying == true) {
			Scanner scan = new Scanner(System.in);
			String answer = "";
			System.out.println("Please enter a command (a, s, or q): ");
			answer = scan.nextLine();
			if (answer.equals("a")){
				for(int k=0; k<wordArr.size(); k++) { //loop through all words
					answer = wordArr.get(k);
					Word x = new Word(); //create temp word object to be compared in contains()
					x.setWord(answer);  
					if(wordTree.contains(x)) {  //check if tree contains each word
						System.out.print("files containing "+answer+":");
						ArrayList<String> tempList = new ArrayList<String>();
						tempList = wordTree.find(x).getList();  
						System.out.print(Arrays.toString(tempList.toArray())); //print each list for given word
						System.out.println();
					}
				}
			}
			else if(answer.equals("s")) {
				System.out.println("Word to find: ");
				answer = scan.nextLine();
				Word x = new Word();
				x.setWord(answer);
				if(wordTree.contains(x)) {		//same as above without loop
					System.out.print("files containing "+answer+":");
					ArrayList<String> tempList = new ArrayList<String>();
					tempList = wordTree.find(x).getList();
					System.out.print(Arrays.toString(tempList.toArray()));
					System.out.println();
				}
				else {
					System.out.println(answer+" is not found");
				}
			}
			else {
				keepPlaying = false;
			}
		}
	}
	static void scanFiles(File f) throws IOException, UnderflowException{
		File[] fileArr = f.listFiles();  //array of file objects
		for(int i=0; i<fileArr.length; i++) {
			if(!fileArr[i].isHidden()) {
				//System.out.println(fileArr[i].getName());
				if (fileArr[i].isDirectory()) {
					//System.out.println("isDirectory");
					scanFiles(fileArr[i]);
				}
				else {
					Scanner filereader = new Scanner(fileArr[i]);
					while(filereader.hasNext()) {
						Word w = new Word();
						w.word(); //initialize object's ArrayList
						String temp = "";
						w.setWord(filereader.next());
						for(int j=0; j<w.getWord().length(); j++) {  //loop through word by character and weed out punctuation
							if((Character.isLetter(w.getWord().charAt(j))) || (Character.isDigit(w.getWord().charAt(j)))) {
								temp+=w.getWord().charAt(j);
							}
						}
						w.setWord(temp);
						//System.out.println(w.getWord());
						if(wordTree.contains(w)) {
							wordTree.find(w).addWord(fileArr[i].getName()); //if already in tree, add file name to list of each word object
						}
						else { //if not already in tree, add to word array, add file name to word object, and insert into tree
							wordArr.add(w.getWord());
							w.addWord(fileArr[i].getName());
							wordTree.insert(w);
						}
					}
				}
			}
		}
	}
}

package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	public List<String> rwSbagliate = new LinkedList<String>();
	public List<RichWord> rw = new LinkedList<RichWord>();
	public int cont=0;
	
	public List<String> dizionarioItaliano = new LinkedList<String>();
	public List<String> dizionarioInglese = new LinkedList<String>();
	public List<String> dizionarioCorrente;
	
	public void loadAll() {
		loadDictionary(dizionarioItaliano, "Italian");
		loadDictionary(dizionarioInglese, "English");
	}
	
	
	public void loadDictionary(List<String> dict,String language) {
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr); 
			String word;
			while ((word = br.readLine()) != null) {
			     dict.add(word);
			                    }
			   br.close();
		} catch (IOException e){
			System.out.println("Errore nella lettura del file"); 
			}
		
	}
		
		/*public List<RichWord> spellCheckText(List<String> inputTextList) {
			for(String s : inputTextList) {
				if(!dizionarioCorrente.contains(s)) {
					//rw.add(new RichWord(s,false));
					rwSbagliate.add(s);
					cont++;
				}
				if(dizionarioCorrente.contains(s)) {
					rw.add(new RichWord(s,true));
				}
			}
			return null;
				
			}*/
		public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
			for(String s : inputTextList) {
				if(!dizionarioCorrente.contains(s)) {
					rwSbagliate.add(s);
					cont++;
				}
				if(dizionarioCorrente.contains(s)) {
					rw.add(new RichWord(s,true));
				}
			}
			return null;
				
			}
		public List<RichWord> spellCheckTextDicotomic(List<String> inputTextList) {
			for(String s : inputTextList) {
				if(s.compareTo(dizionarioCorrente.get(dizionarioCorrente.size()/2))<0) {
					for(int j=dizionarioCorrente.size()/2 ; j<dizionarioCorrente.size() ; j--) {
						if(s.compareTo(dizionarioCorrente.get(j))==0) {
							rw.add(new RichWord ( s, true));
						}
					}
				}
				else 
					
				if(s.compareTo(dizionarioCorrente.get(dizionarioCorrente.size()/2))>0) {
					for(int j=dizionarioCorrente.size()/2 ; j<dizionarioCorrente.size() ; j++) {
						if(s.compareTo(dizionarioCorrente.get(j))==0) {
							rw.add(new RichWord ( s, true));
						}
					}
				}
				else {
				if(s.compareTo(dizionarioCorrente.get(dizionarioCorrente.size()/2))==0) {
					rw.add(new RichWord ( s, true));
					}
				else {
					rwSbagliate.add(s);
					cont++;
				}
				}
			}
			return null;
				
			}
		
		public String stampaStringa(List<String> rwS) {
			String stringaa = "";
			for(String s1 : rwS) {
				stringaa += s1+"\n";
			}
			return stringaa;
		}
	}



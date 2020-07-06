package F28DA_CW1;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/** Main class for the Word Index program */
public class WordIndex {

	static final File textFilesFolder = new File("TextFiles");
	static final FileFilter commandFileFilter = (File file) -> file.getParent()==null;
	static final FilenameFilter txtFilenameFilter = (File dir, String filename) -> filename.endsWith(".txt");

	public static void main(String[] argv) {
		if (argv.length != 1 ) {
			System.err.println("Usage: WordIndex commands.txt");
			System.exit(1);
		}
		try{
			File commandFile = new File("commands.txt");
			if (commandFile.getParent()!=null) {
				System.err.println("Use a command file in current directory");
				System.exit(1);
			}

			// creating a command reader from a file
			
			WordTxtReader commandReader = new WordTxtReader(commandFile);

			// Initialize map
			IWordMap wordPossMap=new ListWordMap();
			// ...

			// reading the content of the command file
			while(commandReader.hasNextWord()) {
				// getting the next command
				String command = commandReader.nextWord().getWord();

				switch (command) {
				case "addall":
					assert(textFilesFolder.isDirectory());
					File[] listOfFiles = textFilesFolder.listFiles(txtFilenameFilter);
					Arrays.sort(listOfFiles);
					for (File textFile : listOfFiles) {
						WordTxtReader wordReader = new WordTxtReader(textFile);

						while (wordReader.hasNextWord()) {
							WordPosition wordPos = wordReader.nextWord();
							wordPossMap.addPos(wordPos.getWord(), wordPos);
							
						}
					}
					System.out.println(wordPossMap.numberOfEntries()+" entries have been indexed from "+listOfFiles.length+" files");
					break;

				case "add":
					File textFile = new File(textFilesFolder, commandReader.nextWord().getWord()+".txt");
					WordTxtReader wordReader = new WordTxtReader(textFile);
					while (wordReader.hasNextWord()) {
						WordPosition word = wordReader.nextWord();
						// adding word to the map
						wordPossMap.addPos(word.getWord(), word);
					}
					System.out.println(wordPossMap.numberOfEntries()+" entries have been indexed from "+textFile.getName());
					break;

				case "search":
					int nb = Integer.parseInt(commandReader.nextWord().getWord());
					String word = commandReader.nextWord().getWord();
					// search for word entry in map
					IPosition ipos;
					HashMap<String,Integer> map1=new HashMap<>();
					int no=0;
					String key;
					try {
						Iterator<IPosition> poss = wordPossMap.positions(word);
						int i = 0;
						while(poss.hasNext()) {
							ipos=poss.next();
							if(map1.containsKey(ipos.getFileName()))
							{
								
								key=ipos.getFileName();
								no=map1.get(key).intValue();
								no++;
								map1.put(key,no);
							}
							else
							{
								key=ipos.getFileName();
								no=1;
								map1.put(key,no);
							}
							i++;
						}
						
						System.out.println("The word "+word+" occurs "+i+" times in "+map1.size()+" files: ");
						Set<String> s=map1.keySet();
						Object obj[]=s.toArray();
						for(int j=0;j<nb;j++)
						{
							poss=wordPossMap.positions(word);
							System.out.println(map1.get(obj[j]).intValue()+" times in "+obj[j]);
							System.out.print("( Lines ");
							while(poss.hasNext())
							{
								IPosition ipos2=poss.next();
								if(obj[j].equals(ipos2.getFileName()))
										{
											System.out.print(ipos2.getLine()+" ");
										}
							}
						}
					} catch (WordException e) {
						System.err.println("not found");
					}
					
					break;

				case "remove":
					File textFileToRemove = new File(textFilesFolder, commandReader.nextWord().getWord()+".txt");
					String file=textFileToRemove.getName();
					int i=0;
					Iterator<String>itr=wordPossMap.words();
					while(itr.hasNext())
					{
						String word1=itr.next();
						Iterator<IPosition> itrpos;
						try {
							itrpos = wordPossMap.positions(word1);
						
						while(itrpos.hasNext())
						{
							IPosition pos=itrpos.next();
							if(pos.getFileName().contentEquals(file))
							{
								wordPossMap.removePos(word1, pos);
								i++;
							}
						}
					}
					 catch (WordException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						System.out.println(i+" entries have been removed from file"+textFileToRemove.getName());
					}
					
					break;

				case "overview":
					System.out.println("Overview:");
					System.out.println("Number of words:"+getWordCount(wordPossMap));
					System.out.println("Number of Positions:"+wordPossMap.numberOfEntries());
					System.out.println("Number of Files"+getFileCount(wordPossMap));
					break;

				default:
					break;
				}

			}

		}
		catch (IOException e){ // catch exceptions caused by file input/output errors
			System.err.println("Check your file name");
			System.exit(1);
		} 
		
	}
	private static int getWordCount(IWordMap wordPossMap) {
		Iterator<String> itr=wordPossMap.words();
		int i=0;
		while(itr.hasNext())
		{
			itr.hasNext();
			i++;
		}
		return i;
	}
	
	private static int getFileCount(IWordMap wordPossMap)
	{
		Iterator<String> itr=wordPossMap.words();
		ArrayList<String> files=new ArrayList<>();
		int i=0;
		while(itr.hasNext())
		{
			Iterator<IPosition> ipos;
			try {
				ipos = wordPossMap.positions(itr.next());
			
			while(ipos.hasNext())
			{
				WordPosition wp=(WordPosition) ipos.next();
				if(!files.contains(wp.getFileName()))
				{
					files.add(wp.getFileName());
					i++;
				}
				
			}
		}
		 catch (WordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		return i;
	
}
}

package F28DA_CW1;


import java.util.Iterator;
import java.util.LinkedList;

import F28DA_CW1.ListWordMap.Entry;

/**
 *
 * @author Dell
 */
public class ListWordMap implements IWordMap {
    public class Entry{
        private String word;
        private IPosition pos;
        public Entry(String word,IPosition pos)
        {
            this.word=word;
            this.pos=pos;
        }
        public String getWord()
        {
            return word;
        }
        public IPosition getPos()
        {
            return pos;
        }
    }
    LinkedList<Entry> ll;
    int size;
    public ListWordMap()
    {
        ll=new LinkedList();
        size=0;
    }

    @Override
    public void addPos(String word, IPosition pos) {
       Iterator<Entry> itr=ll.iterator();
       int count=0;
       while(itr.hasNext())
       {
           Entry e=(Entry)itr.next();
           String word1=e.getWord();
           IPosition pos1=e.getPos();
           if(word.equals(word1)&&pos.equals(pos1))
           {
               count++;
           }
       }
           if(count==0)
           {
        	 
               Entry entry=new Entry(word,pos);
               ll.add(entry);
               size++;
           }
       
    }

    @Override
    public void removeWord(String word) throws WordException {
        Iterator <Entry>itr=ll.iterator();
        int count=0;
        while(itr.hasNext())
        {
            Object obj=itr.next();
            Entry e=(Entry)obj;
            if(e.getWord().equals(word))
            {
                ll.remove(e);
                count++;
                size--;
            }
        }
        if(count==0)
        {
            throw new WordException();
        }
    }

    @Override
    public void removePos(String word, IPosition pos) throws WordException {
    	Iterator<Entry> itr=ll.iterator();
        int count=0;
        while(itr.hasNext())
        {
            Object obj=itr.next();
            Entry e=(Entry)obj;
            if(e.getWord().equals(word)&&e.getPos().equals(pos))
            {
                ll.remove(e);
                count++;
                size--;
            }
        }
        if(count==0)
        {
            throw new WordException();
        }
        
    }

    @Override
    public Iterator<String> words() {
        LinkedList<String> wll=new LinkedList<String>();
        Iterator<Entry> itr=ll.iterator();
        while(itr.hasNext())
        {
        	Entry e=(Entry)itr.next();
        	wll.add(e.getWord());
        	
        }
        Iterator<String> i=wll.iterator();
        return i;
    }

    @Override
    public Iterator<IPosition> positions(String word) throws WordException {
    	LinkedList<IPosition> pll=new LinkedList<IPosition>();
        Iterator<Entry> itr=ll.iterator();
        while(itr.hasNext())
        {
        	Entry e=(Entry)itr.next();
        	pll.add(e.getPos());
        	
        }
        Iterator<IPosition> i=pll.iterator();
        return i;
    }

    @Override
    public int numberOfEntries() {
        return size;
    }
}

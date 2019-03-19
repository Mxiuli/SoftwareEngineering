import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.List;  
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;  
import java.util.TreeMap; 
//import java.io.FileOutputStream;  
import java.io.PrintStream; 
import java.io.FileNotFoundException;
 
  
public class EWfrequencycount {  
	
	
  
    public static void main(String[] args) throws Exception {  
          
        BufferedReader br = new BufferedReader(new FileReader("content.txt"));  
        List<String> lists = new ArrayList<String>();            //存储过滤后单词的列表  
        String readLine = null;
		while((readLine = br.readLine()) != null){  
            String[] wordsArr1 = readLine.split("[^a-zA-Z]");     //过滤出只含有字母的  
            for (String word : wordsArr1) {  
                if(word.length() != 0){        //去除长度为0的行  
                    lists.add(word);  
                }  
            }  
        }  
          
        br.close();  
        		
          
        Map<String, Integer> wordscount = new TreeMap<String,Integer>();  //存储单词计数信息，key值为单词，value为单词数       
          
        //单词的词频统计  
        for (String e : lists) {  
            if(wordscount.get(e) != null){  
                wordscount.put(e,wordscount.get(e) + 1);  
            }
            else{  
                wordscount.put(e,1);  
            }  
  
        }  
        
        while(true)
		{
			menu();
			Scanner in = new Scanner(System.in);
			System.out.println("请选择指令:");
			String order = in.nextLine();
			
			switch(order)
			{
			case"t":SortMap(wordscount);  break;     //按值进行排序 
			case"s":Draw(wordscount);     break;     //查询单词频数及柱状图
			case"k":Frontk(wordscount);   break;      //前k个单词的频数
					
			}
		}
          
            
        
        
        
      
    }  
    
    
    //选择菜单
    private static void menu()
	{    
    	System.out.println("*************************************");
		System.out.println("s:统计某单词频数并画出其柱状图");
		System.out.println("k:显示前k个高频词频数");
		System.out.println("t:统计文本文件中的词频并降序输出在result.txt文件");
		System.out.println("*************************************");
		
	}
    
	public static void Frontk(Map<String,Integer> tmap) throws FileNotFoundException
    {
    	ArrayList<Map.Entry<String,Integer>> list0 = new ArrayList<Map.Entry<String,Integer>>(tmap.entrySet()); 
    	
    	 Collections.sort(list0,new Comparator<Map.Entry<String,Integer>>(){  
             @Override  
             public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                 return o2.getValue() - o1.getValue();  //降序  
             }  
         }); 
    	Scanner in = new Scanner(System.in);
		System.out.println("请输出想查的前K个单词数:");
		int k = in.nextInt();
		for (int i = 0; i < k; i++)
        {
			System.out.println(list0.get(i).getKey()+ ": " +list0.get(i).getValue()); 
         }
    	
    }
    
    public static void Draw(Map<String,Integer> tmap)
    {
    	ArrayList<Map.Entry<String,Integer>> list1 = new ArrayList<Map.Entry<String,Integer>>(tmap.entrySet()); 
    	
    	Scanner in = new Scanner(System.in);
		System.out.println("请输出查询单词:");
		String w = in.nextLine();
		String ws[] = w.split(" ");
		
    	 for (String word : ws) 
         {
             for (Map.Entry<String, Integer> entry : list1)
              {

                 if (word.equals(entry.getKey())) 
                 {

                     int n = entry.getValue();
                     System.out.print(entry.getKey()+"   numbers "+entry.getValue()+"   :  ");
                     for (int i = 0; i < n ; i++)
                      {
                         System.out.print("█");
                       }
                     System.out.println();
                     System.out.println();
                     break;
                    }
              
             }
         }
    	System.out.println();     
    }
      
    //按value的大小进行排序  
	public static void SortMap(Map<String,Integer> tmap) throws FileNotFoundException{  
          
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(tmap.entrySet());  
          
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
            @Override  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue() - o1.getValue();  //降序  
            }  
        }); 
        
        try {
        PrintStream tx=new PrintStream("result.txt");  
    	System.setOut(tx);//把创建的打印输出流赋给系统。即系统下次向 tx输出
        for(int i = 0; i<list.size(); i++){  
            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
            
        } 
        tx.close();
        }catch (FileNotFoundException e) {    
            e.printStackTrace();    
        }    
        
       // PrintStream out = System.out; // 先把系统默认的打印输出流缓存
        System.out.println("完成！");
        
    }  
    
} 
 



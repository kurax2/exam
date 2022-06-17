package p1;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main implements IOrderService {
	public static void main(String[] args) {
		
		Main app = new Main();
		List<Order> list = new ArrayList<>();
		
	  try (Scanner sc = new Scanner(System.in)) {
          System.out.println("Enter choice:\n1. All Orders by Category\n2. Total Order Cost\n3. All Cancelled Orders\n4. Orders By Username\n5. Orders by Action\n6. Orders by Cost\n7. PDFReports");

          int choice =sc.nextInt();


          switch(choice) {
              case 1:
            	 System.out.println("Which Category:");
            	 String cat =sc.next();
				
				try {
				list=app.getAllOrdersByCategory(cat);
				
				System.out.println(list);
			} catch (InvalidCategryException e) {
				
				System.out.println(e);
			}
                 break;
              
              case 2:
            	  System.out.println("Which Category:");
             	  String cat1 =sc.next();
             	  
					try {
						int tot=app.getTotalOrderCost(cat1);
						System.out.println("\nTotal Order Cost: "+tot);
					} catch (InvalidCategryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
              
            	  break;
                
              case 3: 
            	  System.out.println("All Cancelled Order:");
            	  app.getAllCanceledOrder();            	  
            	  break;
              case 4: 
            	  
            	  System.out.println("By username:");
            	  Map<String, List<Order>> map=app.createOrderMapByUser();   
            	  System.out.println(map);
            	  break;
              case 5: 
            	  System.out.println("Filter by:");
            	  String act =sc.next();
            	  list=app.filterOrders(act);
            	  System.out.println(list);
            	  
            	  break;
              case 6: 
            	  System.out.println("Orders by Cost:");
            	  list=app.getOrdersBasedOnOrderValue();
            	  System.out.println(list);
            	  break;
              case 7: 
            	  System.out.println("Generated PDF Reports:");
            	  app.generatePDFReports();
            	  break;
              default:
                break;
            }
      }
	}

	@Override
	public List<Order> getAllOrdersByCategory(String category) throws InvalidCategryException {
		
			 List<Order> list=new ArrayList<>();
			
			
			
			String rootPath = "C:\\Users\\dani_\\Downloads\\Mid Term 17-Jun-22-20220617\\CoreJavaTest-main\\CaseStudy_CoreJava\\";
			String fileName = "Order.csv";
			
			File f = new File(rootPath+fileName);
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line="";
				
				br.readLine(); // consume first line and ignore
				line = br.readLine();
				
				while((line = br.readLine())!=null)
				{
					
					String[] values = line.split(",");
					
					Order o = new Order();
				
					if(category.equals(values[1])) {
						
						o.setUserName(values[0]);
						o.setCategory(values[1]);
						o.setOrderCost(values[2]);
						o.setDate(values[3]);
						o.setAction(values[4]);
						list.add(o);
					}	
						
					
					
					
				}// while loop
					
				
				
			}
			
			
			catch (Exception e) {
				
				System.out.println(e);
			}
			
			
			
			return list;
			
			
		
		
	}//by category

	@Override
	public int getTotalOrderCost(String category) throws InvalidCategryException {
		
		Main app = new Main();
		
		int tot=0;
		
		List<Order> list=app.getAllOrdersByCategory(category);
		for(Order l:list) {
			int cost=Integer.parseInt(l.getOrderCost());
			tot+=cost;
			
			
		}
		
		return tot;
	}//by cost

	@Override
	public List<Order> getAllCanceledOrder() {
		 List<Order> list=new ArrayList<>();
		
		String rootPath = "C:\\Users\\dani_\\Downloads\\Mid Term 17-Jun-22-20220617\\CoreJavaTest-main\\CaseStudy_CoreJava\\";
		String fileName = "Order.csv";
		
		File f = new File(rootPath+fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line="";
			
			br.readLine(); // consume first line and ignore
			line = br.readLine();
			
			while((line = br.readLine())!=null)
			{
				
				String[] values = line.split(",");
				
				Order o = new Order();
				
				
				if(values[4].startsWith("c")) {
					
					o.setUserName(values[0]);
					o.setCategory(values[1]);
					o.setOrderCost(values[2]);
					o.setDate(values[3]);
					o.setAction(values[4]);
					list.add(o);
				}
				
					
				
				
				
			}// while loop
				
			
			
		}
		
		
		catch (Exception e) {
			
			System.out.println(e);
		}
		return list;
		
		
	
		
	}//all cancelled orders


	@Override
	public List<Order> filterOrders(String action) {
		 List<Order> list=new ArrayList<>();
			
			
			
			String rootPath = "C:\\Users\\dani_\\Downloads\\Mid Term 17-Jun-22-20220617\\CoreJavaTest-main\\CaseStudy_CoreJava\\";
			String fileName = "Order.csv";
			
			File f = new File(rootPath+fileName);
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line="";
				
				br.readLine(); // consume first line and ignore
				line = br.readLine();
				
				while((line = br.readLine())!=null)
				{
					
					String[] values = line.split(",");
					
					Order o = new Order();
				
					
					if(values[4].equals(action)) {	
						o.setUserName(values[0]);
						o.setCategory(values[1]);
						o.setOrderCost(values[2]);
						o.setDate(values[3]);
						o.setAction(values[4]);
						list.add(o);
					}
						
					
					
					
				}// while loop
					
				
				
			}
			
			
			catch (Exception e) {
				
				System.out.println(e);
			}
			
			
			
			return list;
		
	}

	@Override
	public List<Order> getOrdersBasedOnOrderValue() {
		
		 List<Order> list=new ArrayList<>();
		 
			String rootPath = "C:\\Users\\dani_\\Downloads\\Mid Term 17-Jun-22-20220617\\CoreJavaTest-main\\CaseStudy_CoreJava\\";
			String fileName = "Order.csv";
			
			File f = new File(rootPath+fileName);
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line="";
				
				br.readLine(); // consume first line and ignore
				line = br.readLine();
				
				while((line = br.readLine())!=null)
				{
					
					String[] values = line.split(",");
					
					Order o = new Order();
						
					o.setUserName(values[0]);
					o.setCategory(values[1]);
					o.setOrderCost(values[2]);
					o.setDate(values[3]);
					o.setAction(values[4]);
					list.add(o);
						
					
				}// while loop
					
				
				
			}
			
			
			catch (Exception e) {
				
				System.out.println(e);
			}
			List<Order> listOrder = new ArrayList<>();
	        listOrder = list;

	        Order order = new Order();
	        Collections.sort(listOrder, order);

	        return listOrder;
	}

	@Override
	public boolean generatePDFReports() {
		 boolean test = false;

	        try (PDDocument document = new PDDocument()) {

	            PDPage my_page = new PDPage();
	            document.addPage(my_page);

	            PDPageContentStream contentStream = new PDPageContentStream(document, my_page);

	            contentStream.beginText();
	            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
	            contentStream.newLineAtOffset(25, 700);
	            contentStream.setLeading(14.5f);

	            List<Order> listDelivered = filterOrders("delivered");
	            List<Order> listCancelled = filterOrders("cancelled");

	            String line = "-------------------------------------";

	            String heading = "No.of Delivered      " + listDelivered.size();
	            String heading2 = "No.of Cancelled      " + listCancelled.size();

	            contentStream.showText(line);
	            contentStream.newLine();
	            contentStream.showText(heading);
	            contentStream.newLine();
	            contentStream.showText(line);
	            contentStream.newLine();
	            contentStream.showText(heading2);

	            contentStream.endText();

	            contentStream.close();
	            document.save(new File("C:\\Users\\dani_\\Downloads\\orderReport.pdf"));
	            document.close();

	            test = true;

	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        // TODO Auto-generated method stub
	        return test;
		
	}

	@Override
	public Map<String, List<Order>> createOrderMapByUser() {
		
		List<Order> list=new ArrayList<>();
		 
		String rootPath = "C:\\Users\\dani_\\Downloads\\Mid Term 17-Jun-22-20220617\\CoreJavaTest-main\\CaseStudy_CoreJava\\";
		String fileName = "Order.csv";
		
		File f = new File(rootPath+fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line="";
			
			br.readLine(); // consume first line and ignore
			line = br.readLine();
			
			while((line = br.readLine())!=null)
			{
				
				String[] values = line.split(",");
				
				Order o = new Order();
					
				o.setUserName(values[0]);
				o.setCategory(values[1]);
				o.setOrderCost(values[2]);
				o.setDate(values[3]);
				o.setAction(values[4]);
				list.add(o);
					
				
			}// while loop
				
			
			
		}
		
		
		catch (Exception e) {
			
			System.out.println(e);
		}
		
		Map<String, List<Order>> toReturn = new HashMap<>();
        List<Order> listOrder = new ArrayList<>();
        List<String> getUsernames = new ArrayList<>();

        for(Order order:list) {
            if(!getUsernames.contains(order.getUserName()))
            {
                getUsernames.add(order.getUserName());
            }
        }

        for(String username : getUsernames)
        {
         for (Order order : list)
         {
             if(order.getUserName().equals(username))
             {
                 listOrder.add(order);
             }
         }
         toReturn.put(username, listOrder);
        }
        
        return toReturn;
		
		
		
	}
	

}//end of main

import java.util.*;


class Customer
{
	private int id;
	private String name;
	private String mobileno;
	private static int counter = 2000;



	Customer(String name,String mobileno)
	{
		id = ++counter;
		this.name = name;
		this.mobileno = mobileno;
	}

	public static int getcustomercount()
	{
		return counter - 2000;
	}

	public  int getcustomerId()
	{
		return this.id;
	}
	public void setmobileno(String mobileno)
	{
		this.mobileno = mobileno;
		
	}

	public String getmobileno()
	{
		return this.mobileno;
	}

	public String getcustomername()
	{
		return this.name;
	}
}


		
class PremiumCustomer extends Customer
{

	private int points;

	PremiumCustomer(String name, String mobileno)
	{

		super(name,mobileno);
		this.points = 0;
	}

	public void setpoints(int points)
	{
		this.points = points;

	}

	public int getpoints()
	{
		return this.points;

	}

}


//----------------------------------------------------------------------------------------------------------

class Product    
{
	private int id;
	private String name;
	private int qty;
	private int price;
	private static int counter = 1000; 

  private static HashMap<String,Product> products= new HashMap<String ,Product>();
	
	Product(String name, int qty, int price)
	{
		this.name = name;
		this.qty = qty;
		this.price = price;
    this.id = ++counter;
    products.put(this.name.toLowerCase(),this);

   }

   public static Product getProductRef(String prod)
   {
     return products.get(prod.toLowerCase());
   }
	 public static HashMap<String,Product> getAllProductRef()
   {
     return products;
   }
	 
	 public static void showProducts()
	 {
		  System.out.println("\n---------------------------------------------------------");
			System.out.println("-------------------------MENU-------------------------------");
			System.out.println("-----------------------------------------------------------\n");
			System.out.println("Options    items		price_per_qty    Available qty\n");;
			int c=1;
			for(Product p:products.values())
			{
				System.out.printf("	%d	 %s		%d        %d\n",c++,p.getName(),p.getPrice(),p.getQuantity());
			}
	 }

	public static int getcount()
	{
		return counter - 1000;
	}


	public int getQuantity()
	{
		return this.qty;
	}
	public String  getName()
	{
		return this.name;
	}

	public int getPrice()
	{
		return this.price;
	}

	public void setQuantity(int qty)

	{
			this.qty = qty;
	}

  public void setPrice(int price)
	{
			this.price = price;
	}
	

}
//----------------------------------------------------------------------------------------------------------

class Sale
{
	private Product product=null;
	private int qty;
	private int amount;
	private Customer customer=null;
	private PremiumCustomer pcustomer;

	private static  HashMap<Integer,ArrayList<ArrayList<Object>>> sale = new HashMap<>();

		
	Sale(Product product, int qty, Customer customer)
	{
      this.product = product;
			this.qty = qty;
			this.customer = customer;
			ArrayList<Object> ob= new ArrayList<>();
			ArrayList<ArrayList<Object>> ar= new ArrayList<>();
			ob.add(product.getName());
			ob.add(qty);
			ob.add(product.getPrice());
			ob.add(qty*product.getPrice());

			product.setQuantity(product.getQuantity() - this.qty);

			if(!sale.containsKey(customer.getcustomerId())){
				ar.add(ob);
			sale.put(customer.getcustomerId(),ar);
			}
			else
			{
				ar=sale.get(customer.getcustomerId());
				ar.add(ob);
				sale.put(customer.getcustomerId(),ar);
			}


	}
  Sale(Product product, int qty, PremiumCustomer pcustomer)
	{
		  this.product = product;
			this.qty = qty;
			this.pcustomer = pcustomer;
			ArrayList<Object> ob= new ArrayList<>();
			ArrayList<ArrayList<Object>> ar= new ArrayList<>();
			ob.add(product.getName());
			ob.add(qty);
			ob.add(product.getPrice());
			ob.add(qty*product.getPrice());
			product.setQuantity(product.getQuantity() - this.qty);

			if(!sale.containsKey(pcustomer.getcustomerId())){
				ar.add(ob);
			sale.put(pcustomer.getcustomerId(),ar);
			}
			else
			{
				ar=sale.get(pcustomer.getcustomerId());
				ar.add(ob);
				sale.put(pcustomer.getcustomerId(),ar);
			}
	}


	public String getcustomerdetails()
	{
    if(this.customer==null)
		return pcustomer.getcustomername() + " - " + pcustomer.getmobileno();

    return this.customer.getcustomername() + " - " + this.customer.getmobileno();

	}

	public static void make_sale(Customer customer,PremiumCustomer pcustomer)
	{
		int amount =0;
		ArrayList<ArrayList<Object>> arr= new ArrayList<>();

		if(customer==null)
			arr=sale.get(pcustomer.getcustomerId());
		else
			arr=sale.get(customer.getcustomerId());

		System.out.println("\n--------------------------------------------");
		System.out.println("------------------BILL------------------------");
		System.out.println("--------------------------------------------\n\n");
		System.out.println("***** ABC STORE *****\n");
		if(customer!=null)
		{
		System.out.println("Customer name : "+customer.getcustomername());
		System.out.println("Mobile No : "+customer.getmobileno());
		}
		else
		{
		System.out.println("Customer name : "+pcustomer.getcustomername());
		System.out.println("Mobile No : "+pcustomer.getmobileno());
		System.out.println("$Premium customer\n\n");
		}
		System.out.println("Item      Qty      Price\n");
		System.out.println("----------------------------");

		for(ArrayList<Object> ar:arr)
		{
			amount=amount + (Integer)(ar.get(3));
			for(Object o :ar)
			{
				System.out.print(o+"      ");
			}
			System.out.println();
			
		}
		System.out.println("--------------------------");
		System.out.println("                  Total : "+amount);

		if(customer==null)
		{ 
			int point = amount/100;
			
			pcustomer.setpoints(pcustomer.getpoints() + point);
		  System.out.println(" Total Points: "+(pcustomer.getpoints()));	
		}
	}

}

//----------------------------------------------------------------------------------------------------------

class Driver
{
	public static void main(String args[])throws Exception
	{
	 int pc=0;
    Scanner get = new Scanner(System.in);
    
		Product P1 = new Product("Wheat",500,75);
    Product P2 = new Product("Rice",1000,65);
    Product P3 = new Product("Salt",500,15);
    Product P4 = new Product("Sugar",500,35);
    Product P5 = new Product("Milk",500,40);
    
    boolean flag = true;

		 Customer c1 = null;
		 PremiumCustomer p1 = null;

    while(flag)
    { 
      System.out.println("      Choice \n");
      System.out.println("Owner     -> 1");
      System.out.println("Customer  -> 2");
      System.out.println("Exit      -> 3");
      Thread.sleep(2000);
      System.out.println("\nEnter the option : ");
      int opt=get.nextInt();

      if(opt==1){
				boolean badmin=true;
        while(badmin){
            System.out.println("\n-------------------------------------------------------------");
            System.out.println("-----------------------------MENU------------------------------");
            System.out.println("---------------------------------------------------------------\n");
            System.out.println("    Options       Actions\n");;
            System.out.println("      1.          Add items");
            System.out.println("      2.          Update price");
						System.out.println("      3.          Add qty");
						System.out.println("      4.          Show Products ");
            System.out.println("      5.          Exit");
            Thread.sleep(2000);

            System.out.println("\nEnter the option : ");
            int ownOpt=get.nextInt();

            switch(ownOpt)
            {
              case 1:{
								System.out.print("\nEnter the product name : ");
								String pname = get.next();

								System.out.print("\nEnter qty : ");
								int qty = get.nextInt();

								System.out.print("\nEnter price per qty  :  ");
								int price = get.nextInt();
								
                new Product(pname,qty,price);
                break;
							}
              
              case 2:{
                System.out.print("\nEnter the product name : ");
								String pname = get.next();

								Product prod=Product.getProductRef(pname);
								System.out.print("\nEnter the new price :  ");
								int price = get.nextInt();
								prod.setPrice(price);
								break;
							}

							case 3:{
								System.out.print("\nEnter the product name");
								String pname = get.next();

								Product prod=Product.getProductRef(pname);
								System.out.print("\nEnter qty to add :  ");

								int qty = get.nextInt();
								prod.setQuantity(prod.getQuantity()+qty);
								break;

								}
							
							case 4:
							 Product.showProducts();
							 Thread.sleep(4000);
							 break;

							case 5:
							badmin=false;
							 break;

							default :
							 System.out.println("\nEnter valid option ... \n");
				 }
      }
    }
    else if(opt==2){
			System.out.print("Enter customer name : ");
			String cname  = get.next();
			System.out.println("Enter mobile no : ");
			String cmob = get.next();
			System.out.println("Are you a premium customer ? (1/0)");
		
			pc = get.nextInt();
			if(pc==0)
			    c1 = new Customer(cname, cmob);
			else
				 p1 = new PremiumCustomer(cname, cmob);

			HashMap<String , Product> pmap=Product.getAllProductRef();
			System.out.println("\n---------------------------------------------------------");
			System.out.println("-------------------------MENU-------------------------------");
			System.out.println("-----------------------------------------------------------\n");
			System.out.println("Options    items		price_per_qty    Available qty\n");;
			int c=1;
			for(Product p:pmap.values())
			{
				System.out.printf("	%d	 %s		%d        %d\n",c++,p.getName(),p.getPrice(),p.getQuantity());
			}
			System.out.println("\nEnter makebill -> to finish shopping ... ");
			Thread.sleep(5000);

			

			while(true)
			{
				 System.out.print("\nEnter item name : ") ;
				 String item = get.next();
				 
				 System.out.print("\nEnter quantity : ") ;
				 int qty =get.nextInt() ;
				 if(pc==0)
				 new Sale(Product.getProductRef(item),qty,c1);
				 else
				  new Sale(Product.getProductRef(item),qty,p1);


				 System.out.println("Do you want to continue shopping\n\n   yes->1 \n\n   goto bill ->0 \n\n   exit -1");
				 int yes = get.nextInt();
				 if(yes==0)
				 { 
					if(pc==1)
					 Sale.make_sale(null, p1);
					 else
					 Sale.make_sale(c1, null);
					 
					 System.out.println("Happy shopping ... :)");
					 Thread.sleep(4000);
					 //flag=false;
					 break;
					 
				 }
				 else if(yes ==-1){
				 break;}
				 else 
				  continue;

			}
    }
		else
		{
			System.out.print("Program ended .... ");
			flag=false;
		}
    }


	
	}
	
}
		
	
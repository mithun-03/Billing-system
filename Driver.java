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
class Product    
{
	private int id;
	private String name;
	private int qty;
	private int price;
	private static int counter = 1000; 
	
	Product(String name, int qty, int price)
	{
		this.id = ++counter;
		this.name = name;
		this.qty = qty;
		this.price = price;

   }

	public static int getcount()
	{
		return counter - 1000;
	}


	public int getQuantity()
	{
		return this.qty;
	}

	public int getPrice()
	{
		return this.price;
	}


	public void setQuantity(int qty)

	{
			this.qty = qty;
	}
	

}

class Sale
{
	private Product product;
	private int qty;
	private int amount;
	private Customer customer;
	private PremiumCustomer pcustomer;

		
	Sale(Product product, int qty, Customer customer, PremiumCustomer pcustomer)
	{

			this.product = product;
			this.qty = qty;
			this.customer = customer;
			this.pcustomer = pcustomer;

	}

	public String getcustomerdetails()
	{
		return pcustomer.getcustomername() + " - " + pcustomer.getmobileno();

	}

	public int make_sale()
	{
		this.amount = this.qty * product.getPrice();

		int point = this.amount/100;
		product.setQuantity(product.getQuantity() - this.qty);
		pcustomer.setpoints(pcustomer.getpoints() + point);
		return this.amount;
	}

}

class Driver
{
	public static void main(String args[])
	{
		

		Product P1 = new Product("Wheat",500,75);


		//Customer c1 = new Customer("Ramesh", "9898989898");
		PremiumCustomer c1 = new PremiumCustomer("Ramesh", "9898989898");

		Sale S1 = new Sale(P1, 3, null,c1);
		System.out.println(S1.make_sale());
		System.out.println(P1.getQuantity());
		System.out.println(S1.getcustomerdetails());
		System.out.println(c1.getpoints());

		/*
		System.out.println(Product.getcount());
		System.out.println(P1.getcount());
		Product P2 = new Product("Rice",1000,250);
		System.out.println(Product.getcount());
		System.out.println(P1.getcount());
		System.out.println(P2.getcount());*/
	}
}
		
	
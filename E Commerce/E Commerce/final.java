import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 class finalllll{
    public static Connection c;
    static Scanner sc=new Scanner(System.in);
    public static PreparedStatement pst;
    public static Statement st;
    public static CallableStatement cst;
    static int c_id;
    public static Queue<order> pq=new ArrayDeque<>();
    static HashMap<Integer,cart> hm=new HashMap<>();
    public static Stack<food_product> fp=new Stack<>();
    public static Stack<electronic_product> ep=new Stack<>();
    public static Stack<clothing_product> cp=new Stack<>();
    static String Name;
    public static void main(String[] args) throws Exception {
        try {
                    String jdbcUrl = "jdbc:mysql://localhost:3306/e_commerce";
                    String username = "root";
                    String password = "";
                    Class.forName("com.mysql.cj.jdbc.Driver");
            
                    c = DriverManager.getConnection(jdbcUrl, username, password);
                      if(c!=null){
                          System.out.println("\n Welcome To Our E-Commerce Platform ");
                      }
                      else{
                          System.out.println("Connection failed");
                      }
                      int ch=0;
                      st=c.createStatement();
                      ResultSet rss=st.executeQuery("select * from food_product");

                  while (rss.next()) {
                     String n1=  rss.getString(2);
                     double p=   rss.getDouble(3);
                     int s= rss.getInt(4);
                     fp.add(new food_product(rss.getInt(1), n1,  p, s))  ;
                }

               ResultSet rss1=st.executeQuery("select * from clothing_product");
                while (rss1.next()) {
                    String n1=  rss1.getString(2);
                     double p=   rss1.getDouble(3);
                     int s= rss1.getInt(4);
                     int siz=rss1.getInt(5);
                     cp.add(new clothing_product(rss1.getInt(1), n1, p, s,siz))  ;
                } 
                
              ResultSet  rss2=st.executeQuery("select * from electronic_product");
                while (rss2.next()) {
                    String n1=  rss2.getString(2);
                    double p=   rss2.getDouble(3); 
                    int s= rss2.getInt(4);
                    String bb=rss2.getString(5);
                    ep.add(new electronic_product(rss2.getInt(1), n1, p, s,bb))  ;
                } 

        while (ch!=3) {
            
            System.out.println("\n1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter Your Choice Here ---> ");
            try {
                ch=sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter valid input!!");
                sc.nextLine();
                continue;
            }
            switch (ch) {
                case 1:
                    admin();
                    break;

                case 2:
                    customer();
                    break;

                case 3:
                            System.out.print("Exiting");
                            for (int i = 0; i < 3; i++) {
                                System.out.print(".");
                                Thread.sleep(1000);
                            }
                            System.out.println();
                            System.out.println("Thank You For Visiting Our Platform");
                break;
            
                default:
                    break;
            }
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
	 public static void admin() throws Exception{
            int c = 0;
            while (c != 3) {
                System.out.println("\n1. Create Account As Admin");
                System.out.println("2. Login As Admin");
                System.out.println("3. Exit");
                System.out.print("Enter Your Choice Here ---> ");
                try {
                    c=sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Enter valid input!!");
                    sc.nextLine();
                    continue;
                }
                switch (c) {
                    case 1:
                        AdminSignUp();
                        break;
                    case 2:
                        if (AdminSignIn()) {
                            int ch=0;
                            while (ch!=8) {
                                
                             System.out.println("\n1. View Products");
                             System.out.println("2. Add Products in Electronics"); 
                             System.out.println("3. Add Products in Clothing");          
                             System.out.println("4. Add Products in Food and Beverage");          
                             System.out.println("5. Modify Product Details");               
                             System.out.println("6. Delete Product");                
                             System.out.println("7. Log Out");                    
                             System.out.println("8. Exit");                  
                             System.out.print("Enter Your Choice Here ---> ");
                             try {
                                ch=sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Enter valid input!!");
                                sc.nextLine();
                                continue;
                            }
                            switch (ch) {
                                case 1 :
                                        DisplayAllProducts();

                                        
                                    break;
                             case 2 :
                                        AddElectronicProduct();
                                    
                                    break;
                             case 3 :
                                         AddClothingProduct();

                                    break;
                             case 4 :
                                         AddFoodProduct();
                                    break;
                             case 5 :
                                          ModifyProduct();
                                    break;
                             case 6 :
                                         DeleteProduct();

                                    break;

                                case 7 : 

                                System.out.print("Log Outing");
                                for(int i=0;i<3;i++){
                                    System.out.print(".");
                                    Thread.sleep(1000);
                                }
                                System.out.println();
                                c=3;
                                ch=8;
                                break;
                             case 8:
                             System.out.print("Exiting");
                             for(int i=0;i<3;i++){
                                 System.out.print(".");
                                 Thread.sleep(1000);
                             }
                             System.out.println();
                                 break;
                             
                            
                                default:
                                System.out.println("Invalid Choice");
                                    break;
                            }
                        }
                    }
                        break;

                        case  3 :
                        System.out.print("Exiting");
                        for(int i=0;i<3;i++){
                            System.out.print(".");
                            Thread.sleep(1000);
                        }
                        System.out.println();
                        System.out.println("Exit"); 
                                                break;
                    default :
                    System.out.println("Invalid Choice");
                    break;
                }}
        }

        public static void AdminSignUp() throws Exception{
            try {
                String email = "";
                System.out.println();
                boolean f=true;
                 while (f) {
            System.out.print("\nEnter Email : ");
            email = sc.next();
            String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                st=c.createStatement();
                String s="select * from admin";
                ResultSet rs=st.executeQuery(s);
                int fl=0;
                while (rs.next()) {
                    if(rs.getString(2).equals(email)){
                        System.out.println("Email already exists");
                        fl=1;
                        break;
                        
                    }
                    
                }
    
                if(fl==0){
                    f=false;
                }
            } else {
                System.out.println("   Enter Valid Email");
            }
        }
    
    
        String psdw;
        while (true) {
            System.out.print("Set Password : ");
            psdw = sc.next();
    
        if(psdw.length()>=8){
               break;
        }
        else{
            System.out.println("Please Enter Eight Digit Password Or High Number Of Digit");
        }
    }
        
        try {
            pst=c.prepareStatement("insert into admin(email,password) values(?,?)");
            pst.setString(1, email);
            pst.setString(2, psdw);
            int r=pst.executeUpdate();
            if(r > 0){
                System.out.println("Account Created Successfully...");
            }
            else{
                System.out.println("Account Not Created");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }

        
        public static boolean AdminSignIn() throws Exception{
            try {
                llAdmin l=new llAdmin();

                st=c.createStatement();
                ResultSet rs=st.executeQuery("select * from admin");
                while (rs.next()) {
                   l.add(new Admin(rs.getString(2), rs.getString(3)));
                }
    
                boolean flag=true;
                while (flag) {
                    System.out.print("\nEnter the email Id : ");
                    String email1=sc.next();
                 
                       if(l.containMail(email1)){
    
                        boolean fl=true;
                        while (fl) {
                            System.out.print("Enter the password : ");
                            String pass=sc.next();
                            sc.nextLine();
                            if(l.MatchPassword(email1, pass)){
                            fl=false;
                            flag=false;
                            return true;
                        }
                        else{
                            System.out.println("Password invalid");
                        }
                        }
                        
                    }
                    else{
                        System.out.println("Invalid Email");
                    }
                }
            
            } catch (Exception e) {
                    System.out.println(e.getMessage());
            }
            return false;

         }

            public static void DisplayAllProducts() throws Exception{
                                        try {
                                            int ch=0;
                        boolean flag=true;
                        while(flag){

                            System.out.println("\n1. View Cloth Products");
                            System.out.println("2. View Electronic Products");
                            System.out.println("3. View Food Products");
                            System.out.println("4. Exit");
                            System.out.print("Enter Your Choice Here ---> ");
                            try {
                                ch=sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Enter valid input!!");
                                sc.nextLine();
                                continue;
                            }
                            switch (ch) {
                                case 1:

                                System.out.println("\nCloth Products");
                                try {
                                    st=c.createStatement();
                                    ResultSet rs=st.executeQuery("select * from  clothing_product");
System.out.println("+---------------+----------------+----------+-----------------+----------------+");
System.out.println("| Product Name  | Price          | Stock    | Size            | Date of add    |");
System.out.println("+---------------+----------------+----------+-----------------+----------------+");
                        
                                    while (rs.next()) {
                                         String n1=  rs.getString(2);
                                         double p=   rs.getDouble(3);
                                         int s= rs.getInt(4);
                                         int n5=  rs.getInt(5);
                                         String n4=  rs.getDate(6).toString();
                                         
                            System.out.printf("| %-13s | %-14g | %-8d | %-13d   |  %-7s    |\n",
                            n1,p,s,n5,  n4);
System.out.println("+---------------+----------------+----------+-----------------+----------------+");
                
                                        
                                    }}catch(Exception e){
                                        System.out.println(e.getMessage());
                                    }

                                    break;
                                    case 2:
                                                try {
                                                    
                                                    System.out.println("\nElectronic Products");
                  ResultSet  rs=st.executeQuery("select * from electronic_product");
System.out.println("+---------------+----------------+----------+-----------------+----------------+");
System.out.println("| Product Name  | Price          | Stock    | Brand           | Date of add    |");
System.out.println("+---------------+----------------+----------+-----------------+----------------+");
        
                    while (rs.next()) {
                         String n1=  rs.getString(2);
                         double p=   rs.getDouble(3);
                         int s= rs.getInt(4);
                         String n5=  rs.getString(5);
                         String n4=  rs.getDate(6).toString();
                         
            System.out.printf("| %-13s | %-14g | %-8d | %-13s   |  %-7s    |\n",
            n1,p,s,n5,n4);
System.out.println("+---------------+----------------+----------+-----------------+----------------+");

                        
                    }
                                                } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                }
                                    break;
                                    case 3:
                                                    try {
                                                        System.out.println("\nFood Products");
              ResultSet      rs=st.executeQuery("select * from food_product");
System.out.println("+--------------------+----------------+----------+-----------------+----------------+");
System.out.println("| Product Name       | Price          | Stock    | Expire Date     | Date of add    |");
System.out.println("+--------------------+----------------+----------+-----------------+----------------+");
        
                    while (rs.next()) {
                         String n1=  rs.getString(2);
                         double p=   rs.getDouble(3);
                         int s= rs.getInt(4);
                         String n5=  rs.getDate(5).toString();
                         String n4=  rs.getDate(6).toString();

                         
            System.out.printf("| %-18s | %-14g | %-8d | %-13s   |  %-7s    |\n",
            n1, p,s,n5, n4);
System.out.println("+--------------------+----------------+----------+-----------------+----------------+");

                        
                    }
                                                    } catch (Exception e) {
                                                            System.out.println(e.getMessage());
                                                    }
                                    break;
                            case 4:
                                            System.out.print("Exiting");
                                            for (int i = 0; i < 3; i++) {
                                                System.out.print(".");
                                                Thread.sleep(1000);
                                            }
                                            System.out.println();
                                            flag=false;
                                    break;
                            
                                default:
                                            System.out.println("Invalid choice");
                                    break;
                            }
                            
                        }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }

            }
			public static void AddClothingProduct() throws Exception{
                try {
                sc.nextLine();
                System.out.print("\nEnter the Product Name : ");
                String name=sc.nextLine();
                System.out.print("Enter The Price : ");
                double price=sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter Quantity : ");
                int quantity=sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Size : ");
                int size=sc.nextInt();
                sc.nextLine();
                
                
                    CallableStatement cst=c.prepareCall("call insertcloth(?,?,?,?)");
                    cst.setString(1, name);
                    cst.setDouble(2, price);
                    cst.setInt(3, quantity);
                    cst.setInt(4, size);
                    int r=cst.executeUpdate();
                    if(r > 0){
                        
                        System.out.println("Product Added Successfully...");
                        st=c.createStatement();
                        ResultSet rss=st.executeQuery("select * from food_product where product_name= ' "+name+" ' ");
                
                        while (rss.next()) {
                            cp.add(new clothing_product(rss.getInt(1), name, price, quantity, size));
                }
                    }
                    else{
                        System.out.println("Product Not Added");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public static void AddElectronicProduct() throws Exception{
                try {
                sc.nextLine();
                System.out.print("\nEnter Product Name : ");
                String name=sc.nextLine();
               
                System.out.print("Enter Price : ");
                double price=sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter Quantity : ");
                int quantity=sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Brand : ");
                String brand=sc.nextLine();
                


                
                    CallableStatement cst=c.prepareCall("call insertelectronic(?,?,?,?)");
                    cst.setString(1, name);
                    cst.setDouble(2, price);
                    cst.setInt(3, quantity);
                    cst.setString(4, brand);
                    int r=cst.executeUpdate();
                    if(r > 0){
                        System.out.println("Product Added Successfully...");
                        st=c.createStatement();
                        ResultSet rss=st.executeQuery("select * from electronic_product where product_name= ' "+name+" ' ");
                
                        while (rss.next()) {
                            ep.add(new electronic_product(rss.getInt(1), name, price, quantity, brand));
                }
                    }
                    else{
                        System.out.println("Product Not Added");
                    }
                }
                 catch (Exception e) {
                        System.out.println(e.getMessage());
                }
            }

            public static void AddFoodProduct() throws Exception{
                try {
                sc.nextLine();
                System.out.print("\nEnter Product Name : ");
                String name=sc.nextLine();
                
                System.out.print("Enter Price : ");
                double price=sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter Quantity : ");
                int quantity=sc.nextInt();
                sc.nextLine();
               

                
                    CallableStatement cst=c.prepareCall("call insertfood(?,?,?)");
                    cst.setString(1, name);
                    cst.setDouble(2, price);
                    cst.setInt(3, quantity);
                    int r=cst.executeUpdate();
                    if(r > 0){
                        System.out.println("Product Added Successfully...");
                        st=c.createStatement();
                        ResultSet rss=st.executeQuery("select * from food_product where product_name= ' "+name+" ' ");
                
                        while (rss.next()) {
                            fp.add(new food_product(rss.getInt(1), name, price, quantity));
                }
                    }
                    else{
                        System.out.println("Product Not Added");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
			            public static void ModifyProduct() throws Exception{

                            try  {
                                int c1=0;
                    while (c1!=4) {
                        System.out.println("\n1. Update Food Product");
                        System.out.println("2. Update Electronic Product");
                        System.out.println("3. Update Cloth Product");
                        System.out.println("4. Exit");
                        System.out.print("Enter Your Choice Here ---> ");
                        try {
                            c1=sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Enter valid input!!");
                            sc.nextLine();
                            continue;
                        }
                        switch (c1) {
                                 case 1:
                                 System.out.println("\nFood Product");
ResultSet      rs=st.executeQuery("select * from food_product");
System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
System.out.println("| Product Id | Product Name       | Price          | Stock    | Expire Date     | Date of add    |");
System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");

while (rs.next()) {
int d=rs.getInt(1);
String n1=  rs.getString(2);
double p=   rs.getDouble(3);
int s= rs.getInt(4);
String n5=  rs.getDate(5).toString();
String n4=  rs.getDate(6).toString();

System.out.printf("|%-11d | %-18s | %-14g | %-8d | %-13s   |  %-7s    |\n",
d,n1, p,s,n5, n4);
System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");


                    }
                    try {
                                 int f1=0;
                                 System.out.print("\nEnter Product Id : ");
                                 int id=sc.nextInt();

                                 
                                    rs=st.executeQuery("select * from food_product");
                                     while (rs.next()) {
                                    if(rs.getInt(1)==id){
                                        f1=1;
                                    }
                                 }
                                 
                                
                                     if(f1==1){
                                            boolean flll=true;
                                            while (flll) {
                                            System.out.println("1. Update only Product Name");
                                            System.out.println("2. Update only Price");
                                            System.out.println("3. Update only Quantity");
                                            System.out.println("4. Update All");
                                            int  choice=sc.nextInt();
                                            switch (choice) {
                                                case 1 :
                                                System.out.print("Enter the Product Name : ");
                                                sc.nextLine();
                                                String name=sc.nextLine();  
                                                String sql="update food_product set product_name = ? where product_id=?";
                                                pst=c.prepareStatement(sql);
                                                pst.setString(1,name);
                                                pst.setInt(2, id);
                                                int r=pst.executeUpdate();
                                                if(r > 0){

                                                    System.out.println("Updated successfull");
                                                    for (food_product x : fp) {
                                                        if(x.getProduct_id()==id){
                                                           
                                                            x.setProduct_name(name);
                                                        }
                                                    }
                                                }
                                                else{
                                                    System.out.println("Unsuccessfull");
                                                }
                                                flll=false;

                                                    break;
                                                case 2 :
                                                System.out.print("Enter Price : ");
                                                double price=sc.nextDouble();
                                                sc.nextLine();
                                                sql="update food_product set price = ? where product_id=?";
                                                pst=c.prepareStatement(sql);
                                                pst.setDouble(1,price);
                                                pst.setInt(2, id);
                                                 r=pst.executeUpdate();
                                                if(r > 0){

                                                    System.out.println("Updated successfull");
                                                    for (food_product x : fp) {
                                                        if(x.getProduct_id()==id){
                                                           
                                                            x.setPrice(price);
                                                        }
                                                    }
                                                }
                                                else{
                                                    System.out.println("Unsuccessfull");
                                                } 
                                                flll=false;

                                                    break;
                                                case 3 :
                                                System.out.print("Enter Quantity : ");
                                                int quantity=sc.nextInt();
                                                sc.nextLine();
                                                sql="update food_product set stock_quantity = ? where product_id=?";
                                                pst=c.prepareStatement(sql);
                                                pst.setInt(1,quantity);
                                                pst.setInt(2, id);
                                                 r=pst.executeUpdate();
                                                if(r > 0){

                                                    System.out.println("Updated successfull");
                                                    for (food_product x : fp) {
                                                        if(x.getProduct_id()==id){
                                                           
                                                            x.setStock_quantity(quantity);
                                                        }
                                                    }
                                                }
                                                else{
                                                    System.out.println("Unsuccessfull");
                                                }
                                                flll=false;

                                                    break;
                                                case 4 :
                                                try {
                                                System.out.print("Enter the Product Name : ");
                                                sc.nextLine();
                                                 name=sc.nextLine();
                                                
                                                System.out.print("Enter Price : ");
                                                 price=sc.nextDouble();
                                                sc.nextLine();
                                                System.out.print("Enter Quantity : ");
                                                 quantity=sc.nextInt();
                                                sc.nextLine();
                                                
            
                                                    CallableStatement cst=c.prepareCall("{call updatefood(?,?,?,?)}");
                                                    cst.setInt(1, id);
                                                    cst.setDouble(2, price);
                                                    cst.setInt(3, quantity);
                                                    cst.setString(4, name);
                                                     r=cst.executeUpdate();
                                                    
                                                    if(r > 0){
                                                        System.out.println("Product Updated Successfully...");
                                                        for (food_product x : fp) {
                                                            if(x.getProduct_id()==id){
                                                               
                                                                x.setPrice(price);
                                                                x.setProduct_name(name);
                                                                x.setStock_quantity(quantity);
                                                            }
                                                        }
                                                    }
                                                    else{
                                                        System.out.println("Product Not Updated");
                                                    }
                                                } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                }
                                                flll=false;

                                                    break;
                                                default:
                                                System.out.println("enter valid choice");
                                                    break;
                                            }
                                        }
                                    
                                 }

                                 else{
                                    System.out.println("Product id does not exist");
                                 }} catch (Exception e) {
                                    System.out.println(e.getMessage());
                            }
                                
                                 break;
                                 case 2:
                                 System.out.println("\nElectronic Products");
  rs=st.executeQuery("select * from electronic_product");
System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
System.out.println("| Product Id  |Product Name  | Price          | Stock    | Brand           | Date of add    |");
System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");

while (rs.next()) {
    int d=rs.getInt(1);
 String n1=  rs.getString(2);
 double p=   rs.getDouble(3);
 int s= rs.getInt(4);
 String n5=  rs.getString(5);
 String n4=  rs.getDate(6).toString();
 
System.out.printf("| %-12d| %-12s | %-14g | %-8d | %-13s   |  %-7s    |\n",
d,n1,p,s,n5, n4);
System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
}

                                 int f2=0;
                                 
                                 try {
                                    System.out.print("\nEnter Product Id : ");
                                     int id2=sc.nextInt();
                                     rs=st.executeQuery("select * from electronic_product");
                                 while (rs.next()) {
                                    if(rs.getInt(1)==id2){
                                        f2=1;
                                    }
                                 }

                                
                                 
                                 if(f2==1){
                                    boolean flll=true;
                                    while (flll) {
                                        System.out.println("1. Update only Product Name");
                                        System.out.println("2. Update only Price");
                                        System.out.println("3. Update only Quantity");
                                        System.out.println("4. Update only Brand");
                                        System.out.println("5. Update All");
                                    int  choice=sc.nextInt();
                                    switch (choice) {
                                        case 1 :
                                        System.out.print("Enter the Product Name : ");
                                        sc.nextLine();
                                        String name=sc.nextLine();  
                                        String sql="update electronic_product set product_name = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setString(1,name);
                                        pst.setInt(2, id2);
                                        int r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (electronic_product x : ep) {
                                                if(x.getProduct_id()==id2){
                                                    x.setProduct_name(name);
                                                   
                                                }
                                            }
                                        }

                                        else{
                                            System.out.println("Unsuccessfull");
                                        }
                                                flll=false;
                                            break;
                                        case 2 :
                                        System.out.print("Enter Price : ");
                                        double price=sc.nextDouble();
                                        sc.nextLine();
                                        sql="update electronic_product set price = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setDouble(1,price);
                                        pst.setInt(2, id2);
                                         r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (electronic_product x : ep) {
                                                if(x.getProduct_id()==id2){
                                                    x.setPrice(price);
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Unsuccessfull");
                                        } 
                                        flll=false;

                                            break;
                                        case 3 :
                                        System.out.print("Enter Quantity : ");
                                        int quantity=sc.nextInt();
                                        sc.nextLine();
                                        sql="update electronic_product set stock_quantity = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setInt(1,quantity);
                                        pst.setInt(2, id2);
                                         r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (electronic_product x : ep) {
                                                if(x.getProduct_id()==id2){
                                                    x.setStock_quantity(quantity);
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Unsuccessfull");
                                        }
                                        flll=false;

                                            break;
                                            case 4 :
                                            System.out.print("Enter Brand : ");
                                            sc.nextLine();
                                             String brand=sc.nextLine();
                                             sql="update electronic_product set brand = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setString(1,brand);
                                        pst.setInt(2, id2);
                                         r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (electronic_product x : ep) {
                                                if(x.getProduct_id()==id2){
                                                    x.setBrand(brand);
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Unsuccessfull");
                                        }
                                        flll=false;
                                            break;
                                        case 5 :
                                        
                                   System.out.print("Enter Product Name : ");
                                   sc.nextLine();
                                    name=sc.nextLine();
                                  
                                  System.out.print("Enter Price : ");
                                   price=sc.nextDouble();
                                  sc.nextLine();
                                  System.out.print("Enter Quantity : ");
                                   quantity=sc.nextInt();
                                  sc.nextLine();
                                  System.out.print("Enter Brand : ");
                                   brand=sc.nextLine();
                                  
                                        
    
                                  CallableStatement cst=c.prepareCall("call updateElectronicProduct(?,?,?,?,?)");
                                  cst.setInt(1, id2);
                                  cst.setDouble(2, price);
                                  cst.setInt(3, quantity);
                                  cst.setString(4, brand);
                                  cst.setString(5, name);
                                             r=cst.executeUpdate();
                                             if(r > 0){

                                                System.out.println("Updated successfull");
                                                for (electronic_product x : ep) {
                                                    if(x.getProduct_id()==id2){
                                                        x.setPrice(price);
                                                        x.setProduct_name(name);
                                                        x.setStock_quantity(quantity);
                                                        x.setBrand(brand);
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Unsuccessfull");
                                            }
                                            flll=false;

                                            break;
                                            default :
                                            System.out.println("enter valid choice");
                                            break ;
                                            
                                    }
                                }
                                 
                                }

                                 else{
                                    System.out.println("Product id does not exit");
                                 } 
                                
                                
                                
                                
                                
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                 }
                                 break;
                                 case 3:
                                 System.out.println("\nClothing Product");
                            st=c.createStatement();
                             rs=st.executeQuery("select * from  clothing_product");
System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
System.out.println("| Product Id | Product Name  | Price          | Stock    | Size            | Date of add    |");
System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                
                            while (rs.next()) {
                                int d=rs.getInt(1);
                                 String n1=  rs.getString(2);
                                 double p=   rs.getDouble(3);
                                 int s= rs.getInt(4);
                                 int n5=  rs.getInt(5);
                                 String n4=  rs.getDate(6).toString();
                                 
                    System.out.printf("|%-11d | %-13s | %-14g | %-8d | %-13d   |  %-7s    |\n",
                    d,n1,p,s,n5, n4);
System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
        
                                
                            }
                            try {
                                 int f3=0;
                                 System.out.print("\nEnter Product Id : ");
                                 int id3=sc.nextInt();
                                    rs=st.executeQuery("select * from clothing_product");
                                     while (rs.next()) {
                                    if(rs.getInt(1)==id3){
                                        f3=1;
                                    }
                                 }
                                 

                                 if(f3==1){
                                    boolean flll=true;
                                    while (flll) {
                                        System.out.println("1. Update only Product Name");
                                        System.out.println("2. Update only Price");
                                        System.out.println("3. Update only Quantity");
                                        System.out.println("4. Update All");
                                    int  choice=sc.nextInt();
                                    switch (choice) {
                                        case 1 :
                                        System.out.print("Enter the Product Name : ");
                                        sc.nextLine();
                                        String name=sc.nextLine();  
                                        String sql="update clothing_product set product_name = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setString(1,name);
                                        pst.setInt(2, id3);
                                        int r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (clothing_product x : cp) {
                                                if(x.getProduct_id()==id3){
                                                    x.setProduct_name(name);
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Unsuccessfull");
                                        }
                                                flll=false;
                                            break;
                                        case 2 :
                                        System.out.print("Enter Price : ");
                                        double price=sc.nextDouble();
                                        sc.nextLine();
                                        sql="update clothing_product set price = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setDouble(1,price);
                                        pst.setInt(2, id3);
                                         r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (clothing_product x : cp) {
                                                if(x.getProduct_id()==id3){
                                                    x.setPrice(price);
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Unsuccessfull");
                                        } 
                                        flll=false;

                                            break;
                                        case 3 :
                                        System.out.print("Enter Quantity : ");
                                        int quantity=sc.nextInt();
                                        sc.nextLine();
                                        sql="update clothing_product set stock_quantity = ? where product_id=?";
                                        pst=c.prepareStatement(sql);
                                        pst.setInt(1,quantity);
                                        pst.setInt(2, id3);
                                         r=pst.executeUpdate();
                                        if(r > 0){

                                            System.out.println("Updated successfull");
                                            for (clothing_product x : cp) {
                                                if(x.getProduct_id()==id3){
                                                    x.setStock_quantity(quantity);
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("Unsuccessfull");
                                        }
                                        flll=false;

                                            break;
                                        case 4 :
                                        
                                        System.out.print("Enter Product Name : ");
                                         name=sc.next();
                                        sc.nextLine();
                                        
                                        System.out.print("Enter Price : ");
                                         price=sc.nextDouble();
                                        sc.nextLine();
                                        System.out.print("Enter Quantity : ");
                                         quantity=sc.nextInt();
                                        sc.nextLine();
                                        System.out.print("Enter Size : ");
                                        int size=sc.nextInt();
                                        sc.nextLine();
                                       
                        
    
                                            CallableStatement cst=c.prepareCall("call updatecloth(?,?,?,?,?)");
                                            cst.setString(1, name);
                                            cst.setDouble(2, price);
                                            cst.setInt(3, quantity);
                                            cst.setInt(4, size);
                                            cst.setInt(5, id3);
                                             r=cst.executeUpdate();
                                             if(r > 0){

                                                System.out.println("Updated successfull");
                                                for (clothing_product x : cp) {
                                                    if(x.getProduct_id()==id3){
                                                        x.setPrice(price);
                                                        x.setProduct_name(name);
                                                        x.setStock_quantity(quantity);
                                                        x.setSize(size);
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Unsuccessfull");
                                            }
                                            flll=false;

                                            break;
                                            default :
                                            System.out.println("enter valid choice");
                                            break ;
                                            
                                    }
                                }
                                 }

                                 else{
                                    System.out.println("Product id does not exist");
                                 }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                 }
                                 break;
                                 case 4:
                                System.out.print("Exiting");
                                for (int i = 0; i < 3; i++) {
                                    System.out.print(".");
                                    Thread.sleep(1000);
                                }
                                System.out.println();
                                 break;
                        
                            default:
                            System.out.println("Invalid Choice");
                                break;
                        }
                    }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
            }
 public static void DeleteProduct() throws Exception
        {
                        try {
                            int c1=0;
                            while (c1!=4) {
                                System.out.println("\n1. Delete Food Product");
                                System.out.println("2. Delete Electronic Product");
                                System.out.println("3. Delete Cloth Product");
                                System.out.println("4. Exit");
                                System.out.print("Enter Your Choice Here ---> ");
                                try {
                                    c1=sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Enter valid input!!");
                                    sc.nextLine();
                                    continue;
                                }
                                switch (c1) {
                                         case 1:
                                         ResultSet      rs1=st.executeQuery("select * from food_product");
        System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
        System.out.println("| Product Id | Product Name       | Price          | Stock    | Expire Date     | Date of add    |");
        System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
        
        while (rs1.next()) {
            int d=rs1.getInt(1);
        String n1=  rs1.getString(2);
        double p=   rs1.getDouble(3);
        int s= rs1.getInt(4);
        String n5=  rs1.getDate(5).toString();
        String n4=  rs1.getDate(6).toString();
        
        System.out.printf("|%-11d | %-18s | %-14g | %-8d | %-13s   |  %-7s    |\n",
        d,n1, p,s,n5, n4);
        System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
        
        
        }
                                         int f1=0;
                                         try {
                                            
                                         System.out.print("\nEnter Product Id : ");
                                         int id=sc.nextInt();
                                         ResultSet rs=st.executeQuery("select * from food_product");
                                            while (rs.next()) {
                                                if(rs.getInt(1)==id){
                                                    f1=1;
                                                }
                                             }
                                        
        
                                         if(f1==1){
        
                                            try {
                                                pst=c.prepareStatement("delete from food_product where product_id=?");
                                                pst.setInt(1, id);
                                                int r=pst.executeUpdate();
                                                if(r>0){
                                                    System.out.println("Product Deleted...");
                                                }
                                                else{
                                                    System.out.println("Product Not deleted");
                                                }
                                            } catch (Exception e) {
        
                                                System.out.println(e.getMessage());
        
                                            }
                                         }
        
                                         else{
                                            System.out.println("Product id does not exist");
                                         }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());   
                                     }
                                         break;
                                         case 2:
                                        ResultSet rs=st.executeQuery("select * from electronic_product");
                                         System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                         System.out.println("| Product Id  |Product Name  | Price          | Stock    | Brand           | Date of add    |");
                                         System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                         
                                         while (rs.next()) {
                                             int d=rs.getInt(1);
                                          String n1=  rs.getString(2);
                                          double p=   rs.getDouble(3);
                                          int s= rs.getInt(4);
                                          String n5=  rs.getString(5);
                                          String n4=  rs.getDate(6).toString();
                                          
                                         System.out.printf("| %-12d| %-12s | %-14g | %-8d | %-13s   |  %-7s    |\n",
                                         d,n1, p,s,n5, n4);
                                         System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                         
                                         
                                         }
                                         try {
                                         int f2=0;
                                         System.out.print("\nEnter Product Id : ");
                                         int id2=sc.nextInt();
                                          rs=st.executeQuery("select * from electronic_product");
                                         
                                            while (rs.next()) {
                                                if(rs.getInt(1)==id2){
                                                    f2=1;
                                                }
                                             }
                                         
                                         if(f2==1){
                                            try {
                                                pst=c.prepareStatement("delete from electronic_product where product_id=?");
                                                pst.setInt(1, id2);
                                                int r=pst.executeUpdate();
                                                if(r>0){
                                                    System.out.println("Product Deleted...");
                                                }
                                                else{
                                                    System.out.println("Product Not deleted");
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                         }
        
                                         else{
                                            System.out.println("Product id does not exist");
                                         }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                          }
        
                                         break;
                                         case 3:
                                         rs=st.executeQuery("select * from  clothing_product");
                                         System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                         System.out.println("| Product Id | Product Name  | Price          | Stock    | Size            | Date of add    |");
                                         System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                                        
                                                                     while (rs.next()) {
                                                                         int d=rs.getInt(1);
                                                                          String n1=  rs.getString(2);
                                                                          double p=   rs.getDouble(3);
                                                                          int s= rs.getInt(4);
                                                                          int n5=  rs.getInt(5);
                                                                          String n4=  rs.getDate(6).toString();
                                                                          
                                                             System.out.printf("|%-11d | %-13s | %-14g | %-8d | %-13d   |  %-7s    |\n",
                                                             d,n1,p,s,n5, n4);
                                         System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                                 
                                                                         
                                                                     }
                                         int f3=0;
                                         
                                          try {
                                            System.out.print("\nEnter Product Id : ");
                                         int id3=sc.nextInt();
                                          rs=st.executeQuery("select * from clothing_product");
                                            while (rs.next()) {
                                                if(rs.getInt(1)==id3){
                                                    f3=1;
                                                }
                                             }
                                         
        
                                         if(f3==1){
        
                                            try {
                                                pst=c.prepareStatement("delete from clothing_product where product_id=?");
                                            pst.setInt(1, id3);
                                            int r=pst.executeUpdate();
                                            if(r>0){
                                                System.out.println("Product Deleted...");
                                            }
                                            else{
                                                System.out.println("Product Not deleted");
                                            }
                                            } catch (Exception e) {
                                               System.out.println(e.getMessage());
                                            }
                                         }
        
                                         else{
                                            System.out.println("Product id does not exist");
                                         }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                           }
                                         break;
                                         case 4:
                                         System.out.print("Exiting");
                                         for(int i=0;i<3;i++){
                                             System.out.print(".");
                                             Thread.sleep(1000);
                                         }
                                         System.out.println();
                                         break;
                                
                                    default:
                                    System.out.println("Invalid Choice");
                                        break;
                                }
                            } 
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    
        }

     //		Admin's Portion end
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//		Customer's Portion Start
	
	public static void customer() throws Exception{
            int c = 0;
            while (c != 3) {
                System.out.println("\n1. Create Account As Customer");
                System.out.println("2. Login as Customer");
                System.out.println("3. Exit");
                System.out.print("Enter Your Choice Here ---> ");
                try {
                    c=sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Enter valid input!!");
                    sc.nextLine();
                    continue;
                }
                switch (c) {
                    case 1:
                        customerSignUp();
                        break;
                    case 2:
                        if (customerSignIn()) {
                            int ch=0;
                            while (ch!=6) {
                                
                                System.out.println("\n1. Buy Products");
                                System.out.println("2. Add To Cart Product");
                                System.out.println("3. View Shopping Cart");
                                System.out.println("4. Check Your Order History");
                                System.out.println("5. Log Out");
                                System.out.println("6. Exit");
                                System.out.print("Enter Your Choice Here ---> ");
                                try {
                                    ch=sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Enter valid input!!");
                                    sc.nextLine();
                                    continue;
                                }
                                
                                                                switch (ch) {
                                                                        case 1:
                                                                        BuyProduct(c_id);
                                                                        break;
                                                                        case 2:
                                                                        AddToCart(c_id);
                                                                        break;
                                                                        case 3:
                                                                        viewShoppingCart( c_id);
                                                                        break;
                                                                        case 4:
                                                                        CheckOrderHistory( c_id);
                                                                        break;

                                                                        case 5 :
                                                                        System.out.print("Log Outing");
                                                                        for(int i=0;i<3;i++){
                                                                            System.out.print(".");
                                                                            Thread.sleep(1000);
                                                                        }
                                                                        System.out.println();
                                                                        c=3;
                                                                        ch=6;
                                                                        break;
                                                                        case 6:
                                                                        System.out.print("Exiting");
                                                                        for(int i=0;i<3;i++){
                                                                            System.out.print(".");
                                                                            Thread.sleep(1000);
                                                                        }
                                                                        System.out.println();
                                                                        System.out.println("Thank you");
                                                                        break;
                                                                        default:
                                                                        System.out.println("Invalid choice");
                                                                        break;
                                                                }
                        }
                    }
                        break;
                    case 3 :
                    System.out.print("Exiting");
                    for(int i=0;i<3;i++){
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                    System.out.println();
                    break;    
                    default :
                    break;
                }}

        }
        public static void verified() throws Exception {
            Scanner sc = new Scanner(System.in);
            boolean f = true;
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println();
            System.out.println("Sending OTP To Your Device....");
            while (f) {
        
                Thread.sleep(2000);
                System.out.println();
                int r = (int) (Math.random() * 10000);
                System.out.println("OTP Received On Your Phone : " + r);
                System.out.println();
                System.out.print("Enter Your OTP : ");
                int r1=0;
                try {
                     r1 = sc.nextInt();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (r1 == r) {
                    System.out.println();
                    System.out.println("-------------------- VERIFIED ----------------------");
                    System.out.println();
                    f = false;
                } else {
                    System.out.println();
                    System.out.println("----------------------------------------------------");
                    System.out.println();
                    System.out.println("Entered OTP Is Wrong!");
                    System.out.println();
                    System.out.println("Re-sending the OTP...");
                }
            }
        }


        public static void customerSignUp() throws Exception{
            sc.nextLine();
            try {
                System.out.print("\nEnter Full Name : ");
                String name=sc.nextLine();
                String email = "";
                System.out.println();
                boolean f=true;
                 while (f) {
            System.out.print("Enter Email : ");
            email = sc.nextLine();
            String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                st=c.createStatement();
                String s="select * from customer";
                ResultSet rs=st.executeQuery(s);
                int fl=0;
                while (rs.next()) {
                    if(rs.getString(3).equals(email)){
                        System.out.println("Email already exists");
                        fl=1;
                        break;
                        
                    }
                    
                }
    
                if(fl==0){
                    f=false;
                }
            } else {
                System.out.println("  Enter Valid Email");
            }
        }
    
    
        String psdw;
        while (true) {
            System.out.print("Set Password : ");
            psdw = sc.next();
    
        if(psdw.length()>=8){
               break;
        }
        else{
            System.out.println("Please Enter Eight Digit Password Or High Number Of Digit");
        }
    }
        System.out.println();
        System.out.print("Enter Your mobile Number : ");
        String mobile = "";
        boolean flag=true;
    
            while (flag) {
                int k=0;
            mobile = sc.next();
            if (mobile.charAt(0) != '0' && mobile.length() == 10) {
                for (int i = 0; i < 10; i++) {
                    if (mobile.charAt(i) >= '0' && mobile.charAt(i) <= '9') {
                       k=k+1;
                    } 
                }
            } else {
                System.out.println();
                System.out.println("----------------------------------------------------");
                System.out.println();
                System.out.println("Please Enter 10-Digit Number And Number Is ");
                System.out.println("Not Starting With 0");
                System.out.println();
                System.out.print("Re-enter Your Mobile Number : ");
            }

            if(k==10){
                flag=false;
            }
            else {
                System.out.println();
                System.out.println("----------------------------------------------------");

                System.out.println("Please Enter Valid Number Don't Write Alphabets");

                System.out.print("Re-Enter Your Mobile Number: ");
            }
        }
        try {
            verified();
        } catch (Exception e) {
            System.out.println(e);
        }
    
        try {
    
            pst=c.prepareStatement("insert into customer(name,email,password,mobile_no) values(?,?,?,?)");
        pst.setString(1, name);    
        pst.setString(2, email);
        pst.setString(3, psdw);
        pst.setString(4, mobile);
        int r=pst.executeUpdate();
        if(r > 0){
            System.out.println("Account Created Successfully...");
        }
        else{
            System.out.println("Account Not Created");
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            } catch (Exception e) {
                    System.out.println(e.getMessage());
            }

           
        }
        
        public static boolean customerSignIn() throws Exception{
            try {
                llCustomer ll=new llCustomer();
                st=c.createStatement();
                ResultSet rs=st.executeQuery("select * from customer");
                while (rs.next()) {
                   ll.add(new customer(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));
                }
    
                    
    
                boolean flag=true;
                while (flag) {
                    System.out.print("\nEnter Email Id : ");
                    String email1=sc.next();
                 
                       if(ll.containMail(email1)){
    
                        boolean fl=true;
                        while (fl) {
                           
                            System.out.print("Enter Password : ");
                            String pass=sc.next();
                            sc.nextLine();
                            c_id=ll.getId(email1);
                                Name=ll.getName(email1);
                            if(ll.MatchPassword(email1, pass)){
                                
    
                            fl=false;
                            flag=false;
                            return true;
                        }
                        else{
                            System.out.println("Invalid Password");
                        }
                        }
                        
                    }
                    else{
                        System.out.println("Invalid Email");
                    }
                }
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
                return false;
        }
public static void BuyProduct(int id) throws Exception {
                try {
                    boolean flag=true;
                    int ch=0;
                    while (flag) {
                        c.setAutoCommit(false);
        
                        System.out.println("\n1. Buy Food Product");
                        System.out.println("2. Buy Electronic Product");
                        System.out.println("3. Buy Clothing Product");
                        System.out.println("4. Exit");
                        System.out.print("Enter Your Choice Here ---> ");
                        try {
                            ch=sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Enter valid input!!");
                            sc.nextLine();
                            continue;
                        }
                        try {
                            
                            switch (ch) {
                                case 1:
                                try {
                                    System.out.println("\nFood Product");
        ResultSet     rs=st.executeQuery("select * from food_product");
        System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
        System.out.println("| Product Id | Product Name       | Price          | Stock    | Expire Date     | Date of add    |");
        System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
        
        while (rs.next()) {
        int d=rs.getInt(1);
        String n1=  rs.getString(2);
        double p=   rs.getDouble(3);
        int s= rs.getInt(4);
        String n5=  rs.getDate(5).toString();
        String n4=  rs.getDate(6).toString();
        
        System.out.printf("|%-11d | %-18s | %-14g | %-8d | %-13s   |  %-7s    |\n",
        d,n1, p,s,n5, n4);
        System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
        
        
                            }
        
                                } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                }                              
                                ResultSet rs=st.executeQuery("select * from customer");
                                        int f=0;
                                        while (rs.next()) {
                                            if(rs.getInt(1)==id){
                                                    f=1;
                                            }
                                        }
                                        if(f==1){
                                            int ff=0;
                                                System.out.print("Enter Product Id : ");
                                                int pid=sc.nextInt();
                                                pst=c.prepareStatement("select * from food_product where product_id=?");
                                                pst.setInt(1, pid);
                                                 rs=pst.executeQuery();
                                                 while (rs.next()) {
                                                    if(rs.getInt(1)==pid){
                                                        ff=1;
                                                        String pname=rs.getString(2);
                                                        double price=rs.getDouble(3);
                                                        System.out.print("Enter Quantity : ");
                                                        int q=sc.nextInt();
                                                        if(q <= rs.getInt(4)){
                                                            price=price*q;
                                                          CallableStatement cst=c.prepareCall("{call insertorder(?,?,?,?,?)}");
                                                        cst.setInt(1, id);
                                                        cst.setInt(2, pid);
                                                        cst.setString(3, pname);
                                                        cst.setDouble(4, price);
                                                        cst.setInt(5, q);
                                                        int r=cst.executeUpdate();
                                                        while (true) {
                                                            System.out.println("Are You Sure You Want To Buy Product");
                                                            System.out.println("Type Yes For Confirm");
                                                            System.out.println("Type No For Cancel");
                                                            String w=sc.next();
                                                            if(w.equalsIgnoreCase("yes")){
                                                                if(r > 0){
                                                                    System.out.println("Order Confirmed");
                                                                    printBill(id, pname, pid);
                                                                }
                                                                else{
                                                                    System.out.println("Order Not Confirmed");
                                                                }
                                                                
                                                                String sql="update food_product set stock_quantity=? where product_id=?";
                                                                pst=c.prepareStatement(sql);
                                                                int stock=rs.getInt(4);
                                                                stock=stock-q;
                                                                pst.setInt(1, stock);
                                                                pst.setInt(2, pid);
                                                                pst.executeUpdate();
                                                                for (food_product x : fp) {
                                                                    if(x.getProduct_id()==pid){
                                                                        x.setStock_quantity(stock);
                                                                    }
                                                                }
                                                                c.commit();
                                                                break;
                                                            }
                                                            else if(w.equalsIgnoreCase("no")){
                                                                c.rollback();
                                                                System.out.println("Order Canceled");
                                                                break;
                                                            }
                                                            else{
                                                                System.out.println("Enter Valid Choice");
                                                            }
                                                        }
                                                    
                                                    }
                                                    else{
                                                        System.out.println("Insufficient Quantity");
                                                    }
                                                       
                    
            
                                                    }
                                                 }
                                                 if(ff==0){
                                                    System.out.println("Product Id Does Not Exists");
                                                 }
            
                                            }
                                        else{
                                            System.out.println("Customer ID Does Not Exists");
                                        }
                                    break; 
                                
                                case 2:
                                try {
                                                            
                                    System.out.println("\nElectronic Products");
                                    rs=st.executeQuery("select * from electronic_product");
                                    System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                    System.out.println("| Product Id  |Product Name  | Price          | Stock    | Brand           | Date of add    |");
                                    System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                    
                                    while (rs.next()) {
                                        int d=rs.getInt(1);
                                     String n1=  rs.getString(2);
                                     double p=   rs.getDouble(3);
                                     int s= rs.getInt(4);
                                     String n5=  rs.getString(5);
                                     String n4=  rs.getDate(6).toString();
                                     
                                    System.out.printf("| %-12d| %-12s | %-14g | %-8d | %-13s   |  %-7s    |\n",
                                    d,n1, p,s,n5, n4);
                                    System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                    
                                    
                                    }
                                } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                }
                                 rs=st.executeQuery("select * from customer");
                                 f=0;
                                while (rs.next()) {
                                    if(rs.getInt(1)==id){
                                            f=1;
                                    }
                                }
                                if(f==1){
                                    int ff=0;
                                        System.out.print("Enter Product Id : ");
                                        int pid=sc.nextInt();
                                        pst=c.prepareStatement("select * from electronic_product where product_id=?");
                                        pst.setInt(1, pid);
            
                                         rs=pst.executeQuery();
                                         while (rs.next()) {
                                            if(rs.getInt(1)==pid){
                                                ff=1;
                                                String pname=rs.getString(2);
                                                double price=rs.getDouble(3);
                                                System.out.print("Enter Quantity : ");
                                                int q=sc.nextInt();
                                                if(q <= rs.getInt(4)){
                                                    price=price*q;
                                                    CallableStatement cst=c.prepareCall("{call insertorder(?,?,?,?,?)}");
                                                cst.setInt(1, id);
                                                cst.setInt(2, pid);
                                                cst.setString(3, pname);
                                                cst.setDouble(4, price);
                                                cst.setInt(5, q);
                                                int r=cst.executeUpdate();
                                                while (true) {
                                                    System.out.println("Are You Sure You Want To Buy Product");
                                                    System.out.println("Type Yes For Confirm");
                                                    System.out.println("Yype No For Cancel");
                                                    String w=sc.next();
                                                    if(w.equalsIgnoreCase("yes")){
                                                        if(r > 0){
                                                            System.out.println("Order Confirmed");
                                                                    printBill(id, pname, pid);                                                }
                                                        else{
                                                            System.out.println("Order Not Confirmed");
                                                        }
                                                       String sql="update electronic_product set stock_quantity=? where product_id=?";
                                                        pst=c.prepareCall(sql);
                                                        int stock=rs.getInt(4);
                                                        stock=stock-q;
                                                        pst.setInt(1, stock);
                                                        pst.setInt(2, pid);
                                                        pst.executeUpdate();
                                                        for (electronic_product x : ep) {
                                                            if(x.getProduct_id()==pid){
                                                                x.setStock_quantity(stock);
                                                            }
                                                        }
                                                        c.commit();
                                                        break;
                                                    }
                                                    else if(w.equalsIgnoreCase("no")){
                                                        c.rollback();
                                                        System.out.println("Order Canceled");
                                                        break;
                                                    }
                                                    else{
                                                        System.out.println("Enter Valid Choice");
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Insufficient Quantity");
                                            }
                                               
            
                                            }
                                         }
                                         if(ff==0){
                                            System.out.println("Product Id Does Not Exists");
                                         }
            
                                    }
                                else{
                                    System.out.println("Customer ID Does Not Exists");
                                }
                            
                                    break; 
                                case 3:
                                try {
                                    System.out.println("\nClothing Product");
                                    st=c.createStatement();
                                     rs=st.executeQuery("select * from  clothing_product");
                                     System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                     System.out.println("| Product Id | Product Name  | Price          | Stock    | Size            | Date of add    |");
                                     System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                                    
                                                                 while (rs.next()) {
                                                                     int d=rs.getInt(1);
                                                                      String n1=  rs.getString(2);
                                                                      double p=   rs.getDouble(3);
                                                                      int s= rs.getInt(4);
                                                                      int n5=  rs.getInt(5);
                                                                      String n4=  rs.getDate(6).toString();
                                                                      
                                                         System.out.printf("|%-11d | %-13s | %-14g | %-8d | %-13d   |  %-7s    |\n",
                                                         d,n1,p,s,n5, n4);
                                     System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                             
                                                                     
                                                                 }
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                    }
        
                                 rs=st.executeQuery("select * from customer");
                                 f=0;
                                while (rs.next()) {
                                    if(rs.getInt(1)==id){
                                            f=1;
                                    }
                                }
                                if(f==1){
                                    int ff=0;
                                        System.out.print("Enter Product Id : ");
                                        int pid=sc.nextInt();
                                        pst=c.prepareStatement("select * from clothing_product where product_id=?");
                                        pst.setInt(1, pid);
            
                                         rs=pst.executeQuery();
                                         while (rs.next()) {
                                            if(rs.getInt(1)==pid){
                                                ff=1;
                                                String pname=rs.getString(2);
                                                double price=rs.getDouble(3);
                                                System.out.print("Enter Quantity : ");
                                                int q=sc.nextInt();
                                                if(q <= rs.getInt(4)){
                                                    price=price*q;
        
                                                    CallableStatement cst=c.prepareCall("{call insertorder(?,?,?,?,?)}");
                                                cst.setInt(1, id);
                                                cst.setInt(2, pid);
                                                cst.setString(3, pname);
                                                cst.setDouble(4, price);
                                                cst.setInt(5, q);
                                                int r=cst.executeUpdate();
                                                while (true) {
                                                    System.out.println("You Want To Confirm To Buy Product");
                                                    System.out.println("Type Yes For Confirm");
                                                    System.out.println("Type No For Cancel ");
                                                    String w=sc.next();
                                                    if(w.equalsIgnoreCase("yes")){
                                                        if(r > 0){
                                                            System.out.println("Order Confirmed");
                                                                    printBill(id, pname, pid);
                                                        }
                                                        else{
                                                            System.out.println("Order Not Confirmed");
                                                        }
                                                        String sql="update clothing_product set stock_quantity=? where product_id=?";
                                                        pst=c.prepareCall(sql);
                                                        int stock=rs.getInt(4);
                                                        System.out.println(stock);
                                                        stock=stock-q;
                                                        pst.setInt(1, stock);
                                                        pst.setInt(2, pid);
                                                        pst.executeUpdate();
                                                        for (clothing_product x : cp) {
                                                            if(x.getProduct_id()==pid){
                                                                x.setStock_quantity(stock);
                                                            }
                                                        }
                                                        c.commit();
                                                        break;
                                                    }
                                                    else if(w.equalsIgnoreCase("no")){
                                                        c.rollback();
                                                        System.out.println("Order Canceled");
                                                        break;
                                                    }
                                                    else{
                                                        System.out.println("Enter Valid Choice");
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Insufficient Quantity");
                                            }
                                               
            
                                            }
                                         }
                                         if(ff==0){
                                            System.out.println("Product Id Does Not Exists");
                                         }
            
                                    }
                                else{
                                    System.out.println("Customer ID Does Not Exists");
                                }
                                break;
                                case 4 :
                                System.out.print("Exiting");
                                for(int i=0;i<3;i++){
                                    System.out.print(".");
                                    Thread.sleep(1000);
                                }
                                System.out.println();
                                                        flag=false;
                                    
                                    break;
                            
                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        
                    }
        
                } catch (Exception e) {
                    System.out.println(e.getMessage());  
                              }
           
        }

        public static void AddToCart(int id) throws Exception{
            try {
                boolean flag=true;
                int ch=0;
                while (flag) {
                    System.out.println("\n1. Add to Cart Food Product");
                    System.out.println("2. Add to Cart Electronic Product");
                    System.out.println("3. Add to Cart Clothing Product");
                    System.out.println("4. Exit");
                    System.out.print("Enter Your Choice Here ---> ");
                    try {
                        ch=sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Enter valid input!!");
                        sc.nextLine();
                        continue;
                    }
                    try {
                        switch (ch) {
                            case 1:
                            try {
                           ResultSet     rs=st.executeQuery("select * from food_product");
    System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
    System.out.println("| Product Id | Product Name       | Price          | Stock    | Expire Date     | Date of add    |");
    System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
    
    while (rs.next()) {
    int d=rs.getInt(1);
    String n1=  rs.getString(2);
    double p=   rs.getDouble(3);
    int s= rs.getInt(4);
    String n5=  rs.getDate(5).toString();
    String n4=  rs.getDate(6).toString();
    
    System.out.printf("|%-11d | %-18s | %-14g | %-8d | %-13s   |  %-7s    |\n",
    d,n1, p,s,n5, n4);
    System.out.println("+------------+--------------------+----------------+----------+-----------------+----------------+");
    
    
                        }
    
                            } catch (Exception e) {
                                    System.out.println(e.getMessage());
                            }                              
                                    ResultSet rs=st.executeQuery("select * from customer");
                                    int f=0;
                                    while (rs.next()) {
                                        if(rs.getInt(1)==id){
                                                f=1;
                                        }
                                    }
                                    if(f==1){
                                        int ff=0;
                                            System.out.print("Enter Product Id : ");
                                            int pid=sc.nextInt();
                                            pst=c.prepareStatement("select * from food_product where product_id=?");
                                            pst.setInt(1, pid);
                                             rs=pst.executeQuery();
                                             while (rs.next()) {
                                                if(rs.getInt(1)==pid){
                                                    ff=1;
                                                   
                                                    System.out.print("Enter Quantity : ");
                                                    int q=sc.nextInt();
        
                                                    if(q <= rs.getInt(4)){
                                                        pst=c.prepareStatement("insert into cart(cust_id,product_id,Product_name,quantity,price) values(?,?,?,?,?)");
                                                         pst.setInt(1, id);
                                                         pst.setInt(2, pid);
                                                        pst.setString(3, rs.getString(2));
                                                            pst.setInt(4, q);
                                                            double price=rs.getDouble(3)*q;
    
                                                            pst.setDouble(5, price);
                                                      
                                                    int r=pst.executeUpdate();
                                                    if(r > 0){
                                                        System.out.println("Product Added To Cart...");
                                                    }
                                                    else{
                                                        System.out.println("Product Not Added To Cart");
                                                    }
                                                    }else{
                                                        System.out.println("Insuffucient Quantity");
                                                    }
                                                }
                                             }
                                             if(ff==0){
                                                System.out.println("Product Id Does Not Exists");
                                             }
        
                                        }
                                    else{
                                        System.out.println("Customer ID Does Not Exists");
                                    }
                                break; 
                            
                            case 2:
                            try {
                                                        
                                System.out.println("Electronic Products");
                                rs=st.executeQuery("select * from electronic_product");
                                System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                System.out.println("| Product Id  |Product Name  | Price          | Stock    | Brand           | Date of add    |");
                                System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                
                                while (rs.next()) {
                                    int d=rs.getInt(1);
                                 String n1=  rs.getString(2);
                                 double p=   rs.getDouble(3);
                                 int s= rs.getInt(4);
                                 String n5=  rs.getString(5);
                                 String n4=  rs.getDate(6).toString();
                                 
                                System.out.printf("| %-12d| %-12s | %-14g | %-8d | %-13s   |  %-7s    |\n",
                                d,n1, p,s,n5, n4);
                                System.out.println("+-------------+--------------+----------------+----------+-----------------+----------------+");
                                
                                
                                }
                            } catch (Exception e) {
                                    System.out.println(e.getMessage());
                            }             
                           
                         rs=st.executeQuery("select * from customer");
                             f=0;
                            while (rs.next()) {
                                if(rs.getInt(1)==id){
                                        f=1;
                                }
                            }
                            if(f==1){
                                int ff=0;
                                    System.out.print("\nEnter Product Id : ");
                                    int pid=sc.nextInt();
                                    pst=c.prepareStatement("select * from electronic_product where product_id=?");
                                    pst.setInt(1, pid);
                                     rs=pst.executeQuery();
                                     while (rs.next()) {
                                        if(rs.getInt(1)==pid){
                                            ff=1;
                                           
                                                                                       
                                            System.out.print("Enter Quantity : ");
                                            int q=sc.nextInt();
        
                                            if(q <= rs.getInt(4)){
                                                pst=c.prepareStatement("insert into cart(cust_id,product_id,Product_name,quantity,price) values(?,?,?,?,?)");
                                                 pst.setInt(1, id);
                                                 pst.setInt(2, pid);
                                                pst.setString(3, rs.getString(2));
                                                    pst.setInt(4, q);
                                                    double price=rs.getDouble(3)*q;
    
                                                    pst.setDouble(5, price);
                                              
                                                    
                                            int r=pst.executeUpdate();
                                            if(r > 0){
                                                System.out.println("Product Added To Cart");
                                            }
                                            else{
                                                System.out.println("Product Not Added To Cart");
                                            }
                                            }else{
                                                System.out.println("Insuffucient Quantity");
                                            }
        
                                        }
                                     }
                                     if(ff==0){
                                        System.out.println("Product Id Does Not Exists");
                                     }
        
                                }
                            else{
                                System.out.println("Customer ID Does Not Exists");
                            }     break; 
                            case 3:
                            try {
                                st=c.createStatement();
                                rs=st.executeQuery("select * from  clothing_product");
                                System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                System.out.println("| Product Id | Product Name  | Price          | Stock    | Size            | Date of add    |");
                                System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                               
                                                            while (rs.next()) {
                                                                int d=rs.getInt(1);
                                                                 String n1=  rs.getString(2);
                                                                 double p=   rs.getDouble(3);
                                                                 int s= rs.getInt(4);
                                                                 int n5=  rs.getInt(5);
                                                                 String n4=  rs.getDate(6).toString();
                                                                 
                                                    System.out.printf("|%-11d | %-13s | %-14g | %-8d | %-13d   |  %-7s    |\n",
                                                    d,n1,p,s,n5, n4);
                                System.out.println("+------------+---------------+----------------+----------+-----------------+----------------+");
                                        
                                                                
                                                            }
                               }catch(Exception e){
                                    System.out.println(e.getMessage());
                                }
                            
                           
                             rs=st.executeQuery("select * from customer");
                             f=0;
                            while (rs.next()) {
                                if(rs.getInt(1)==id){
                                        f=1;
                                }
                            }
                            if(f==1){
                                int ff=0;
                                    System.out.print("\nEnter product Id : ");
                                    int pid=sc.nextInt();
                                    pst=c.prepareStatement("select * from clothing_product where product_id=?");
                                    pst.setInt(1, pid);
                                     rs=pst.executeQuery();
                                     while (rs.next()) {
                                        if(rs.getInt(1)==pid){
                                            ff=1;
                                           
                                                                                      
                                            System.out.print("Enter Quantity : ");
                                            int q=sc.nextInt();
        
                                            if(q <= rs.getInt(4)){
                                                pst=c.prepareStatement("insert into cart(cust_id,product_id,Product_name,quantity,price) values(?,?,?,?,?)");
                                                 pst.setInt(1, id);
                                                 pst.setInt(2, pid);
                                                 pst.setString(3, rs.getString(2));
                                                 pst.setInt(4, q);
                                                 double price=rs.getDouble(3)*q;
    
                                                 pst.setDouble(5, price);
                                            int r=pst.executeUpdate();
                                            if(r > 0){
                                                System.out.println("Product Added To Cart...");
                                            }
                                            else{
                                                System.out.println("Product Not Added To Cart");
                                            }
                                            }else{
                                                System.out.println("Insuffucient Quantity");
                                            }
                                        }
                                     }
                                     if(ff==0){
                                        System.out.println("Product Id Does Not Exists");
                                     }
        
                                }
                            else{
                                System.out.println("Customer ID Does Not Exists");
                            }
                            break;
                            case 4 :
                            System.out.print("Exiting");
                            for(int i=0;i<3;i++){
                                System.out.print(".");
                                Thread.sleep(1000);
                            }
                            System.out.println();
                                                    flag=false;
                                
                                break;
                        
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                }
    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
           
        }
		
		public static void viewShoppingCart(int id) throws Exception{
            try {

                  boolean flag=true;
                  pst=c.prepareStatement("select * from  cart where cust_id=?");
                  pst.setInt(1, id);
                  ResultSet  rs=pst.executeQuery();
                              
                  while (rs.next()) {
                    int   d=rs.getInt(1);
                    int  d2=rs.getInt(3);
                    String  n1=rs.getString(4);
                    int  d3=rs.getInt(5);
                    double  nn=rs.getDouble(6);
                  
                    cart cc=new cart( id, d2, n1, nn, d3);
                    hm.put(d,cc);
            } 
                if(hm.isEmpty()){
                    System.out.println("Cart is Empty");
                }
               else {
                    while (flag) {
                        pst=c.prepareStatement("select * from  cart where cust_id=?");
                        pst.setInt(1, id);
                          rs=pst.executeQuery();
                        System.out.println("+------------+--------------------+---------------------+---------------------+----------+-----------------+");
                        System.out.println("| Cart Id    | Customer Id        | Product Id          | Product Name        | Stock    | Price           |");
                        System.out.println("+------------+--------------------+---------------------+---------------------+----------+-----------------+");
                          
                        while (rs.next()) {
                          int   d=rs.getInt(1);
                           int  d2=rs.getInt(3);
                           String  n1=rs.getString(4);
                           int  d3=rs.getInt(5);
                           double  nn=rs.getDouble(6);
                        
                        System.out.printf("|%-11d | %-18d | %-19d | %-19s | %-8d | %-13g   |\n",
                        d,id,d2,n1,d3,nn);
                        System.out.println("+------------+--------------------+---------------------+---------------------+----------+-----------------+");
                        
                        
                        
                        
                                    } 
                        
                        
                                    
                                        int ch=0;
                                        System.out.println("\n1. Buy All Cart Products");
                                        System.out.println("2. Buy Perticular Product From Cart");
                                        System.out.println("3. Remove Product From Cart ");
                                        System.out.println("4. Exit");
                                        System.out.print("Enter Your Choice Here ---> ");
                                        try {
                                            ch=sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Enter valid input!!");
                                            sc.nextLine();
                                            continue;
                                        }
                                        switch (ch) {
                                            case 1:
                                                        pst=c.prepareStatement("select * from cart where cust_id=?");
                                                        pst.setInt(1, id);
                                                        rs=pst.executeQuery();
                                                        while (rs.next()) {
                                                                    int pid=rs.getInt("product_id");
                                                                    cst=c.prepareCall("{call insertorder(?,?,?,?,?)}");
                                                                    cst.setInt(1, id);
                                                                    cst.setInt(2, rs.getInt(3));
                                                                    cst.setString(3, rs.getString(4));
                                                                    cst.setDouble(4, rs.getDouble(6));
                                                                    cst.setInt(5, rs.getInt(5));
                                                                    cst.executeUpdate();
                                                                    cartUpdate(pid, rs.getString(4), rs.getInt(5));
                                                                    printBill(id, rs.getString(4), pid);
                                                        }     
                        
                                                              pst=c.prepareStatement("delete from cart where cust_id=?");
                                                              pst.setInt(1, id);
                                                              pst.execute();
                                                              System.out.println("Successfull...");  
                                                              hm.clear();
                                                              flag=false;
                                                break;
                                            case 2 :
                           
                                                        while (true) {
                                                            System.out.print("\nEnter The Cart ID For Buy Product : ");
                                                            int cartId=sc.nextInt();
                                                            st=c.createStatement();
                                                            int f=0;
                                                            rs=st.executeQuery("select * from cart");
                                                            while (rs.next()) {
                                                                if(rs.getInt(1)==cartId){
                                                                    f=1;
                                                                }
                                                            }
                                                            if(f==1){
                                                                pst=c.prepareStatement("select * from cart where cart_id=?");
                                                                pst.setInt(1, cartId);
                                                                rs=pst.executeQuery();
                                                                int q=0;
                                                                int pid=0;
                                                                String pname="";
                                                                Double price=0.0;
                                                                while (rs.next()) {
                                                                    cst=c.prepareCall("{call insertorder(?,?,?,?,?)}");
                                                                    q=rs.getInt(5);
                                                                    pid=rs.getInt(3);
                                                                    pname=rs.getString(4);
                                                                    price=rs.getDouble(6);
                                                                    cst.setInt(1, id);
                                                                    cst.setInt(2, rs.getInt(3));
                                                                    cst.setString(3, rs.getString(4));
                                                                    cst.setDouble(4, rs.getDouble(6));
                                                                    cst.setInt(5, rs.getInt(5));
                                                                    cst.executeUpdate();
                                                                    cartUpdate(pid, pname, q);
                        
                                                              }
                                                             
                                                               
                                                              pst=c.prepareStatement("delete from cart where cart_id=?");
                                                              pst.setInt(1, cartId);
                                                              pst.execute();
                                                              System.out.println("Order Confirmed");
                                                              printBill(id, pname, pid);
                                                              hm.remove(cartId);
                                                                           
                                                                            if(hm.isEmpty()){
                                                                                System.out.println("Cart is Empty");
                                                                                flag=false;
                                                                                break;
                                                                            }
                                                                            else{
                                                                                System.out.println("You Want To Buy Any Product From Cart Type Yes For Buy Product Type No For Exit");
                                                                                String w=sc.next();
                                                                                if(w.equalsIgnoreCase("no")){
                                                                                    break;
                                                                                }
                                                                            }
                                                                   
                                                                
                                                            }
                                                            else{
                                                                System.out.println("Enter the valid Cart Id");
                                                                break;
                                                            }
                                                        }
                                                
                                                break;
                                            case 3:
                                            while (true) {
                                                System.out.print("Enter Cart ID For Delete Product : ");
                                                int cartId=sc.nextInt();
                                                st=c.createStatement();
                                                int f=0;
                                                rs=st.executeQuery("select * from cart");
                                                while (rs.next()) {
                                                    if(rs.getInt(1)==cartId){
                                                        f=1;
                                                    }
                                                }
                                                if(f==1){
                                                    pst=c.prepareStatement("delete from cart where cart_id=?");
                                                    pst.setInt(1, cartId);
                                                    
                                                    pst.execute();
                                                    hm.remove(cartId);
                                                    if(hm.isEmpty()){
                                                        System.out.println("Cart is Empty");
                                                        
                                                        flag=false;
                                                        break;
                                                    }
                                                    else{
                                                        System.out.println("You Want To Remove Any Product From Cart Type Yes For Remove Product Type No For Exit");
                                                     String w=sc.next();
                                                     if(w.equalsIgnoreCase("no")){
                                                         break;
                                                     }
                                                    }
                                                    
                                                }
                                                else{
                                                    System.out.println("Enter the valid Cart Id");
                                                    break;
                                                }
                                            }
                                    
                                                break;
                                            case 4:
                                                flag=false;
                                                break;
                                        
                                            default:
                                            System.out.println("Enter the valid choice");
                                                break;
                                        }
                                    }
                                        
                        
                                    }
                
            
        }catch (Exception e) {
               System.out.println(e.getMessage());
            }
           
        }   
  public static void CheckOrderHistory(int id) throws Exception{
           
          try {
                    String sql="{call displayOrderHistory(?)}";
                    CallableStatement cst=c.prepareCall(sql);
                    cst.setInt(1, id);
                    boolean hasResult=cst.execute();
                    String name="";
                    double totalprice=0;
                    if(hasResult){
                        ResultSet rs=cst.getResultSet();
                        while (rs.next()) {
                            totalprice=totalprice+rs.getDouble("price");
                            order oo=new order(rs.getInt("order_id"),id, rs.getInt("product_id"), rs.getString("name"), rs.getString("product_name"), rs.getInt("quantity"), rs.getDouble("price"), rs.getDate("date_of_buy").toString());
                            name=rs.getString("name");
                            pq.add(oo);
                        }
                            if(pq.isEmpty()){
                                    System.out.println("Empty Order History");
                            }
                            else{
                                        System.out.println(pq);
                                        boolean fl=true;
                                        int ch=0;
                                        while (fl) {
                                            System.out.println("1. Print Total Bill");
                                            System.out.println("2. Exit");
                                            try {
                                                ch=sc.nextInt();
                                            } catch (Exception e) {
                                                System.out.println("Enter valid input!!");
                                                sc.nextLine();
                                                continue;
                                            }
                                            switch (ch) {
                                                case 1 :
                                                   FileWriter fw=new FileWriter("TotalBillOf "+name+" .txt");
                                                    fw.write(pq.toString());
                                                    fw.write("\n ---------------------------------------------------------\n");
                                                    fw.write("Name        : " + name +"\n");
                                                    fw.write("Total Price : " + totalprice);
                                                    fw.close();
                                                    pq.clear();
                                                    fl=false;
                                                    break;
                                                case 2 :
                                                System.out.print("Exiting");
                                           for (int i = 0; i < 3; i++) {
                                           System.out.print(".");
                                           Thread.sleep(1000);
                            }
                            System.out.println();
                                                
                                                pq.clear();
                                                fl=false;
                                                break;    
                                            
                                                default:
                                                System.out.println("Invalid Choice");
                                                    break;
                                            }
                                        }
                                        
                            }

                    }
                    

          } catch (Exception e) {
                System.out.println(e.getMessage());
        }
}
public static void  cartUpdate(int pid,String pname,int stock) throws Exception{
        boolean f1=true;
        boolean f2=true;
    for (food_product x : fp) {

        if(x.getProduct_name().equals(pname) && x.getProduct_id()==pid){
           int sf=x.getStock_quantity();
            PreparedStatement pst1=c.prepareCall("update food_product set stock_quantity=? where product_id=?");
            sf=sf-stock;
            pst1.setInt(1, sf);
            pst1.setInt(2, pid);
            pst1.executeUpdate();
            f1=false;
            f2=false;
            x.setStock_quantity(sf);
        }
    }
    if(f1){
        for (clothing_product x : cp) {
            if(x.getProduct_name().equals(pname)  && x.getProduct_id()==pid){
              int  scc=x.getStock_quantity();
                PreparedStatement   pst2=c.prepareCall("update clothing_product set stock_quantity=? where product_id=?");
            f1=false;
            f2=false;
            scc=scc-stock;
            pst2.setInt(1, scc);
            pst2.setInt(2, pid);
            pst2.executeUpdate();
            x.setStock_quantity(scc);
            }
        }

        if(f2){
            for (electronic_product x : ep) {
                if(x.getProduct_name().equals(pname)  && x.getProduct_id()==pid){
                   int se=x.getStock_quantity();
                    PreparedStatement   pst3=c.prepareCall("update electronic_product set stock_quantity=? where product_id=?");
                  
                    se=se-stock;
                    pst3.setInt(1, se);
                    pst3.setInt(2, pid);
                    pst3.executeUpdate();
                    x.setStock_quantity(se);
                    f1=false;
            f2=false;
                }
            }
        }   
    }
}
     public static void printBill(int id,String pname,int pid) throws Exception{
                pst=c.prepareStatement("select * from orders where product_id= ? and product_name=? and cust_id=? order by order_id desc");
                pst.setInt(3, id);
                pst.setString(2, pname);
                pst.setInt(1, pid);
                ResultSet   rs1=pst.executeQuery();
                while (rs1.next()) {
                    order oo=new order(rs1.getInt(1), id, pid, Name, pname, rs1.getInt(6), rs1.getDouble(5), rs1.getDate("date_of_buy").toString());
                    FileWriter fw=new FileWriter(Name+" "+rs1.getInt(1)+".txt");
                    fw.write(oo.toString());
                    fw.close();
                    break;
                }
            }
 }

class customer{
    String email;
    String password;
    String mobile_no;
    int id;
    String name;
    public customer(int id,String name,String email, String password, String mobile_no) {
        this.id=id;
        this.email = email;
        this.password = password;
        this.mobile_no = mobile_no;
        this.name=name;
    }
    public String getEmail() {
        return email;
    }
   
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }   
    

}
class Admin{
    String email;
    String password;
    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}

class llAdmin{
    class node{
        node next;
        Admin data;
        public node(Admin data) {
            this.data = data;
            next=null;
        }
        
    }

    node first=null;

    void add(Admin data){
        node n=new node(data);
        if(first==null){
            first=n;
        }

        else{
            node temp=first;
            while (temp.next!=null) {
                    temp=temp.next;
            }
            temp.next=n;
        }
    }

        public boolean containMail(String Email){  
            if(first==null){
                System.out.println("Empty");
            }
            if(first.data.getEmail().equals(Email)){
                return true;
            }   
            else{
                node temp=first;
                while (!(temp.data.getEmail().equals(Email)) && temp.next!=null) {
                    temp=temp.next;
                }
                if(temp.data.getEmail().equals(Email)){
                    return true;
                }
                else if(temp.next==null){
                    if(temp.data.getEmail().equals(Email)){
                        return true;
                    }
                }
            }    
       return false;
}

        public boolean MatchPassword(String Email,String pass){
            if(first.data.getEmail().equals(Email)){
                if(first.data.getPassword().equals(pass)){

                    return true;

                }
            }   
            else{
                node temp=first;
                while (!(temp.data.getEmail().equals(Email)) && temp.next!=null) {
                    temp=temp.next;
                }
                if(temp.data.getEmail().equals(Email)){
                    if(temp.data.getPassword().equals(pass)){
                        return true;
                    }
                    
                }
                else if(temp.next==null){
                    if(temp.data.getEmail().equals(Email)){
                        if(temp.data.getEmail().equals(pass))
                        {
                            return true;
                        }
                        
                    }
                }
            }   
       return false;

    }

}
class llCustomer{

    class node{
        node next;
        
       customer data;
        public node(customer data) {
            this.data = data;
            next=null;
        }
        
    }

    node first=null;

    void add(customer data){
        node n=new node(data);
        if(first==null){
            first=n;
        }

        else{
            node temp=first;
            while (temp.next!=null) {
                    temp=temp.next;
            }
            temp.next=n;
        }
    }

        public boolean containMail(String Email){  
            if(first==null){
                System.out.println("Empty");
            }
            if(first.data.getEmail().equals(Email)){
                return true;
            }   
            else{
                node temp=first;
                while (!(temp.data.getEmail().equals(Email)) && temp.next!=null) {
                    temp=temp.next;
                }
                if(temp.data.getEmail().equals(Email)){
                    return true;
                }
                else if(temp.next==null){
                    if(temp.data.getEmail().equals(Email)){
                        return true;
                    }
                }
            }   
       return false;
}
int getId(String email){
    int id;
    if(first.data.getEmail().equals(email)){
        id=first.data.getId();
        return id;
    }   
    else{
        node temp=first;
                while (!(temp.data.getEmail().equals(email)) && temp.next!=null) {
                    temp=temp.next;
                }
                if(temp.data.getEmail().equals(email)){
                    id=temp.data.getId();
                    return id;
                }
                else if(temp.next==null){
                    if(temp.data.getEmail().equals(email)){
                        id=temp.data.getId();
                        return id;
                    }
                }
    }   

    return 0;
}

String getName(String email){
        String name;
    if(first.data.getEmail().equals(email)){
        name=first.data.getName();
        return name;
    }   
    else{
        node temp=first;
                while (!(temp.data.getEmail().equals(email)) && temp.next!=null) {
                    temp=temp.next;
                }
                if(temp.data.getEmail().equals(email)){
                    name=temp.data.getName();
                    return name;
                }
                else if(temp.next==null){
                    if(temp.data.getEmail().equals(email)){
                        name=temp.data.getName();
                        return name;                    }
                }
    }   

    return null;
}

        public boolean MatchPassword(String Email,String pass){
            if(first.data.getEmail().equals(Email)){
                if(first.data.getPassword().equals(pass)){

                    return true;

                }
            }   
            else{
                node temp=first;
                while (!(temp.data.getEmail().equals(Email)) && temp.next!=null) {
                    temp=temp.next;
                }
                if(temp.data.getEmail().equals(Email)){
                    if(temp.data.getPassword().equals(pass)){
                        return true;
                    }
                    
                }
                else if(temp.next==null){
                    if(temp.data.getEmail().equals(Email)){
                        if(temp.data.getEmail().equals(pass))
                        {
                            return true;
                        }
                        
                    }
                }
            }   
       return false;

    }

}
class cart{
    int customer_id;
    int product_id;
    String product_name;
    double price;
    int quantity;
    public cart(int customer_id, int product_id, String product_name, double price, int quantity) {
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public int getCustomer_id() {
        return customer_id;
    }
    public int getProduct_id() {
        return product_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {
    return"\n"+
               "\n Cust_Id       : " + customer_id + 
               "\n Product_Id    : " + product_id 
             + "\n Product name  : " + product_name +
               "\n Quantity      : " + quantity + 
               "\n Price         : " + price 
              + "\n\n";
    }
    
    
}

class order{
    int order_id;
    int cust_id;
    int product_id;
    String name;
    String pname;
    int quantity;
    double price;
    String date;
    public order(int order_id,int cust_id,int product_id, String name, String pname, int quantity, double price, String date) {
        this.product_id = product_id;
        this.order_id=order_id;
        this.name = name;
        this.pname = pname;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.cust_id=cust_id;
    }
    public int getOrder_id() {
        return order_id;
    }
    public int getProduct_id() {
        return product_id;
    }
    public String getName() {
        return name;
    }
    public String getPname() {
        return pname;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public String getDate() {
        return date;
    }
    public int getCust_id() {
        return cust_id;
    }
    
    @Override
    public String toString() {
return   "\n" +"\n order_id      : " 
    + order_id+"\n cust_id       : " + cust_id + 
               "\n product_id    : " + product_id 
             + "\n Customer name : " + name
             + "\n product name  : " + pname +
               "\n quantity      : " + quantity + 
               "\n price         : " + price 
             + "\n date of buy   : " + date + "\n\n";
    }  
}
class food_product{
int     product_id ;
String  product_name;		
double	price;
int	    stock_quantity;	
public food_product(int product_id, String product_name, double price, int stock_quantity) {
    this.product_id = product_id;
    this.product_name = product_name;
    this.price = price;
    this.stock_quantity = stock_quantity;
}
public int getProduct_id() {
    return product_id;
}
public String getProduct_name() {
    return product_name;
}

public double getPrice() {
    return price;
}
public int getStock_quantity() {
    return stock_quantity;
}

public void setProduct_id(int product_id) {
    this.product_id = product_id;
}
public void setProduct_name(String product_name) {
    this.product_name = product_name;
}

public void setPrice(double price) {
    this.price = price;
}
public void setStock_quantity(int stock_quantity) {
    this.stock_quantity = stock_quantity;
}

@Override
public String toString() {
    return "\n Product_id     : " + product_id 
    +      "\n Product_name   : " + product_name + 
           "\n Price          : " + price + 
           "\n Stock_quantity : " + stock_quantity 
             + "\n\n";
}	

        
}

class clothing_product{
int     product_id ;
String  product_name;		
double	price;
int	    stock_quantity;	
int size;
public clothing_product(int product_id, String product_name,  double price, int stock_quantity,
        int size) {
    this.product_id = product_id;
    this.product_name = product_name;
    this.price = price;
    this.stock_quantity = stock_quantity;
    this.size = size;
}
public int getProduct_id() {
    return product_id;
}
public String getProduct_name() {
    return product_name;
}
public double getPrice() {
    return price;
}
public int getStock_quantity() {
    return stock_quantity;
}
public int getSize() {
    return size;
}

public void setProduct_id(int product_id) {
    this.product_id = product_id;
}
public void setProduct_name(String product_name) {
    this.product_name = product_name;
}

public void setPrice(double price) {
    this.price = price;
}
public void setStock_quantity(int stock_quantity) {
    this.stock_quantity = stock_quantity;
}
public void setSize(int size) {
    this.size = size;
}

@Override
public String toString() {
    return "\n Product_id     : " + product_id 
    +      "\n Product_name   : " + product_name + 
           "\n Price          : " + price + 
           "\n Stock_quantity : " + stock_quantity 
             + "\n\n";
}



                
}
class electronic_product{
    int     product_id ;
String  product_name;		
double	price;
int	    stock_quantity;	
String brand;public electronic_product(int product_id, String product_name, double price, int stock_quantity,
        String brand) {
    this.product_id = product_id;
    this.product_name = product_name;
    this.price = price;
    this.stock_quantity = stock_quantity;
    this.brand = brand;
}
public int getProduct_id() {
    return product_id;
}
public String getProduct_name() {
    return product_name;
}

public double getPrice() {
    return price;
}
public int getStock_quantity() {
    return stock_quantity;
}
public String getBrand() {
    return brand;
}

public void setProduct_id(int product_id) {
    this.product_id = product_id;
}
public void setProduct_name(String product_name) {
    this.product_name = product_name;
}

public void setPrice(double price) {
    this.price = price;
}
public void setStock_quantity(int stock_quantity) {
    this.stock_quantity = stock_quantity;
}
public void setBrand(String brand) {
    this.brand = brand;
}

@Override
public String toString() {
    return "\n Product_id     : " + product_id 
    +      "\n Product_name   : " + product_name + 
           "\n Price          : " + price + 
           "\n Stock_quantity : " + stock_quantity 
             + "\n\n";
}

}       
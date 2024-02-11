-- Table: salesrecord
CREATE TABLE IF NOT EXISTS salesrecord (staff_id INT,firstname VARCHAR,surname VARCHAR,purchaseid INT,totalSales INT,date DATE,paymentMethod VARCHAR);
INSERT INTO salesrecord VALUES ('7','OlADEJO','oluwatise','37281','185400','2024-02-10','Bank Transfer');
INSERT INTO salesrecord VALUES ('7','OlADEJO','oluwatise','43591','43600','2024-02-10','Bank Transfer');
INSERT INTO salesrecord VALUES ('1','Damilolas',' Oladejo','47287','43600','2024-02-09','Card');
INSERT INTO salesrecord VALUES ('1','Damilolas',' Oladejo','54459','132000','2024-02-10','Card');
INSERT INTO salesrecord VALUES ('1','Damilolas',' Oladejo','77533','96300','2024-02-10','Card');
-- Table: staff
CREATE TABLE IF NOT EXISTS staff (staff_id INT,firstname VARCHAR,middlename VARCHAR,surname VARCHAR,date_of_birth DATE,phone_number VARCHAR,email VARCHAR,gender VARCHAR,role VARCHAR,username VARCHAR,password VARCHAR,passwordChanged BIT);
INSERT INTO staff VALUES ('1','Damilola','Samsons',' Oladejo','2005-02-12','090747678443','dejodammy7@gmail.com','Male','IT Person ','dejo','cb2f6009e2fc98025c1a2bd658c2742e','1');
INSERT INTO staff VALUES ('3','Damilola ','','','2005-02-12','08156291203','cjoguibe@gmail.com','Male','IT Person ','dejodammy7','dcb66fb089b204811d0d2e203996bb6e','0');
INSERT INTO staff VALUES ('4','Tolu Odusola','null','null','2024-01-27','090746784443','dejodammy@gmail.com','Male','IT Person ','tolubae','b380a2231a197e568116487358fb4c40','0');
INSERT INTO staff VALUES ('5','Oladejo Oluwadamilola ','null','null','2024-01-27','08238293829','donaldgrape2005@gmail.com','Female','Inventory Person ','fiyin','b86f2cadb97302f4f7ab6e0650b4400f','0');
INSERT INTO staff VALUES ('6','tolu odusola','null','null','2024-01-27','08156291203','dejodammy7@gmail.com','Male','IT Person ','dejos','1541fcd9c2c3c396a7332293752d378c','0');
INSERT INTO staff VALUES ('7','OlADEJO','kimberly','oluwatise','2020-10-23','08023354651','oluwadamilola.oladejo@pau.edu.ng','Female','IT Person ','tiseQueen','5038da5e7e2446a34a87c542e440e3c4','0');
INSERT INTO staff VALUES ('9','owoeye','temidire','dammy','2024-01-27','08156391203','dejodammy@gmail.com','Male','IT Person ','dejouiuoik','d4c50ae1189cbfe962a564d05ffda58e','0');
INSERT INTO staff VALUES ('11','12erwfwrwv','sdw','SDWS','2024-01-05','W3F','dejodammy@gmail.com','Male','Sales Person','EFW','295fb8a549752c3eb87b44bd5a697e92','0');
INSERT INTO staff VALUES ('12','pweiqdw','wdq','qdw','2024-02-01','wdq','wdqdwq','Male','Sales Person','qwd','3b00e0fcce067fcec60286fa1dd105da','0');
INSERT INTO staff VALUES ('13','moses','samson','oladejo','2024-02-01','09023738447','dejodammy7@gmail.com','Male','~Select Role~','moses','ed02a8cd9c1a04a63f4d0150cab28a03','0');
INSERT INTO staff VALUES ('15','moses','damilola','oladejos','2024-02-11','09074678443','dejodammy7@gmail.com','Male','Sales Manager','mosesd','463df90544f472ea60fc50c0ef5813ce','0');
-- Table: stock
CREATE TABLE IF NOT EXISTS stock (product_id INT,product_name VARCHAR,product_code VARCHAR,manufacturer VARCHAR,manufacturing_date DATE,expiry_date DATE,quantity INT,price DECIMAL,low_stock_amount INT);
INSERT INTO stock VALUES ('1','Moisturising Lotion','1','CeraVe','2024-01-01','2025-01-01','6','12000.00','10');
INSERT INTO stock VALUES ('2','Versace parfum','2','Versace','2022-01-01','2025-01-02','1224','40000.00','10');
INSERT INTO stock VALUES ('3','Milo','3','Nestle','2024-01-04','2025-01-18','6','2500.00','2');
INSERT INTO stock VALUES ('4','Dano Milk 200g','4','Dano','2024-01-03','2025-01-12','0','1500.00','4');
INSERT INTO stock VALUES ('5','Blue Band Butter','5','Blue Band','2024-01-11','2025-01-18','7','700.00','1');
INSERT INTO stock VALUES ('6','Kellogs Corn Flakes','6','Kellogs','2024-01-05','2025-01-19','8','3600.00','2');

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2024 at 08:58 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_commerce`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `displayOrderHistory` (IN `idd` INT)   BEGIN
select c.name,o.order_id,o.product_id,o.product_name,o.price,o.quantity,o.date_of_buy from orders o inner join customer c on o.cust_id=c.cust_id WHERE c.cust_id=idd ;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertcloth` (IN `name` VARCHAR(50), IN `description` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT, IN `size` INT, IN `catagory` VARCHAR(50))   BEGIN
INSERT INTO clothing_product(product_name, description, price, stock_quantity,size, category, date_of_add) VALUES (name,description,price,stock,size,catagory,CURRENT_DATE);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertelectronic` (IN `name` VARCHAR(50), IN `description` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT, IN `brand` VARCHAR(50), IN `catagory` VARCHAR(50))   BEGIN
INSERT INTO electronic_product(product_name, description, price, stock_quantity, brand, category, date_of_add) VALUES (name,description,price,stock,brand,catagory,CURRENT_DATE);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertfood` (IN `name` VARCHAR(50), IN `description` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT, IN `catagory` VARCHAR(50))   BEGIN
INSERT INTO food_product(product_name, description, price, stock_quantity, expire_date, category, date_of_add) VALUES (name,description,price,stock,CURRENT_DATE + '1 month',catagory,CURRENT_DATE);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertorder` (IN `c_id` INT, IN `pid` INT, IN `name` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT)   BEGIN
INSERT INTO orders( cust_id, product_id, product_name, price, quantity, date_of_buy) VALUES
(c_id,pid,name,price,stock,CURRENT_DATE);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatecloth` (IN `name` VARCHAR(50), IN `description` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT, IN `size` INT, IN `category` VARCHAR(50), IN `id` INT)   BEGIN
    UPDATE clothing_product 
    SET 
        product_name = name, 
        description = description, 
        price = price, 
        stock_quantity = stock, 
        size = size, 
        category = category 
    WHERE 
        product_id = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateElectronicProduct` (IN `id` INT, IN `description` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT, IN `brand` VARCHAR(50), IN `category` VARCHAR(50), IN `name` VARCHAR(50))   BEGIN
    UPDATE electronic_product 
    SET 
        product_name = name, 
        description = description, 
        price = price, 
        stock_quantity = stock, 
        brand = brand, 
        category = category 
    WHERE 
        product_id = id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatefood` (IN `id` INT, IN `description` VARCHAR(50), IN `price` DOUBLE, IN `stock` INT, IN `name` VARCHAR(50), IN `category` VARCHAR(50))   BEGIN
    UPDATE food_product 
    SET 
        product_name = name, 
        description = description, 
        price = price, 
        stock_quantity = stock, 
        category = category 
    WHERE 
        product_id = id;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`a_id`, `email`, `password`) VALUES
(1, 'meet@gmail.com', '12345678'),
(2, 'maru@gmail.com', '12345678'),
(3, 'manthan@gmail.com', '87654321'),
(5, 'dev@gmail.com', '12345678'),
(7, 'aum@gmail.com', '123123123'),
(8, 'abc@gmail.com', '-12345678'),
(9, 'zoro@gmail.com', '1230123000');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `cust_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cart_id`, `cust_id`, `product_id`, `product_name`, `quantity`, `price`) VALUES
(23, 4, 2, 'Jacket', 2, 199.98);

-- --------------------------------------------------------

--
-- Table structure for table `clothing_product`
--

CREATE TABLE `clothing_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clothing_product`
--

INSERT INTO `clothing_product` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `size`, `category`, `date_of_add`) VALUES
(1, 'us polo jean', 'loose fit', 450, 44, 31, 'men\'s jeans', '2024-08-17'),
(2, 'Jacket', 'Leather jacket', 99.99, 20, 42, 'Outerwear', '2024-08-17'),
(3, 'Sweater', 'Wool sweater', 39.99, 40, 15, 'Knitwear', '2024-08-17'),
(4, 'Shoes', 'Running shoes', 59.99, 25, 8, 'Footwear', '2024-08-17');

--
-- Triggers `clothing_product`
--
DELIMITER $$
CREATE TRIGGER `before_delete_cloths` AFTER DELETE ON `clothing_product` FOR EACH ROW BEGIN
INSERT INTO clothing_product_backup (product_id, product_name, description, price, stock_quantity, size, category, date_of_add) VALUES
(old.product_id, old.product_name, old.description, old.price, old.stock_quantity, old.size, old.category, old.date_of_add);

end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_insert_in_clothing_product` BEFORE INSERT ON `clothing_product` FOR EACH ROW BEGIN
    IF NEW.price < 0 or new.stock_quantity < 0 or new.size < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_cloths` BEFORE UPDATE ON `clothing_product` FOR EACH ROW BEGIN
    IF NEW.price < 0 or new.stock_quantity < 0 or new.size < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_log_cloths` BEFORE UPDATE ON `clothing_product` FOR EACH ROW BEGIN
INSERT INTO update_log_clothing_product (product_id, product_name, description, price, stock_quantity, size, category, date_of_add) VALUES
(old.product_id, old.product_name, old.description, old.price, old.stock_quantity, old.size, old.category, old.date_of_add);

end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `clothing_product_backup`
--

CREATE TABLE `clothing_product_backup` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clothing_product_backup`
--

INSERT INTO `clothing_product_backup` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `size`, `category`, `date_of_add`) VALUES
(1, 'us polo jean', 'loose fit', 450, 35, 31, 'men\'s jeans', '2024-08-17'),
(2, 'Jacket', 'Leather jacket', 99.99, 14, 42, 'Outerwear', '2024-08-17'),
(3, 'Sweater', 'Wool sweater', 39.99, 37, 15, 'Knitwear', '2024-08-17'),
(4, 'Shoes', 'Running shoes', 59.99, 25, 8, 'Footwear', '2024-08-17'),
(7, 'levis', 'jeans', 1699, 42, 41, 'mens wear', '2024-08-29');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobile_no` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cust_id`, `name`, `email`, `password`, `mobile_no`) VALUES
(1, 'dev', 'dev@gmail.com', '12345678', '1478523695'),
(2, 'man', 'man@gmail.com', '12345678', '1478523695'),
(7, 'Dev ', 'abc@gmail.com', '123123123', '7894561235'),
(8, 'zoro', 'zoro@gmail.com', '1230123000', '8529631475');

-- --------------------------------------------------------

--
-- Table structure for table `electronic_product`
--

CREATE TABLE `electronic_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `electronic_product`
--

INSERT INTO `electronic_product` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `brand`, `category`, `date_of_add`) VALUES
(1, 'iphone', 'preamium', 35000, 49, 'apple', 'moobile', '2024-08-17'),
(2, 'Smart TV', '4K UHD Smart TV', 499.99, 30, 'BrandC', 'Televisions', '2024-08-17'),
(3, 'Headphones', 'Noise-cancelling headphones', 199.99, 75, 'BrandD', 'Audio', '2024-08-17'),
(4, 'Cooler', 'COOL the room', 4500, 45, 'Havvels', 'electronics', '2024-08-20'),
(14, 'mobile', '8 gb ram', 52000, 10, 'iphone', 'mobile', '2024-08-20');

--
-- Triggers `electronic_product`
--
DELIMITER $$
CREATE TRIGGER `before_delete_electronic` AFTER DELETE ON `electronic_product` FOR EACH ROW BEGIN
INSERT INTO electronic_product_backup (product_id, product_name, description, price, stock_quantity, brand, category, date_of_add) VALUES
(old.product_id, old.product_name, old.description, old.price, old.stock_quantity, old.brand, old.category, old.date_of_add);

end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_insert_in_electronic_product` BEFORE INSERT ON `electronic_product` FOR EACH ROW BEGIN
    IF NEW.price < 0 or new.stock_quantity < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_electronic` BEFORE UPDATE ON `electronic_product` FOR EACH ROW BEGIN
   IF NEW.price < 0 or new.stock_quantity < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_log_electronic` BEFORE UPDATE ON `electronic_product` FOR EACH ROW BEGIN
INSERT INTO update_log_electronic_product (product_id, product_name, description, price, stock_quantity, brand, category, date_of_add) VALUES
(old.product_id, old.product_name, old.description, old.price, old.stock_quantity, old.brand, old.category, old.date_of_add);

end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `electronic_product_backup`
--

CREATE TABLE `electronic_product_backup` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `electronic_product_backup`
--

INSERT INTO `electronic_product_backup` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `brand`, `category`, `date_of_add`) VALUES
(1, 'iphone', 'preamium', 35000, 46, 'apple', 'moobile', '2024-08-17'),
(2, 'Smart TV', '4K UHD Smart TV', 499.99, 25, 'BrandC', 'Televisions', '2024-08-17'),
(3, 'Headphones', 'Noise-cancelling headphones', 199.99, 68, 'BrandD', 'Audio', '2024-08-17'),
(4, 'Cooler', 'COOL the room', 4500, 40, 'Havvels', 'electronics', '2024-08-20'),
(14, 'game', 'mobile', 58000, 20, 'ROG', 'mobile', '2024-08-20'),
(16, 'motor engine', 'for water', 2999, 52, 'crompton', 'very good', '2024-08-29');

-- --------------------------------------------------------

--
-- Table structure for table `food_product`
--

CREATE TABLE `food_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `expire_date` date NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food_product`
--

INSERT INTO `food_product` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `expire_date`, `category`, `date_of_add`) VALUES
(1, 'thums up', 'sugar free', 25, 100, '2024-09-30', 'drinkks', '2024-08-17'),
(2, 'Milk', 'Organic whole milk', 3.49, 200, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 150, '2024-09-10', 'Dairy', '2024-08-17'),
(5, 'Yogurt', 'Greek yogurt', 5.99, 80, '2024-08-29', 'Dairy', '2024-08-17');

--
-- Triggers `food_product`
--
DELIMITER $$
CREATE TRIGGER `before_delete_food` AFTER DELETE ON `food_product` FOR EACH ROW BEGIN
INSERT INTO food_product_backup (product_id, product_name, description, price, stock_quantity, expire_date, category, date_of_add) VALUES
(old.product_id, old.product_name, old.description, old.price, old.stock_quantity, old.expire_date, old.category, old.date_of_add);

end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_insert_in_food_product` BEFORE INSERT ON `food_product` FOR EACH ROW BEGIN
    IF NEW.price < 0 or new.stock_quantity < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_food` BEFORE UPDATE ON `food_product` FOR EACH ROW BEGIN
     IF NEW.price < 0 or new.stock_quantity < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `before_update_log_food` BEFORE UPDATE ON `food_product` FOR EACH ROW BEGIN
INSERT INTO update_log_food_product (product_id, product_name, description, price, stock_quantity, expire_date, category, date_of_add) VALUES
(old.product_id, old.product_name, old.description, old.price, old.stock_quantity, old.expire_date, old.category, old.date_of_add);

end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `food_product_backup`
--

CREATE TABLE `food_product_backup` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `expire_date` date NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food_product_backup`
--

INSERT INTO `food_product_backup` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `expire_date`, `category`, `date_of_add`) VALUES
(1, 'thums up', 'sugar free', 25, 64, '2024-09-30', 'drinkks', '2024-08-17'),
(2, 'Milk', 'Organic whole milk', 3.49, 177, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 143, '2024-09-10', 'Dairy', '2024-08-17'),
(4, 'Yogurt', 'Greek yogurt', 5.99, 80, '2024-08-29', 'Dairy', '2024-08-17'),
(13, 'peri peri fries', 'tasty', 100, 60, '2024-08-30', 'healthy', '2024-08-29'),
(43, 'Yogurt', 'Greek yogurt', 5.99, 50, '2024-08-29', 'Dairy', '2024-08-17');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `cust_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `date_of_buy` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `cust_id`, `product_id`, `product_name`, `price`, `quantity`, `date_of_buy`) VALUES
(12, 1, 1, 'thums up', 25, 1, '2024-08-21'),
(15, 1, 1, 'us polo jean', 1800, 4, '2024-08-23'),
(17, 1, 1, 'thums up', 250, 10, '2024-08-24'),
(18, 1, 1, 'thums up', 250, 10, '2024-08-24'),
(19, 1, 1, 'thums up', 125, 5, '2024-08-24'),
(21, 1, 4, 'Cooler', 22500, 5, '2024-08-24'),
(22, 1, 2, 'Jacket', 199.98, 2, '2024-08-24'),
(23, 6, 3, 'Eggs', 49.900000000000006, 10, '2024-08-24'),
(24, 6, 2, 'Jacket', 199.98, 2, '2024-08-24'),
(25, 7, 2, 'Milk', 34.900000000000006, 10, '2024-08-25'),
(27, 7, 4, 'Yogurt', 479.20000000000005, 80, '2024-08-25'),
(29, 7, 14, 'mobile', 52000, 1, '2024-08-25'),
(30, 7, 2, 'Jacket', 299.96999999999997, 3, '2024-08-25'),
(32, 7, 1, 'thums up', 125, 5, '2024-08-25'),
(33, 7, 1, 'us polo jean', 2250, 5, '2024-08-25'),
(67, 1, 1, 'thums up', 250, 10, '2024-08-25'),
(68, 1, 1, 'thums up', 125, 5, '2024-08-25'),
(69, 1, 1, 'thums up', 125, 5, '2024-08-27'),
(71, 1, 14, 'mobile', 104000, 2, '2024-08-27'),
(72, 1, 1, 'Coca cola', 45, 1, '2024-08-27'),
(73, 1, 3, 'Headphones', 999.95, 5, '2024-08-27'),
(77, 1, 3, 'Sweater', 39.99, 1, '2024-08-28'),
(78, 1, 3, 'Headphones', 999.95, 5, '2024-08-28'),
(79, 1, 2, 'Milk', 17.450000000000003, 5, '2024-08-28'),
(80, 1, 1, 'us polo jean', 450, 1, '2024-08-28'),
(81, 1, 3, 'Eggs', 4.99, 1, '2024-08-28'),
(82, 1, 3, 'Headphones', 199.99, 1, '2024-08-28'),
(83, 1, 2, 'Milk', 17.450000000000003, 5, '2024-08-28'),
(84, 1, 1, 'us polo jean', 900, 2, '2024-08-28'),
(85, 1, 3, 'Eggs', 4.99, 1, '2024-08-28'),
(86, 7, 1, 'thums up', 25, 1, '2024-08-28'),
(87, 7, 2, 'Milk', 3.49, 1, '2024-08-28'),
(88, 7, 1, 'iphone', 35000, 1, '2024-08-28'),
(89, 7, 2, 'Smart TV', 499.99, 1, '2024-08-28'),
(90, 7, 2, 'Jacket', 99.99, 1, '2024-08-28'),
(91, 7, 1, 'us polo jean', 450, 1, '2024-08-28'),
(92, 8, 3, 'Eggs', 9.98, 2, '2024-08-28'),
(93, 8, 1, 'thums up', 50, 2, '2024-08-28'),
(94, 8, 1, 'iphone', 70000, 2, '2024-08-28'),
(95, 8, 1, 'thums up', 25, 1, '2024-08-28'),
(96, 8, 1, 'thums up', 25, 1, '2024-08-28'),
(97, 8, 2, 'Milk', 3.49, 1, '2024-08-28'),
(98, 8, 3, 'Eggs', 4.99, 1, '2024-08-28'),
(99, 8, 2, 'Smart TV', 1499.97, 3, '2024-08-28'),
(100, 8, 3, 'Headphones', 199.99, 1, '2024-08-28'),
(102, 8, 1, 'us polo jean', 450, 1, '2024-08-28'),
(103, 8, 1, 'thums up', 25, 1, '2024-08-28'),
(104, 8, 2, 'Milk', 3.49, 1, '2024-08-28'),
(105, 8, 2, 'Smart TV', 499.99, 1, '2024-08-28'),
(106, 8, 3, 'Sweater', 79.98, 2, '2024-08-28'),
(107, 8, 3, 'Eggs', 9.98, 2, '2024-08-28');

--
-- Triggers `orders`
--
DELIMITER $$
CREATE TRIGGER `before_insert_order` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
    IF NEW.price < 0 or new.quantity < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'value cannot be negative.';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `update_log_clothing_product`
--

CREATE TABLE `update_log_clothing_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `update_log_clothing_product`
--

INSERT INTO `update_log_clothing_product` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `size`, `category`, `date_of_add`) VALUES
(3, 'Sweater', 'Wool sweater', 39.99, 40, 15, 'Knitwear', '2024-08-17'),
(1, 'us polo jean', 'loose fit', 450, 40, 31, 'men\'s jeans', '2024-08-17'),
(1, 'us polo jean', 'loose fit', 450, 39, 31, 'men\'s jeans', '2024-08-17'),
(2, 'Jacket', 'Leather jacket', 99.99, 15, 42, 'Outerwear', '2024-08-17'),
(1, 'us polo jean', 'loose fit', 450, 37, 31, 'men\'s jeans', '2024-08-17'),
(1, 'us polo jean', 'loose fit', 450, 36, 31, 'men\'s jeans', '2024-08-17'),
(3, 'Sweater', 'Wool sweater', 39.99, 39, 15, 'Knitwear', '2024-08-17'),
(7, 'ck tshirt', 'wearing', 799, 50, 30, 'mens wear', '2024-08-29');

-- --------------------------------------------------------

--
-- Table structure for table `update_log_electronic_product`
--

CREATE TABLE `update_log_electronic_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `update_log_electronic_product`
--

INSERT INTO `update_log_electronic_product` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `brand`, `category`, `date_of_add`) VALUES
(14, 'mobile', '8 gb ram', 52000, 9, 'iphone', 'mobile', '2024-08-20'),
(3, 'Headphones', 'Noise-cancelling headphones', 199.99, 75, 'BrandD', 'Audio', '2024-08-17'),
(3, 'Headphones', 'Noise-cancelling headphones', 199.99, 70, 'BrandD', 'Audio', '2024-08-17'),
(1, 'iphone', 'preamium', 35000, 49, 'apple', 'moobile', '2024-08-17'),
(2, 'Smart TV', '4K UHD Smart TV', 499.99, 30, 'BrandC', 'Televisions', '2024-08-17'),
(1, 'iphone', 'preamium', 35000, 48, 'apple', 'moobile', '2024-08-17'),
(2, 'Smart TV', '4K UHD Smart TV', 499.99, 29, 'BrandC', 'Televisions', '2024-08-17'),
(3, 'Headphones', 'Noise-cancelling headphones', 199.99, 69, 'BrandD', 'Audio', '2024-08-17'),
(2, 'Smart TV', '4K UHD Smart TV', 499.99, 26, 'BrandC', 'Televisions', '2024-08-17'),
(16, 'engine', 'cars', 6999, 45, 'lamborgini', 'supercars', '2024-08-29');

-- --------------------------------------------------------

--
-- Table structure for table `update_log_food_product`
--

CREATE TABLE `update_log_food_product` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `stock_quantity` int(11) NOT NULL,
  `expire_date` date NOT NULL,
  `category` varchar(50) NOT NULL,
  `date_of_add` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `update_log_food_product`
--

INSERT INTO `update_log_food_product` (`product_id`, `product_name`, `description`, `price`, `stock_quantity`, `expire_date`, `category`, `date_of_add`) VALUES
(2, 'Milk', 'Organic whole milk', 3.49, 190, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 150, '2024-09-10', 'Dairy', '2024-08-17'),
(2, 'Milk', 'Organic whole milk', 3.49, 185, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 149, '2024-09-10', 'Dairy', '2024-08-17'),
(1, 'thums up', 'sugar free', 25, 70, '2024-09-30', 'drinkks', '2024-08-17'),
(2, 'Milk', 'Organic whole milk', 3.49, 180, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 148, '2024-09-10', 'Dairy', '2024-08-17'),
(1, 'thums up', 'sugar free', 25, 69, '2024-09-30', 'drinkks', '2024-08-17'),
(1, 'thums up', 'sugar free', 25, 67, '2024-09-30', 'drinkks', '2024-08-17'),
(1, 'thums up', 'sugar free', 25, 66, '2024-09-30', 'drinkks', '2024-08-17'),
(2, 'Milk', 'Organic whole milk', 3.49, 179, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 146, '2024-09-10', 'Dairy', '2024-08-17'),
(1, 'thums up', 'sugar free', 25, 65, '2024-09-30', 'drinkks', '2024-08-17'),
(2, 'Milk', 'Organic whole milk', 3.49, 178, '2024-09-05', 'Dairy', '2024-08-17'),
(3, 'Eggs', 'Free-range eggs', 4.99, 145, '2024-09-10', 'Dairy', '2024-08-17'),
(4, 'Yogurt', 'Greek yogurt', 5.99, 0, '2024-08-29', 'Dairy', '2024-08-17'),
(13, 'fries', 'tasty', 82, 35, '2024-08-30', 'made with alloo', '2024-08-29'),
(4, 'Yogurt', 'Greek yogurt', 5.99, 50, '2024-08-29', 'Dairy', '2024-08-17'),
(4, 'Yogurt', 'Greek yogurt', 5.99, 80, '2024-08-29', 'Dairy', '2024-08-17');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`a_id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`);

--
-- Indexes for table `clothing_product`
--
ALTER TABLE `clothing_product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `clothing_product_backup`
--
ALTER TABLE `clothing_product_backup`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cust_id`);

--
-- Indexes for table `electronic_product`
--
ALTER TABLE `electronic_product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `electronic_product_backup`
--
ALTER TABLE `electronic_product_backup`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `food_product`
--
ALTER TABLE `food_product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `food_product_backup`
--
ALTER TABLE `food_product_backup`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `clothing_product`
--
ALTER TABLE `clothing_product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `clothing_product_backup`
--
ALTER TABLE `clothing_product_backup`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cust_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `electronic_product`
--
ALTER TABLE `electronic_product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `electronic_product_backup`
--
ALTER TABLE `electronic_product_backup`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `food_product`
--
ALTER TABLE `food_product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `food_product_backup`
--
ALTER TABLE `food_product_backup`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

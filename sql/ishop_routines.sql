-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: ishop
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'ishop'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_new_item` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_new_item`(
	IN id_category VARCHAR(255), 
	IN name_short VARCHAR(45), 
	IN name_full VARCHAR(255), 
	IN description VARCHAR(1024), 
	IN manufacturer VARCHAR(255), 
	IN price decimal(10,2), 
	IN state_id int,
    IN count int,
    OUT item_id INT
)
BEGIN

	DECLARE l_item_id INT DEFAULT 0;
    
	START TRANSACTION;
    
    insert into ishop.items(id_category, name_short, name_full, description, manufacturer, price, state_id)
        VALUES(id_category,name_short,name_full,description,manufacturer,price,state_id);
    
    -- get item id
    SET l_item_id = LAST_INSERT_ID();
    
     IF l_item_id > 0 THEN
		insert into ishop.storage(item_id, count) values(l_item_id, count);
        -- commit
        COMMIT;
        SET item_id = l_item_id;
     ELSE
		ROLLBACK;
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `confirm_order` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `confirm_order`(
	IN payment_type VARCHAR(45), 
	IN comment VARCHAR(526), 
	IN address VARCHAR(526), 
	IN order_id INT
)
BEGIN
    DECLARE exit_loop BOOLEAN DEFAULT FALSE;
    DECLARE i, c INT;
	DECLARE v_payment_type_id INT DEFAULT 1;
	DECLARE v_order_status_id INT DEFAULT 2;
    
    DECLARE employee_cursor CURSOR FOR SELECT od.item_id, od.COUNT FROM ishop.order_details od where od.order_id = order_id;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = TRUE;
    
   START TRANSACTION;
	SELECT pt.id into v_payment_type_id FROM ishop.dict_payment_types pt where pt.DESCRIPTION = '';
    
	update ishop.orders ord set ord.payment_type_id = v_payment_type_id, ord.comment = comment, ord.delivery_address = address
		where ord.id = order_id;
	
	insert into ishop.order_status_history(order_id,order_status) values(order_id, v_order_status_id);
	
    SET exit_loop = false;
    OPEN employee_cursor;

	employee_loop: LOOP
     FETCH NEXT FROM employee_cursor INTO i, c;
		
     IF exit_loop THEN
         CLOSE employee_cursor;
         LEAVE employee_loop;
     END IF;
     update ishop.storage st set st.count = (st.count - c) where st.item_id = i;
   END LOOP employee_loop;
   
   COMMIT;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_order` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_order`(
	IN id_user INT, 
    OUT order_id INT
)
BEGIN

	DECLARE l_order_id INT DEFAULT 0;
	DECLARE l_state_name VARCHAR(45);
    
	START TRANSACTION;
    
	insert into ishop.orders(start_date,user_id) values(sysdate(), id_user);
	
	-- get item id
    SET l_order_id = LAST_INSERT_ID();
	
	IF l_order_id > 0 THEN
		insert into ishop.order_status_history(event_date,order_id,order_status) values(sysdate(),l_order_id,1);
	
	COMMIT;
        SET order_id = l_order_id;
     ELSE
		ROLLBACK;
	END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-22 11:03:16

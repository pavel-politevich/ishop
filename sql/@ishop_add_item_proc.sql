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

END
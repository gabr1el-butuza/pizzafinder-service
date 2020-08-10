/*
  This procedure drops a foreign key if exists.
  @param myTableName
          - the name of the table that contains the foreign key to be dropped
          
  @param myConstraintName
         - the name of the foreign key that will be dropped

  To use this procedure you can call it like this:
        e.g.:  CALL dropForeignKeyIfExists('principal', 'FK_salw1sdtjflj157kqr3jvtbdo');
 */
DROP PROCEDURE IF EXISTS dropForeignKeyIfExists;
DELIMITER $$
CREATE PROCEDURE dropForeignKeyIfExists(
  IN myTableName VARCHAR(255),
  IN myConstraintName VARCHAR(255))
BEGIN
  SELECT count(*) INTO @uniqueCount FROM information_schema.table_constraints
  WHERE constraint_name = myConstraintName
  AND table_name = myTableName
  AND table_schema = DATABASE();

  IF (@uniqueCount > 0) THEN
     SET @tmpSql = CONCAT('alter table ', myTableName, ' drop foreign key ', myConstraintName);
     PREPARE stmt FROM @tmpSql;
     EXECUTE stmt;
     DEALLOCATE PREPARE stmt;
  END IF;
END $$

DELIMITER ;

/*
  This procedure adds a foreign key if exists.
  @param myTableName
          - the name of the table that contains the foreign key to be added
          
  @param myConstraintName
         - the name of the foreign key that will be added

  To use this procedure you can call it like this:
        e.g.:  CALL addForeignKeyIfNotExists('field', 'FK_salw1sdtjflj157kqr3jvtbdo', 'portfolioId', 'portfolio', 'id');
 */
DROP PROCEDURE IF EXISTS addForeignKeyIfNotExists;
DELIMITER $$
CREATE PROCEDURE addForeignKeyIfNotExists(
  IN myTableName VARCHAR(255),
  IN myConstraintName VARCHAR(255),
  IN myCcolumnName VARCHAR(255),
  IN foreignTableName VARCHAR(255),
  IN foreignColumnName VARCHAR(255))
BEGIN
  SELECT count(*) INTO @uniqueCount FROM information_schema.table_constraints
  WHERE constraint_name = myConstraintName
  AND table_name = myTableName
  AND table_schema = DATABASE();

  IF (@uniqueCount = 0) THEN
     SET @tmpSql = CONCAT('alter table ', myTableName, ' add constraint ', myConstraintName, ' foreign key ', myConstraintName, ' (', myCcolumnName, ') ', ' references ', foreignTableName, ' (', foreignColumnName, ')');
     PREPARE stmt FROM @tmpSql;
     EXECUTE stmt;
     DEALLOCATE PREPARE stmt;
  END IF;
END $$

DELIMITER ;

/*
  This procedure drops a unique constraint if exists.
  @param myTableName
          - the name of the table that contains the unique constraint to be dropped
          
  @param myConstraintName
         - the name of the unique constraint that will be dropped

  To use this procedure you can call it like this:
        e.g.:  CALL dropUniqueConstraintIfExists('principal', 'UK_salw1sdtjflj157kqr3jvtbdo');
 */
DROP PROCEDURE IF EXISTS dropUniqueConstraintIfExists;
DELIMITER $$
CREATE PROCEDURE dropUniqueConstraintIfExists(
  IN myTableName VARCHAR(255),
  IN myConstraintName VARCHAR(255))
BEGIN
  SELECT count(*) INTO @uniqueCount FROM information_schema.table_constraints
  WHERE constraint_name = myConstraintName
  AND table_name = myTableName
  AND table_schema = DATABASE();

  IF (@uniqueCount > 0) THEN
     SET @tmpSql = CONCAT('alter table ', myTableName, ' drop index ', myConstraintName);
     PREPARE stmt FROM @tmpSql;
     EXECUTE stmt;
     DEALLOCATE PREPARE stmt;
  END IF;
END $$

DELIMITER ;



/*
   This procedure safely adds a unique constraint made of up to 4 columns.
   @param myTableName
                - the name of the table where to add the column
   @param myConstraintName
                - the name of the unique constraint
   @param myColumnName1
                - first column (required) of the unique constraint
   @param myColumnName2
                - second column (optional) of the unique constraint
   @param myColumnName3
                - third column (optional) of the unique constraint
   @param myColumnName4
                - fourth column (optional) of the unique constraint                
                
   To use this procedure you can call it like this:
        e.g.:  CALL addUniqueConstraintIfNotExists('categorymapping', 'UK_4letxwnftxbryiixrxmd1ht97', 'modelEntityId', 'categoryId', null, null);
 */
DROP PROCEDURE IF EXISTS addUniqueConstraintIfNotExists;
DELIMITER $$

CREATE PROCEDURE addUniqueConstraintIfNotExists(
    IN myTableName VARCHAR(255),
    IN myConstraintName VARCHAR(255), 
    IN myColumnName1 VARCHAR(255),
    IN myColumnName2 VARCHAR(255),
    IN myColumnName3 VARCHAR(255),
    IN myColumnName4 VARCHAR(255)
    )
BEGIN
    SET @addSql=CONCAT('ALTER TABLE ', myTableName, ' ADD UNIQUE ', myConstraintName, ' (', myColumnName1);
    
    IF (myColumnName2 != '') THEN
       SET @addSql = CONCAT(@addSql, ',', myColumnName2);
    END IF;
       
    IF (myColumnName3 != '') THEN
       SET @addSql = CONCAT(@addSql, ',', myColumnName3);
    END IF;
    
    IF (myColumnName4 != '') THEN
       SET @addSql = CONCAT(@addSql, ',', myColumnName4);
    END IF;
       
    SET @addSql = CONCAT(@addSql, ')');  
    
    SET @uniqueCount = 0;
    
    SELECT count(*) INTO @uniqueCount FROM information_schema.table_constraints
    WHERE constraint_type = 'UNIQUE'
    AND table_name = myTableName
    AND table_schema=DATABASE();
    
    IF (@uniqueCount = 0) THEN
       PREPARE stmt FROM @addSql;
       EXECUTE stmt;
       DEALLOCATE PREPARE stmt;
    END IF;   
            
END $$

DELIMITER ;


/*
  This procedure drops a column if exists with all it's foreign key constraints.
  @param myTableName
          - the name of the table that contains the column to be deleted
  @param myColumnName
         - the name of the column that will be deleted

  To use this procedure you can call it like this:
        e.g.:  CALL dropColumnIfExists('model', 'activeMeetingId');
 */
DROP PROCEDURE IF EXISTS dropColumnIfExists;
DELIMITER $$
CREATE PROCEDURE dropColumnIfExists(
  IN myTableName VARCHAR(255),
  IN myColumnName VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE dropCommand VARCHAR(255);
  DECLARE dropCursor CURSOR FOR
    SELECT CONCAT('ALTER TABLE ', myTableName, ' DROP FOREIGN KEY ', i.constraint_name, ';')
    FROM information_schema.table_constraints i
      LEFT JOIN information_schema.key_column_usage k ON i.constraint_name = k.constraint_name and i.table_schema = k.table_schema
    WHERE i.constraint_type = 'FOREIGN KEY'
          AND i.table_schema = DATABASE()
          AND i.table_name = myTableName
          AND k.column_name = myColumnName;

  DECLARE continue handler for not found SET done = TRUE;

  IF exists (SELECT * FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = myTableName and column_name = myColumnName) then
    OPEN dropCursor;
    read_loop: loop
      FETCH dropCursor INTO dropCommand;
      IF done then
        leave read_loop;
      END IF;

      SET @dropComm = dropCommand;

      PREPARE dropClientUpdateKeyStmt from @dropComm;
      EXECUTE dropClientUpdateKeyStmt;
      DEALLOCATE PREPARE dropClientUpdateKeyStmt;

    end loop;
    CLOSE dropCursor;

    SET @tmpSql = CONCAT('alter table ', myTableName, ' drop column ', myColumnName);
    PREPARE stmt FROM @tmpSql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;

  END IF;
END $$

DELIMITER ;

/*
   This procedure safely adds a new column to a table if that column does not exist.
   @param myTableName
                - the name of the table where to add the column
   @param myColumnName
                - the name of the column to be added
   @param myColumnDefinition
                - the definition of the column to be added
   To use this procedure you can call it like this:
        e.g.:  CALL addColumnIfNotExists('modelparticipant', 'reviewer', 'tinyint(1) NOT NULL');
 */
DROP PROCEDURE IF EXISTS addColumnIfNotExists;
DELIMITER $$

CREATE PROCEDURE addColumnIfNotExists(
	IN myTableName VARCHAR(255),
	IN myColumnName VARCHAR(255),
	IN myColumnDefinition VARCHAR(255))
BEGIN
	IF NOT EXISTS (
      SELECT *
      FROM information_schema.columns
      WHERE column_name = myColumnName
        AND table_name = myTableName
        AND table_schema=DATABASE()
		) THEN
        SET @addColSql=CONCAT('ALTER TABLE ', myTableName, ' ADD COLUMN ', myColumnName, ' ', myColumnDefinition);
        PREPARE stmt FROM @addColSql;
        EXECUTE stmt;
	END IF;
END $$

DELIMITER ;

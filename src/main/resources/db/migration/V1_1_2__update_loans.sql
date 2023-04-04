ALTER TABLE `online_banking`.`loans_package`
    CHANGE COLUMN `loans_id` `loan_package_id` BIGINT NOT NULL AUTO_INCREMENT ;
ALTER TABLE `online_banking`.`loans`
    ADD COLUMN `loan_package_id` BIGINT NULL AFTER `loans_amount_taken`,
ADD COLUMN `status` VARCHAR(45) NULL AFTER `loan_package_id`,
ADD COLUMN `user_id` BIGINT NULL AFTER `status`,
CHANGE COLUMN `loan_id` `id` BIGINT NOT NULL ,
CHANGE COLUMN `ssn` `date_of_payment` DATE NULL ,
CHANGE COLUMN `loans_amount_repaid` `loans_amount_repaid` DECIMAL NULL ,
CHANGE COLUMN `loans_amount_taken` `loans_amount_taken` DECIMAL NULL ;

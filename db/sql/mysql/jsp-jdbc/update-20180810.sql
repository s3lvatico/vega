CREATE TABLE `vega`.`vega_user`
(
  `user_name` VARCHAR
(20) NOT NULL,
  `user_password` VARCHAR
(132) NOT NULL,
  `full_name` VARCHAR
(50) NOT NULL,
  PRIMARY KEY
(`user_name`));

CREATE TABLE `vega`.`vega_role`
(
  `role_name` VARCHAR
(20) NOT NULL,
  `user_name` VARCHAR
(20) NOT NULL,
  PRIMARY KEY
(`role_name`, `user_name`));

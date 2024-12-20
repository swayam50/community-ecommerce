USE community_ecommerce_db;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `u_id`       VARCHAR(36)  NOT NULL UNIQUE DEFAULT (uuid()) COMMENT "User's unique id.",
    `u_fullname` VARCHAR(75)  NOT NULL                         COMMENT "User's full name",
    `u_username` VARCHAR(30)  NOT NULL UNIQUE                  COMMENT "User's unique username.",
    `u_email`    VARCHAR(75)  NOT NULL UNIQUE                  COMMENT "User's unique email.",
    `u_password` TEXT         NOT NULL                         COMMENT "User's encrypted password.",
    `u_role`     VARCHAR(7)   NOT NULL                         COMMENT "User's role assigned.",

    PRIMARY KEY (`u_id`)
);
create table clients ( id  INT primary key auto_increment, client VARCHAR(50) NOT NULL);

create table bills (id  INT primary key auto_increment,
                    bill      DECIMAL(20) NOT NULL, balance INT NOT NULL, client_id VARCHAR(50) NOT NULL,
                    FOREIGN KEY (client_id) REFERENCES CLIENTS (client));

create table cards ( id  INT primary key auto_increment,
                 card_number      DECIMAL(16) NOT NULL,bill_id DECIMAL(20) NOT NULL,
                FOREIGN KEY (bill_id) REFERENCES BILLS (bill));

ALTER TABLE BILLS ADD UNIQUE (BILL);

ALTER TABLE CARDS ADD UNIQUE (card_number);

ALTER TABLE BILLS ALTER COLUMN ID RESTART WITH 1;

ALTER TABLE CARDS ALTER COLUMN ID RESTART WITH 1;


INSERT INTO clients(client) VALUES ('petr');

INSERT INTO clients(client) VALUES ('oleg');

INSERT INTO bills (bill, balance, client_id) VALUES  ('11111111111111111111',5000,'petr');

INSERT INTO bills (bill, balance, client_id) VALUES  ('22222222222222222222',5000,'oleg');

INSERT INTO cards (card_number ,bill_id) VALUES  ('5555555555555555','11111111111111111111');

INSERT INTO cards (card_number ,bill_id) VALUES  ('5555555555555595','11111111111111111111');

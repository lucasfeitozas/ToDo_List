DROP TABLE IF EXISTS TB_USER;
DROP TABLE IF EXISTS TB_LISTA;
DROP TABLE IF EXISTS TB_ITEM;

-- create tb_user
CREATE TABLE TB_USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  login VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL
);

-- create tb_lista
CREATE TABLE TB_LISTA(
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES TB_USER(id)
);

-- create tb_item
CREATE TABLE TB_ITEM (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(250),
  user_id INT NOT NULL,
  lista_id INT NOT NULL,
  item_id INT,
  FOREIGN KEY (user_id)  REFERENCES TB_USER (id),
  FOREIGN KEY (lista_id) REFERENCES TB_LISTA (id),
  FOREIGN KEY (item_id)  REFERENCES TB_ITEM (id)
);

-- add values to table
INSERT INTO TB_USER (id, name, email, login, password) VALUES
   (1, 'Geralt', 'geralt@example.com', 'geralt', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
   (2, 'Bill', 'boo@example.com', 'gates', '$2a$11$bF./aFuKU14vRD0jT4CcGeCiVB825nNKOJSn31pmV7LL3L9sx.mMe'),
   (3, 'Antoine', 'you@example.com', 'antoine', '$2a$11$bF./aFuKU14vRD0jT4CcGeCiVB825nNKOJSn31pmV7LL3L9sx.mMe');
  
INSERT INTO TB_LISTA (id, title, user_id) VALUES
   (1, 'Material Escolar', 1),
   (2, 'Supermercado', 2),
   (3, 'Pendencias Trabalho', 2);

INSERT INTO TB_ITEM (id, title, description, user_id, lista_id, item_id) VALUES
   (1, 'Caderno', 'Tilibra, 500 folhas, capa dura', 2, 1, NULL),
   (2, 'Lápis', 'Faber-Castell',  2, 1, NULL),
   (3, 'Carne', 'ALCATRA, CONTRA-FILÉ', 1, 2, NULL ),
   (4, 'Ovos', NULL,  3, 2, NULL ),
   (5, 'Tópicos de conversa', 'Descrição das pendências', 1, 3, NULL),
   (6, 'Gastos do mês de Junho', NULL, 3, 3, 5 ),
   (7, 'Avalição das atividades', NULL, 3, 3, NULL );
   
   
   
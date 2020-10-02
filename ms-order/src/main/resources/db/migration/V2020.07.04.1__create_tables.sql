CREATE TABLE customer_tb
(
    id SERIAL ,
    nome character varying(60)   COLLATE pg_catalog."default" NOT NULL,
    
    CONSTRAINT customer_tb_pkey PRIMARY KEY (id)
);

CREATE TABLE product_tb(
    id SERIAL ,
    nome character varying(60)   COLLATE pg_catalog."default" NOT NULL,
    
    CONSTRAINT product_tb_pkey PRIMARY KEY (id)

);

CREATE TABLE payment_details_tb(
	  id SERIAL ,
      nome character varying(60)   COLLATE pg_catalog."default" NOT NULL,
    
      CONSTRAINT  payment_details_tb_pkey PRIMARY KEY (id)

);



CREATE TABLE order_tb
(
    id SERIAL ,
    nome character varying(60)   COLLATE pg_catalog."default" NOT NULL,
    situacao character varying(20)   COLLATE pg_catalog."default" NOT NULL,
      
    
    id_customer         integer references customer_tb(id) NOT NULL,
    id_payment_details  integer references payment_details_tb(id) NOT NULL,
    
     
    
    CONSTRAINT order_tb_pkey PRIMARY KEY (id)
);


CREATE TABLE order_item_tb(
	  id SERIAL ,
      nome character varying(60)   COLLATE pg_catalog."default" NOT NULL,
    
      id_product  integer references product_tb(id) NOT NULL,
      id_order    integer references order_tb(id) NOT NULL,
      CONSTRAINT  order_item_tb_pkey PRIMARY KEY (id)

);

-- Usuário/role = postgresql, caso necessário é só dar um replace all (:

CREATE TABLE item_venda (
    produto_id bigint NOT NULL,
    venda_id bigint NOT NULL
);


ALTER TABLE public.item_venda OWNER TO postgres;

CREATE SEQUENCE permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permissao_id_seq OWNER TO postgres;

SELECT pg_catalog.setval('permissao_id_seq', 2, true);

CREATE TABLE permissao (
    id bigint DEFAULT nextval('permissao_id_seq'::regclass) NOT NULL,
    tipo bigint NOT NULL
);


ALTER TABLE public.permissao OWNER TO postgres;

CREATE SEQUENCE pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pessoa_id_seq OWNER TO postgres;

SELECT pg_catalog.setval('pessoa_id_seq', 5, true);

CREATE TABLE pessoa (
    id bigint DEFAULT nextval('pessoa_id_seq'::regclass) NOT NULL,
    cidade_estado character varying,
    cpf character varying NOT NULL,
    endereco character varying,
    limite_credito double precision NOT NULL,
    nome character varying NOT NULL
);


ALTER TABLE public.pessoa OWNER TO postgres;

CREATE SEQUENCE produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produto_id_seq OWNER TO postgres;

SELECT pg_catalog.setval('produto_id_seq', 2, true);

CREATE TABLE produto (
    id bigint DEFAULT nextval('produto_id_seq'::regclass) NOT NULL,
    descricao character varying NOT NULL,
    quantidade bigint NOT NULL,
    valor double precision NOT NULL
);


ALTER TABLE public.produto OWNER TO postgres;

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

SELECT pg_catalog.setval('usuario_id_seq', 1, false);

CREATE TABLE usuario (
    id bigint DEFAULT nextval('usuario_id_seq'::regclass) NOT NULL,
    nome character varying NOT NULL,
    permissao_id bigint NOT NULL,
    senha character varying NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

CREATE SEQUENCE venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venda_id_seq OWNER TO postgres;

SELECT pg_catalog.setval('venda_id_seq', 56, true);

CREATE TABLE venda (
    desconto real,
    id bigint DEFAULT nextval('venda_id_seq'::regclass) NOT NULL,
    descricao character varying,
    cliente_id smallint NOT NULL,
    valor_total real
);


ALTER TABLE public.venda OWNER TO postgres;

ALTER TABLE ONLY item_venda
    ADD CONSTRAINT "Item_Venda_pkey" PRIMARY KEY (produto_id, venda_id);

ALTER TABLE ONLY permissao
    ADD CONSTRAINT "Permissao_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT "Pessoa_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY produto
    ADD CONSTRAINT "Produto_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY venda
    ADD CONSTRAINT "Venda_pkey" PRIMARY KEY (id);

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


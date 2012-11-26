/*
 Navicat Premium Backup

 Source Server         : MBP - PostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 90104
 Source Host           : localhost
 Source Database       : TrabalhoDeJava
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90104
 File Encoding         : utf-8

 Date: 11/25/2012 23:11:18 PM
*/

-- ----------------------------
--  Table structure for "User"
-- ----------------------------
DROP TABLE IF EXISTS "User" CASCADE;
CREATE TABLE "User" (
	"id" int8 NOT NULL,
	"nome" varchar NOT NULL,
	"permissao_id" int8 NOT NULL,
	"senha" varchar NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "User" OWNER TO "tiagocouto";

-- ----------------------------
--  Table structure for "Permissao"
-- ----------------------------
DROP TABLE IF EXISTS "Permissao" CASCADE;
CREATE TABLE "Permissao" (
	"id" int8 NOT NULL,
	"tipo" int8 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "Permissao" OWNER TO "tiagocouto";

-- ----------------------------
--  Table structure for "Item_Venda"
-- ----------------------------
DROP TABLE IF EXISTS "Item_Venda" CASCADE;
CREATE TABLE "Item_Venda" (
	"produto_id" int8 NOT NULL,
	"venda_id" int8 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "Item_Venda" OWNER TO "tiagocouto";

-- ----------------------------
--  Table structure for "Pessoa"
-- ----------------------------
DROP TABLE IF EXISTS "Pessoa" CASCADE;
CREATE TABLE "Pessoa" (
	"id" int8 NOT NULL,
	"cidade_estado" varchar,
	"cpf" varchar NOT NULL,
	"endereco" varchar,
	"limite_credito" float8 NOT NULL,
	"nome" varchar NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "Pessoa" OWNER TO "tiagocouto";

-- ----------------------------
--  Table structure for "Produto"
-- ----------------------------
DROP TABLE IF EXISTS "Produto" CASCADE;
CREATE TABLE "Produto" (
	"id" int8 NOT NULL,
	"descricao" varchar NOT NULL,
	"quantidade" int8 NOT NULL,
	"valor" float8 NOT NULL
)
WITH (OIDS=FALSE);
ALTER TABLE "Produto" OWNER TO "tiagocouto";

-- ----------------------------
--  Table structure for "Venda"
-- ----------------------------
DROP TABLE IF EXISTS "Venda" CASCADE;
CREATE TABLE "Venda" (
	"desconto" float4,
	"id" int8 NOT NULL,
	"descricao" varchar,
	"cliente_id" int2 NOT NULL,
	"valor_total" float4
)
WITH (OIDS=FALSE);
ALTER TABLE "Venda" OWNER TO "tiagocouto";

-- ----------------------------
--  Primary key structure for table "User"
-- ----------------------------
ALTER TABLE "User" ADD CONSTRAINT "User_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "Permissao"
-- ----------------------------
ALTER TABLE "Permissao" ADD CONSTRAINT "Permissao_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "Item_Venda"
-- ----------------------------
ALTER TABLE "Item_Venda" ADD CONSTRAINT "Item_Venda_pkey" PRIMARY KEY ("produto_id", "venda_id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "Pessoa"
-- ----------------------------
ALTER TABLE "Pessoa" ADD CONSTRAINT "Pessoa_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "Produto"
-- ----------------------------
ALTER TABLE "Produto" ADD CONSTRAINT "Produto_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Primary key structure for table "Venda"
-- ----------------------------
ALTER TABLE "Venda" ADD CONSTRAINT "Venda_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;


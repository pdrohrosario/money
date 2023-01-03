-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: money
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `forma_pagamento`
--

DROP TABLE IF EXISTS `forma_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_pagamento`
--

LOCK TABLES `forma_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_pagamento` DISABLE KEYS */;
INSERT INTO `forma_pagamento` VALUES (1,'CREDITO'),(2,'PIX');
/*!40000 ALTER TABLE `forma_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivo`
--

DROP TABLE IF EXISTS `objetivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetivo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `plano_gasto_id` bigint DEFAULT NULL,
  `tipo_gasto_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhaonc5gowrpeawhgfpk6otj0` (`plano_gasto_id`),
  KEY `FK82dleii3mgfonbukbbks52i85` (`tipo_gasto_id`),
  KEY `FKtl7ltw64lxiogn11xn4ypm0lq` (`user_id`),
  CONSTRAINT `FK82dleii3mgfonbukbbks52i85` FOREIGN KEY (`tipo_gasto_id`) REFERENCES `tipo_gasto` (`id`),
  CONSTRAINT `FKhaonc5gowrpeawhgfpk6otj0` FOREIGN KEY (`plano_gasto_id`) REFERENCES `plano_gasto` (`id`),
  CONSTRAINT `FKtl7ltw64lxiogn11xn4ypm0lq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivo`
--

LOCK TABLES `objetivo` WRITE;
/*!40000 ALTER TABLE `objetivo` DISABLE KEYS */;
INSERT INTO `objetivo` VALUES (73,67881,2,1),(66001,62558,4,1);
/*!40000 ALTER TABLE `objetivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plano_gasto`
--

DROP TABLE IF EXISTS `plano_gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plano_gasto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_fim` datetime DEFAULT NULL,
  `data_inicio` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `quantia` double DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67882 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plano_gasto`
--

LOCK TABLES `plano_gasto` WRITE;
/*!40000 ALTER TABLE `plano_gasto` DISABLE KEYS */;
INSERT INTO `plano_gasto` VALUES (27301,'2022-12-31 23:59:59','2022-10-06 15:14:09','Nda',30000,'Carro novo'),(62558,'2022-12-31 23:59:59','2022-10-06 23:59:59','Nda',700,'Gastar menos com fastfood'),(67881,'2021-12-31 23:59:59','2021-01-05 23:59:59','Nda',350,'Gastar menos com academia');
/*!40000 ALTER TABLE `plano_gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poupanca`
--

DROP TABLE IF EXISTS `poupanca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poupanca` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantia_mes_esperada` double DEFAULT NULL,
  `quantidade_meses` int DEFAULT NULL,
  `quantidade_minima_transferencias` int DEFAULT NULL,
  `plano_gasto_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlxbhk88c9b20y8ngewtmdc6bc` (`plano_gasto_id`),
  KEY `FKnfk353f9gmb2gyod172xi1ye1` (`user_id`),
  CONSTRAINT `FKlxbhk88c9b20y8ngewtmdc6bc` FOREIGN KEY (`plano_gasto_id`) REFERENCES `plano_gasto` (`id`),
  CONSTRAINT `FKnfk353f9gmb2gyod172xi1ye1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poupanca`
--

LOCK TABLES `poupanca` WRITE;
/*!40000 ALTER TABLE `poupanca` DISABLE KEYS */;
INSERT INTO `poupanca` VALUES (68147,15000,2,5,27301,1);
/*!40000 ALTER TABLE `poupanca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poupanca_transferencias`
--

DROP TABLE IF EXISTS `poupanca_transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poupanca_transferencias` (
  `poupanca_id` bigint NOT NULL,
  `transferencias_id` bigint NOT NULL,
  UNIQUE KEY `UK_70la8rheje5di1q193iwvidu2` (`transferencias_id`),
  KEY `FKbhkvwwjtdfg3gokp9molm6f2m` (`poupanca_id`),
  CONSTRAINT `FKbhkvwwjtdfg3gokp9molm6f2m` FOREIGN KEY (`poupanca_id`) REFERENCES `poupanca` (`id`),
  CONSTRAINT `FKc06n8cej3y78qo3ebg7v5nn1p` FOREIGN KEY (`transferencias_id`) REFERENCES `transferencia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poupanca_transferencias`
--

LOCK TABLES `poupanca_transferencias` WRITE;
/*!40000 ALTER TABLE `poupanca_transferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `poupanca_transferencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_gasto`
--

DROP TABLE IF EXISTS `tipo_gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_gasto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_gasto`
--

LOCK TABLES `tipo_gasto` WRITE;
/*!40000 ALTER TABLE `tipo_gasto` DISABLE KEYS */;
INSERT INTO `tipo_gasto` VALUES (1,'POUPANÇA'),(2,'ACADEMIA'),(3,'OPERADORA DE CELULAR'),(4,'COMIDA');
/*!40000 ALTER TABLE `tipo_gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencia`
--

DROP TABLE IF EXISTS `transferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferencia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `quantia` double DEFAULT NULL,
  `forma_pagamento_id` bigint NOT NULL,
  `tipo_gasto_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4461kcbearaed4944bymwigus` (`forma_pagamento_id`),
  KEY `FK7okwo30cv0xdu8xgu8csq728c` (`tipo_gasto_id`),
  KEY `FKfwkg8bfpi2xmyta9i4tlfvhu6` (`user_id`),
  CONSTRAINT `FK4461kcbearaed4944bymwigus` FOREIGN KEY (`forma_pagamento_id`) REFERENCES `forma_pagamento` (`id`),
  CONSTRAINT `FK7okwo30cv0xdu8xgu8csq728c` FOREIGN KEY (`tipo_gasto_id`) REFERENCES `tipo_gasto` (`id`),
  CONSTRAINT `FKfwkg8bfpi2xmyta9i4tlfvhu6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89660 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencia`
--

LOCK TABLES `transferencia` WRITE;
/*!40000 ALTER TABLE `transferencia` DISABLE KEYS */;
INSERT INTO `transferencia` VALUES (58420,'2022-10-06 23:59:59','Nda',500,2,1,1),(64921,'2022-10-19 23:59:59','Nda',60,2,2,1),(71236,'2021-02-05 23:59:59','Nda',50,2,2,1),(89659,'2022-11-05 23:59:59','Nda',500,2,4,1);
/*!40000 ALTER TABLE `transferencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user4@email.com','Andre da Silva','$2a$10$hM1xGrXln16hprAcEn2Ta.rfwZ3NV9rQf82X7lJ8HSFkPiSQwtyOO','andreSilva');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-06 15:36:09

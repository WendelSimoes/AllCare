CREATE DATABASE  IF NOT EXISTS `allcare` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `allcare`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: allcare
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `doenca`
--

DROP TABLE IF EXISTS `doenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `doenca` (
  `cod_doenca` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  `descricao` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`cod_doenca`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doenca`
--

LOCK TABLES `doenca` WRITE;
/*!40000 ALTER TABLE `doenca` DISABLE KEYS */;
INSERT INTO `doenca` VALUES (3,'Gripe','Propagação: Aérea\nTratamento: Pode ser tratado pela própria pessoa\nDuração: Curto prazo\nDiagnosticável: Sim\nMédico(s): Clínico geral\nDescrição geral: Uma infecção viral comum que pode ser fatal, especialmente em grupos de alto risco'),(4,'AIDS','Propagação: Sexualmente\nTratamento: Não possui cura\nDuração: Crônica\nDiagnosticável: Sim\nMédico(s): Infectologista e Clínico geral\nDescrição geral: A AIDS é causada pelo vírus HIV, que interfere na capacidade do organismo de combater infecções'),(5,'Catapora','Propagação: Contato com infectados\nTratamento: Tratado pela própria pessoa\nDuração: Curto prazo\nDiagnosticável: Sim\nMédico(s): Pediatra e Clínico geral\nDescrição geral: Infecção viral altamente contagiosa que causa uma irritação cutânea com bolhas na pele'),(6,'Sarampo','Propagação: Contato com infectados\nTratamento: Auxílio médico\nDuração: Curto prazo\nDiagnosticável: Sim\nMédico(s): Infectologista, pediatra e clínico geral\nDescrição geral: Infecção viral que é grave para crianças pequenas, mas de fácil prevenção por meio de vacina\n'),(7,'Caxumba','Propagação: Contato com infectados\nTratamento: Auxílio médico\nDuração: Curto prazo\nDiagnosticável: Sim\nMédico(s): Infectologista e clínico geral\nDescrição geral: Infecção viral que afeta as glândulas salivares. Pode ser facilmente prevenida por meio de vacinação'),(8,'Herpes labial','Propagação: Contato labial com infectados\nTratamento: Não tem cura\nDuração: Crônica\nDiagnosticável: Sim\nMédico(s): Clínico geral e dermatologista\nDescrição geral: Infecção pelo vírus herpes simplex próxima à borda dos lábios'),(9,'Dengue','Propagação: Mosquito Aedes aegypti\nTratamento: Ingestão de líquidos e analgésicos\nDuração: Curto prazo\nDiagnosticável: Sim\nMédico(s): Infectologista\nDescrição geral: Doença viral transmitida por mosquitos que ocorre em áreas tropicais e subtropicais');
/*!40000 ALTER TABLE `doenca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doenca_x_sintoma`
--

DROP TABLE IF EXISTS `doenca_x_sintoma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `doenca_x_sintoma` (
  `cod_sintoma` int(11) NOT NULL,
  `cod_doenca` int(11) NOT NULL,
  PRIMARY KEY (`cod_doenca`,`cod_sintoma`),
  KEY `cod_sintoma` (`cod_sintoma`),
  CONSTRAINT `doenca_x_sintoma_ibfk_1` FOREIGN KEY (`cod_sintoma`) REFERENCES `sintoma` (`cod_sintoma`),
  CONSTRAINT `doenca_x_sintoma_ibfk_2` FOREIGN KEY (`cod_doenca`) REFERENCES `doenca` (`cod_doenca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doenca_x_sintoma`
--

LOCK TABLES `doenca_x_sintoma` WRITE;
/*!40000 ALTER TABLE `doenca_x_sintoma` DISABLE KEYS */;
INSERT INTO `doenca_x_sintoma` VALUES (1,3),(1,4),(1,6),(2,3),(2,6),(2,7),(2,9),(3,3),(3,9),(4,3),(4,7),(4,9),(5,3),(5,6),(6,3),(6,4),(6,5),(6,6),(6,7),(6,9),(8,3),(8,4),(8,6),(9,4),(9,9),(10,4),(11,4),(12,4),(13,3),(13,4),(13,5),(13,6),(13,7),(13,9),(14,3),(14,5),(14,7),(14,9),(15,5),(16,5),(16,6),(16,8),(17,6),(18,7),(18,8),(19,8),(20,9);
/*!40000 ALTER TABLE `doenca_x_sintoma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `medico` (
  `id_user` varchar(60) NOT NULL,
  `senha` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
INSERT INTO `medico` VALUES ('joao','joao'),('wendel','021011wd');
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `paciente` (
  `id_user` varchar(60) NOT NULL,
  `senha` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES ('wendel','021011wd'),('wendelthebeast','wendelthebeast');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sintoma`
--

DROP TABLE IF EXISTS `sintoma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sintoma` (
  `cod_sintoma` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`cod_sintoma`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sintoma`
--

LOCK TABLES `sintoma` WRITE;
/*!40000 ALTER TABLE `sintoma` DISABLE KEYS */;
INSERT INTO `sintoma` VALUES (1,'Tosse'),(2,'Dores no corpo'),(3,'Calafrios'),(4,'Dor de cabeça'),(5,'Congestão nasal'),(6,'Fadiga'),(8,'Dor na garganta'),(9,'Náusea'),(10,'Vômito'),(11,'Diarreia'),(12,'Dores genitais'),(13,'Febre'),(14,'Perda de apetite'),(15,'Coceira'),(16,'Erupções'),(17,'Conjutivite'),(18,'Inchaço'),(19,'Lesão bucal'),(20,'Dor nos olhos');
/*!40000 ALTER TABLE `sintoma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'allcare'
--

--
-- Dumping routines for database 'allcare'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-05 23:17:55

CREATE DATABASE  IF NOT EXISTS `recetas_db` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `recetas_db`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: recetas_db
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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'criolla'),(2,'vegana'),(3,'marina'),(4,'postres'),(5,'carnes'),(6,'pollo'),(7,'nikkei'),(8,'árabe'),(9,'cafeterias');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipamiento`
--

DROP TABLE IF EXISTS `equipamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipamiento` (
  `idequipamiento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idequipamiento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipamiento`
--

LOCK TABLES `equipamiento` WRITE;
/*!40000 ALTER TABLE `equipamiento` DISABLE KEYS */;
INSERT INTO `equipamiento` VALUES (1,'horno'),(2,'microondas'),(3,'batidora');
/*!40000 ALTER TABLE `equipamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingrediente` (
  `idingrediente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idingrediente`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente`
--

LOCK TABLES `ingrediente` WRITE;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` VALUES (1,'ajo'),(2,'mantequilla'),(3,'arroz'),(4,'agua'),(5,'pollo'),(6,'bistec'),(7,'lentejas'),(8,'papas'),(9,'pasta'),(10,'leche'),(11,'tomate'),(12,'sal'),(13,'pimienta'),(14,'oregano');
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nutricion`
--

DROP TABLE IF EXISTS `nutricion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nutricion` (
  `idnutricion` int NOT NULL AUTO_INCREMENT,
  `idreceta` int NOT NULL,
  `calorias` double DEFAULT NULL,
  `grasas` double DEFAULT NULL,
  `proteínas` double DEFAULT NULL,
  `carbohidratos` double DEFAULT NULL,
  `fibra` double DEFAULT NULL,
  PRIMARY KEY (`idnutricion`),
  KEY `fk_nutricion_recetas1_idx` (`idreceta`),
  CONSTRAINT `fk_nutricion_recetas1` FOREIGN KEY (`idreceta`) REFERENCES `receta` (`idreceta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutricion`
--

LOCK TABLES `nutricion` WRITE;
/*!40000 ALTER TABLE `nutricion` DISABLE KEYS */;
INSERT INTO `nutricion` VALUES (1,1,15,15,15,15,15);
/*!40000 ALTER TABLE `nutricion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receta` (
  `idreceta` int NOT NULL AUTO_INCREMENT,
  `idcategoria` int NOT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `instrucciones` text,
  `dificultad` int DEFAULT NULL,
  `tiempo_preparacion` time DEFAULT NULL,
  PRIMARY KEY (`idreceta`),
  KEY `fk_recetas_categorias1_idx` (`idcategoria`),
  CONSTRAINT `fk_recetas_categorias1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES (1,1,'arroz cocido','dorar ajo en aceite, vertir agua y posteriormente el arroz, una cucharadita de sal por taza de arroz',1,'00:15:00'),(2,1,'pasta cocida','vertir agua en olla con un poco de aceite, una vez hervida colocar sal y pasta',1,'00:20:00');
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta_equipamiento`
--

DROP TABLE IF EXISTS `receta_equipamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receta_equipamiento` (
  `idreceta_equipamiento` int NOT NULL AUTO_INCREMENT,
  `idreceta` int NOT NULL,
  `idequipamiento` int NOT NULL,
  PRIMARY KEY (`idreceta_equipamiento`),
  KEY `fk_receta_equipamiento_recetas1_idx` (`idreceta`),
  KEY `fk_receta_equipamiento_equipamiento1_idx` (`idequipamiento`),
  CONSTRAINT `fk_receta_equipamiento_equipamiento1` FOREIGN KEY (`idequipamiento`) REFERENCES `equipamiento` (`idequipamiento`),
  CONSTRAINT `fk_receta_equipamiento_recetas1` FOREIGN KEY (`idreceta`) REFERENCES `receta` (`idreceta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta_equipamiento`
--

LOCK TABLES `receta_equipamiento` WRITE;
/*!40000 ALTER TABLE `receta_equipamiento` DISABLE KEYS */;
INSERT INTO `receta_equipamiento` VALUES (1,1,1);
/*!40000 ALTER TABLE `receta_equipamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta_ingrediente`
--

DROP TABLE IF EXISTS `receta_ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receta_ingrediente` (
  `id_receta_ingrediente` int NOT NULL AUTO_INCREMENT,
  `idreceta` int NOT NULL,
  `idingrediente` int NOT NULL,
  `idunidad_medida` int NOT NULL,
  `cantidad` double DEFAULT NULL,
  PRIMARY KEY (`id_receta_ingrediente`),
  KEY `fk_recetas_ingredientes_recetas_idx` (`idreceta`),
  KEY `fk_recetas_ingredientes_ingredientes1_idx` (`idingrediente`),
  KEY `fk_receta_ingrediente_unidad_medida1_idx` (`idunidad_medida`),
  CONSTRAINT `fk_receta_ingrediente_unidad_medida1` FOREIGN KEY (`idunidad_medida`) REFERENCES `unidad_medida` (`idunidad_medida`),
  CONSTRAINT `fk_recetas_ingredientes_ingredientes1` FOREIGN KEY (`idingrediente`) REFERENCES `ingrediente` (`idingrediente`),
  CONSTRAINT `fk_recetas_ingredientes_recetas` FOREIGN KEY (`idreceta`) REFERENCES `receta` (`idreceta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta_ingrediente`
--

LOCK TABLES `receta_ingrediente` WRITE;
/*!40000 ALTER TABLE `receta_ingrediente` DISABLE KEYS */;
INSERT INTO `receta_ingrediente` VALUES (1,1,1,1,0.25),(2,1,3,2,5),(3,1,4,3,0.5);
/*!40000 ALTER TABLE `receta_ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_medida`
--

DROP TABLE IF EXISTS `unidad_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidad_medida` (
  `idunidad_medida` int NOT NULL AUTO_INCREMENT,
  `nombre_unidad` varchar(45) DEFAULT NULL,
  `simbolo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idunidad_medida`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_medida`
--

LOCK TABLES `unidad_medida` WRITE;
/*!40000 ALTER TABLE `unidad_medida` DISABLE KEYS */;
INSERT INTO `unidad_medida` VALUES (1,'litros','l'),(2,'gramos','g'),(3,'kilogramos','kg'),(4,'onzas','oz'),(5,'mililitros','ml'),(6,'cucharada','c/s'),(7,'cucharadita','c/c');
/*!40000 ALTER TABLE `unidad_medida` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-11 17:31:30

CREATE DATABASE  IF NOT EXISTS `agendafx` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `agendafx`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: agendafx
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `notificacao`
--

DROP TABLE IF EXISTS `notificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificacao` (
  `idNotific` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(25) NOT NULL,
  `descricao` varchar(1500) NOT NULL,
  `image` varchar(5000) DEFAULT NULL,
  `horario` time NOT NULL,
  `avisado` tinyint(1) NOT NULL,
  `anexo` varchar(5000) DEFAULT NULL,
  `musica` varchar(5000) DEFAULT NULL,
  `userNotification` int(11) NOT NULL,
  `marcado` date DEFAULT NULL,
  `tipo_notificacao` int(11) NOT NULL,
  PRIMARY KEY (`idNotific`),
  UNIQUE KEY `idNotific` (`idNotific`),
  KEY `userNotification` (`userNotification`),
  KEY `fk_tipo` (`tipo_notificacao`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacao`
--

LOCK TABLES `notificacao` WRITE;
/*!40000 ALTER TABLE `notificacao` DISABLE KEYS */;
INSERT INTO `notificacao` VALUES (23,'Reuniao no Meet','Reuniao no meet para \ndefinir a estratégia de \nmarket da empresa','C:\\Users\\Samuel\\Pictures\\5c9bffd81b5e6.jpeg','19:40:00',0,'','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-17',0),(2,'JooJ','Comprar uma lhama onetm','C:\\Users\\Samuel\\Pictures\\IMG_20091231_232900.jpg','16:37:00',1,'C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\Astronomia - Tony igy - (acoustic guitar cover) - version - Mike Malcher.mp3',2,'2020-05-22',0),(47,'JOJO','fmfrytfvbnvbnfj5i7yjffku8888888888','C:\\Users\\Samuel\\Pictures\\3zeaylyqcto11.jpg','16:06:00',1,'C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\B.o.B - Nothin\' On You (feat. Bruno Mars) [Official Video].mp3',2,'2020-05-20',0),(22,'teste','ffjjjyjyjyjyjyjyjyj','','13:00:00',0,'','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\?????????????????? (Lofi remix).mp3',2,'2020-05-14',0),(18,'Turnar','turnar','C:\\Users\\Samuel\\Pictures\\5c9bffd81b5e6.jpeg','16:37:00',1,'C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-22',0),(25,'atividade biologia','atividade que nao deveria \nocorrer','C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX\\src\\View\\Images\\agenda_multi_color.png','13:59:00',0,'','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-18',0),(26,'Atividade de fisica','nao deveria ocorrer ;-;','C:\\Users\\Samuel\\Pictures\\5c9bffd81b5e6.jpeg','14:07:00',0,'','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\?????????????????? (Lofi remix).mp3',2,'2020-05-18',0),(27,'teste','pao','','14:10:00',0,'','',2,'2020-05-18',0),(28,':-:','Comprar uma lhama','C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','16:37:00',1,'C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-22',0),(29,'Comprar uma lhama','EU PRECISO DE UMA \nLHAMA','C:\\Users\\Samuel\\Pictures\\5c9bffd81b5e6.jpeg','11:12:00',0,'','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-19',0),(48,'JooJ','fmfrytfvbnvbnfj5i7yjffku8888888888','C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','17:15:00',1,'C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-20',0),(49,'Aula Online','Aula Será realizada no meet','C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX\\src\\View\\Images\\postits_white.png','18:51:00',1,'C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX','C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-21',0);
/*!40000 ALTER TABLE `notificacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `notification_from_user`
--

DROP TABLE IF EXISTS `notification_from_user`;
/*!50001 DROP VIEW IF EXISTS `notification_from_user`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `notification_from_user` AS SELECT 
 1 AS `idNotific`,
 1 AS `titulo`,
 1 AS `descricao`,
 1 AS `image`,
 1 AS `horario`,
 1 AS `avisado`,
 1 AS `anexo`,
 1 AS `musica`,
 1 AS `userNotification`,
 1 AS `marcado`,
 1 AS `tipo_notificacao`,
 1 AS `id`,
 1 AS `nome`,
 1 AS `sexo`,
 1 AS `dataNascimento`,
 1 AS `telefone`,
 1 AS `email`,
 1 AS `cpf`,
 1 AS `senha`,
 1 AS `imagePerfil`,
 1 AS `id_tipo`,
 1 AS `tipo`,
 1 AS `cor`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `postit`
--

DROP TABLE IF EXISTS `postit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postit` (
  `idPostIt` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `body` tinytext NOT NULL,
  `scheduled` varchar(25) DEFAULT NULL,
  `horary` varchar(10) DEFAULT NULL,
  `warned` tinyint(1) DEFAULT NULL,
  `user_postit` int(11) NOT NULL,
  `sound` text,
  `type_postit` int(11) NOT NULL,
  PRIMARY KEY (`idPostIt`),
  UNIQUE KEY `idPostIt` (`idPostIt`),
  KEY `postItUser` (`user_postit`),
  KEY `fk_postit_tipo` (`type_postit`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postit`
--

LOCK TABLES `postit` WRITE;
/*!40000 ALTER TABLE `postit` DISABLE KEYS */;
INSERT INTO `postit` VALUES (1,'Compras no Mercado','-feijao\n-arroz\n-macarrao\n-ovo','26/05/0020','15:30',0,2,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\Ali Gatie - It\'s You LEGENDADOTRADUÇÃO.mp3',0);
/*!40000 ALTER TABLE `postit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `postit_from_user`
--

DROP TABLE IF EXISTS `postit_from_user`;
/*!50001 DROP VIEW IF EXISTS `postit_from_user`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `postit_from_user` AS SELECT 
 1 AS `idPostIt`,
 1 AS `title`,
 1 AS `body`,
 1 AS `scheduled`,
 1 AS `horary`,
 1 AS `warned`,
 1 AS `user_postit`,
 1 AS `sound`,
 1 AS `type_postit`,
 1 AS `id`,
 1 AS `nome`,
 1 AS `sexo`,
 1 AS `dataNascimento`,
 1 AS `telefone`,
 1 AS `email`,
 1 AS `cpf`,
 1 AS `senha`,
 1 AS `imagePerfil`,
 1 AS `id_tipo`,
 1 AS `tipo`,
 1 AS `cor`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo` (
  `id_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(30) DEFAULT NULL,
  `cor` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE KEY `id` (`id_tipo`),
  UNIQUE KEY `id_tipo` (`id_tipo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `sexo` enum('Masculino','Feminino') DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cpf` char(11) DEFAULT NULL,
  `senha` varchar(50) NOT NULL,
  `imagePerfil` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `senha` (`senha`),
  UNIQUE KEY `senha_2` (`senha`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'sa',NULL,NULL,NULL,NULL,NULL,'1',NULL),(2,'Samuel','Masculino','2004-01-31','(81) 987128496','samueldebarro@gmail.com','46357485732','123','C:\\Users\\Samuel\\Documents\\NetBeansProjects\\JavaApplication1\\mavenproject1\\AgendaFX\\src\\View\\Images\\sino.png'),(3,'Daciolo','Masculino','1990-01-01','(81) 40028922','cabodaciolo@gmail.com','32454368267','gloriadeux','C:\\Users\\Samuel\\Pictures\\3zeaylyqcto11.jpg');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'agendafx'
--

--
-- Dumping routines for database 'agendafx'
--

--
-- Final view structure for view `notification_from_user`
--

/*!50001 DROP VIEW IF EXISTS `notification_from_user`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `notification_from_user` AS select `notificacao`.`idNotific` AS `idNotific`,`notificacao`.`titulo` AS `titulo`,`notificacao`.`descricao` AS `descricao`,`notificacao`.`image` AS `image`,`notificacao`.`horario` AS `horario`,`notificacao`.`avisado` AS `avisado`,`notificacao`.`anexo` AS `anexo`,`notificacao`.`musica` AS `musica`,`notificacao`.`userNotification` AS `userNotification`,`notificacao`.`marcado` AS `marcado`,`notificacao`.`tipo_notificacao` AS `tipo_notificacao`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil`,`tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor` from ((`notificacao` join `usuario` on((`notificacao`.`userNotification` = `usuario`.`id`))) join `tipo` on((`notificacao`.`tipo_notificacao` = `tipo`.`id_tipo`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `postit_from_user`
--

/*!50001 DROP VIEW IF EXISTS `postit_from_user`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `postit_from_user` AS select `postit`.`idPostIt` AS `idPostIt`,`postit`.`title` AS `title`,`postit`.`body` AS `body`,`postit`.`scheduled` AS `scheduled`,`postit`.`horary` AS `horary`,`postit`.`warned` AS `warned`,`postit`.`user_postit` AS `user_postit`,`postit`.`sound` AS `sound`,`postit`.`type_postit` AS `type_postit`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil`,`tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor` from ((`postit` join `usuario` on((`postit`.`user_postit` = `usuario`.`id`))) join `tipo` on((`postit`.`type_postit` = `tipo`.`id_tipo`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-28 10:14:50

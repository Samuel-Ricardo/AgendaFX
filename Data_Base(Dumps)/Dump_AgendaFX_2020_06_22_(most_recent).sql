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
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `id_file` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) NOT NULL,
  `file_way` text,
  `file_bytes` varbinary(55530) DEFAULT NULL,
  `file_postIt_id` int(11) DEFAULT NULL,
  `file_notification_id` int(11) DEFAULT NULL,
  `file_size` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_file`),
  KEY `FK_file_postIt` (`file_postIt_id`),
  KEY `FK_file_notification` (`file_notification_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id_image` int(11) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(50) NOT NULL,
  `image_way` text,
  `image_bytes` varbinary(55530) DEFAULT NULL,
  `image_user_id` int(11) DEFAULT NULL,
  `image_postIt_id` int(11) DEFAULT NULL,
  `image_notification_id` int(11) DEFAULT NULL,
  `image_size` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_image`),
  KEY `FK_image_user` (`image_user_id`),
  KEY `FK_image_postIt` (`image_postIt_id`),
  KEY `FK_image_notification` (`image_notification_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

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
  `horario` time NOT NULL,
  `avisado` tinyint(1) NOT NULL,
  `musica` varchar(5000) DEFAULT NULL,
  `userNotification` int(11) NOT NULL,
  `marcado` date DEFAULT NULL,
  `tipo_notificacao` int(11) NOT NULL,
  PRIMARY KEY (`idNotific`),
  UNIQUE KEY `idNotific` (`idNotific`),
  KEY `userNotification` (`userNotification`),
  KEY `fk_tipo` (`tipo_notificacao`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacao`
--

LOCK TABLES `notificacao` WRITE;
/*!40000 ALTER TABLE `notificacao` DISABLE KEYS */;
INSERT INTO `notificacao` VALUES (23,'Reuniao no Meet','Reuniao no meet para \ndefinir a estratégia de \nmarket da empresa','19:40:00',0,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-17',0),(2,'JooJ','Comprar uma lhama onetm','16:37:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\Astronomia - Tony igy - (acoustic guitar cover) - version - Mike Malcher.mp3',2,'2020-05-22',0),(47,'JOJO','fmfrytfvbnvbnfj5i7yjffku8888888888','13:52:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\B.o.B - Nothin\' On You (feat. Bruno Mars) [Official Video].mp3',2,'2020-06-20',135),(22,'teste','ffjjjyjyjyjyjyjyjyj','13:00:00',0,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\?????????????????? (Lofi remix).mp3',2,'2020-05-14',0),(18,'Turnar','turnar','13:44:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-06-20',136),(25,'atividade biologia','atividade que nao deveria \nocorrer','13:52:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-06-20',139),(26,'Atividade de fisica','nao deveria ocorrer ;-;','14:07:00',0,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\?????????????????? (Lofi remix).mp3',2,'2020-05-18',0),(27,'teste','pao','14:10:00',0,'',2,'2020-05-18',0),(28,':-:','Comprar uma lhama','16:37:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-22',0),(29,'Comprar uma lhama','EU PRECISO DE UMA \nEU PRECISO DE UMA LHAMA','13:53:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-06-20',140),(48,'JooJ','fmfrytfvbnvbnfj5i7yjffku8888888888','17:15:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-20',0),(49,'Aula Online','Aula Será realizada no meet','18:51:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-05-21',0),(50,'Rogeru','klklklkl','11:23:00',1,'C:\\Users\\Samuel\\Desktop\\Musicas_Mp3\\ASTRONOMIA - Tony Igy ELECTRIC GUITAR COVER.mp3',2,'2020-06-06',140);
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
 1 AS `horario`,
 1 AS `avisado`,
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
 1 AS `cor`,
 1 AS `detalhes_de_cores`,
 1 AS `id_usuario_tipo`,
 1 AS `importancia`,
 1 AS `id_image`,
 1 AS `image_name`,
 1 AS `image_way`,
 1 AS `image_bytes`,
 1 AS `image_user_id`,
 1 AS `image_postIt_id`,
 1 AS `image_notification_id`,
 1 AS `image_size`,
 1 AS `id_file`,
 1 AS `file_name`,
 1 AS `file_way`,
 1 AS `file_bytes`,
 1 AS `file_postIt_id`,
 1 AS `file_notification_id`,
 1 AS `file_size`*/;
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
  `body` text NOT NULL,
  `user_postit` int(11) NOT NULL,
  `type_postit` int(11) NOT NULL,
  PRIMARY KEY (`idPostIt`),
  UNIQUE KEY `idPostIt` (`idPostIt`),
  KEY `postItUser` (`user_postit`),
  KEY `fk_postit_tipo` (`type_postit`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postit`
--

LOCK TABLES `postit` WRITE;
/*!40000 ALTER TABLE `postit` DISABLE KEYS */;
INSERT INTO `postit` VALUES (1,'Compras no Mercado','-feijao\n-arroz\n-macarrao\n-ovo',2,0),(2,'pao','pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);pnMenuBackground.setVisible(menuVissible);',2,140);
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
 1 AS `user_postit`,
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
 1 AS `cor`,
 1 AS `detalhes_de_cores`,
 1 AS `id_usuario_tipo`,
 1 AS `importancia`,
 1 AS `id_image`,
 1 AS `image_name`,
 1 AS `image_way`,
 1 AS `image_bytes`,
 1 AS `image_user_id`,
 1 AS `image_postIt_id`,
 1 AS `image_notification_id`,
 1 AS `image_size`,
 1 AS `id_file`,
 1 AS `file_name`,
 1 AS `file_way`,
 1 AS `file_bytes`,
 1 AS `file_postIt_id`,
 1 AS `file_notification_id`,
 1 AS `file_size`*/;
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
  `detalhes_de_cores` varchar(20) DEFAULT NULL,
  `id_usuario_tipo` int(11) NOT NULL,
  `importancia` int(11) NOT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE KEY `id` (`id_tipo`),
  UNIQUE KEY `id_tipo` (`id_tipo`),
  KEY `fk_usiario_tipo` (`id_usuario_tipo`)
) ENGINE=MyISAM AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (140,'Urgente','#ff0000','#ff7373',2,10),(139,'Escola','#00faff','#aef3f4',2,8),(138,'Banal','#000080','#5b5ba4',2,1),(137,'Post-It','#ff00ff','#ee71ee',2,5),(136,'Especial','#ffd700','#ffe97e',2,6),(135,'Evento','#d4ff00','#e1f481',2,5),(134,'Exercicio','#00ff00','#7fff7f',2,5),(133,'Atividade','#8b008b','#a457a4',2,4),(132,'Trabalho','#8a2be2','#9370db',2,10),(131,'Meta','#0400f7','#7f7df1',2,8);
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `types_from_user`
--

DROP TABLE IF EXISTS `types_from_user`;
/*!50001 DROP VIEW IF EXISTS `types_from_user`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `types_from_user` AS SELECT 
 1 AS `id_tipo`,
 1 AS `tipo`,
 1 AS `cor`,
 1 AS `detalhes_de_cores`,
 1 AS `id_usuario_tipo`,
 1 AS `importancia`,
 1 AS `id`,
 1 AS `nome`,
 1 AS `sexo`,
 1 AS `dataNascimento`,
 1 AS `telefone`,
 1 AS `email`,
 1 AS `cpf`,
 1 AS `senha`,
 1 AS `imagePerfil`*/;
SET character_set_client = @saved_cs_client;

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
/*!50001 VIEW `notification_from_user` AS select `notificacao`.`idNotific` AS `idNotific`,`notificacao`.`titulo` AS `titulo`,`notificacao`.`descricao` AS `descricao`,`notificacao`.`horario` AS `horario`,`notificacao`.`avisado` AS `avisado`,`notificacao`.`musica` AS `musica`,`notificacao`.`userNotification` AS `userNotification`,`notificacao`.`marcado` AS `marcado`,`notificacao`.`tipo_notificacao` AS `tipo_notificacao`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil`,`tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor`,`tipo`.`detalhes_de_cores` AS `detalhes_de_cores`,`tipo`.`id_usuario_tipo` AS `id_usuario_tipo`,`tipo`.`importancia` AS `importancia`,`image`.`id_image` AS `id_image`,`image`.`image_name` AS `image_name`,`image`.`image_way` AS `image_way`,`image`.`image_bytes` AS `image_bytes`,`image`.`image_user_id` AS `image_user_id`,`image`.`image_postIt_id` AS `image_postIt_id`,`image`.`image_notification_id` AS `image_notification_id`,`image`.`image_size` AS `image_size`,`file`.`id_file` AS `id_file`,`file`.`file_name` AS `file_name`,`file`.`file_way` AS `file_way`,`file`.`file_bytes` AS `file_bytes`,`file`.`file_postIt_id` AS `file_postIt_id`,`file`.`file_notification_id` AS `file_notification_id`,`file`.`file_size` AS `file_size` from ((((`notificacao` left join `usuario` on((`notificacao`.`userNotification` = `usuario`.`id`))) left join `tipo` on((`notificacao`.`tipo_notificacao` = `tipo`.`id_tipo`))) left join `image` on((`image`.`image_notification_id` = `notificacao`.`idNotific`))) left join `file` on((`file`.`file_notification_id` = `notificacao`.`idNotific`))) union select `notificacao`.`idNotific` AS `idNotific`,`notificacao`.`titulo` AS `titulo`,`notificacao`.`descricao` AS `descricao`,`notificacao`.`horario` AS `horario`,`notificacao`.`avisado` AS `avisado`,`notificacao`.`musica` AS `musica`,`notificacao`.`userNotification` AS `userNotification`,`notificacao`.`marcado` AS `marcado`,`notificacao`.`tipo_notificacao` AS `tipo_notificacao`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil`,`tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor`,`tipo`.`detalhes_de_cores` AS `detalhes_de_cores`,`tipo`.`id_usuario_tipo` AS `id_usuario_tipo`,`tipo`.`importancia` AS `importancia`,`image`.`id_image` AS `id_image`,`image`.`image_name` AS `image_name`,`image`.`image_way` AS `image_way`,`image`.`image_bytes` AS `image_bytes`,`image`.`image_user_id` AS `image_user_id`,`image`.`image_postIt_id` AS `image_postIt_id`,`image`.`image_notification_id` AS `image_notification_id`,`image`.`image_size` AS `image_size`,`file`.`id_file` AS `id_file`,`file`.`file_name` AS `file_name`,`file`.`file_way` AS `file_way`,`file`.`file_bytes` AS `file_bytes`,`file`.`file_postIt_id` AS `file_postIt_id`,`file`.`file_notification_id` AS `file_notification_id`,`file`.`file_size` AS `file_size` from (`file` left join (`image` left join ((`notificacao` left join `usuario` on((`notificacao`.`userNotification` = `usuario`.`id`))) left join `tipo` on((`notificacao`.`tipo_notificacao` = `tipo`.`id_tipo`))) on((`image`.`image_notification_id` = `notificacao`.`idNotific`))) on((`file`.`file_notification_id` = `notificacao`.`idNotific`))) */;
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
/*!50001 VIEW `postit_from_user` AS select `postit`.`idPostIt` AS `idPostIt`,`postit`.`title` AS `title`,`postit`.`body` AS `body`,`postit`.`user_postit` AS `user_postit`,`postit`.`type_postit` AS `type_postit`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil`,`tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor`,`tipo`.`detalhes_de_cores` AS `detalhes_de_cores`,`tipo`.`id_usuario_tipo` AS `id_usuario_tipo`,`tipo`.`importancia` AS `importancia`,`image`.`id_image` AS `id_image`,`image`.`image_name` AS `image_name`,`image`.`image_way` AS `image_way`,`image`.`image_bytes` AS `image_bytes`,`image`.`image_user_id` AS `image_user_id`,`image`.`image_postIt_id` AS `image_postIt_id`,`image`.`image_notification_id` AS `image_notification_id`,`image`.`image_size` AS `image_size`,`file`.`id_file` AS `id_file`,`file`.`file_name` AS `file_name`,`file`.`file_way` AS `file_way`,`file`.`file_bytes` AS `file_bytes`,`file`.`file_postIt_id` AS `file_postIt_id`,`file`.`file_notification_id` AS `file_notification_id`,`file`.`file_size` AS `file_size` from ((((`postit` join `usuario` on((`postit`.`user_postit` = `usuario`.`id`))) left join `tipo` on((`postit`.`type_postit` = `tipo`.`id_tipo`))) left join `image` on((`image`.`image_postIt_id` = `postit`.`idPostIt`))) left join `file` on((`file`.`file_postIt_id` = `postit`.`idPostIt`))) union select `postit`.`idPostIt` AS `idPostIt`,`postit`.`title` AS `title`,`postit`.`body` AS `body`,`postit`.`user_postit` AS `user_postit`,`postit`.`type_postit` AS `type_postit`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil`,`tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor`,`tipo`.`detalhes_de_cores` AS `detalhes_de_cores`,`tipo`.`id_usuario_tipo` AS `id_usuario_tipo`,`tipo`.`importancia` AS `importancia`,`image`.`id_image` AS `id_image`,`image`.`image_name` AS `image_name`,`image`.`image_way` AS `image_way`,`image`.`image_bytes` AS `image_bytes`,`image`.`image_user_id` AS `image_user_id`,`image`.`image_postIt_id` AS `image_postIt_id`,`image`.`image_notification_id` AS `image_notification_id`,`image`.`image_size` AS `image_size`,`file`.`id_file` AS `id_file`,`file`.`file_name` AS `file_name`,`file`.`file_way` AS `file_way`,`file`.`file_bytes` AS `file_bytes`,`file`.`file_postIt_id` AS `file_postIt_id`,`file`.`file_notification_id` AS `file_notification_id`,`file`.`file_size` AS `file_size` from (`file` left join (`image` left join ((`postit` join `usuario` on((`postit`.`user_postit` = `usuario`.`id`))) left join `tipo` on((`postit`.`type_postit` = `tipo`.`id_tipo`))) on((`image`.`image_postIt_id` = `postit`.`idPostIt`))) on((`file`.`file_postIt_id` = `postit`.`idPostIt`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `types_from_user`
--

/*!50001 DROP VIEW IF EXISTS `types_from_user`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `types_from_user` AS select `tipo`.`id_tipo` AS `id_tipo`,`tipo`.`tipo` AS `tipo`,`tipo`.`cor` AS `cor`,`tipo`.`detalhes_de_cores` AS `detalhes_de_cores`,`tipo`.`id_usuario_tipo` AS `id_usuario_tipo`,`tipo`.`importancia` AS `importancia`,`usuario`.`id` AS `id`,`usuario`.`nome` AS `nome`,`usuario`.`sexo` AS `sexo`,`usuario`.`dataNascimento` AS `dataNascimento`,`usuario`.`telefone` AS `telefone`,`usuario`.`email` AS `email`,`usuario`.`cpf` AS `cpf`,`usuario`.`senha` AS `senha`,`usuario`.`imagePerfil` AS `imagePerfil` from (`tipo` join `usuario` on((`tipo`.`id_usuario_tipo` = `usuario`.`id`))) */;
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

-- Dump completed on 2020-06-22 18:22:09

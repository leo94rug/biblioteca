-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2016 at 12:57 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bibliotecaoop`
--

-- --------------------------------------------------------

--
-- Table structure for table `opera`
--

CREATE TABLE `opera` (
  `id` int(11) NOT NULL,
  `titolo` varchar(256) NOT NULL,
  `descrizione` text NOT NULL,
  `autore` varchar(256) NOT NULL,
  `data_aggiunta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pagina`
--

CREATE TABLE `pagina` (
  `tipo` varchar(100) DEFAULT NULL,
  `url_img` varchar(100) DEFAULT NULL,
  `size` double DEFAULT NULL,
  `id` int(11) NOT NULL,
  `id_opera` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `numero_pag` int(11) NOT NULL,
  `contenuto` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

CREATE TABLE `utente` (
  `email` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `tipo` int(10) NOT NULL DEFAULT '0',
  `indirizzo` varchar(100) DEFAULT NULL,
  `professione` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabella degli utenti';

--
-- Dumping data for table `utente`
--

INSERT INTO `utente` (`email`, `pwd`, `nome`, `cognome`, `tipo`, `indirizzo`, `professione`) VALUES
('a@a.it', 'dddd', 'matte', 'ricci', 1, 'roma', 'studente'),
('carlo@martello.fr', 'a', 'carlo', 'martello', 1, 'parigi,via della saggezza', 're'),
('master_94@virgilio.it', '000000', 'Leonardo', 'Ruggeri', 2, 'civitanova', 'studente');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `opera`
--
ALTER TABLE `opera`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pagina`
--
ALTER TABLE `pagina`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `opera`
--
ALTER TABLE `opera`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pagina`
--
ALTER TABLE `pagina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

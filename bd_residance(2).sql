-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 10 mai 2024 à 01:02
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_residance`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(50) NOT NULL,
  `nom_utilisateur` varchar(50) DEFAULT NULL,
  `mot_de_passe` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `nom_utilisateur`, `mot_de_passe`) VALUES
('ad1', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `batiment`
--

CREATE TABLE `batiment` (
  `id_batiment` varchar(50) NOT NULL,
  `genre` varchar(1) DEFAULT NULL,
  `etat` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `batiment`
--

INSERT INTO `batiment` (`id_batiment`, `genre`, `etat`) VALUES
('F1', 'F', 1),
('G1', 'G', 1),
('G2', 'G', 0),
('G3', 'G', 1);

--
-- Déclencheurs `batiment`
--
DELIMITER $$
CREATE TRIGGER `update_etat_batiment` AFTER UPDATE ON `batiment` FOR EACH ROW BEGIN
    
    IF OLD.etat <> NEW.etat THEN
        UPDATE etage SET etage.etat = NEW.etat WHERE etage.id_batiment = NEW.id_batiment;
        
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_genre_batiment` AFTER UPDATE ON `batiment` FOR EACH ROW BEGIN
    IF OLD.genre <> NEW.genre THEN
        UPDATE etage SET etage.id_etage = CONCAT(etage.id_batiment,'E',etage.num) WHERE etage.id_batiment = NEW.id_batiment;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `id_chambre` varchar(50) NOT NULL,
  `num` int(11) DEFAULT NULL,
  `etat` tinyint(4) DEFAULT NULL,
  `id_etage` varchar(50) DEFAULT NULL,
  `reservee` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `chambre`
--

INSERT INTO `chambre` (`id_chambre`, `num`, `etat`, `id_etage`, `reservee`) VALUES
('F1E1C1', 1, 1, 'F1E1', 1),
('F1E1C2', 2, 1, 'F1E1', 1),
('F1E2C1', 1, 1, 'F1E2', 1),
('G3E1C1', 1, 1, 'G3E1', 0),
('G3E1C2', 2, 1, 'G3E1', 0);

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `id_employee` varchar(50) NOT NULL,
  `fonction` varchar(50) DEFAULT NULL,
  `etat` tinyint(1) DEFAULT NULL,
  `cin` varchar(50) DEFAULT NULL,
  `id_salire` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`id_employee`, `fonction`, `etat`, `cin`, `id_salire`) VALUES
('ad1', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etage`
--

CREATE TABLE `etage` (
  `id_etage` varchar(50) NOT NULL,
  `etat` tinyint(4) DEFAULT NULL,
  `id_batiment` varchar(50) DEFAULT NULL,
  `num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etage`
--

INSERT INTO `etage` (`id_etage`, `etat`, `id_batiment`, `num`) VALUES
('F1E1', 1, 'F1', 1),
('F1E2', 1, 'F1', 2),
('G1E1', 1, 'G1', 1),
('G3E1', 1, 'G3', 1);

--
-- Déclencheurs `etage`
--
DELIMITER $$
CREATE TRIGGER ` update_etat_chambres` AFTER UPDATE ON `etage` FOR EACH ROW IF OLD.etat <> NEW.etat THEN
        UPDATE chambre SET chambre.etat = NEW.etat WHERE chambre.id_etage = NEW.id_etage;
        
END IF
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_genre_chambre` AFTER UPDATE ON `etage` FOR EACH ROW BEGIN
    IF OLD.id_etage <> NEW.id_etage THEN
        UPDATE chambre SET chambre.id_chambre = CONCAT(NEW.id_etage,'C',chambre.num) WHERE chambre.id_etage = NEW.id_etage;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id_personne` varchar(50) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `naissance` date DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `genre` varchar(1) DEFAULT NULL,
  `date_entree` varchar(15) DEFAULT NULL,
  `date_sortie` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id_personne`, `nom`, `prenom`, `naissance`, `tel`, `adresse`, `mail`, `genre`, `date_entree`, `date_sortie`) VALUES
('ad1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('er1', 'ernom1', 'erprenom1', '2003-04-25', 8138635, 'iadresse1', 'ernom1@gmail.com', 'F', '2003-04-25', '2003-04-25'),
('er2', 'ernom2', 'erprenom2', '2001-07-28', 7215966, 'eradresse2', 'ernom2@gmail.com', 'F', '2023-10-15', '2024-05-28'),
('rl1', 'jaafar', 'wafa', '2003-09-18', 6239233, 'Agadir', 'wafa@gmail.com', 'F', '2023-11-18', '2024-06-18'),
('rl2', 'nom2', 'prenom2', '2002-12-09', 6139394, 'adresse2', 'nom2@gmail.com', 'M', '2023-12-07', '2024-06-07'),
('UD14142', 'yadani', 'adnane', '2002-05-20', 641681499, 'agadir', 'yadani.adnane20@gmail.com', 'M', '2024-05-02', '2024-06-02');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `date_reservation` date DEFAULT NULL,
  `id_residant` varchar(50) DEFAULT NULL,
  `id_chambre` varchar(50) DEFAULT NULL,
  `payee` tinyint(1) DEFAULT NULL,
  `recu` varchar(50) DEFAULT NULL,
  `frais_de_reservation` float(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `residant`
--

CREATE TABLE `residant` (
  `id_residant` varchar(50) NOT NULL,
  `id_chambre` varchar(50) DEFAULT NULL,
  `telgarant` int(11) DEFAULT NULL,
  `programmeDetudes` varchar(50) DEFAULT NULL,
  `etat` varchar(50) DEFAULT NULL,
  `universite` varchar(50) DEFAULT NULL,
  `reservationNonPayees` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `residant`
--

INSERT INTO `residant` (`id_residant`, `id_chambre`, `telgarant`, `programmeDetudes`, `etat`, `universite`, `reservationNonPayees`) VALUES
('er1', 'F1E1C2', 6154891, 'Doctorat', 'Active', 'fsa', NULL),
('er2', 'F1E2C1', 7163932, 'Doctorat', 'Active', 'flsh', NULL),
('rl1', 'F1E1C1', 6834602, 'Licence', 'Active', 'fsa', NULL),
('rl2', 'G3E1C1', 629024, 'Master', 'Actif', 'flsh', NULL),
('UD14142', 'G3E1C2', 668510500, 'Doctorat', 'Actif', 'uiz', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `residantetranger`
--

CREATE TABLE `residantetranger` (
  `id` varchar(50) NOT NULL,
  `numPassport` varchar(50) DEFAULT NULL,
  `pays` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `residantetranger`
--

INSERT INTO `residantetranger` (`id`, `numPassport`, `pays`) VALUES
('er1', 'JA46810', 'pays1'),
('er2', 'G16393J', 'erpays2');

-- --------------------------------------------------------

--
-- Structure de la table `residantlocal`
--

CREATE TABLE `residantlocal` (
  `id` varchar(50) NOT NULL,
  `cne` varchar(50) DEFAULT NULL,
  `cin` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `residantlocal`
--

INSERT INTO `residantlocal` (`id`, `cne`, `cin`) VALUES
('rl1', 'D1782490', 'JC484'),
('rl2', 'D1128493', 'JC6428'),
('UD14142', 'M131524628', 'UD14142');

-- --------------------------------------------------------

--
-- Structure de la table `salaire`
--

CREATE TABLE `salaire` (
  `id_salire` varchar(50) NOT NULL,
  `date` varchar(50) DEFAULT NULL,
  `id_employee` varchar(50) DEFAULT NULL,
  `regle` tinyint(1) DEFAULT NULL,
  `recu` varchar(50) DEFAULT NULL,
  `montant` float(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Index pour la table `batiment`
--
ALTER TABLE `batiment`
  ADD PRIMARY KEY (`id_batiment`);

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id_chambre`),
  ADD KEY `FK_Chambre_Etage` (`id_etage`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id_employee`),
  ADD KEY `FK_Slaire_Employee` (`id_salire`);

--
-- Index pour la table `etage`
--
ALTER TABLE `etage`
  ADD PRIMARY KEY (`id_etage`),
  ADD KEY `FK_Etage_Batiment` (`id_batiment`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id_personne`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `FK_Reservation_Chambre` (`id_chambre`),
  ADD KEY `FK_Reservation_Residant` (`id_residant`);

--
-- Index pour la table `residant`
--
ALTER TABLE `residant`
  ADD PRIMARY KEY (`id_residant`);

--
-- Index pour la table `residantetranger`
--
ALTER TABLE `residantetranger`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `residantlocal`
--
ALTER TABLE `residantlocal`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `salaire`
--
ALTER TABLE `salaire`
  ADD PRIMARY KEY (`id_salire`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK_Admin_Employee` FOREIGN KEY (`id_admin`) REFERENCES `employee` (`id_employee`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD CONSTRAINT `FK_Chambre_Etage` FOREIGN KEY (`id_etage`) REFERENCES `etage` (`id_etage`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_Employee_Personne` FOREIGN KEY (`id_employee`) REFERENCES `personne` (`id_personne`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Slaire_Employee` FOREIGN KEY (`id_salire`) REFERENCES `salaire` (`id_salire`);

--
-- Contraintes pour la table `etage`
--
ALTER TABLE `etage`
  ADD CONSTRAINT `FK_Etage_Batiment` FOREIGN KEY (`id_batiment`) REFERENCES `batiment` (`id_batiment`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_Reservation_Chambre` FOREIGN KEY (`id_chambre`) REFERENCES `chambre` (`id_chambre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reservation_Residant` FOREIGN KEY (`id_residant`) REFERENCES `residant` (`id_residant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `residant`
--
ALTER TABLE `residant`
  ADD CONSTRAINT `FK_Residant_Personne` FOREIGN KEY (`id_residant`) REFERENCES `personne` (`id_personne`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `residantetranger`
--
ALTER TABLE `residantetranger`
  ADD CONSTRAINT `FK_ResidantEtranger_Residant` FOREIGN KEY (`id`) REFERENCES `residant` (`id_residant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `residantlocal`
--
ALTER TABLE `residantlocal`
  ADD CONSTRAINT `FK_ResidantLocal_Residant` FOREIGN KEY (`id`) REFERENCES `residant` (`id_residant`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

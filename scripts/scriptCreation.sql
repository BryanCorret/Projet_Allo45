-- A n'executer que si vous avez les droit de création/destruction d'une base de données.

drop database sondage;
create database sondage;
use sondage;

CREATE TABLE `CLIENT` (
  `numC` Decimal(5),
  `raisonSoc` Varchar(30),
  `adresse1` Varchar(30),
  `adresse2` Varchar(30),
  `CodePostal` Decimal(5),
  `Ville` Varchar(30),
  `Telephone` Varchar(20),
  `email` Varchar(30),
  PRIMARY KEY(`numC`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `PANEL` (
  `idPan` Decimal(3),
  `nomPan` Varchar(30),
  PRIMARY KEY(`idPan`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `QUESTIONNAIRE` (
  `idQ` Decimal(6),
  `Titre` Varchar(30),
  `Etat` Char(1),
  `numC` Decimal(5),
  `idU` Decimal(3),
  `idPan` Decimal(3),
  PRIMARY KEY(`idQ`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `CONSTITUER` (
  `numSond` Decimal(6),
  `idPan` Decimal(3),
  PRIMARY KEY(`numSond`, `idPan`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `INTERROGER` (
  `idU` Decimal(3),
  `numSond` Decimal(6),
  `idQ` Decimal(6),
  PRIMARY KEY(`idU`, `numSond`, `idQ`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `SONDE` (
  `numSond` Decimal(6),
  `nomSond` Varchar(30),
  `prenomSond` Varchar(30),
  `dateNaisSond` Date,
  `telephoneSond` Varchar(10),
  `idC` Char(3),
  PRIMARY KEY(`numSond`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `UTILISATEUR` (
  `idU` Decimal(3),
  `nomU` Varchar(30),
  `prenomU` Varchar(30),
  `login` Varchar(15),
  `motDePasse` Varchar(50),
  `idR` Char(1),
  PRIMARY KEY(`idU`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `ROLEUTIL` (
  `idR` Char(1),
  `nomR` Varchar(30),
  PRIMARY KEY(`idR`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `VALPOSSIBLE` (
  `idQ` Decimal(6),
  `numQ` Decimal(2),
  `idV` Decimal(2),
  `Valeur` Text,
  PRIMARY KEY(`idQ`, `numQ`, `idV`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `QUESTION` (
  `idQ` Decimal(6),
  `numQ` Decimal(2),
  `texteQ` Text,
  `MaxVal` Decimal(3),
  `idT` Char(1),
  PRIMARY KEY(`idQ`, `numQ`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `CARACTERISTIQUE` (
  `idC` Char(3),
  `sexe` Char(1),
  `idTr` Char(1),
  `idCat` Char(1),
  PRIMARY KEY(`idC`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `REPONDRE` (
  `idQ` Decimal(6),
  `numQ` Decimal(2),
  `idC` Char(3),
  `valeur` Varchar(30)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `CATEGORIE` (
  `idCat` Char(1),
  `intituleCat` Varchar(50),
  PRIMARY KEY(`idCat`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `TYPEQUESTION` (
  `idT` Char(1),
  `intituleT` VarChar(30),
  `typeReponse` Varchar(10),
  PRIMARY KEY(`idT`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `TRANCHE` (
  `idTr` char(1),
  `valDebut` Decimal(3),
  `valFin` Decimal(3),
  PRIMARY KEY(`idTr`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


ALTER TABLE `QUESTIONNAIRE` ADD FOREIGN KEY (`numC`) REFERENCES `CLIENT` (`numC`);
ALTER TABLE `QUESTIONNAIRE` ADD FOREIGN KEY (`idU`) REFERENCES `UTILISATEUR` (`idU`);
ALTER TABLE `QUESTIONNAIRE` ADD FOREIGN KEY (`idPan`) REFERENCES `PANEL` (`idPan`);
ALTER TABLE `CONSTITUER` ADD FOREIGN KEY (`numSond`) REFERENCES `SONDE` (`numSond`);
ALTER TABLE `CONSTITUER` ADD FOREIGN KEY (`idPan`) REFERENCES `PANEL` (`idPan`);
ALTER TABLE `INTERROGER` ADD FOREIGN KEY (`idU`) REFERENCES `UTILISATEUR` (`idU`);
ALTER TABLE `INTERROGER` ADD FOREIGN KEY (`numSond`) REFERENCES `SONDE` (`numSond`);
ALTER TABLE `INTERROGER` ADD FOREIGN KEY (`idQ`) REFERENCES `QUESTIONNAIRE` (`idQ`);
ALTER TABLE `SONDE` ADD FOREIGN KEY (`idC`) REFERENCES `CARACTERISTIQUE` (`idC`);
ALTER TABLE `UTILISATEUR` ADD FOREIGN KEY (`idR`) REFERENCES `ROLEUTIL` (`idR`);
ALTER TABLE `VALPOSSIBLE` ADD FOREIGN KEY (`idQ`,`numQ`) REFERENCES `QUESTION` (`idQ`,`numQ`);
ALTER TABLE `QUESTION` ADD FOREIGN KEY (`idT`) REFERENCES `TYPEQUESTION` (`idT`);
ALTER TABLE `QUESTION` ADD FOREIGN KEY (`idQ`) REFERENCES `QUESTIONNAIRE` (`idQ`);
ALTER TABLE `REPONDRE` ADD FOREIGN KEY (`idC`) REFERENCES `CARACTERISTIQUE` (`idC`);
ALTER TABLE `REPONDRE` ADD FOREIGN KEY (`idQ`,`numQ`) REFERENCES `QUESTION` (`idQ`,`numQ`);
ALTER TABLE `CARACTERISTIQUE` ADD FOREIGN KEY (`idTr`) REFERENCES `TRANCHE` (`idTr`);
ALTER TABLE `CARACTERISTIQUE` ADD FOREIGN KEY (`idCat`) REFERENCES `CATEGORIE` (`idCat`);

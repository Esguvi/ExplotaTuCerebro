-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2024 a las 18:25:21
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_juego`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

CREATE TABLE `pregunta` (
  `idPregunta` int(10) NOT NULL,
  `pregunta` varchar(200) NOT NULL,
  `respuesta1` varchar(200) NOT NULL,
  `respuesta2` varchar(200) NOT NULL,
  `respuesta3` varchar(200) NOT NULL,
  `respuestaCorrecta` varchar(200) NOT NULL,
  `idTest` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `pregunta`
--

INSERT INTO `pregunta` (`idPregunta`, `pregunta`, `respuesta1`, `respuesta2`, `respuesta3`, `respuestaCorrecta`, `idTest`) VALUES
(1, 'España está formada por...', '...toda la Península Ibérica y Baleares.', '...toda la Península Ibérica y Canarias.', '...gran parte de la Península Ibérica, Baleares y Canarias.', '...gran parte de la Península Ibérica, Baleares, Canarias, Ceuta y Melilla.', 1),
(2, 'Gran parte de los territorios peninsulares y de los archipiélagos son...', '...llanos.', '...poco montañosos.\r\n', '...semillanos.\r\n', '...muy montañosos.\r\n', 1),
(3, 'La zona central de la Península está ocupada por...', '...montañas.', '...un río muy importante.', '...se alternan llanuras y montañas.', '...una gran meseta.', 1),
(4, 'La meseta es...', '...una gran llanura deprimida.', '...una sierra.', '...una cordillera.', '...una gran llanura elevada.', 1),
(5, 'Los sistemas montañosos que rodean la Meseta Norte son...', '...Sistema Central, Montes de Toledo, Cordillera Cantábrica y Macizo Galaico.', '...Sistema Central, Pirineos, Cordillera Cantábrica y Sistemas Béticos.\r\n', '...Sistema Central, Pirineos, Cordillera Cantábrica y Macizo Galaico.', '...Sistema central, Sistema Ibérico, Cordillera Cantábrica y Macizo. Galaico.', 1),
(6, 'Los sistemas montañosos más importantes de España son...', '...la Cordillera Cantábrica y los Pirineos.', '...los Pirineos y el Sistema Central.', '...los Sistemas Béticos y el Sistema Ibérico.', '...los Pirineos y los Sistemas Béticos.', 1),
(7, 'La Submeseta Sur está atravesada por...', '...el Sistema Central.', '...el Sistema Ibérico.', '...el Macizo Galaico.', '...los Montes de Toledo.', 1),
(8, 'El valle más importante que atraviesa Andalucía es...', '...el valle del Duero.', '...el valle del Ebro.', '...el valle del Tajo.', '...el valle del Guadalquivir.', 1),
(9, 'La Costa Cantábrica...', '...es acantilada y muy recortada por numerosas rías.', '...es muy recta y baja, con amplias zonas de marismas y playas.', '...es recta y baja en la zona central.', '...es recta y acantilada con playas, rías y bahías.', 1),
(10, 'Las Islas Canarias tienen un relieve...', '...montañoso.', '...llano.', '...donde predominan las mesetas.', '...de origen volcánico y muy montañoso.', 1),
(11, 'El pico de mayor altura de la Península Ibérica se encuentra en...', '...los Pirineos.', '...la Cordillera Cantábrica.', '...el Sistema Ibérico.', '...Sierra Nevada.', 1),
(12, 'El río mas largo de España es...', '...el Ebro.', '...el Guadalquivir.', '...el Miño.', '...el Tajo', 1),
(13, 'El río más caudaloso de la Península es...', '...el Tajo.', '...el Guadalquivir.', '...el Guadiana.', '...el Ebro.', 1),
(14, '¿Qué cadena montañosa hay al sur de la Submeseta Sur?', 'Los Sistemas Béticos.', 'El Sistema Ibérico.', 'El Macizo Galaico.', 'Sierra Morena.', 1),
(15, '¿Qué río importante divide en dos a la Submeseta Norte?', 'El Tajo.', 'El Guadalquivir.', 'El Segura.', 'El Duero.', 1),
(16, '¿Qué sistema montañoso bordea por la parte este la Submeseta Norte?', 'Los Montes Vascos.', 'Sierra Morena.', 'El Sistema Central.', 'El Sistema Ibérico.', 1),
(17, '¿A qué sistema montañoso pertenece Sierra Nevada?', 'Al Sistema Ibérico.', 'Al Sistema Central.', 'Al Sistema Pirenaico.', 'Al Sistema Penibético.', 1),
(18, '¿Cómo se llama el pico más alto de los Pirineos?', 'Mulhacén.', 'Veleta.', 'Alcazaba.', 'Aneto.', 1),
(19, '¿A qué archipiélago pertenece la isla de Ibiza?', 'Azores.', 'Canarias.', 'Madeira.', 'Baleares.', 1),
(20, '¿Por qué comunidad autónoma pasa y desemboca el río Miño?', 'Extremadura.', 'Cataluña.', 'Andalucía.', 'Galicia.', 1),
(21, '¿Cuál de estos animales no es doméstico?', 'Perro', 'Gato', 'Gallina', 'Mono', 4),
(22, '¿Cuál de estos animales es ovíparo?', 'Mono', 'Elefante', 'Perro', 'Gallina', 4),
(23, '¿Qué significa que un animal esté en \"peligro de extinción?', 'Que no tiene extintores', 'Que es muy peligroso', 'Que no podemos tocarlo', 'Que va a desaparecer', 4),
(24, '¿Cuál de estos animales tiene escamas?', 'Caballo', 'Ratón', 'Jirafa', 'Serpiente', 4),
(25, '¿Cuál de estos animales no es salvaje?', 'Jirafa', 'Elefante', 'Lince', 'Cerdo', 4),
(26, '¿Cuál es el planeta que tiene anillos?', 'Mercurio', 'Urano', 'Neptuno', 'Saturno', 4),
(27, '¿Cuál es el planeta rojo?', 'Mercurio', 'Venus', 'La Tierra', 'Marte', 4),
(28, '¿Cuál es el único planeta del universo que se conoce que existe vida?', 'Urano', 'Mercurio', 'Marte', 'La Tierra', 4),
(29, '¿Cuál es el el planeta más grande del sistema solar?', 'Mercurio', 'Satuerno', 'Urano', 'Júpiter', 4),
(30, '¿Cuál es el es el octavo y último planeta del sistema solar?', 'Júpiter', 'Urano', 'Plutón', 'Neptuno', 4),
(31, '¿Cuáles son los tres tipos de músculos que hay en el cuerpo humano?', 'Músculo liso, músculo rugoso y músculo cardíaco', 'Músculo voluntario, músculo liso y músculo esquelético', 'Músculo voluntario, músculo involuntario y músculo esquelético', 'Músculo liso, músculo cardíaco y músculo esquelético', 2),
(32, '¿Cuántos huesos tenemos en el cuerpo humano?', '150', '470', '340', '206', 2),
(33, '¿Cuántas vértebras componen la columna vertebral?', '20', '12', '18', '24', 2),
(34, '¿Cuántas regiones se diferencian en la columna vertebral?', '3 regiones (dorsal, sacro, clavícula)', '4 regiones(dorsal, sacro, lumbar, costillas)', '4 regiones (cervical, costillas, esternón, cadera)', '5 regiones (cervical, dorsal, lumbar, sacro, coxis)', 2),
(35, '¿Cuál es la función principal del bíceps?', 'Extensión del codo', 'Rotación externa del hombro', 'Rotación interna del hombro', 'Flexión del codo', 2),
(36, '¿Cuál es el hueso más largo del cuerpo?', 'Peroné', 'Tibia', 'Radio', 'Fémur', 2),
(37, '¿Cuál es la función principal del cuádriceps?', 'Flexión de rodilla', 'Rotación interna de rodilla', 'Rotación externa de rodilla', 'Extensión de rodilla', 2),
(38, '¿Cuál es la función de los isquiotibiales?', 'Rotación interna de rodilla', 'Rotación externa de rodilla', 'Extensión de rodilla', 'Flexión de rodilla', 2),
(39, '¿Qué no se debe se debe hacer al aplicar  primeros auxilios?', 'Llamar por teléfono o pedir ayuda médica', 'Evaluar con rapidez la situación', 'Acostar a un accidentado inconsciente en posición lateral de seguridad', 'Manipular una herida grave', 2),
(40, '¿Cuáles son los principios específicos del calentamiento?', 'Globalidad, especificidad, variedad, progresión y volumen', 'Generalidad, especificidad, duración, volumen y progresión', 'Globalidad, independencia, progresión y duración', 'Globalidad, especificidad, variedad, progresión y duración', 2),
(41, '¿Quién fue el primer español en alcanzar el nº1 en el ranking de la ATP?', 'Rafael Nadal', 'Manuel Orantes', 'Manolo Santana', 'Carlos Moyá', 3),
(42, '¿Quién fue el primer español en jugar en la NBA?', 'Pau Gasol', 'Rudy Fernández', 'Fernando Romay', 'Fernando Martín', 3),
(43, '¿Quién es el jugador de la selección española de fútbol que ha disputado más partidos oficiales?', 'Andoni Zubizarreta', 'Raúl González', 'Julio Salinas', 'Iker Casillas', 3),
(44, '¿A que altura se encuentra el aro de baloncesto?', '2,95 metros', '2,90 metros', '3 metros', '3,05 metros', 3),
(45, '¿Cuáles son los puntos de medición de la frecuencia cardíaca en nuestro cuerpo?', 'En la aorta y la carótida', 'En la arteria radial y la aorta', 'En la arteria radial y la carótida', 'En la arteria radial, la aorta y la carótida', 2),
(46, '¿Cuáles son los métodos de trabajo para la resistencia aeróbica?', 'Método fraccionado e isométrico', 'Método continuo e interválico', 'Método interválico e isométrico', 'Método continuo y fraccionado', 2),
(47, '¿Cuáles son las cualidades coordinativas?', 'Velocidad, flexibilidad y coordinación', 'Agilidad, equilibrio y velocidad', 'Agilidad, equilibrio y flexibilidad', 'Agilidad, equilibrio y coordinación', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `test`
--

CREATE TABLE `test` (
  `idTest` int(10) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `categoria` varchar(100) NOT NULL,
  `numPreguntas` int(10) NOT NULL,
  `autor` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `test`
--

INSERT INTO `test` (`idTest`, `titulo`, `categoria`, `numPreguntas`, `autor`) VALUES
(1, 'El relieve de España', 'Geografía', 20, 'Encarnación García Santos'),
(2, 'El cuerpo humano', 'Anatomía', 13, 'Pilar Brito Cabrera'),
(3, 'Deporte', 'Deporte', 3, 'Marta Fernández'),
(4, 'Ciencias Naturales', 'Ciencias Naturales', 10, 'María Rosario Arias Quirós'),
(5, 'Historia', 'Historia', 15, 'María Rosario Arias Quirós');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(9, 'Victor', 'victor@gmail.com', '12345'),
(10, 'Paco', 'lokmegustaes@gmail.com', 'paco123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  `puntosTotales` int(50) NOT NULL,
  `ultimaConexion` date NOT NULL,
  `numTestRealizados` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD PRIMARY KEY (`idPregunta`),
  ADD KEY `idTest_pregunta` (`idTest`);

--
-- Indices de la tabla `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`idTest`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  MODIFY `idPregunta` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `test`
--
ALTER TABLE `test`
  MODIFY `idTest` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(10) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD CONSTRAINT `idTest_pregunta` FOREIGN KEY (`idTest`) REFERENCES `test` (`idTest`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-05-2023 a las 21:06:43
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `studycircledb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activities`
--

CREATE TABLE `activities` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `time_activity` bigint(20) NOT NULL,
  `id_course` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `activities`
--

INSERT INTO `activities` (`id`, `name`, `description`, `time_activity`, `id_course`, `created_at`) VALUES
(1, 'primera actividad', 'descripcion primera actividad', 1682932323717, 10, 1683230845730);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `activity_alerts`
--

CREATE TABLE `activity_alerts` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  `id_activity` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `friendship_alerts`
--

CREATE TABLE `friendship_alerts` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  `id_friendship` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `announcements`
--

CREATE TABLE `announcements` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `reason` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calendar_events`
--

CREATE TABLE `calendar_events` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `time_event` bigint(20) NOT NULL,
  `user_profile` varchar(25) NOT NULL,
  `id_activity` int(11) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `confirmation_token`
--

CREATE TABLE `confirmation_token` (
  `id` int(11) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `expires_at` bigint(20) DEFAULT NULL,
  `confirmed_at` bigint(20) DEFAULT NULL,
  `is_valid` tinyint(1) NOT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `confirmation_token`
--

INSERT INTO `confirmation_token` (`id`, `token`, `created_at`, `expires_at`, `confirmed_at`, `is_valid`, `id_user`) VALUES
(20, '9d7d6871-bb78-4814-97fd-571c9017c6c7', 1680957383707, 1680958283678, 1680957418714, 0, 24),
(28, 'c24c5474-ba08-4126-b666-b91a895852e2', 1682787797333, 1682788697333, NULL, 0, 31),
(29, 'bf657115-25a6-4ece-b6e6-5cb5e7bda4f0', 1682787861732, 1682788761732, NULL, 1, 31),
(30, '96ec87bc-8616-40d0-a981-b2b16b060189', 1682880318483, 1682881218483, NULL, 0, 33),
(31, '6e78f75c-c5f8-4772-adb2-e5efe8898bb4', 1682880402807, 1682881302807, NULL, 0, 33),
(32, '395308b9-c6ce-40b8-b1f9-62f6de2e0a1c', 1682880514968, 1682881414968, NULL, 0, 33),
(33, '5451708d-8954-4595-85ce-089bf363231c', 1682880558317, 1682881458317, NULL, 0, 33),
(34, 'a6582d20-4abc-41cc-acd4-e3477c48b7d6', 1682880870002, 1682881770002, NULL, 0, 33),
(35, 'f104a0e5-fb0c-44fb-bb8f-f40798ccb9f8', 1682881144554, 1682882044554, 1682881183866, 1, 33),
(36, 'b95dc41e-f51c-43b1-b3cb-c1eeffa93e6c', 1682932323757, 1682933223757, NULL, 0, 34),
(37, '4f306c00-e9b3-4029-bbcf-3a7982b77b73', 1682932530519, 1682933430519, NULL, 0, 34),
(38, 'fb24dd2b-0f31-42a0-af2e-44eefc520fef', 1682932624358, 1682933524358, 1682932657057, 1, 34),
(52, '8f749156-40be-407d-be68-47af853da5b1', 1682939048780, 1682939948780, NULL, 0, 48),
(53, 'a30e9a19-afc4-40ca-ba77-d2425843dd51', 1683017141413, 1683018041413, 1683017192123, 1, 48),
(60, 'e24811b2-e75d-4584-8453-5df79634d8b1', 1683239825237, 1683240725237, NULL, 0, 53),
(61, '9a759bcd-6f56-4f24-bae5-161900b769e3', 1683239951782, 1683240851782, 1683239980245, 1, 53);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `id_tutor_subject` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `course`
--

INSERT INTO `course` (`id`, `id_tutor_subject`, `title`, `created_at`) VALUES
(10, 24, 'nuevo titulo', 1683122889983);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course_activities`
--

CREATE TABLE `course_activities` (
  `id` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_activity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course_students`
--

CREATE TABLE `course_students` (
  `id` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `course_students`
--

INSERT INTO `course_students` (`id`, `id_course`, `id_student`) VALUES
(23, 10, 4),
(24, 10, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `friendships`
--

CREATE TABLE `friendships` (
  `id` int(11) NOT NULL,
  `id_user1` int(11) NOT NULL,
  `id_user2` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  `friends_from` bigint(20) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role_user`
--

CREATE TABLE `role_user` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `role_user`
--

INSERT INTO `role_user` (`id`, `id_user`, `id_role`) VALUES
(23, 24, 2),
(24, 25, 1),
(25, 26, 1),
(30, 31, 2),
(31, 33, 2),
(47, 34, 2),
(46, 48, 2),
(63, 53, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_study_level` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `students`
--

INSERT INTO `students` (`id`, `id_user`, `id_study_level`, `created_at`) VALUES
(4, 48, 2, 1683018066916),
(12, 53, 2, 1683240004792);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student_subjects`
--

CREATE TABLE `student_subjects` (
  `id` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `student_subjects`
--

INSERT INTO `student_subjects` (`id`, `id_student`, `id_subject`) VALUES
(7, 4, 1),
(8, 4, 2),
(15, 12, 1),
(16, 12, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `study_levels`
--

CREATE TABLE `study_levels` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `study_levels`
--

INSERT INTO `study_levels` (`id`, `name`, `created_at`) VALUES
(2, 'prueba2', 1680987449039),
(3, 'string', 1681042746494),
(4, 'xd2', 1681043303441);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `id_study_level` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `subjects`
--

INSERT INTO `subjects` (`id`, `name`, `id_study_level`, `created_at`) VALUES
(1, 'materia1', 2, 1680972623000),
(2, 'materia2', 4, 1680972623000),
(3, 'materia3', 3, 1680972623000),
(4, 'materia4', 2, 1680972623000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutors`
--

CREATE TABLE `tutors` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tutors`
--

INSERT INTO `tutors` (`id`, `id_user`, `created_at`) VALUES
(34, 24, 1681656197917),
(36, 34, 1683050964598),
(51, 53, 1683240058799);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutor_subjects`
--

CREATE TABLE `tutor_subjects` (
  `id` int(11) NOT NULL,
  `id_tutor` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tutor_subjects`
--

INSERT INTO `tutor_subjects` (`id`, `id_tutor`, `id_subject`) VALUES
(21, 34, 3),
(22, 34, 4),
(23, 36, 1),
(24, 36, 2),
(46, 51, 1),
(47, 51, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `hashpswd` varchar(255) NOT NULL,
  `created_at` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `username`, `full_name`, `email`, `status`, `hashpswd`, `created_at`) VALUES
(24, 'sam1', 'Samuel González Machado', 'samuglezmachado@gmail.com', 'STATUS_ACTIVE', '$2a$10$JaVxA9Y08HEe0YXCUIiz1.njj7FOa1nokZiAQvAiSiNR9QoiKMKmO', 1680957383678),
(25, 'admin', 'admin', 'stdycircleofficial@gmail.com', 'STATUS_ACTIVE', '$2y$10$5C5XrxjkI4aQIDag8wcNa.7hV/KBYgSdcHT6d1vudS0W2wcW53Zou', 1680972623000),
(26, 'string', 'string', 'string@gm.com', 'STATUS_ACTIVE', '$2y$10$BgGhuwMrs6LkoJ0rFlXpQObUhhFPVOeqPRMW0bMkInXh6PLXoOU9C', 1680957399999),
(31, 'pruee2', 'string', 'watek77714@meidecn.com', 'STATUS_PENDING_VERIFICATION', '$2a$10$PVDaqMkgIE2ttx2mfcZZHOQlo.r6R9B79Ld9rLY35aTIpBmMnUAUu', 1682787797283),
(33, 'pruee3', 'string', 'rojog68539@meidecn.com', 'STATUS_ACTIVE', '$2a$10$H4FB9cbdpSEFXwb2p02o0.PIOW5ggs8itTOVkDyVx/a6hI9EHMq7O', 1682880318475),
(34, 'str', 'stri', 'siwobe5230@meidecn.com', 'STATUS_ACTIVE', '$2a$10$/3EzV7ViFbIScIVUcOxogOgf7xAn.hQsz8BkHOETUIttK3MddQPEq', 1682932323717),
(48, 'pruee4', 'samuel', 'bisiboy499@jobbrett.com', 'STATUS_ACTIVE', '$2a$10$m9Dt.56SczW46ualYCnJROG0Ywa/27SKmwST89H130kS8DFGMkhUK', 1682939048709),
(53, 'prueba', 'nombre prueba', 'basal72606@larland.com', 'STATUS_ACTIVE', '$2a$10$/fS8WTzaCSifES4KpDZQX.pP.8sJioVQ9B9yy11C49N3V75NQ3ye6', 1683239825231);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_profiles`
--

CREATE TABLE `user_profiles` (
  `user_profile` varchar(25) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_profiles`
--

INSERT INTO `user_profiles` (`user_profile`, `description`) VALUES
('STUDENT_PROFILE', 'Perfil de usuario alumno'),
('TUTOR_PROFILE', 'Perfil de usuario tutor');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_course` (`id_course`) USING BTREE;

--
-- Indices de la tabla `activity_alerts`
--
ALTER TABLE `activity_alerts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_activity` (`id_activity`),
  ADD KEY `id_user` (`id_user`);


--
-- Indices de la tabla `friendship_alerts`
--
ALTER TABLE `friendship_alerts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_friendship` (`id_friendship`),
  ADD KEY `id_user` (`id_user`);

--
-- Indices de la tabla `announcements`
--
ALTER TABLE `announcements`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_subject` (`id_subject`);

--
-- Indices de la tabla `calendar_events`
--
ALTER TABLE `calendar_events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_activity` (`id_activity`),
  ADD KEY `user_profile` (`user_profile`);

--
-- Indices de la tabla `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`) USING BTREE;

--
-- Indices de la tabla `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_tutor_subject` (`id_tutor_subject`);

--
-- Indices de la tabla `course_activities`
--
ALTER TABLE `course_activities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_course_activity` (`id_course`,`id_activity`),
  ADD KEY `id_activity` (`id_activity`);

--
-- Indices de la tabla `course_students`
--
ALTER TABLE `course_students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_course_student` (`id_course`,`id_student`),
  ADD KEY `id_student` (`id_student`);

--
-- Indices de la tabla `friendships`
--
ALTER TABLE `friendships`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_friendships` (`id_user1`,`id_user2`),
  ADD KEY `id_user2` (`id_user2`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role_user`
--
ALTER TABLE `role_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_role_user` (`id_user`,`id_role`),
  ADD KEY `id_role` (`id_role`);

--
-- Indices de la tabla `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_user` (`id_user`),
  ADD KEY `id_study_level` (`id_study_level`);

--
-- Indices de la tabla `student_subjects`
--
ALTER TABLE `student_subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_student_subject` (`id_student`,`id_subject`),
  ADD KEY `student_subjects_ibfk_2` (`id_subject`);

--
-- Indices de la tabla `study_levels`
--
ALTER TABLE `study_levels`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_study_level` (`id_study_level`);

--
-- Indices de la tabla `tutors`
--
ALTER TABLE `tutors`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- Indices de la tabla `tutor_subjects`
--
ALTER TABLE `tutor_subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_tutor_subject` (`id_tutor`,`id_subject`),
  ADD KEY `tutor_subjects_ibfk_2` (`id_subject`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `user_profiles`
--
ALTER TABLE `user_profiles`
  ADD PRIMARY KEY (`user_profile`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `activities`
--
ALTER TABLE `activities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `activity_alerts`
--
ALTER TABLE `activity_alerts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `friendship_alerts`
--
ALTER TABLE `friendship_alerts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `announcements`
--
ALTER TABLE `announcements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `calendar_events`
--
ALTER TABLE `calendar_events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `confirmation_token`
--
ALTER TABLE `confirmation_token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT de la tabla `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `course_activities`
--
ALTER TABLE `course_activities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `course_students`
--
ALTER TABLE `course_students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `friendships`
--
ALTER TABLE `friendships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `role_user`
--
ALTER TABLE `role_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT de la tabla `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `student_subjects`
--
ALTER TABLE `student_subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `study_levels`
--
ALTER TABLE `study_levels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tutors`
--
ALTER TABLE `tutors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `tutor_subjects`
--
ALTER TABLE `tutor_subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `activities`
--
ALTER TABLE `activities`
  ADD CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`);

--
-- Filtros para la tabla `activity_alerts`
--
ALTER TABLE `activity_alerts`
  ADD CONSTRAINT `activity_alerts_ibfk_1` FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`),
  ADD CONSTRAINT `activity_alerts_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `friendship_alerts`
--
ALTER TABLE `friendship_alerts`
  ADD CONSTRAINT `friendship_alerts_ibfk_1` FOREIGN KEY (`id_friendship`) REFERENCES `friendships` (`id`),
  ADD CONSTRAINT `friendship_alerts_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `announcements`
--
ALTER TABLE `announcements`
  ADD CONSTRAINT `announcements_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `announcements_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);

--
-- Filtros para la tabla `calendar_events`
--
ALTER TABLE `calendar_events`
  ADD CONSTRAINT `calendar_events_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `calendar_events_ibfk_2` FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`),
  ADD CONSTRAINT `calendar_events_ibfk_3` FOREIGN KEY (`user_profile`) REFERENCES `user_profiles` (`user_profile`);

--
-- Filtros para la tabla `confirmation_token`
--
ALTER TABLE `confirmation_token`
  ADD CONSTRAINT `confirmation_token_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`id_tutor_subject`) REFERENCES `tutor_subjects` (`id`);

--
-- Filtros para la tabla `course_activities`
--
ALTER TABLE `course_activities`
  ADD CONSTRAINT `course_activities_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `course_activities_ibfk_2` FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`);

--
-- Filtros para la tabla `course_students`
--
ALTER TABLE `course_students`
  ADD CONSTRAINT `course_students_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `course_students_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `students` (`id`);

--
-- Filtros para la tabla `friendships`
--
ALTER TABLE `friendships`
  ADD CONSTRAINT `friendships_ibfk_1` FOREIGN KEY (`id_user1`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `friendships_ibfk_2` FOREIGN KEY (`id_user2`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `role_user`
--
ALTER TABLE `role_user`
  ADD CONSTRAINT `role_user_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `role_user_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`);

--
-- Filtros para la tabla `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`id_study_level`) REFERENCES `study_levels` (`id`);

--
-- Filtros para la tabla `student_subjects`
--
ALTER TABLE `student_subjects`
  ADD CONSTRAINT `student_subjects_ibfk_1` FOREIGN KEY (`id_student`) REFERENCES `students` (`id`),
  ADD CONSTRAINT `student_subjects_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);

--
-- Filtros para la tabla `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`id_study_level`) REFERENCES `study_levels` (`id`);

--
-- Filtros para la tabla `tutors`
--
ALTER TABLE `tutors`
  ADD CONSTRAINT `tutors_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `tutor_subjects`
--
ALTER TABLE `tutor_subjects`
  ADD CONSTRAINT `tutor_subjects_ibfk_1` FOREIGN KEY (`id_tutor`) REFERENCES `tutors` (`id`),
  ADD CONSTRAINT `tutor_subjects_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

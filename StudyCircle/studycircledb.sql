CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(25) UNIQUE NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL,
  `email_verification_token` varchar(255),
  `email_verified_at` timestamp,
  `email_verification_sent_at` timestamp,
  `id_status` int NOT NULL,
  `hashpswd` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `role_user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_role` int NOT NULL
);

CREATE TABLE `roles` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `rol` varchar(40) NOT NULL
);

CREATE TABLE `user_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

CREATE TABLE `friendships` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user1` int NOT NULL,
  `id_user2` int NOT NULL,
  `id_status` int NOT NULL,
  `friends_from` timestamp,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `friendship_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

CREATE TABLE `students` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user` int UNIQUE NOT NULL,
  `id_study_level` int NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `tutors` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user` int UNIQUE NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `tutor_rates` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_tutor` int NOT NULL,
  `id_subject` int NOT NULL,
  `price_per__hour` decimal(4,2)
);

CREATE TABLE `tutor_subjects` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_tutor` int NOT NULL,
  `id_subject` int NOT NULL
);

CREATE TABLE `study_levels` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `subjects` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `id_study_level` int NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `activities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `id_tutor` int NOT NULL,
  `id_subject` int NOT NULL,
  `id_status` int NOT NULL,
  `time_activity` timestamp NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `activity_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

CREATE TABLE `activities_students` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_activity` int NOT NULL,
  `id_student` int NOT NULL
);

CREATE TABLE `calendar_events` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `time_event` timestamp NOT NULL,
  `id_activity` int,
  `id_tutor` int,
  `id_student` int,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `announcements` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `id_reason` int NOT NULL,
  `id_status` int NOT NULL,
  `id_subject` int NOT NULL,
  `id_tutor` int,
  `id_student` int,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `announcement_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

CREATE TABLE `announcement_reasons` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `reason` varchar(100) NOT NULL
);

CREATE TABLE `alerts` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `type` int NOT NULL,
  `message` varchar(255) NOT NULL,
  `id_status` int NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `alert_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

CREATE TABLE `alert_types` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `type` varchar(100) NOT NULL
);

CREATE TABLE `students_tutor_subjects` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_student` int NOT NULL,
  `id_tutor_subject` int NOT NULL,
  `id_status` int NOT NULL,
  `created_at` timestamp NOT NULL
);

CREATE TABLE `students_tutor_subjects_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

CREATE TABLE `chat_messages` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `id_user_sender` int NOT NULL,
  `id_user_receptor` int NOT NULL,
  `message` varchar(255) NOT NULL,
  `id_status` int NOT NULL,
  `send_at` timestamp NOT NULL
);

CREATE TABLE `chat_messages_statuses` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(100) NOT NULL
);

ALTER TABLE `users` COMMENT = 'La tabla users refleja cada usuario registrado en la app con
  sus datos. Tiene una relacion N a M con la tabla roles, generando
  una tabla intermedia. El correo electrónico debe ser verificado
  para poder activar la cuenta, por lo que se agrega el campo
  state_account para conocer su situación.';

ALTER TABLE `role_user` COMMENT = 'Tabla intermedia para albergar los roles que pueda tener un usuario.';

ALTER TABLE `roles` COMMENT = 'Tabla con los roles existentes en la aplicación.';

ALTER TABLE `user_statuses` COMMENT = 'Se genera la tabla user_statuses para representar los estados de las
  cuentas de los usuarios';

ALTER TABLE `friendships` COMMENT = 'La tabla friendships representa las amistades entre usuarios.
  Su estado determina la situación en la que se encuentra la amistad
  (pendiente, amigos, eliminada, etc.).';

ALTER TABLE `friendship_statuses` COMMENT = 'Se genera la tabla friendship_statuses para representar los estados de las
  relaciones entre usuarios';

ALTER TABLE `students` COMMENT = 'La tabla students representa el perfil de alumno del usuario,
  por lo que se relaciona con la tabla users de forma que cada 
  registro de students debe pertenecer a un único usuario.
  En el momento de configurar el perfil de alumno, el usuario debe
  seleccionar un nivel de estudios, por lo que generamos una relación 
  N a 1 con study_levels.';

ALTER TABLE `tutors` COMMENT = 'La tabla tutors representa el perfil de tutor del usuario,
  por lo que se relaciona con la tabla users de forma que cada 
  registro de tutors debe pertenecer a un único usuario.';

ALTER TABLE `tutor_rates` COMMENT = 'La tabla tutor_rates guarda en cada registro el valor por hora de las
  sesiones de un tutor para cada materia que imparte, siendo la relación
  con la tabla subjects de 1 a N.';

ALTER TABLE `tutor_subjects` COMMENT = 'La tabla tutor_subjects es una tabla intermedia para persistir
  qué asignaturas los tutores han marcado como cursables por ellos mismos.
  Al relacionarse las materias de forma que cada materia solo pertenece a
  un nivel de estudios, no es necesario dedicar una tabla para conocer qué
  niveles de estudios imparte el tutor, desde esta tabla podemos conocer esa
  información.';

ALTER TABLE `study_levels` COMMENT = 'Tablas para los niveles de estudios y las materias, cada materia 
  pertenece a un nivel de estudios y un nivel de estudios puede abarcar 
  varias materias (Relacion 1 a N).';

ALTER TABLE `subjects` COMMENT = 'La tabla subjects representa las materias existentes para cada nivel de estudios.';

ALTER TABLE `activities` COMMENT = 'La tabla activities genera la persistencia de las actividades que un
  tutor crea para sus alumnos. Es necesario identificarla por tutor, y por la
  materia con la que se relaciona.';

ALTER TABLE `activity_statuses` COMMENT = 'Se genera la tabla activity_statuses para representar los estados de las
  actividades generadas';

ALTER TABLE `activities_students` COMMENT = 'La intención es que el tutor pueda generar actividades escogiendo a los
  alumnos a los que va enfocada (es posible que tenga alumnos suscritos a una misma
  materia, pero se encuentren en distintos puntos del temario o necesiten apoyo en
  en diferentes temas). Por lo que se genera una tabla intermedia para relacionar
  a los alumnos con las actividades que tienen marcadas.';

ALTER TABLE `calendar_events` COMMENT = 'Cada perfil del usuario tiene su propio calendario, por lo que es necesario
  separar eventos por perfil, usando los campos id_tutor e id_student para ello (si es
  un evento de tutor, el campo id_student es nulo y viceversa). Se agrega un campo para
  generar una relación con la tabla activities para esos eventos que se crean en el
  momento que un tutor sube una nueva actividad (tanto para el tutor como para los 
  estudiantes a los que se ha marcado la actividad se genera un evento con una referencia
  a la actividad). En caso de ser un evento personalizado creado por el usuario, este campo
  será nulo.';

ALTER TABLE `announcements` COMMENT = 'La tabla announcements representa los anuncios que los usuarios crean 
  para el tablón de anuncios, relacionándose con su perfil de alumno o tutor, 
  a elección del usuario. El campo reason determina qué tipo de anuncio es.';

ALTER TABLE `announcement_statuses` COMMENT = 'Se genera la tabla announcement_statuses para representar los estados de los
  anuncios generados';

ALTER TABLE `announcement_reasons` COMMENT = 'Se genera la tabla announcement_reasons para representar las razones de los
  anuncios generados';

ALTER TABLE `alerts` COMMENT = 'La tabla alerts guarda las notificaciones que se muestran  al usuario 
  en la app. Estas guardan el tipo de mensaje que es (amistad, evento, actividad, 
  etc.) y su estado (no leído, leído, eliminado, etc.)';

ALTER TABLE `alert_statuses` COMMENT = 'Se genera la tabla alert_statuses para representar los estados de las
  alertas generadas';

ALTER TABLE `alert_types` COMMENT = 'Se genera la tabla alert_types para representar los tipos de las
  alertas generadas';

ALTER TABLE `students_tutor_subjects` COMMENT = 'Para identificar qué alumnos están suscritos a una materia de un tutor,
  relacionamos la tabla students con la tabla tutor_subjects. Generamos así, una
  tabla intermedia (Relación N a M) donde estudiantes pueden pertenecer a varios
  cursos de un tutor y un curso de un tutor puede impartirse a varios alumnos';

ALTER TABLE `students_tutor_subjects_statuses` COMMENT = 'Se genera la tabla students_tutor_subjects_statuses para representar los 
  estados de las relaciones entre alumnos y las materias que cursan';

ALTER TABLE `chat_messages` COMMENT = 'La tabla chat_messages se encarga de la persistencia de los mensajes que se
  envíen a través de chat los usuarios. Guardando campos como el remitente, el receptor,
  su estado (leído, no leído, etc.)';

ALTER TABLE `chat_messages_statuses` COMMENT = 'Se genera la tabla chat_messages_statuses para representar los estados de los
  mensajes enviados por los usuarios';

ALTER TABLE `role_user` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

ALTER TABLE `role_user` ADD FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id_status`) REFERENCES `user_statuses` (`id`);

ALTER TABLE `friendships` ADD FOREIGN KEY (`id_user1`) REFERENCES `users` (`id`);

ALTER TABLE `friendships` ADD FOREIGN KEY (`id_user2`) REFERENCES `users` (`id`);

ALTER TABLE `friendships` ADD FOREIGN KEY (`id_status`) REFERENCES `friendship_statuses` (`id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `students` (`id_user`);

ALTER TABLE `students` ADD FOREIGN KEY (`id_study_level`) REFERENCES `study_levels` (`id`);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `tutors` (`id_user`);

ALTER TABLE `tutor_rates` ADD FOREIGN KEY (`id_tutor`) REFERENCES `tutors` (`id`);

ALTER TABLE `tutor_rates` ADD FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);

ALTER TABLE `tutor_subjects` ADD FOREIGN KEY (`id_tutor`) REFERENCES `tutors` (`id`);

ALTER TABLE `tutor_subjects` ADD FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);

ALTER TABLE `subjects` ADD FOREIGN KEY (`id_study_level`) REFERENCES `study_levels` (`id`);

ALTER TABLE `activities` ADD FOREIGN KEY (`id_tutor`) REFERENCES `tutors` (`id`);

ALTER TABLE `activities` ADD FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);

ALTER TABLE `activities` ADD FOREIGN KEY (`id_status`) REFERENCES `activity_statuses` (`id`);

ALTER TABLE `activities_students` ADD FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`);

ALTER TABLE `activities_students` ADD FOREIGN KEY (`id_student`) REFERENCES `students` (`id`);

ALTER TABLE `calendar_events` ADD FOREIGN KEY (`id_activity`) REFERENCES `activities` (`id`);

ALTER TABLE `calendar_events` ADD FOREIGN KEY (`id_tutor`) REFERENCES `tutors` (`id`);

ALTER TABLE `calendar_events` ADD FOREIGN KEY (`id_student`) REFERENCES `students` (`id`);

ALTER TABLE `announcements` ADD FOREIGN KEY (`id_tutor`) REFERENCES `tutors` (`id`);

ALTER TABLE `announcements` ADD FOREIGN KEY (`id_student`) REFERENCES `students` (`id`);

ALTER TABLE `announcements` ADD FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id`);

ALTER TABLE `announcements` ADD FOREIGN KEY (`id_status`) REFERENCES `announcement_statuses` (`id`);

ALTER TABLE `announcements` ADD FOREIGN KEY (`id_reason`) REFERENCES `announcement_reasons` (`id`);

ALTER TABLE `alerts` ADD FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

ALTER TABLE `alerts` ADD FOREIGN KEY (`id_status`) REFERENCES `alert_statuses` (`id`);

ALTER TABLE `alerts` ADD FOREIGN KEY (`id_status`) REFERENCES `alert_types` (`id`);

ALTER TABLE `students_tutor_subjects` ADD FOREIGN KEY (`id_student`) REFERENCES `students` (`id`);

ALTER TABLE `students_tutor_subjects` ADD FOREIGN KEY (`id_tutor_subject`) REFERENCES `tutor_subjects` (`id`);

ALTER TABLE `students_tutor_subjects` ADD FOREIGN KEY (`id_status`) REFERENCES `students_tutor_subjects_statuses` (`id`);

ALTER TABLE `chat_messages` ADD FOREIGN KEY (`id_user_sender`) REFERENCES `users` (`id`);

ALTER TABLE `chat_messages` ADD FOREIGN KEY (`id_user_receptor`) REFERENCES `users` (`id`);

ALTER TABLE `chat_messages` ADD FOREIGN KEY (`id_status`) REFERENCES `chat_messages_statuses` (`id`);

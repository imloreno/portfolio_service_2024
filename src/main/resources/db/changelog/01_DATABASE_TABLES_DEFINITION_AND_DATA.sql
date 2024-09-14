/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_portfolio
CREATE DATABASE IF NOT EXISTS `db_portfolio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_portfolio`;

-- Dumping structure for table db_portfolio.projects
CREATE TABLE IF NOT EXISTS `projects` (
  `id` binary(16) NOT NULL,
  `started_from` date NOT NULL,
  `name` varchar(100) NOT NULL,
  `picture` varchar(250) DEFAULT NULL,
  `position` varchar(100) NOT NULL,
  `terminated_at` date DEFAULT NULL,
  `type` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK1e447b96pedrvtxw44ot4qxem` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_portfolio.projects: ~4 rows (approximately)
INSERT IGNORE INTO `projects` (`id`, `started_from`, `name`, `picture`, `position`, `terminated_at`, `type`) VALUES
	(_binary 0x47a03bea62234034a3a0f822f7b43922, '2021-06-01', 'COSMON', NULL, 'Frontend Developer', '2021-11-30', 'Freelance - Internship'),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, '2020-02-01', 'Tucandera Tours', 'projects/6dc81502-b998-4692-ba55-944a9f5370ac/images/tucandera_tours.png', 'Frontend Developer', '2021-05-31', 'Full-time'),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, '2019-07-01', 'Gourmand', NULL, 'Full Stack Developer', '2019-12-31', 'Freelance'),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, '2022-06-01', 'NICE Ltd.', NULL, 'Software Engineer', '2024-06-30', 'Remote - Full-time');

-- Dumping structure for table db_portfolio.project_details
CREATE TABLE IF NOT EXISTS `project_details` (
  `project_id` binary(16) NOT NULL,
  `description` text,
  `github` varchar(70) DEFAULT NULL,
  `link` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  CONSTRAINT `FKch158nfh8pt25cxhhg901beh1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_portfolio.project_details: ~3 rows (approximately)
INSERT IGNORE INTO `project_details` (`project_id`, `description`, `github`, `link`) VALUES
	(_binary 0x47a03bea62234034a3a0f822f7b43922, 'Leveraging my proficiency in React, I implemented responsive and dynamic components, ensuring optimal performance across different devices and screen sizes.<br />Followed the SOLID principles and applied architecture and design patterns such as clean architecture and Flux to achieve a clean and maintainable codebase for the system.<br />Created reusable and modular components for efficient development.<br />Collaborated in regular design meetings, discussing wire frames and prototypes with designers to gain a thorough understanding of the desired visual and interactive elements.<br />Conducted regular knowledge-sharing sessions with designers and backend developers to foster a deeper understanding of each team\'s requirements and workflows.', 'https://github.com/imloreno/sistema_votacion_cosmon_front', NULL),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, 'Implemented responsive design updates to an existing static website, ensuring optimal user experience across different devices and screen sizes.<br />Successfully transformed a static website into a Single-Page Application (SPA) with React.js, enabling seamless navigation and enhanced user interactivity.<br />Collaborated in the development of a responsive and user-friendly frontend interface for a management system, enabling efficient customer management and streamlined administrative processes.<br />Being involved in the backend infrastructure development to ensure high availability and fault tolerance in the backend infrastructure.', 'https://github.com/imloreno/tucandera_tours_react', 'https://tucandera.com/'),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, 'Successfully development of the backend infrastructure based on customer and company requirements, ensuring efficient and secure operations.<br />Developed a visually appealing and user-friendly frontend interface that met the specific requirements of the customer and company ensuring seamless user experience across different devices and screen sizes.<br />Normalization of the database in MySQL taking care about the future needs, making possible its scalability.', NULL, NULL),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, 'Developed a RESTful API using Node.js and Express.js, ensuring efficient communication between the frontend and backend systems.<br />Implemented a secure authentication system using JWT tokens, ensuring secure access to the system.<br />Developed a scalable and efficient database schema in MySQL, ensuring optimal performance and data integrity.<br />Collaborated with frontend developers to ensure seamless integration between the frontend and backend systems.<br />Conducted regular code reviews and testing to ensure the quality and reliability of the system.', NULL, NULL);

-- Dumping structure for table db_portfolio.project_skills
CREATE TABLE IF NOT EXISTS `project_skills` (
  `projects_id` binary(16) NOT NULL,
  `skills_id` binary(16) NOT NULL,
  PRIMARY KEY (`projects_id`,`skills_id`),
  KEY `FKf5e16xmbxkfjvj765fihe6gsn` (`skills_id`),
  CONSTRAINT `FK9ol4nerys67g8rrjqixb510i2` FOREIGN KEY (`projects_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKf5e16xmbxkfjvj765fihe6gsn` FOREIGN KEY (`skills_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_portfolio.project_skills: ~44 rows (approximately)
INSERT IGNORE INTO `project_skills` (`projects_id`, `skills_id`) VALUES
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x1c5cb9b9c66c4f95a041091584ac360c),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x1c5cb9b9c66c4f95a041091584ac360c),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x332c747774a442ba856c4e7bb6ebfe93),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x332c747774a442ba856c4e7bb6ebfe93),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0x332c747774a442ba856c4e7bb6ebfe93),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x332c747774a442ba856c4e7bb6ebfe93),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x345eb35ff2ab4d59885eb4e59a835711),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x34ad193f15374bc5960d8fd40dd5c8aa),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x34ad193f15374bc5960d8fd40dd5c8aa),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x35ce9098f60944d48026c620a468b715),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x42c852a3cf5442428ca3fb95db842736),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x42c852a3cf5442428ca3fb95db842736),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x4a6c60907d2e4ff5b067eda857b77f9b),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x53f7138c54474e399961d4a80b6086a9),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0x53f7138c54474e399961d4a80b6086a9),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x53f7138c54474e399961d4a80b6086a9),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x5544d906e5a5447daf4882aded5136ee),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x5544d906e5a5447daf4882aded5136ee),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0x5544d906e5a5447daf4882aded5136ee),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x5544d906e5a5447daf4882aded5136ee),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0x6552dffbdcec48a6a13a5795e8bd4baa),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x792a0b8f040946e6b3973626148d4b48),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x7cf5387a48f24999a78a2fd95ee62710),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x8aed39ad63fe4ee7921a9e57f30bcbfb),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0x8aed39ad63fe4ee7921a9e57f30bcbfb),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x8b3f2fe263e7464cbfc1bdaa3adff878),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x8b3f2fe263e7464cbfc1bdaa3adff878),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x8b3f2fe263e7464cbfc1bdaa3adff878),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x91a1d207934b4e55b3011f6e1172bbbc),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x91a1d207934b4e55b3011f6e1172bbbc),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x91a1d207934b4e55b3011f6e1172bbbc),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x940fbc2d20b34c8db0b05b7b9eabce42),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x940fbc2d20b34c8db0b05b7b9eabce42),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0x940fbc2d20b34c8db0b05b7b9eabce42),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0x9c4af2ae1e4e4bddb8f1dbbdebd2eb84),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0x9c4af2ae1e4e4bddb8f1dbbdebd2eb84),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0x9c4af2ae1e4e4bddb8f1dbbdebd2eb84),
	(_binary 0x47a03bea62234034a3a0f822f7b43922, _binary 0xa86fe6c966ad42898d5435d971741014),
	(_binary 0x6dc81502b9984692ba55944a9f5370ac, _binary 0xa86fe6c966ad42898d5435d971741014),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0xa86fe6c966ad42898d5435d971741014),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0xa86fe6c966ad42898d5435d971741014),
	(_binary 0xc04ae6fc800e40608d5a41f9a693c649, _binary 0xc02457877cba4677a31a20bb6c539bbb),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0xd19e29c197f745568f084c61c966f80a),
	(_binary 0xe0ba793717b14bad8b23f18abff24b23, _binary 0xdcf2bd59714b44aebb8f32c7f5f7e84b);

-- Dumping structure for table db_portfolio.skills
CREATE TABLE IF NOT EXISTS `skills` (
  `id` binary(16) NOT NULL,
  `category` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `years_of_experience` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK85woe63nu9klkk9fa73vf0jd0` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_portfolio.skills: ~30 rows (approximately)
INSERT IGNORE INTO `skills` (`id`, `category`, `name`, `years_of_experience`) VALUES
	(_binary 0x097e775e38dd464391587fbb4ea2ad24, 'BACKEND', 'POSTGRESQL', 2),
	(_binary 0x17b0dc695d8c4530bdfc872b070141c6, 'REPOSITORY', 'GIT', 4),
	(_binary 0x1c5cb9b9c66c4f95a041091584ac360c, 'DESIGN', 'ILLUSTRATOR', 2),
	(_binary 0x332c747774a442ba856c4e7bb6ebfe93, 'TOOLS', 'VS_CODE', 5),
	(_binary 0x345eb35ff2ab4d59885eb4e59a835711, 'DESIGN', 'FIGMA', 2),
	(_binary 0x34ad193f15374bc5960d8fd40dd5c8aa, 'METHODOLOGIES', 'AGILE', 3),
	(_binary 0x355024d924df4f29b3f8c1acc1841745, 'FRONTEND', 'SASS', 1),
	(_binary 0x35ce9098f60944d48026c620a468b715, 'REPOSITORY', 'BITBUCKET', 2),
	(_binary 0x377adaf952fc449abb060694414dfa8d, 'BACKEND', 'API_REST', 2),
	(_binary 0x42c852a3cf5442428ca3fb95db842736, 'DESIGN', 'PHOTOSHOP', 2),
	(_binary 0x4a6c60907d2e4ff5b067eda857b77f9b, 'DEVOPS', 'MICROSERVICES', 1),
	(_binary 0x53f7138c54474e399961d4a80b6086a9, 'FRONTEND', 'JAVASCRIPT', 5),
	(_binary 0x5544d906e5a5447daf4882aded5136ee, 'FRONTEND', 'HTML', 5),
	(_binary 0x6552dffbdcec48a6a13a5795e8bd4baa, 'METHODOLOGIES', 'MVC', 2),
	(_binary 0x792a0b8f040946e6b3973626148d4b48, 'BACKEND', 'MONGODB', 2),
	(_binary 0x7cf5387a48f24999a78a2fd95ee62710, 'TOOLS', 'JIRA', 2),
	(_binary 0x8aed39ad63fe4ee7921a9e57f30bcbfb, 'BACKEND', 'MYSQL', 2),
	(_binary 0x8b3f2fe263e7464cbfc1bdaa3adff878, 'FRONTEND', 'TYPESCRIPT', 3),
	(_binary 0x8f2458ec43cd4ac3b5bc1c0031fc6ff5, 'FRONTEND', 'SVELTE', 1),
	(_binary 0x91a1d207934b4e55b3011f6e1172bbbc, 'FRONTEND', 'REDUX', 3),
	(_binary 0x940fbc2d20b34c8db0b05b7b9eabce42, 'REPOSITORY', 'GITHUB', 4),
	(_binary 0x9c4af2ae1e4e4bddb8f1dbbdebd2eb84, 'FRONTEND', 'REACT', 4),
	(_binary 0x9f090f3b236c469e95782a4663437feb, 'BACKEND', 'JAVA', 1),
	(_binary 0xa86fe6c966ad42898d5435d971741014, 'FRONTEND', 'CSS', 5),
	(_binary 0xbce3b316e49d439ab88b2e82088be91d, 'FRONTEND', 'HTML5', 5),
	(_binary 0xc02457877cba4677a31a20bb6c539bbb, 'BACKEND', 'PHP', 1),
	(_binary 0xd102585a607c41849aec3c537e5eea76, 'FRONTEND', 'ZUSTAND', 1),
	(_binary 0xd19e29c197f745568f084c61c966f80a, 'DEVOPS', 'DOCKER', 1),
	(_binary 0xd2b2cfdd3aa043798f925e802e38ab6d, 'FRONTEND', 'RESPONSIVE', 5),
	(_binary 0xdcf2bd59714b44aebb8f32c7f5f7e84b, 'BACKEND', 'SPRING_BOOT', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

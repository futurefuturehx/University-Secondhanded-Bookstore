-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2016 年 3 月 13 日 07:56
-- サーバのバージョン： 10.1.10-MariaDB
-- PHP Version: 7.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `book`
--

CREATE TABLE `book` (
  `item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `version` varchar(10) DEFAULT NULL,
  `writer` varchar(100) DEFAULT NULL,
  `ISBN` varchar(20) DEFAULT NULL,
  `course_no` varchar(20) DEFAULT NULL,
  `course_name` varchar(100) DEFAULT NULL,
  `professor` varchar(100) DEFAULT NULL,
  `price` int(10) NOT NULL,
  `condtn` text,
  `picture_pass` varchar(255) DEFAULT NULL,
  `inpdate` datetime NOT NULL,
  `upddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

--
-- テーブルのデータのダンプ `book`
--

INSERT INTO `book` (`item_id`, `user_id`, `item_name`, `version`, `writer`, `ISBN`, `course_no`, `course_name`, `professor`, `price`, `condtn`, `picture_pass`, `inpdate`, `upddate`) VALUES
(18, 4, 'The Complete Java Reference', '5', 'Herbert Schildt', '1001', '0001', 'Java', 'Herbert Schildt', 3000, 'still new', 'http://localhost:8080/bookstore/pic/4/cr.jpg', '2016-03-04 10:08:15', '2016-03-04 01:08:15'),
(19, 4, 'Head First Java', '2', 'Kathy Sierra', '1002', '0001', 'Java', 'Herbert Schildt', 500, 'pretty old', 'http://localhost:8080/bookstore/pic/4/Head_First_Java_Kathy_Sierra_Bert_Bates.jpg', '2016-03-04 10:10:16', '2016-03-04 01:10:16'),
(20, 4, 'Hardcore Java', '1', 'Robert Simmons', '1003', '0001', 'Java', 'Herbert Schildt', 1500, 'as good as new', 'http://localhost:8080/bookstore/pic/4/lrg.jpg', '2016-03-04 10:12:13', '2016-03-04 01:12:13'),
(21, 5, 'PHP Professional Project', '', 'mit', '2001', '0002', 'PHP', 'php teacher', 2500, 'some stains', 'http://localhost:8080/bookstore/pic/5/0735440.jpg', '2016-03-04 10:14:50', '2016-03-04 01:14:50'),
(22, 5, 'PHP for Absolute Beginners', '1', 'Jason Lengstorf', '2002', '0002', 'PHP', 'php teacher', 5000, 'abaolutely good', 'http://localhost:8080/bookstore/pic/5/phpab.jpg', '2016-03-04 10:16:05', '2016-03-04 01:16:05'),
(23, 5, 'Advanced PHP Programming', '2', 'George S.', '2003', '0002', 'PHP', 'php teacher', 2400, 'oily', 'http://localhost:8080/bookstore/pic/5/download.jpg', '2016-03-04 10:17:46', '2016-03-04 01:17:46'),
(24, 6, 'Microsoft SQL Server', '', 'Ray Rankins', '3001', '0003', 'SQL', 'sql teacher', 700, 'so gooood', 'http://localhost:8080/bookstore/pic/6/51l6N2CbtWL._SX378_BO1,204,203,200_.jpg', '2016-03-04 10:24:21', '2016-03-04 01:24:21'),
(25, 6, 'Learning SQL', '2', 'Alan', '3002', '0003', 'SQL', 'sql teacher', 2100, 'good', 'http://localhost:8080/bookstore/pic/6/learning_sql_second_edition.jpg', '2016-03-04 10:25:49', '2016-03-04 01:25:49'),
(26, 6, 'SQL for Dummies', '7', 'Allen G. Taylor', '3003', '0003', 'SQL', 'sql teacher', 5000, 'new', 'http://localhost:8080/bookstore/pic/6/sql_for_dummies_7th_edition.jpg', '2016-03-04 10:27:06', '2016-03-04 01:27:06'),
(28, 1, 'tale of Genji', '', '', '', '', '', '', 555, '', 'http://localhost:8080/bookstore/pic/admin/no-img.png', '2016-03-04 14:46:50', '2016-03-04 05:46:50');

-- --------------------------------------------------------

--
-- テーブルの構造 `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `valid_til` date NOT NULL,
  `status` int(1) NOT NULL,
  `inpdate` datetime NOT NULL,
  `upddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

--
-- テーブルのデータのダンプ `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `item_id`, `valid_til`, `status`, `inpdate`, `upddate`) VALUES
(17, 4, 18, '2016-04-03', 1, '2016-03-04 10:08:15', '2016-03-04 01:08:15'),
(18, 4, 19, '2016-03-18', 1, '2016-03-04 10:10:16', '2016-03-04 01:10:16'),
(19, 4, 20, '2016-03-18', 1, '2016-03-04 10:12:13', '2016-03-04 01:12:13'),
(20, 5, 21, '2016-04-03', 1, '2016-03-04 10:14:50', '2016-03-04 01:14:50'),
(21, 5, 22, '2016-03-18', 1, '2016-03-04 10:16:05', '2016-03-04 01:16:05'),
(22, 5, 23, '2016-03-18', 1, '2016-03-04 10:17:46', '2016-03-04 01:17:46'),
(23, 6, 24, '2016-03-18', 1, '2016-03-04 10:24:21', '2016-03-04 01:24:21'),
(24, 6, 25, '2016-04-03', 1, '2016-03-04 10:25:49', '2016-03-07 13:17:58'),
(25, 6, 26, '2016-04-03', 2, '2016-03-04 10:27:06', '2016-03-13 05:36:22'),
(27, 1, 28, '2016-04-03', 1, '2016-03-04 14:46:50', '2016-03-09 12:20:11');

-- --------------------------------------------------------

--
-- テーブルの構造 `request`
--

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `message` text,
  `inpdate` datetime NOT NULL,
  `upddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

-- --------------------------------------------------------

--
-- テーブルの構造 `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `inpdate` datetime NOT NULL,
  `upddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

--
-- テーブルのデータのダンプ `user`
--

INSERT INTO `user` (`user_id`, `email`, `username`, `password`, `inpdate`, `upddate`) VALUES
(1, 'admin@gmail.com', 'admin', 'admin', '2016-02-29 11:56:21', '2016-03-01 07:36:26'),
(4, 'java@gmail.com', 'java', 'java', '2016-03-04 10:06:13', '2016-03-04 01:06:13'),
(5, 'php@gmail.com', 'php', 'php', '2016-03-04 10:12:57', '2016-03-04 01:12:57'),
(6, 'sql@gmail.com', 'sql', 'sql', '2016-03-04 10:22:25', '2016-03-04 01:22:25');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- ダンプしたテーブルの制約
--

--
-- テーブルの制約 `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- テーブルの制約 `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `book` (`item_id`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- テーブルの制約 `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

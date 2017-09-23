-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2015 at 03:02 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blooddonordatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `admintable`
--

CREATE TABLE `admintable` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admintable`
--

INSERT INTO `admintable` (`username`, `password`) VALUES
('abc', 'abc');

-- --------------------------------------------------------

--
-- Table structure for table `donortable`
--

CREATE TABLE `donortable` (
  `id` int(255) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(60) NOT NULL,
  `contactNumber` varchar(14) NOT NULL,
  `address` varchar(100) NOT NULL,
  `bloodgroup` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donortable`
--

INSERT INTO `donortable` (`id`, `name`, `email`, `contactNumber`, `address`, `bloodgroup`) VALUES
(1, 'Mohammad Al-Amin', 'alamin.nirob@gmail.com', '01675362998', 'Dhaka', 'A+'),
(2, 'Mashroor Ahmed', 'mashoorahmed31@gmail.com', '01521332482', 'Dhaka', 'B+'),
(3, 'Saadman Avik', 'saadmanavik1081@gmail.com ', '01751999221', 'Dhaka', 'O+'),
(4, 'MD Shadman Tanjim', 'shadman0545@gmail.com', '01911305201', 'Dhaka', 'A+'),
(5, 'Sami', 'sk.sami.aljabar@gmail.com ', '01720345155', 'Dhaka', 'O+'),
(6, 'Ashik Mahmud', 'ashikmahmud41@gmail.com', '01620117012', 'Dhaka', 'B+'),
(7, 'Saqib', 'fattahsaqib@gmail.com', '01682028900', 'Dhaka', 'O+'),
(8, 'Abid Hassan', 'hassanabidtokee@gmail.com', '+8801677032780', 'Dhaka', 'A+');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `donortable`
--
ALTER TABLE `donortable`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

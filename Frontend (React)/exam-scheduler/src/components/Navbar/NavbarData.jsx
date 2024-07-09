import React from 'react';
import { AiFillHome, AiOutlineUser } from 'react-icons/ai';
import { IoIosPaper, IoMdHelpCircle } from 'react-icons/io';
import { FaBookmark, FaSearch, FaCog } from 'react-icons/fa';

export const NavbarData = [
  {
    title: 'Home',
    path: '/',
    icon: <AiFillHome />,
    cName: 'nav-text'
  },
  {
    title: 'View Exams',
    path: '/view-exams',
    icon: <IoIosPaper />,
    cName: 'nav-text'
  },
  {
    title: 'Search',
    path: '/search',
    icon: <FaSearch />,
    cName: 'nav-text'
  },
  {
    title: 'Bookmark',
    path: '/bookmark',
    icon: <FaBookmark />,
    cName: 'nav-text'
  },
  {
    title: 'Profile',
    path: '/profile',
    icon: <AiOutlineUser />,
    cName: 'nav-text'
  },
//   {
//     title: 'Manage Exams',
//     path: '/manage-exams',
//     icon: <IoIosPaper />,
//     cName: 'nav-text'
//   },
//   {
//     title: 'Manage Users',
//     path: '/manage-users',
//     icon: <IoMdPeople />,
//     cName: 'nav-text'
//   },
//   {
//     title: 'Reports',
//     path: '/reports',
//     icon: <IoIosPaper />,
//     cName: 'nav-text'
//   },
  {
    title: 'Settings',
    path: '/settings',
    icon: <FaCog />,
    cName: 'nav-text'
  },
  {
    title: 'Help',
    path: '/help',
    icon: <IoMdHelpCircle />,
    cName: 'nav-text'
  },
  {
    title: 'Contact',
    path: '/contact',
    icon: <IoMdHelpCircle />,
    cName: 'nav-text'
  }
];

import { gql } from '@apollo/client';

export const CREATE_USER = gql`
  mutation CreateUser($firstName: String!, $lastName: String!, $email: String!, $password: String!) {
    createUser(firstName: $firstName, lastName: $lastName, email: $email, password: $password) {
      user {
        userId
        firstName
        lastName
        email
      }
      token
    }
  }
`;

export const LOGIN_USER = gql`
  mutation LoginUser($email: String!, $password: String!) {
    loginUser(email: $email, password: $password) {
      user {
        userId
        firstName
        lastName
        email
      }
      token
    }
  }
`;

export const CREATE_EXAM = gql`
  mutation CreateExam($examIdInput: ExamIdInput!, $classNumber: Int!, $courseTitle: String!, $professorName: String!,
    $examWeekday: String!, $examDate: String!, $startTime: String!, $endTime: String!, $location: String!) {
    createExam(examIdInput: $examIdInput, classNumber: $classNumber, courseTitle: $courseTitle, professorName: $professorName,
      examWeekday: $examWeekday, examDate: $examDate, startTime: $startTime, endTime: $endTime, location: $location) {
      examId {
        courseName
        courseSection
      }
      classNumber
      courseTitle
      professorName
      examWeekday
      examDate
      startTime
      endTime
      location
    }
  }
`;

export const CREATE_BOOKMARK = gql`
  mutation CreateBookmark($userId: ID!, $examIdInput: ExamIdInput!) {
    createBookmark(userId: $userId, examIdInput: $examIdInput) {
      bookmarkId
      user {
        userId
        firstName
        lastName
        email
      }
      exam {
        examId {
          courseName
          courseSection
        }
        courseTitle
      }
    }
  }
`;

export const UPDATE_USER = gql`
  mutation UpdateUser($userId: ID!, $firstName: String, $lastName: String, $email: String, $password: String) {
    updateUser(userId: $userId, firstName: $firstName, lastName: $lastName, email: $email, password: $password)
  }
`;

export const UPDATE_EXAM = gql`
  mutation UpdateExam($courseName: String!, $courseSection: String!, $classNumber: Int, $courseTitle: String,
    $professorName: String, $examWeekday: String, $examDate: String, $startTime: String, $endTime: String,
    $location: String) {
    updateExam(courseName: $courseName, courseSection: $courseSection, classNumber: $classNumber, courseTitle: $courseTitle,
      professorName: $professorName, examWeekday: $examWeekday, examDate: $examDate, startTime: $startTime,
      endTime: $endTime, location: $location)
  }
`;

export const DELETE_USER = gql`
  mutation DeleteUser($userId: ID!) {
    deleteUser(userId: $userId)
  }
`;

export const DELETE_EXAM = gql`
  mutation DeleteExam($examIdInput: ExamIdInput!) {
    deleteExam(examIdInput: $examIdInput)
  }
`;

export const DELETE_BOOKMARK = gql`
  mutation DeleteBookmark($bookmarkId: ID!) {
    deleteBookmark(bookmarkId: $bookmarkId)
  }
`;
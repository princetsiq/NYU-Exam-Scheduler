import { gql } from '@apollo/client';

export const GET_USER = gql`
  query GetUser($userId: Int!) {
    getUser(userId: $userId) {
      userId
      firstName
      lastName
      email
    }
  }
`;

export const GET_USERS = gql`
  query GetUsers {
    getUsers {
      userId
      firstName
      lastName
      email
    }
  }
`;

export const GET_EXAM = gql`
  query GetExam($examIdInput: ExamIdInput!) {
    getExam(examIdInput: $examIdInput) {
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

export const GET_EXAMS = gql`
  query GetExams {
    getExams {
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

export const SEARCH_EXAMS = gql`
  query SearchExams($searchInput: String!) {
    searchExams(searchInput: $searchInput) {
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

export const GET_MULTIPLE_EXAMS = gql`
  query GetMultipleExams($examNames: [String]!) {
    getMultipleExams(examNames: $examNames) {
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

export const GET_BOOKMARKS_BY_USER_ID = gql`
  query GetBookmarksByUserId($userId: ID!) {
    getBookmarksByUserId(userId: $userId) {
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

export const SEARCH_BOOKMARKS = gql`
  query SearchBookmarks($searchInput: String!) {
    searchBookmarks(searchInput: $searchInput) {
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
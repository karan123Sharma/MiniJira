CREATE DATABASE IF NOT EXISTS mini_jira_dev;

USE mini_jira_dev;

CREATE TABLE users (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  email VARCHAR(255),
  name VARCHAR(120),
  password_hash VARCHAR(255),
  active TINYINT(1),
  created_at DATETIME,
  updated_at DATETIME
);

CREATE TABLE projects (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  project_key VARCHAR(10),
  name VARCHAR(200),
  description TEXT,
  owner_user_id CHAR(36),
  issue_seq INT,
  active TINYINT(1),
  created_at DATETIME,
  updated_at DATETIME
);

CREATE TABLE project_members (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  project_id CHAR(36),
  user_id CHAR(36),
  role VARCHAR(20),
  created_at DATETIME
);

CREATE TABLE issues (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  project_id CHAR(36),
  issue_key VARCHAR(30),
  title VARCHAR(250),
  description MEDIUMTEXT,
  status VARCHAR(30),
  priority VARCHAR(30),
  issue_type VARCHAR(30),
  reporter_user_id CHAR(36),
  assignee_user_id CHAR(36),
  due_date DATE,
  created_at DATETIME,
  updated_at DATETIME
);

CREATE TABLE comments (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  issue_id CHAR(36),
  user_id CHAR(36),
  body TEXT,
  created_at DATETIME
);

CREATE TABLE issue_history (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  issue_id CHAR(36),
  changed_by_user_id CHAR(36),
  field_name VARCHAR(50),
  old_value VARCHAR(500),
  new_value VARCHAR(500),
  created_at DATETIME
);

INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_GUEST'),
    ('ROLE_VIEWER')
;

INSERT INTO users (handle, email, password, created_at)
VALUES 
    ('marcio', 'marcio@mail.com', 'password', CURRENT_TIMESTAMP),
    ('josue', 'josue@mail.com', 'password', CURRENT_TIMESTAMP)
;

INSERT INTO users_roles (user_id, role_id) 
VALUES
    (1, 1), -- Marcio has ROLE_USER
    (1, 3), -- Marcio has ROLE_VIEWER
    (2, 2)  -- Josue has ROLE_GUEST
;

INSERT INTO profiles (id, name, company, type)
VALUES
    (1, 'Marcio Ramos', 'Empresa 1', 'PROFESSIONAL'),
    (2, 'Josue Torres', 'Empresa 2', 'FREE')
;

-- RELACIONAL
INSERT INTO vulnerability_reports (system_under_test, created_at, updated_at, user_id) 
VALUES
    ('System A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System E', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System F', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System G', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System H', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System I', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System J', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System K', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System L', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System M', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System O', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System P', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System Q', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System R', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
    ('System S', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('System T', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2)
;

INSERT INTO vulnerabilities (description, severity, report_id, created_at, updated_at)
VALUES
    ('SQL Injection vulnerability in login form', 'HIGH', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Cross-Site Scripting (XSS) in user profile page', 'MEDIUM', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insecure Direct Object Reference (IDOR) in file download feature', 'HIGH', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Buffer Overflow in image upload', 'CRITICAL', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Sensitive Data Exposure in logs', 'MEDIUM', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Broken Authentication', 'HIGH', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('CSRF in payment form', 'MEDIUM', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Open Redirect', 'LOW', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Directory Traversal', 'HIGH', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Privilege Escalation', 'CRITICAL', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Unrestricted File Upload', 'HIGH',11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Improper Error Handling', 'LOW', 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Weak Password Policy', 'LOW', 13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Information Disclosure', 'MEDIUM', 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Improper Access Control', 'HIGH', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insecure Communication', 'MEDIUM', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insufficient Logging and Monitoring', 'MEDIUM', 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Server-Side Request Forgery (SSRF)', 'HIGH', 18, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('XML External Entity (XXE) Injection', 'CRITICAL', 19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insecure Deserialization', 'HIGH', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Cross-Site Request Forgery (CSRF) in admin panel', 'MEDIUM', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Remote Code Execution (RCE) in file upload', 'CRITICAL', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insecure API Endpoint exposing sensitive data', 'HIGH', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Lack of Multi-Factor Authentication (MFA)', 'MEDIUM', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Unvalidated Redirects and Forwards', 'LOW', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Security Misconfiguration in server settings', 'HIGH', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Use of Components with Known Vulnerabilities', 'MEDIUM', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insufficient Session Management', 'HIGH', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Insecure Cryptographic Storage of sensitive data', 'CRITICAL', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;
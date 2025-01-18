create database keycloak_spicey_jiras;

use keycloak_spicey_jiras;

create table keycloak_spicey_jiras.ticket_table_final
(
    id             int auto_increment
        primary key,
    ticket         varchar(255)  null,
    status         varchar(255)  null,
    title          varchar(3000) null,
    classification varchar(255)  null,
    date_created   date          null,
    priority       varchar(255)  null,
    remarks        varchar(3000) null
);


INSERT INTO keycloak_spicey_jiras.ticket_table_final (ticket, status, title, classification, date_created, priority, remarks) VALUES
('TCK-001', 'Open', 'Fix login issue in Keycloak', 'Bug', '2025-01-10', 'High', 'Requires immediate attention.'),
('TCK-002', 'In Progress', 'Implement new user registration flow', 'Feature', '2025-01-09', 'Medium', 'Work assigned to dev team.'),
('TCK-003', 'Closed', 'Update documentation for API integration', 'Task', '2025-01-08', 'Low', 'Completed successfully.'),
('TCK-004', 'Open', 'Resolve session timeout issue', 'Bug', '2025-01-10', 'Critical', 'Reported by multiple users.'),
('TCK-005', 'Open', 'Enhance dashboard UI with Bootstrap 5', 'Improvement', '2025-01-11', 'Medium', 'Requires design approval.'),
('TCK-006', 'In Progress', 'Upgrade database schema to MySQL 8.0', 'Task', '2025-01-10', 'High', 'Migration in progress.'),
('TCK-007', 'Closed', 'Fix typo in user notification emails', 'Bug', '2025-01-08', 'Low', 'Deployed to production.'),
('TCK-008', 'Open', 'Add support for OAuth2 PKCE in Keycloak', 'Feature', '2025-01-11', 'High', 'Stakeholder approval pending.'),
('TCK-009', 'Open', 'Resolve 500 error during password reset', 'Bug', '2025-01-12', 'Critical', 'Impacting major customers.'),
('TCK-010', 'In Progress', 'Integrate Keycloak with LDAP directory', 'Feature', '2025-01-09', 'Medium', 'Development ongoing.'),
('TCK-011', 'Closed', 'Optimize API response time', 'Improvement', '2025-01-07', 'High', 'Performance improved by 30%.'),
('TCK-012', 'Open', 'Fix access control issues in admin panel', 'Bug', '2025-01-13', 'Critical', 'Reported in security audit.'),
('TCK-013', 'In Progress', 'Implement dark mode for user interface', 'Feature', '2025-01-10', 'Low', 'Theme designs reviewed.'),
('TCK-014', 'Open', 'Resolve issue with audit logging', 'Bug', '2025-01-12', 'High', 'Logs not capturing changes.'),
('TCK-015', 'Closed', 'Update user profile page layout', 'Improvement', '2025-01-06', 'Medium', 'Deployed to staging.'),
('TCK-016', 'Open', 'Enhance security for sensitive actions', 'Improvement', '2025-01-14', 'Critical', 'Design review needed.'),
('TCK-017', 'In Progress', 'Add new analytics reports module', 'Feature', '2025-01-09', 'Medium', 'Charts under development.'),
('TCK-018', 'Closed', 'Resolve email notification delays', 'Bug', '2025-01-08', 'High', 'Fixed mail server settings.'),
('TCK-019', 'Open', 'Fix Keycloak admin console freezes', 'Bug', '2025-01-12', 'Critical', 'Occurs during bulk updates.'),
('TCK-020', 'In Progress', 'Migrate old tickets to new schema', 'Task', '2025-01-11', 'High', 'Data transformation ongoing.'),
('TCK-021', 'Open', 'Add localization support for German', 'Feature', '2025-01-14', 'Medium', 'Translations ready.'),
('TCK-022', 'Closed', 'Resolve issue with JWT token expiry', 'Bug', '2025-01-08', 'Critical', 'Hotfix deployed.'),
('TCK-023', 'Open', 'Improve query performance in reports', 'Improvement', '2025-01-13', 'High', 'Query plans under review.'),
('TCK-024', 'In Progress', 'Develop new theme for Keycloak login', 'Feature', '2025-01-10', 'Medium', 'Stakeholder feedback pending.'),
('TCK-025', 'Closed', 'Fix incorrect date format in logs', 'Bug', '2025-01-07', 'Low', 'Fixed during maintenance.'),
('TCK-026', 'Open', 'Add user activity audit trail', 'Feature', '2025-01-12', 'High', 'Compliance requirement.'),
('TCK-027', 'In Progress', 'Enhance two-factor authentication UX', 'Improvement', '2025-01-11', 'Critical', 'UI/UX changes ongoing.'),
('TCK-028', 'Closed', 'Fix 404 error on login page redirect', 'Bug', '2025-01-08', 'High', 'Patched in production.'),
('TCK-029', 'Open', 'Resolve data sync issues with pub/sub', 'Bug', '2025-01-13', 'Critical', 'Pub/Sub logs show errors.'),
('TCK-030', 'In Progress', 'Integrate HighCharts in reports module', 'Feature', '2025-01-11', 'Medium', 'Chart configurations done.'),
('TCK-031', 'Closed', 'Fix issue with API authentication', 'Bug', '2025-01-07', 'Critical', 'Issue fixed and tested.'),
('TCK-032', 'Open', 'Add dynamic user roles feature', 'Feature', '2025-01-15', 'High', 'Initial design ready.'),
('TCK-033', 'In Progress', 'Improve backend error handling', 'Improvement', '2025-01-12', 'High', 'Implementation in progress.'),
('TCK-034', 'Closed', 'Fix memory leaks in service layer', 'Bug', '2025-01-06', 'Critical', 'Deployed memory patch.'),
('TCK-035', 'Open', 'Enhance report export functionality', 'Improvement', '2025-01-13', 'Medium', 'Support for Excel needed.'),
('TCK-036', 'In Progress', 'Resolve performance bottlenecks', 'Improvement', '2025-01-11', 'High', 'Identified slow queries.'),
('TCK-037', 'Closed', 'Fix layout issues in mobile view', 'Bug', '2025-01-08', 'Low', 'CSS updated for mobile.'),
('TCK-038', 'Open', 'Add Keycloak admin API endpoints', 'Feature', '2025-01-14', 'High', 'API design finalized.'),
('TCK-039', 'In Progress', 'Fix issues in JWT token signature', 'Bug', '2025-01-10', 'Critical', 'Review ongoing.'),
('TCK-040', 'Closed', 'Resolve admin panel caching issue', 'Bug', '2025-01-06', 'High', 'Deployed new caching strategy.'),
('TCK-041', 'Open', 'Implement email templates customization', 'Feature', '2025-01-14', 'Medium', 'Design review required.'),
('TCK-042', 'In Progress', 'Enhance audit log filtering options', 'Improvement', '2025-01-13', 'High', 'Frontend changes ongoing.'),
('TCK-043', 'Closed', 'Fix issue with OAuth2 token refresh', 'Bug', '2025-01-08', 'Critical', 'Patch deployed.'),
('TCK-044', 'Open', 'Add multi-tenancy support in Keycloak', 'Feature', '2025-01-14', 'High', 'Design review scheduled.'),
('TCK-045', 'In Progress', 'Fix pagination issue in user table', 'Bug', '2025-01-11', 'Medium', 'Fix under review.'),
('TCK-046', 'Closed', 'Resolve error in pub/sub integration', 'Bug', '2025-01-09', 'Critical', 'Resolved after testing.'),
('TCK-047', 'Open', 'Add backup and restore functionality', 'Feature', '2025-01-15', 'High', 'Compliance requirement.'),
('TCK-048', 'In Progress', 'Optimize database indexing', 'Improvement', '2025-01-12', 'High', 'Index suggestions implemented.'),
('TCK-049', 'Closed', 'Fix cross-origin request issues', 'Bug', '2025-01-08', 'Critical', 'Deployed updated CORS config.'),
('TCK-050', 'Open', 'Enhance user notifications module', 'Improvement', '2025-01-14', 'Medium', 'Designs approved.');

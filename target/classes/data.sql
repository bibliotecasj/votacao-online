-- Enquete de exemplo associada ao admin
INSERT INTO polls (title, description, end_date, status, creator_id, created_at)
VALUES (
    'Qual é a melhor linguagem de programação?',
    'Vote na sua linguagem preferida para desenvolvimento backend',
    DATE_ADD(NOW(), INTERVAL 7 DAY),
    'ACTIVE',
    1, -- ID do admin@voting.com
    NOW()
);
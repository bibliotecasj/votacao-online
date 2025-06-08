-- Usuário admin padrão (senha: "admin123" criptografada)
INSERT INTO users (email, password, is_admin, created_at)
VALUES (
    'admin@voting.com',
    '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', -- bcrypt("admin123")
    true,
    NOW()
);

-- Usuário comum (senha: "user123")
INSERT INTO users (email, password, is_admin, created_at)
VALUES (
    'user@voting.com',
    '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',
    false,
    NOW()
);
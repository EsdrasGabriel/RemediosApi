ALTER TABLE remedio ADD COLUMN ativo BOOLEAN;
UPDATE remedio SET ativo = true;
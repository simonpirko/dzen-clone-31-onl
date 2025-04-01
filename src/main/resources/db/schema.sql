CREATE SCHEMA IF NOT EXISTS schema_dzen;

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX articles_content_gin ON schema_dzen.articles USING gin(content gin_trgm_ops);
CREATE INDEX articles_title_gin ON schema_dzen.articles USING gin(title gin_trgm_ops);
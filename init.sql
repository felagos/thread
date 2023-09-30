SELECT datname FROM pg_database WHERE datname = 'bank';
IF NOT EXISTS (SELECT 1)
THEN
  CREATE DATABASE bank;
END IF;
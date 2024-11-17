UPDATE users
SET "password" = '{bcrypt}$2a$10$jBSe5AIlJNtn99T78nM10u9udpEJDK4PzUE1txn0z9GmIAlZvmLPC'
WHERE username = 'ivan';

UPDATE users
SET "password" = '{bcrypt}$2a$10$mkEXrRgj7SH3M3jintZJm.tUT6lqLriBMEof/CJg.BAaSn6AFXlUW'
WHERE username = 'george';

UPDATE users
SET "password" = '{bcrypt}$2a$10$HafyDnLo.EQhNRvm0k54N.VW3Dm7ajFhGexv.oScN/uQkwStqUkw6'
WHERE username = 'hristo';
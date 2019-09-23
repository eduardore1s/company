--address
INSERT INTO company.address(country, state, city, street, zip_code) VALUES('BRASIL', 'MINAS GERAIS', 'BELO HORIZONTE', 'BERNARDO FIGUEREDO', '30840710');
INSERT INTO company.address(country, state, city, street, zip_code) VALUES('BRASIL', 'MINAS GERAIS', 'FLORESTAL', 'NOSSA SENHORA APARECIDA', '35690000');

--employee
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary) VALUES('EDUARDO REIS', '12028588957', '1996-10-6' , 'MALE', 1, 79000);
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary, supervisor_id) VALUES('JOSE RICARDO', '49857652578', '1990-10-4' , 'MALE', 1, 1300, 1);
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary, supervisor_id) VALUES('MARIA ISABEL', '698572054288', '1990-10-14' , 'FEMALE', 2, 900, 1);

--department
INSERT INTO company.department(name, number) VALUES('SALES', 1019);
INSERT INTO company.department(name, number) VALUES('BUSINESS ANALYTICS', 123);

--


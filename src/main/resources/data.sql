--address
INSERT INTO company.address(country, state, city, street, zip_code) VALUES('BRASIL', 'MINAS GERAIS', 'BELO HORIZONTE', 'BERNARDO FIGUEREDO', '30840710');
INSERT INTO company.address(country, state, city, street, zip_code) VALUES('BRASIL', 'MINAS GERAIS', 'FLORESTAL', 'NOSSA SENHORA APARECIDA', '35690000');
INSERT INTO company.address(country, state, city, street, zip_code) VALUES('HOLANDA', 'STATE', 'AMSTERDAM', 'RED STREET', '69001122');

--employee
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary) VALUES('EDUARDO REIS', '12028588957', '1996-10-6' , 'MALE', 1, 79000);
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary, supervisor_id) VALUES('JOSE RICARDO', '49857652578', '1990-10-4' , 'MALE', 1, 1300, 1);
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary, supervisor_id) VALUES('MARIA ISABEL', '698572054288', '1990-10-14' , 'FEMALE', 2, 900, 1);
INSERT INTO company.employee(name, cpf, date_of_birth, gender, address_id, salary, supervisor_id) VALUES('ROSALINA DE JESUS', '25852698571', '1955-05-04' , 'FEMALE', 3, 600, 1);

--department
INSERT INTO company.department(name, number) VALUES('SALES', 1019);
INSERT INTO company.department(name, number) VALUES('BUSINESS ANALYTICS', 123);
INSERT INTO company.department(name, number) VALUES('MARKETING', 321);
INSERT INTO company.department(name, number) VALUES('SUPPORT', 111);
INSERT INTO company.department(name, number) VALUES('HUMANS RESOURCE', 222);

--project
INSERT INTO company.project(date_final, date_start, name, value, department_id) VALUES('2019-12-31', '2019-10-01', 'Projeto 1', 1000, 1);
INSERT INTO company.project(date_final, date_start, name, value, department_id) VALUES('2019-12-31', '2019-10-01', 'Projeto 2', 2000, 2);
INSERT INTO company.project(date_final, date_start, name, value, department_id) VALUES('2019-12-31', '2019-10-01', 'Projeto 3', 3000, 3);
INSERT INTO company.project(date_final, date_start, name, value, department_id) VALUES('2019-12-31', '2019-10-01', 'Projeto 4', 4000, 4);

--employee_project
INSERT INTO company.employee_project(employee_id, project_list_id) VALUES(1, 1);
INSERT INTO company.employee_project(employee_id, project_list_id) VALUES(2, 1);
INSERT INTO company.employee_project(employee_id, project_list_id) VALUES(3, 2);
INSERT INTO company.employee_project(employee_id, project_list_id) VALUES(4, 2);




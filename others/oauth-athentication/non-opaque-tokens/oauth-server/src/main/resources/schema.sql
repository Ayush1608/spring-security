drop table if exists client_details;
create table client_details(
id SERIAL primary key,
client_id VARCHAR(50) unique,
secret VARCHAR(50),
grant_type VARCHAR(50),
scope VARCHAR(10)
);
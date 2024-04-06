SET SEARCH_PATH = rain_check;

ALTER TABLE "user" ADD COLUMN city_id uuid;
ALTER TABLE "user" ADD CONSTRAINT fk_user_city FOREIGN KEY (city_id) REFERENCES city(id);

UPDATE "user" SET city_id ='ea5bc0ab-6ccf-4791-a048-694a5be1d309' WHERE id='8d1208fc-f401-496c-9cb8-483fef121234';

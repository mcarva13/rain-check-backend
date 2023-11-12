SET SEARCH_PATH = rain_check;

ALTER TABLE "user" ADD COLUMN city_id uuid;
ALTER TABLE "user" ADD CONSTRAINT fk_user_city FOREIGN KEY (city_id) REFERENCES city(id);

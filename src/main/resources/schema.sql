DELETE FROM  hotels where hotel_id > 0;
DELETE FROM  rooms where room_id > 0;
DELETE FROM  hotel_amenities where hotel_ame_id > 0;
DELETE FROM  room_amenities where room_ame_id > 0;
DELETE FROM  amenity_mst where amenity_id > 0;
INSERT INTO amenity_mst(amenity_id ,short_desc,description) VALUES (1, "WIFI","WIFI internet");
COMMIT;